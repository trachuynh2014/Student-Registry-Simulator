package com.company;

// Dinh Ngoc Trac Huynh
// 500995716
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Active University Course

public class ActiveCourse extends Course
{
    private ArrayList<Student> student;
    private String             semester;
    private  int lectureStart;
    private  int lectureDuration;
    private String lectureDay;


    // Add a constructor method with appropriate parameters
    // should call super class constructor to initialize inherited variables
    // make sure to *copy* students array list being passed in into new arraylist of students
    // see class Registry to see how an ActiveCourse object is created and used
    public ActiveCourse(String name, String code, String descr, String fmt,String semester,ArrayList<Student> students)
    {
        super(name,code,descr,fmt);
        this.semester = semester;
        this.student = new ArrayList<Student>(students);

    }

    public String getSemester()
    {
        return semester;
    }

    // Prints the students in this course  (name and student id)
    public void printClassList()
    {
        for (Student value : student) {
            System.out.println("Student ID: " + value.getId() + " Name: " + value.getName());
        }
    }

    // Prints the grade of each student in this course (along with name and student id)
    //
    public void printGrades()
    {
        for (Student value : student)
            System.out.println(value.getId() + " " + value.getName() + " " + value.getGrade(getCode()));
    }

    // Returns the numeric grade in this course for this student
    // If student not found in course, return 0
    public double getGrade(String studentId)
    {
        // Search the student's list of credit courses to find the course code that matches this active course
        for (int i = 0; i< student.size(); i++){
            if(studentId.equalsIgnoreCase(student.get(i).getId()))
                // return the grade stored in the credit course object
                return student.get(i).courses.get(i).grade;
        }
        return 0;
    }

    // Returns a String containing the course information as well as the semester and the number of students
    // enrolled in the course
    // must override method in the superclass Course and use super class method getDescription()
    public String getDescription()
    {
        return super.getDescription() + " " + getSemester() + " " + "Enrollment: " + student.size() + "\n";
    }

    // Sort the students in the course by name using the Collections.sort() method with appropriate arguments
    // Make use of a private Comparator class below
    public void sortByName()
    {
        var nameComparator = new NameComparator();
        Collections.sort(student,nameComparator);
    }

    // Fill in the class so that this class implement the Comparator interface
    // This class is used to compare two Student objects based on student name
    private class NameComparator implements Comparator<Student>
    {

        @Override
        public int compare(Student o1, Student o2) {
            return ((Student)o1).getName().compareTo(((Student)o2).getName());
        }
    }

    // Sort the students in the course by student id using the Collections.sort() method with appropriate arguments
    // Make use of a private Comparator class below
    public void sortById()
    {
        var idComparator = new IdComparator();
        Collections.sort(student,idComparator);

    }

    // Fill in the class so that this class implement the Comparator interface
    // This class is used to compare two Student objects based on student id
    private class IdComparator implements Comparator<Student>
    {

        @Override
        public int compare(Student o1, Student o2) {
            return ((Student)o1).getId().compareTo(((Student)o2).getId());
        }
    }
    // get Arraylist Student
    public ArrayList<Student> getStudent() {
        return student;
    }
    // set Lecture start time
    public void setLectureStart(int lectureStart) {
        this.lectureStart = lectureStart;
    }
    // set lecture duration
    public void setLectureDuration(int lectureDuration) {
        this.lectureDuration = lectureDuration;
    }
    // set lecture day
    public void setLectureDay(String lectureDay) {
        this.lectureDay = lectureDay;
    }
    // get lecture start time
    public int getLectureStart() {
        return lectureStart;
    }
    // get lecture duration
    public int getLectureDuration() {
        return lectureDuration;
    }
    // get lecture day
    public String getLectureDay() {
        return lectureDay;
    }
}

