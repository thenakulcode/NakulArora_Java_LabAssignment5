import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String DATA_FILE = "resources/students.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        // Load data
        List<Student> loaded = FileHandler.loadStudents(DATA_FILE);
        manager.initializeFromList(loaded);
        System.out.println("Loaded " + loaded.size() + " students from file.");

        boolean exit = false;
        while (!exit) {
            showMenu();
            System.out.print("Enter choice: ");
            String line = sc.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter number 1-6.");
                continue;
            }

            try {
                switch (choice) {
                    case 1 -> addStudentFlow(sc, manager);
                    case 2 -> viewAllFlow(manager);
                    case 3 -> searchFlow(sc, manager);
                    case 4 -> deleteFlow(sc, manager);
                    case 5 -> sortFlow(manager);
                    case 6 -> { saveAndExit(manager); exit = true; }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }

        sc.close();
    }

    private static void showMenu() {
        System.out.println("\n===== Capstone Student Menu =====");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search by Name");
        System.out.println("4. Delete by Name");
        System.out.println("5. Sort by Marks");
        System.out.println("6. Save and Exit");
    }

    private static void addStudentFlow(Scanner sc, StudentManager manager) {
        try {
            System.out.print("Enter Roll No: ");
            int roll = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Enter Name: ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) throw new InvalidInputException("Name cannot be empty");

            System.out.print("Enter Email: ");
            String email = sc.nextLine().trim();
            if (email.isEmpty()) throw new InvalidInputException("Email cannot be empty");

            System.out.print("Enter Course: ");
            String course = sc.nextLine().trim();
            if (course.isEmpty()) throw new InvalidInputException("Course cannot be empty");

            System.out.print("Enter Marks: ");
            double marks = Double.parseDouble(sc.nextLine().trim());
            if (marks < 0 || marks > 100) throw new InvalidInputException("Marks must be 0-100");

            Student s = new Student(roll, name, email, course, marks);

            Loader loader = new Loader("Adding student");
            Thread t = new Thread(loader);
            t.start();
            Thread.sleep(700); // simulate work
            manager.addStudent(s);
            loader.stop();
            t.join();
            System.out.println("Student added successfully.");
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid number input. Try again.");
        } catch (InvalidInputException iie) {
            System.out.println("Validation error: " + iie.getMessage());
        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    private static void viewAllFlow(StudentManager manager) {
        List<Student> all = manager.viewAllStudents();
        if (all.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        for (Student s : all) s.displayDetails();
    }

    private static void searchFlow(Scanner sc, StudentManager manager) {
        System.out.print("Enter name to search: ");
        String name = sc.nextLine().trim();
        try {
            Student s = manager.searchStudentByName(name);
            System.out.println("Student Info:");
            s.displayDetails();
        } catch (StudentNotFoundException snfe) {
            System.out.println(snfe.getMessage());
        } catch (Exception e) {
            System.out.println("Search error: " + e.getMessage());
        }
    }

    private static void deleteFlow(Scanner sc, StudentManager manager) {
        System.out.print("Enter name to delete: ");
        String name = sc.nextLine().trim();
        try {
            Loader loader = new Loader("Deleting student");
            Thread t = new Thread(loader);
            t.start();
            Thread.sleep(600);
            manager.deleteStudentByName(name);
            loader.stop();
            t.join();
            System.out.println("Student record deleted.");
        } catch (StudentNotFoundException snfe) {
            System.out.println(snfe.getMessage());
        } catch (Exception e) {
            System.out.println("Delete error: " + e.getMessage());
        }
    }

    private static void sortFlow(StudentManager manager) {
        manager.sortByMarksDescending();
        System.out.println("Sorted Student List by Marks (desc):");
        List<Student> all = manager.viewAllStudents();
        if (all.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        for (Student s : all) s.displayDetails();
    }

    private static void saveAndExit(StudentManager manager) {
        try {
            Loader loader = new Loader("Saving records");
            Thread t = new Thread(loader);
            t.start();
            Thread.sleep(800);
            boolean ok = FileHandler.saveStudents("resources/students.txt", manager.viewAllStudents());
            loader.stop();
            t.join();
            if (ok) System.out.println("Saved and exiting.");
            else System.out.println("Save failed, but exiting.");
        } catch (Exception e) {
            System.out.println("Error during save: " + e.getMessage());
        }
    }
}
