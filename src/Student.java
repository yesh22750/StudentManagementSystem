public class Student {

    private int id;
    private String name;
    private int age;
    private String email;
    private String phone;
    private double marks;

    public Student(int id, String name, int age, String email, String phone, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.marks = marks;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public double getMarks() { return marks; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setMarks(double marks) { this.marks = marks; }

    public String getGrade() {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 70) return "B";
        if (marks >= 60) return "C";
        if (marks >= 50) return "D";
        return "F";
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Name: %s | Age: %d | Email: %s | Phone: %s | Marks: %.2f | Grade: %s",
                id, name, age, email, phone, marks, getGrade()
        );
    }

    public String toFileString() {
        return id + "|" + name + "|" + age + "|" + email + "|" + phone + "|" + marks;
    }
}
