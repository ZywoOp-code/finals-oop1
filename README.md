MindCard.io

	Description:

mindcard.io is an interactive quiz system that provides a fast-paced learning experience through both standard and timed multiple-choice questions. It challenges users to answer questions correctly under pressure, giving immediate feedback, automatic score calculations, and grade assessment. The project applies the basics of object-oriented programming to organize question processing, guide the quiz flow, and process user responses to demonstrate an effective usage of inheritance, usage of interfaces, exception handling, and multithreading. This system will serve as both an educational tool and a showcase for how to program a responsive console-based application.

	OOP Concepts Applied:
## Encapsulation
In this project, we use encapsulation to keep important data protected and well-organized. We declare variables such as questionText, options, correctAnswer, and timeLimit as private, which means they cannot be accessed directly from outside the class. Instead, we use methods like getters and built-in class functions to read or process this information. This ensures that the data inside each object remains secure and prevents unwanted changes that could cause errors in the program. By doing this, we also keep our code cleaner, safer, and easier to manage.
## Inheritance
We apply inheritance by creating the TimedQuestion class as an extension of the Question class using TimedQuestion extends Question. This allows TimedQuestion to automatically inherit all the fields and methods of Question, including displaying questions and checking answers. On top of that, we add new features such as time limits and countdown timers that are unique to timed questions. This approach prevents us from repeating the same code and allows us to enhance functionality efficiently while still using the original question structure.
## Abstract
We demonstrate abstraction by focusing on what our objects are meant to do rather than how all internal processes work. The Question class represents the general concept of a quiz question, while the TimedQuestion class abstracts a special type of question that includes a time limit. We further apply abstraction through the QuizOperations interface, which defines the essential actions every quiz must have, such as starting the quiz, displaying scores, and adding questions. This simplifies our program design and makes each component easier to understand, use, and maintain.
## Polymorphism
We implement polymorphism by storing both regular questions and timed questions inside a single List<Question>. This allows us to treat different types of questions as a single group during execution. When the quiz runs, we check the actual type of each question using instanceof and apply the appropriate behavior depending on whether the object is a Question or a TimedQuestion. This flexibility enables different question types to work together smoothly within the same quiz flow.

We use the QuizOperations interface to define clear rules for how the quiz should operate. The interface lists required methods such as startQuiz(), displayScore(), and addQuestion(). The Quiz class implements this interface, which means we provide exact definitions for each of these methods. This ensures that our quiz follows a consistent structure and helps make the program more organized, reliable, and easier to expand or modify in the future.

	Program Structure:

The Question class serves as the foundation of our quiz system. We use this class to store the question text and answer choices, display each question to the user, and verify whether the selected answer is correct.

The TimedQuestion class extends the Question class to introduce time-limited questions into our program. We added features that manage countdown timers, handle user input using separate threads, and automatically proceed to the next question when time runs out.

The QuizOperations interface defines the core actions required to run the quiz. We designed this interface to include methods for starting the quiz, displaying the final score, and adding new questions, ensuring that every quiz controller follows a consistent structure.

The Quiz class implements the QuizOperations interface and acts as the main controller of our application. We use this class to manage the overall quiz flow, store all questions in a list, validate user answers, track scores, compute grades, and display the final results to the user.

The FinalJavaQuiz class serves as our program’s entry point. This is where we initialize the quiz, load all predefined questions, start the quiz session, and present the user’s final score once the quiz has been completed.

	How To Run The Program:

To run the mindcard.io application, we use Visual Studio Code. We begin by making sure that the Java Extension is installed and the Java Development Kit is properly set up on the computer. After that, we open the project folder containing all the Java sources files in VS Code.

Next, we open the JavaProgrammingQuiz.java file and simply click the Run button displayed above the main() method. VS Code automatically compiles and executes the program, launhcing the quiz directly  in the integrated terminal where we can start interacting with it immediately, no manual command-line steps are required.

	Sample Output:
