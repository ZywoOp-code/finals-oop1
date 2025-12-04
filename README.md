						MindCard.io

## Description:

	mindcard.io is an interactive quiz system that provides a fast-paced learning experience through both standard and timed multiple-choice questions. It challenges users to answer questions correctly under pressure, giving immediate feedback, automatic score calculations, and grade assessment. The project applies the basics of object-oriented programming to organize question processing, guide the quiz flow, and process user responses to demonstrate an effective usage of inheritance, usage of interfaces, exception handling, and multithreading. This system will serve as both an educational tool and a showcase for how to program a responsive console-based application.

## OOP Concepts Applied:

Encapsulation

	In this project, we use encapsulation to keep important data protected and well-organized. We declare variables such as questionText, options, correctAnswer, and timeLimit as private, which means they cannot be accessed directly from outside the class. Instead, we use methods like getters and built-in class functions to read or process this information. This ensures that the data inside each object remains secure and prevents unwanted changes that could cause errors in the program. By doing this, we also keep our code cleaner, safer, and easier to manage.

Inheritance

	We apply inheritance by creating the TimedQuestion class as an extension of the Question class using TimedQuestion extends Question. This allows TimedQuestion to automatically inherit all the fields and methods of Question, including displaying questions and checking answers. On top of that, we add new features such as time limits and countdown timers that are unique to timed questions. This approach prevents us from repeating the same code and allows us to enhance functionality efficiently while still using the original question structure.

Abstract

	We demonstrate abstraction by focusing on what our objects are meant to do rather than how all internal processes work. The Question class represents the general concept of a quiz question, while the TimedQuestion class abstracts a special type of question that includes a time limit. We further apply abstraction through the QuizOperations interface, which defines the essential actions every quiz must have, such as starting the quiz, displaying scores, and adding questions. This simplifies our program design and makes each component easier to understand, use, and maintain.

Polymorphism

	We implement polymorphism by storing both regular questions and timed questions inside a single List<Question>. This allows us to treat different types of questions as a single group during execution. When the quiz runs, we check the actual type of each question using instanceof and apply the appropriate behavior depending on whether the object is a Question or a TimedQuestion. This flexibility enables different question types to work together smoothly within the same quiz flow.

	We use the QuizOperations interface to define clear rules for how the quiz should operate. The interface lists required methods such as startQuiz(), displayScore(), and addQuestion(). The Quiz class implements this interface, which means we provide exact definitions for each of these methods. This ensures that our quiz follows a consistent structure and helps make the program more organized, reliable, and easier to expand or modify in the future.

## Program Structure:

	The Question class serves as the foundation of our quiz system. We use this class to store the question text and answer choices, display each question to the user, and verify whether the selected answer is correct.

	The TimedQuestion class extends the Question class to introduce time-limited questions into our program. We added features that manage countdown timers, handle user input using separate threads, and automatically proceed to the next question when time runs out.

	The QuizOperations interface defines the core actions required to run the quiz. We designed this interface to include methods for starting the quiz, displaying the final score, and adding new questions, ensuring that every quiz controller follows a consistent structure.

	The Quiz class implements the QuizOperations interface and acts as the main controller of our application. We use this class to manage the overall quiz flow, store all questions in a list, validate user answers, track scores, compute grades, and display the final results to the user.

	The FinalJavaQuiz class serves as our program’s entry point. This is where we initialize the quiz, load all predefined questions, start the quiz session, and present the user’s final score once the quiz has been completed.

## How To Run The Program:

	To run the mindcard.io application, we use Visual Studio Code. We begin by making sure that the Java Extension is installed and the Java Development Kit is properly set up on the computer. After that, we open the project folder containing all the Java sources files in VS Code.

	Next, we open the JavaProgrammingQuiz.java file and simply click the Run button displayed above the main() method. VS Code automatically compiles and executes the program, launhcing the quiz directly  in the integrated terminal where we can start interacting with it immediately, no manual command-line steps are required.

## Sample Output:

	Starting Java Programming Quiz Application...
	**************************************************
	Welcome to the Java Programming Quiz!
	Answer the following Java programming questions:
	**************************************************

	What is the extension of Java source files?
	A. .java
	B. .class
	C. .js
	D. .jvm
	Enter your answer (A-D): a
	? Correct!

	Which keyword is used to create a class in Java?
	A. class
	B. new
	C. this
	D. main
	Enter your answer (A-D): b
	? Wrong! The correct answer was: A

	What is the default value of a boolean variable in Java?
	A. true
	B. false
	C. 0
	D. null
	Enter your answer (A-D): a
	? Wrong! The correct answer was: B

	Which data type is used to store whole numbers in Java?
	A. int
	B. float
	C. double
	D. String
	Enter your answer (A-D): b
	? Wrong! The correct answer was: A

	What does JVM stand for?
	A. Java Virtual Machine
	B. Java Variable Method
	C. Java Visual Machine
	D. Java Verified Method
	Enter your answer (A-D): d
	? Wrong! The correct answer was: A

	Which loop is guaranteed to execute at least once in Java?
	A. for loop
	B. while loop
	C. do-while loop
	D. enhanced for loop
	Enter your answer (A-D): d
	? Wrong! The correct answer was: C

	What is the parent class of all Java classes?
	A. Object
	B. Class
	C. Main
	D. System
	Enter your answer (A-D): a
	? Correct!

	Which keyword is used for inheritance in Java?
	A. extends
	B. inherits
	C. implements
	D. super
	Enter your answer (A-D): b
	? Wrong! The correct answer was: A

	What is the size of an int in Java?
	A. 8 bits
	B. 16 bits
	C. 32 bits
	D. 64 bits
	Enter your answer (A-D): c
	? Correct!

	Which method must be implemented by all threads in Java?
	A. start()
	B. run()
	C. execute()
	D. begin()
	Enter your answer (A-D): c
	? Wrong! The correct answer was: B

	? TIMED QUESTION STARTING!

	Which of these is NOT a Java feature?
	A. Platform Independent
	B. Object-Oriented
	C. Use of pointers
	D. Multithreaded
	Time limit: 10 seconds
	Enter your answer (A-D): ? 5 seconds remaining...
	? 4 seconds remaining...
	? 3 seconds remaining...
	? 2 seconds remaining...
	a
	? Wrong! The correct answer was: C

	? TIMED QUESTION STARTING!

	What is the entry point of a Java program?
	A. init()
	B. start()
	C. main()
	D. run()
	Time limit: 8 seconds
	Enter your answer (A-D): s
	? 5 seconds remaining...
	? 4 seconds remaining...
	? 3 seconds remaining...
	? 2 seconds remaining...

	? TIME'S UP! Moving to next question...
	Too slow! No points for this question.

	? TIMED QUESTION STARTING!

	Which package is automatically imported in Java?
	A. java.lang
	B. java.util
	C. java.io
	D. java.net
	Time limit: 5 seconds
	Enter your answer (A-D): a
	? Correct!

	? TIMED QUESTION STARTING!

	What is the superclass of Exception in Java?
	A. Error
	B. Throwable
	C. RuntimeException
	D. Object
	Time limit: 7 seconds
	Enter your answer (A-D): a  
	? Wrong! The correct answer was: B

	? TIMED QUESTION STARTING!

	Which keyword is used to prevent method overriding?
	A. static
	B. final
	C. private
	D. abstract
	Time limit: 6 seconds
	Enter your answer (A-D): ? 5 seconds remaining...
	b
	? 4 seconds remaining...
	? Correct!

	? TIMED QUESTION STARTING!

	What does JIT stand for in Java?
	A. Java Internal Translator
	B. Just In Time
	C. Java Integrated Technology
	D. Java Intermediate Type
	Time limit: 5 seconds
	Enter your answer (A-D): ? 5 seconds remaining...
	? 4 seconds remaining...
	c? 3 seconds remaining...

	? Wrong! The correct answer was: B

	? TIMED QUESTION STARTING!

	Which collection class allows duplicate elements?
	A. Set
	B. Map
	C. List
	D. Queue
	Time limit: 8 seconds
	Enter your answer (A-D): a
	? Wrong! The correct answer was: C

	? TIMED QUESTION STARTING!

	What is the default value of a String in Java?
	A. ""
	B. "null"
	C. null
	D. undefined
	Time limit: 5 seconds
	Enter your answer (A-D): ? 5 seconds remaining...
	? 4 seconds remaining...
	c? 3 seconds remaining...
	? 2 seconds remaining...

	? Correct!

	? TIMED QUESTION STARTING!

	Which interface does ArrayList implement?
	A. Set
	B. Queue
	C. List
	D. Map
	Time limit: 6 seconds
	Enter your answer (A-D): c? 5 seconds remaining...

	? Correct!

	? TIMED QUESTION STARTING!

	What is the output of: System.out.println(10 + 20 + "30");
	A. 3030
	B. 102030
	C. 30"30"
	D. 60
	Time limit: 10 seconds
	Enter your answer (A-D): c? 5 seconds remaining...
	? 4 seconds remaining...

	? Wrong! The correct answer was: A

	? TIMED QUESTION STARTING!

	Which keyword is used to create an object in Java?
	A. create
	B. new
	C. object
	D. instance
	Time limit: 5 seconds
	Enter your answer (A-D): ? 5 seconds remaining...
	? 4 seconds remaining...
	? 3 seconds remaining...
	a
	? Wrong! The correct answer was: B

	? TIMED QUESTION STARTING!

	What is the range of a byte in Java?
	A. -128 to 127
	B. 0 to 255
	C. -32768 to 32767
	D. 0 to 65535
	Time limit: 7 seconds
	Enter your answer (A-D): d
	? Wrong! The correct answer was: A

	? TIMED QUESTION STARTING!

	Which method is used to compare strings in Java?
	A. compare()
	B. equals()
	C. == operator
	D. same()
	Time limit: 6 seconds
	Enter your answer (A-D): a
	? Wrong! The correct answer was: B

	? TIMED QUESTION STARTING!

	What is the purpose of the 'finally' block?
	A. To handle exceptions
	B. To declare variables
	C. To execute code regardless of exceptions
	D. To terminate the program
	Time limit: 8 seconds
	Enter your answer (A-D): a
	? Wrong! The correct answer was: C

	? TIMED QUESTION STARTING!

	Which of these is a wrapper class in Java?
	A. int
	B. Integer
	C. string
	D. array
	Time limit: 5 seconds
	Enter your answer (A-D): a
	? Wrong! The correct answer was: B

	****************************************************
	Quiz completed!
	Your final score: 7/25
	Percentage: 28.0%
	Grade: F ? Need to Study Java More!
	**************************************************

## Author and Acknowledgement:

	This project was developed by Aloria, Jaime Alfonso M., Amistoso, Johan Lemuel N., Fajarito, John Mickell F., Valdez, Emerald S.. We would like to sincerely thank our instructor, Ma'am Christiana Grace Alib, for her guidance, advice and  support throughout the developement of this quiz application. We also appreciate the other online sources and Java documentation we referred to, which help us understand key concepts such as object-oriented programming, multithreading, and exception handling.
