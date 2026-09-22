import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    private static final String FILE_NAME = "students.txt";

    private FileHandler() {
        // Utility class; no objects required.
    }

    public static void saveStudents(StudentManager manager) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : manager.getAllStudents()) {
                writer.write(student.toFileString());
                writer.newLine();
            }
            System.out.println("Students saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    public static void loadStudents(StudentManager manager) {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        int loaded = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                if (data.length != 6) {
                    System.out.println("Skipped invalid record: " + line);
                    continue;
                }

                try {
                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    int age = Integer.parseInt(data[2].trim());
                    String email = data[3].trim();
                    String phone = data[4].trim();
                    double marks = Double.parseDouble(data[5].trim());

                    Student student = new Student(id, name, age, email, phone, marks);

                    if (manager.addStudent(student)) {
                        loaded++;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Skipped invalid record: " + line);
                }
            }

            if (loaded > 0) {
                System.out.println(loaded + " student(s) loaded successfully.");
            }
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
    }
}
