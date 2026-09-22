import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManager manager = new StudentManager();

    public static void main(String[] args) {
        FileHandler.loadStudents(manager);

        boolean running = true;

        while (running) {
            displayMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 6 -> FileHandler.saveStudents(manager);
                case 7 -> {
                    FileHandler.saveStudents(manager);
                    System.out.println("Thank you for using Student Management System.");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please select 1-7.");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("       STUDENT MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Save Students");
        System.out.println("7. Exit");
        System.out.println("========================================");
    }

    private static void addStudent() {
        System.out.println("\n========== ADD STUDENT ==========");

        int id = readPositiveInt("Enter Student ID: ");

        if (manager.searchStudent(id) != null) {
            System.out.println("Student with this ID already exists.");
            return;
        }

        String name = readNonEmptyString("Enter Name: ");
        int age = readAge();
        String email = readEmail();
        String phone = readPhone();
        double marks = readMarks();

        Student student = new Student(id, name, age, email, phone, marks);

        if (manager.addStudent(student)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Unable to add student.");
        }
    }

    private static void viewStudents() {
        System.out.println("\n========== ALL STUDENTS ==========");

        if (manager.size() == 0) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : manager.getAllStudents()) {
            System.out.println(student);
        }

        System.out.println("Total students: " + manager.size());
    }

    private static void searchStudent() {
        System.out.println("\n========== SEARCH STUDENT ==========");

        int id = readPositiveInt("Enter Student ID: ");
        Student student = manager.searchStudent(id);

        if (student != null) {
            System.out.println("Student found:");
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void updateStudent() {
        System.out.println("\n========== UPDATE STUDENT ==========");

        int id = readPositiveInt("Enter Student ID to update: ");
        Student existing = manager.searchStudent(id);

        if (existing == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Current details:");
        System.out.println(existing);

        String name = readNonEmptyString("Enter New Name: ");
        int age = readAge();
        String email = readEmail();
        String phone = readPhone();
        double marks = readMarks();

        if (manager.updateStudent(id, name, age, email, phone, marks)) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Unable to update student.");
        }
    }

    private static void deleteStudent() {
        System.out.println("\n========== DELETE STUDENT ==========");

        int id = readPositiveInt("Enter Student ID to delete: ");
        Student student = manager.searchStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Student:");
        System.out.println(student);

        String confirmation = readNonEmptyString(
                "Are you sure you want to delete? (yes/no): "
        );

        if (confirmation.equalsIgnoreCase("yes")) {
            if (manager.deleteStudent(id)) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Unable to delete student.");
            }
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static int readPositiveInt(String message) {
        while (true) {
            int value = readInt(message);
            if (value > 0) {
                return value;
            }
            System.out.println("Value must be greater than 0.");
        }
    }

    private static int readAge() {
        while (true) {
            int age = readInt("Enter Age: ");
            if (age >= 5 && age <= 100) {
                return age;
            }
            System.out.println("Please enter an age between 5 and 100.");
        }
    }

    private static String readNonEmptyString(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty() && !value.contains("|")) {
                return value;
            }

            System.out.println("Input cannot be empty or contain '|'.");
        }
    }

    private static String readEmail() {
        while (true) {
            System.out.print("Enter Email: ");
            String email = scanner.nextLine().trim();

            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                return email;
            }

            System.out.println("Please enter a valid email.");
        }
    }

    private static String readPhone() {
        while (true) {
            System.out.print("Enter Phone: ");
            String phone = scanner.nextLine().trim();

            if (phone.matches("\\d{10}")) {
                return phone;
            }

            System.out.println("Phone number must contain exactly 10 digits.");
        }
    }

    private static double readMarks() {
        while (true) {
            try {
                System.out.print("Enter Marks (0-100): ");
                double marks = Double.parseDouble(scanner.nextLine().trim());

                if (Double.isFinite(marks) && marks >= 0 && marks <= 100) {
                    return marks;
                }

                System.out.println("Marks must be between 0 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid marks.");
            }
        }
    }
}
