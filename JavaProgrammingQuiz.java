import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswer;
    private char correctLetter;
   
    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.correctLetter = (char) ('A' + correctAnswer - 1);
    }
   
    public String getQuestionText() { return questionText; }
    public String[] getOptions() { return options; }
    public int getCorrectAnswer() { return correctAnswer; }
    public char getCorrectLetter() { return correctLetter; }
   
    public void displayQuestion() {
        System.out.println("\n" + questionText);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
    }
   
    public boolean checkAnswer(int userAnswer) {
        return userAnswer == correctAnswer;
    }
   
    public boolean checkLetterAnswer(char userLetter) {
        return Character.toUpperCase(userLetter) == correctLetter;
    }
}

class TimedQuestion extends Question {
    private int timeLimit;
   
    public TimedQuestion(String questionText, String[] options, int correctAnswer, int timeLimit) {
        super(questionText, options, correctAnswer);
        this.timeLimit = timeLimit;
    }
   
    public int getTimeLimit() {
        return timeLimit;
    }
   
    public void displayWithTimer() {
        displayQuestion();
        System.out.println("Time limit: " + timeLimit + " seconds");
    }
   
    public char askQuestionWithTimer(Scanner scanner) {
        System.out.println("\n⏰ TIMED QUESTION STARTING!");
        displayWithTimer();
       
        AtomicBoolean answered = new AtomicBoolean(false);
        AtomicBoolean timeUp = new AtomicBoolean(false);
        char[] userAnswer = {'X'}; 
       
        Timer timer = new Timer();
       
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!answered.get()) {
                    timeUp.set(true);
                    System.out.println("\n⏰ TIME'S UP! Moving to next question...");
                    synchronized (scanner) {
                        scanner.notify(); 
                    }
                }
            }
        };
       
        timer.schedule(task, timeLimit * 1000L);
       
        Thread countdownThread = new Thread(() -> {
            try {
                for (int i = timeLimit; i > 0; i--) {
                    if (answered.get() || timeUp.get()) break;
                    Thread.sleep(1000);
                    if (i <= 5) {
                        System.out.println("⏰ " + i + " seconds remaining...");
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        countdownThread.start();
       
        try {
            System.out.print("Enter your answer (A-D): ");
           
            Thread inputThread = new Thread(() -> {
                try {
                    if (scanner.hasNext()) {
                        String input = scanner.next().toUpperCase();
                        if (input.length() == 1 && input.charAt(0) >= 'A' && input.charAt(0) <= 'D') {
                            userAnswer[0] = input.charAt(0);
                            answered.set(true);
                        }
                    }
                } catch (Exception e) {
                    // Handle input exception
                }
            });
            inputThread.start();
           
            synchronized (scanner) {
                while (!answered.get() && !timeUp.get()) {
                    scanner.wait(100);
                }
            }
           
            timer.cancel();
            countdownThread.interrupt();
            inputThread.interrupt();
           
            if (timeUp.get()) {
                System.out.println("Too slow! No points for this question.");
                return 'X';
            }
           
            return userAnswer[0];
           
        } catch (InterruptedException e) {
            timer.cancel();
            countdownThread.interrupt();
            return 'X';
        }
    }
}

interface QuizOperations {
    void startQuiz();
    void displayScore();
    void addQuestion(Question question);
}

class Quiz implements QuizOperations {
    private List<Question> questions;
    private int score;
    private Scanner scanner;
   
    public Quiz() {
        this.questions = new ArrayList<>();
        this.score = 0;
        this.scanner = new Scanner(System.in);
    }
   
    @Override
    public void startQuiz() {
        System.out.println("****************************************************");
        System.out.println("Welcome to the Java Programming Quiz!");
        System.out.println("Answer the following Java programming questions:");
        System.out.println("****************************************************");
       
        for (int i = 0; i < questions.size(); i++) {
            Question currentQuestion = questions.get(i);
            char userAnswerChar = ' ';
           
            if (currentQuestion instanceof TimedQuestion) {
                TimedQuestion timedQ = (TimedQuestion) currentQuestion;
                userAnswerChar = timedQ.askQuestionWithTimer(scanner);
               
                if (userAnswerChar == 'X') {
                    continue; 
                }
            } else {
                currentQuestion.displayQuestion();
               
                try {
                    System.out.print("Enter your answer (A-D): ");
                    String input = scanner.next().toUpperCase();
                    if (input.length() == 1) {
                        userAnswerChar = input.charAt(0);
                    } else {
                        System.out.println("Invalid input! Please enter A, B, C, or D only.");
                        i--; // repeat this question
                        continue;
                    }
                    
                    // Validate input
                    if (userAnswerChar < 'A' || userAnswerChar > 'D') {
                        System.out.println("Invalid input! Please enter A, B, C, or D only.");
                        i--; // repeat this question
                        continue;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter letters only.");
                    scanner.next(); // clear invalid input
                    i--; // repeat this question
                    continue;
                }
            }
           
            if (currentQuestion.checkLetterAnswer(userAnswerChar)) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Wrong! The correct answer was: " + currentQuestion.getCorrectLetter());
            }
        }
    }
   
    @Override
    public void displayScore() {
        System.out.println("\n****************************************************");
        System.out.println("Quiz completed!");
        System.out.println("Your final score: " + score + "/" + questions.size());
       
        double percentage = (double) score / questions.size() * 100;
        System.out.printf("Percentage: %.1f%%\n", percentage);
       
        if (percentage >= 90) {
            System.out.println("Grade: A 🎉 Excellent Java Knowledge!");
        } else if (percentage >= 80) {
            System.out.println("Grade: B 👍 Good Java Skills!");
        } else if (percentage >= 70) {
            System.out.println("Grade: C 🙂 Fair Java Understanding");
        } else if (percentage >= 60) {
            System.out.println("Grade: D 😕 Basic Java Knowledge");
        } else {
            System.out.println("Grade: F 😞 Need to Study Java More!");
        }
       
        System.out.println("****************************************************");
    }
   
    @Override
    public void addQuestion(Question question) {
        questions.add(question);
    }
   
    public void closeScanner() {
        scanner.close();
    }
}

public class JavaProgrammingQuiz {
    public static void main(String[] args) {
        System.out.println("Starting Java Programming Quiz Application...");
       
        Quiz quiz = new Quiz();
       
        // Add Java programming questions with A-D options
        quiz.addQuestion(new Question("What is the extension of Java source files?",
            new String[]{"A. .java", "B. .class", "C. .js", "D. .jvm"}, 1)); // A
           
        quiz.addQuestion(new Question("Which keyword is used to create a class in Java?",
            new String[]{"A. class", "B. new", "C. this", "D. main"}, 1)); // A
       
        quiz.addQuestion(new Question("What is the default value of a boolean variable in Java?",
            new String[]{"A. true", "B. false", "C. 0", "D. null"}, 2)); // B
           
        quiz.addQuestion(new Question("Which data type is used to store whole numbers in Java?",
            new String[]{"A. int", "B. float", "C. double", "D. String"}, 1)); // A
           
        quiz.addQuestion(new Question("What does JVM stand for?",
            new String[]{"A. Java Virtual Machine", "B. Java Variable Method", 
                         "C. Java Visual Machine", "D. Java Verified Method"}, 1)); // A
           
        quiz.addQuestion(new Question("Which loop is guaranteed to execute at least once in Java?",
            new String[]{"A. for loop", "B. while loop", "C. do-while loop", "D. enhanced for loop"}, 3)); // C
        
        quiz.addQuestion(new Question("What is the parent class of all Java classes?",
            new String[]{"A. Object", "B. Class", "C. Main", "D. System"}, 1)); // A
            
        quiz.addQuestion(new Question("Which keyword is used for inheritance in Java?",
            new String[]{"A. extends", "B. inherits", "C. implements", "D. super"}, 1)); // A
            
        quiz.addQuestion(new Question("What is the size of an int in Java?",
            new String[]{"A. 8 bits", "B. 16 bits", "C. 32 bits", "D. 64 bits"}, 3)); // C
            
        quiz.addQuestion(new Question("Which method must be implemented by all threads in Java?",
            new String[]{"A. start()", "B. run()", "C. execute()", "D. begin()"}, 2)); // B
            
        // Add timed Java questions
        quiz.addQuestion(new TimedQuestion("Which of these is NOT a Java feature?",
            new String[]{"A. Platform Independent", "B. Object-Oriented", 
                         "C. Use of pointers", "D. Multithreaded"}, 3, 10)); // C
            
        quiz.addQuestion(new TimedQuestion("What is the entry point of a Java program?",
            new String[]{"A. init()", "B. start()", "C. main()", "D. run()"}, 3, 8)); // C
            
        quiz.addQuestion(new TimedQuestion("Which package is automatically imported in Java?",
            new String[]{"A. java.lang", "B. java.util", "C. java.io", "D. java.net"}, 1, 5)); // A
            
        quiz.addQuestion(new TimedQuestion("What is the superclass of Exception in Java?",
            new String[]{"A. Error", "B. Throwable", "C. RuntimeException", "D. Object"}, 2, 7)); // B
            
        quiz.addQuestion(new TimedQuestion("Which keyword is used to prevent method overriding?",
            new String[]{"A. static", "B. final", "C. private", "D. abstract"}, 2, 6)); // B
            
        quiz.addQuestion(new TimedQuestion("What does JIT stand for in Java?",
            new String[]{"A. Java Internal Translator", "B. Just In Time", 
                         "C. Java Integrated Technology", "D. Java Intermediate Type"}, 2, 5)); // B
            
        quiz.addQuestion(new TimedQuestion("Which collection class allows duplicate elements?",
            new String[]{"A. Set", "B. Map", "C. List", "D. Queue"}, 3, 8)); // C
            
        quiz.addQuestion(new TimedQuestion("What is the default value of a String in Java?",
            new String[]{"A. \"\"", "B. \"null\"", "C. null", "D. undefined"}, 3, 5)); // C
            
        quiz.addQuestion(new TimedQuestion("Which interface does ArrayList implement?",
            new String[]{"A. Set", "B. Queue", "C. List", "D. Map"}, 3, 6)); // C
            
        quiz.addQuestion(new TimedQuestion("What is the output of: System.out.println(10 + 20 + \"30\");",
            new String[]{"A. 3030", "B. 102030", "C. 30\"30\"", "D. 60"}, 1, 10)); // A
            
        quiz.addQuestion(new TimedQuestion("Which keyword is used to create an object in Java?",
            new String[]{"A. create", "B. new", "C. object", "D. instance"}, 2, 5)); // B
            
        quiz.addQuestion(new TimedQuestion("What is the range of a byte in Java?",
            new String[]{"A. -128 to 127", "B. 0 to 255", "C. -32768 to 32767", "D. 0 to 65535"}, 1, 7)); // A
            
        quiz.addQuestion(new TimedQuestion("Which method is used to compare strings in Java?",
            new String[]{"A. compare()", "B. equals()", "C. == operator", "D. same()"}, 2, 6)); // B
            
        quiz.addQuestion(new TimedQuestion("What is the purpose of the 'finally' block?",
            new String[]{"A. To handle exceptions", "B. To declare variables", 
                         "C. To execute code regardless of exceptions", "D. To terminate the program"}, 3, 8)); // C
            
        quiz.addQuestion(new TimedQuestion("Which of these is a wrapper class in Java?",
            new String[]{"A. int", "B. Integer", "C. string", "D. array"}, 2, 5)); // B
        
        quiz.startQuiz();
        quiz.displayScore();
        quiz.closeScanner();
       
        System.out.println("Java Programming Quiz finished successfully!");
    }
}