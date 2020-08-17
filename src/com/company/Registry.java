package com.company;

// Dinh Ngoc Trac Huynh
// 500995716
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Registry
{
    private TreeMap<String, Student> students = new TreeMap<>();
    private TreeMap<String, ActiveCourse> courses = new TreeMap<>();

    public Registry() throws FileNotFoundException {
        // Add some students
        // in A2 we will read from a file
        readFile();
        // sort the students alphabetically - see class Student

        ArrayList<Student> list = new ArrayList<>();

        // Add some active courses with students

        // CPS209
        String courseName = "Computer Science II";
        String courseCode = "CPS209";
        String descr = "Learn how to write complex programs!";
        String format = "3Lec 2Lab";
        list.add(students.get("38467")); list.add(students.get("98345")); list.add(students.get("57643"));
        courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
        students.get("38467").addCourse(courseName, courseCode, descr, format, "W2020", 0);
        students.get("98345").addCourse(courseName, courseCode, descr, format, "W2020", 0);
        students.get("57643").addCourse(courseName, courseCode, descr, format, "W2020", 0);

        // CPS511
        list.clear();
        courseName = "Computer Graphics";
        courseCode = "CPS511";
        descr = "Learn how to write cool graphics programs";
        format = "3Lec";
        list.add(students.get("34562")); list.add(students.get("25347")); list.add(students.get("46532"));
        courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
        students.get("34562").addCourse(courseName,courseCode,descr,format,"W2020", 0);
        students.get("25347").addCourse(courseName,courseCode,descr,format,"W2020", 0);
        students.get("46532").addCourse(courseName,courseCode,descr,format,"W2020", 0);

        // CPS643
        list.clear();
        courseName = "Virtual Reality";
        courseCode = "CPS643";
        descr = "Learn how to write extremely cool virtual reality programs";
        format = "3Lec 2Lab";
        list.add(students.get("34562")); list.add(students.get("38467")); list.add(students.get("57643")); list.add(students.get("46532"));
        courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
        students.get("34562").addCourse(courseName,courseCode,descr,format,"W2020", 0);
        students.get("38467").addCourse(courseName,courseCode,descr,format,"W2020", 0);
        students.get("57643").addCourse(courseName,courseCode,descr,format,"W2020", 0);
        students.get("46532").addCourse(courseName,courseCode,descr,format,"W2020", 0);

        // CPS706
        list.clear();
        courseName = "Computer Networks";
        courseCode = "CPS706";
        descr = "Learn about Computer Networking";
        format = "3Lec 1Lab";
        list.add(students.get("34562")); list.add(students.get("98345")); list.add(students.get("25347"));
        courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
        students.get("34562").addCourse(courseName,courseCode,descr,format,"W2020", 0);
        students.get("98345").addCourse(courseName,courseCode,descr,format,"W2020", 0);
        students.get("25347").addCourse(courseName,courseCode,descr,format,"W2020", 0);

        // CPS616
        courseName = "Algorithms";
        courseCode = "CPS616";
        descr = "Learn about Algorithms";
        format = "3Lec 1Lab";
        list.add(students.get("38467")); list.add(students.get("57643")); list.add(students.get("46532"));
        courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
        students.get("38467").addCourse(courseName,courseCode,descr,format,"W2020", 0);
        students.get("57643").addCourse(courseName,courseCode,descr,format,"W2020", 0);
        students.get("46532").addCourse(courseName,courseCode,descr,format,"W2020", 0);



    }

    // Add new student to the registry (students arraylist above)
    public boolean addNewStudent(String name, String id)
    {
        boolean inRegistry = false;
        // Create a new student object
        var newStudent = new Student(name, id);
        for (Student student : students.values()) {
            // check to ensure student is not already in registry
            // make use of equals method in class Student
            if (newStudent.equals(student)) {
                inRegistry = true;
                break;
            }
        }
        // if not, add them and return true, otherwise return false
        if (!(inRegistry)){
            students.put(newStudent.getId(),newStudent);
            return true;
        }
        else
            System.out.println("Student " + newStudent.getName() +" already registered");
        return false;
    }
    // Remove student from registry
    public boolean removeStudent(String studentId)
    {
        for (int i = 0; i<students.size(); i++){
            // Find student in students arraylist
            Student studentRemoved = this.students.get(i);
            // If found, remove this student and return true
            if (studentRemoved.getId().equalsIgnoreCase(studentId)){
                students.remove(studentRemoved);
                return true;
            }
        }
        return false;
    }

    // Print all registered students
    public void printAllStudents()
    {
        for (var student : students.entrySet()) {
            System.out.println("ID: " + student.getKey() + " Name: " + student.getValue().getName());
        }

    }

    // Given a studentId and a course code, add student to the active course
    public void addCourse(String studentId, String courseCode) {
        boolean hasTaken = false;
        boolean enrolled = false;
        Student newStudent;
        ActiveCourse activeCourse;
        // Find student object in registry (i.e. students arraylist)
        for (Student student : students.values()) {
            if (studentId.equalsIgnoreCase(student.getId())) {
                newStudent = student;
                // Check if student has already taken this course in the past Hint: look at their credit course list
                for (int j = 0; j < student.courses.size(); j++) {
                    if (courseCode.equalsIgnoreCase(newStudent.courses.get(j).getCode())) {
                        hasTaken = true;
                        break;
                    }
                }
                // If not, then find the active course in courses array list using course code
                if (!(hasTaken)) {
                    for (ActiveCourse cours : courses.values()) {
                        // using course code
                        if (courseCode.equalsIgnoreCase(cours.getCode())) {
                            activeCourse = cours;
                            ArrayList<Student> listStudentInCourse = activeCourse.getStudent();
                            // If active course found then check to see if student already enrolled in this course
                            for (Student value : listStudentInCourse) {
                                if (studentId.equalsIgnoreCase(value.getId())) {
                                    enrolled = true;
                                    break;
                                }
                            }
                            // If not already enrolled
                            if (!(enrolled)) {
                                //   add student to the active course
                                listStudentInCourse.add(newStudent);
                                //   add course to student list of credit courses with initial grade of 0
                                newStudent.addCourse(activeCourse.getName(), activeCourse.getCode(), activeCourse.getDescription(), activeCourse.getFormat(), activeCourse.getSemester(), 0);
                            }
                        }
                    }
                }
            }
        }
    }
    // Given a studentId and a course code, drop student from the active course
    public void dropCourse(String studentId, String courseCode)
    {
        ActiveCourse activeCourse = null;
        Student newStudent = null;
//        ArrayList<Student> listStudentInCourse = activeCourse.getStudent();
        ArrayList<Student> listStudentInCourse = null;
        //Find the active course
        for (ActiveCourse cours : courses.values()) {
            if (courseCode.equalsIgnoreCase(cours.getCode())){
                activeCourse = cours;
                listStudentInCourse = activeCourse.getStudent();
            }
        }
        // Find the student in the list of students for this course
        if (listStudentInCourse != null){
            for (Student student : listStudentInCourse) {
                if (studentId.equalsIgnoreCase(student.getId()))
                    newStudent = student;
            }
        }
        // If student found:
        if(newStudent != null) {
            //   remove the student from the active course
            listStudentInCourse.remove(newStudent);
            //   remove the credit course from the student's list of credit courses
            newStudent.removeActiveCourse(courseCode);
        }
        else
            // if one student is not in that course but you accidentally drop that student
            System.out.println("That student is not in " + courseCode);

    }

    // Print all active courses
    public void printActiveCourses()
    {
        for (int i = 0; i < courses.size(); i++)
        {
            ActiveCourse ac = courses.get(i);
            System.out.println(ac.getDescription());
        }
    }

    // Print the list of students in an active course
    public void printClassList(String courseCode)
    {
        for (ActiveCourse cours : courses.values()) {
            // find course
            if (courseCode.equalsIgnoreCase(cours.getCode())) {
                // Print the list of students in an active course
                cours.printClassList();
            }
        }
    }

    // Given a course code, find course and sort class list by student name
    public void sortCourseByName(String courseCode)
    {
        for (ActiveCourse cours : courses.values()) {
            // find course
            if (courseCode.equalsIgnoreCase(cours.getCode()))
                // sort class list by student name
                cours.sortByName();
        }
    }

    // Given a course code, find course and sort class list by student name
    public void sortCourseById(String courseCode)
    {
        for (ActiveCourse cours : courses.values()) {
            // find course
            if (courseCode.equalsIgnoreCase(cours.getCode()))
                // sort class list by student ID
                cours.sortById();
        }
    }

    // Given a course code, find course and print student names and grades
    public void printGrades(String courseCode)
    {
        for (ActiveCourse cours : courses.values()) {
            // find course
            if (courseCode.equalsIgnoreCase(cours.getCode())) {
                // print student names and grades
                cours.printGrades();
            }
        }
    }

    // Given a studentId, print all active courses of student
    public void printStudentCourses(String studentId)
    {
        for (Student student : students.values()) {
            // find the student
            if (studentId.equalsIgnoreCase(student.getId()))
                // print all active courses of the student
                student.printActiveCourses();
        }
    }

    // Given a studentId, print all completed courses and grades of student
    public void printStudentTranscript(String studentId)
    {
        for (Student student : students.values()) {
            // find the student
            if (studentId.equalsIgnoreCase(student.getId()))
                // print all completed courses and grades of the student
                student.printTranscript();
        }
    }

    // Given a course code, student id and numeric grade
    // set the final grade of the student
    public void setFinalGrade(String courseCode, String studentId, double grade)
    {
        // find the active course
        for (ActiveCourse cours : courses.values()) {
            if (courseCode.equalsIgnoreCase(cours.getCode())) {
                // If found, find the student in class list
                for (int j = 0; j < cours.getStudent().size(); j++) {
                    if (studentId.equalsIgnoreCase(cours.getStudent().get(j).getId())) {
                        // then search student credit course list in student object and find course
                        for (int k = 0; k < cours.getStudent().get(j).courses.size(); k++) {
                            if (courseCode.equalsIgnoreCase(cours.getStudent().get(j).courses.get(k).getCode())) {
                                // set the grade in credit course and set credit course inactive
                                cours.getStudent().get(j).courses.get(k).grade = grade;
                                cours.getStudent().get(j).courses.get(k).setInactive();
                            }
                        }
                    }
                }
            }
        }

    }

    public TreeMap<String, ActiveCourse> getCourses() {
        return courses;
    }

    // read a list of student from students.txt file
    public void readFile() throws FileNotFoundException {
        FileReader file = new FileReader("students.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String name = scanner.next();
                String id = scanner.next();
                Student student = new Student(name, id);
                students.put(id, student);
            }
        }
    }
}

