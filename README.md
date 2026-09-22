# Student Management System

A console-based Student Management System built with Java.

## Features

- Add student
- View all students
- Search student by ID
- Update student
- Delete student
- Email and phone validation
- Marks validation
- Automatic grade calculation
- File-based data persistence
- HashMap for student storage
- Exception handling

## Technologies

- Java
- OOP
- Collection Framework
- HashMap
- File Handling
- Exception Handling
- Regular Expressions

## Project Structure

```text
StudentManagementSystem/
├── src/
│   ├── Main.java
│   ├── Student.java
│   ├── StudentManager.java
│   └── FileHandler.java
├── students.txt
├── README.md
└── .gitignore
```

## How to Run

### IntelliJ IDEA

1. Open the `StudentManagementSystem` folder.
2. Mark `src` as `Sources Root` if IntelliJ does not do it automatically.
3. Open `src/Main.java`.
4. Run `Main.main()`.

### Command line

From the project folder:

```bash
javac -d out src/*.java
java -cp out Main
```

The project intentionally uses no `package` declaration so it can be opened and run easily as a beginner-friendly IntelliJ project.

## CRUD

- Create → Add Student
- Read → View/Search Student
- Update → Modify Student
- Delete → Remove Student

## Data Persistence

Student data is stored in `students.txt`.

The application loads existing students at startup and saves the current data when the user chooses Save or Exit.

## Student Fields

- ID
- Name
- Age
- Email
- Phone
- Marks
- Grade (calculated automatically)

## Future Improvements

- HTML/CSS/JavaScript frontend
- Spring Boot REST API
- MySQL database
- Login/authentication
- Subject-wise marks
- Attendance management
- Sorting/filtering
