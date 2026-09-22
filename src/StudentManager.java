import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentManager {

    private final Map<Integer, Student> students = new HashMap<>();

    public boolean addStudent(Student student) {
        if (students.containsKey(student.getId())) {
            return false;
        }
        students.put(student.getId(), student);
        return true;
    }

    public Student searchStudent(int id) {
        return students.get(id);
    }

    public boolean updateStudent(int id, String name, int age,
                                 String email, String phone, double marks) {
        Student student = students.get(id);
        if (student == null) {
            return false;
        }

        student.setName(name);
        student.setAge(age);
        student.setEmail(email);
        student.setPhone(phone);
        student.setMarks(marks);
        return true;
    }

    public boolean deleteStudent(int id) {
        return students.remove(id) != null;
    }

    public List<Student> getAllStudents() {
        List<Student> result = new ArrayList<>(students.values());
        result.sort(Comparator.comparingInt(Student::getId));
        return result;
    }

    public int size() {
        return students.size();
    }

    public Map<Integer, Student> getStudents() {
        return students;
    }
}
