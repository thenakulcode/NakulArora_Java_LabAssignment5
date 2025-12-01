 Student Record Management System – Java Lab Assignment 5

A Java-based Student Record Management System that demonstrates
Object-Oriented Programming, File Handling, Exception Handling,
Multithreading, and Java Collections.

This project fulfills the complete requirements of Java Lab Assignment 5
given by K.R. Mangalam University.

🚀 Features

✔ Object-Oriented Programming (OOP)

Abstract class Person
Inheritance (Student extends Person)
Interface (RecordActions) implemented by StudentManager
Encapsulation with private fields and getters/setters
Modular structure with multiple classes
✔ Student Operations

Add a new student
Update existing student details
Delete student by name
Search student by name
View all students
Sort students by marks (Descending)
Validations for duplicate roll numbers
✔ Exception Handling

Custom exception: StudentNotFoundException
Handles:
Invalid marks
Empty fields
Invalid roll number
Missing student on delete/search
✔ File Handling (Persistent Storage)

Uses BufferedReader and BufferedWriter
Automatically loads data from students.txt
Saves updated data back to the file at exit
✔ Multithreading (Loading Simulation)

Loader class implements Runnable
Displays loading animation during:
Adding a student
Saving records
✔ Java Collections

Stores student records in:
List<Student>
Map<Integer, Student> (rollNo → Student mapping)
Uses Comparator for sorting
Uses Iterator for displaying records
📁 Project Structure
src/ │── Person.java │── Student.java │── RecordActions.java │── StudentManager.java │── StudentNotFoundException.java │── Loader.java │── FileHandler.java │── Main.java └── students.txt

🧩 Class Overview

1. Person (Abstract Class) Fields:

String name
String email
Methods:

abstract void displayInfo()
2. Student (extends Person) Fields:

int rollNo
String course
double marks
String grade
Methods:

inputDetails()
displayDetails()
calculateGrade()
RecordActions (Interface)** Methods:
addStudent()
updateStudent()
deleteStudent()
searchStudent()
viewAllStudents()
StudentManager (Implements RecordActions)**
Stores data in List & Map
Validates duplicate roll numbers
Handles search, delete, update
Sorts using Comparator
Loader (Multithreading)**
Simulates loading using Thread.sleep()
FileHandler**
Reads and writes student data using BufferedReader/Writer
Main**
Contains menu-driven program
Integrates all components
▶️ How to Run
1. Compile all Java files
javac *.java
2. Run the Main program
bash
Copy code
java Main
3. Make sure students.txt exists
If not, the program will create it automatically.

📝 Sample Output
===== Capstone Student Menu =====
1. Add Student
2. View All Students
3. Search by Name
4. Delete by Name
5. Sort by Marks
6. Save and Exit
Enter choice:

🛠 Technologies Used
Java (Core)
Java Collections Framework
File I/O
Multithreading
OOP Concepts

👩‍💻 Author
Nakul Arora
B.Tech CSE — K.R. Mangalam University
