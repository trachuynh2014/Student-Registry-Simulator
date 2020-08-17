package com.company;

// Dinh Ngoc Trac Huynh
// 500995716

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;

// Make class Student implement the Comparable interface
// Two student objects should be compared by their name
public class Student implements Comparable
{
    private String name;
    private String id;
    public  ArrayList<CreditCourse> courses;


    public Student(String name, String id)
    {
        this.name = name;
        this.id   = id;
        courses   = new ArrayList<CreditCourse>();
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    // add a credit course to list of courses for this student
    public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade)
    {
        // create a CreditCourse object
        // set course active
        // add to courses array list
        var creditCourse = new CreditCourse(courseName, courseCode, descr, format, sem, grade);
        creditCourse.setActive();
        courses.add(creditCourse);
    }
    // Prints a student transcript
    // Prints all completed (i.e. non active) courses for this student (course code, course name,
    // semester, letter grade
    // see class CreditCourse for useful methods
    public void printTranscript()
    {
        for (CreditCourse cours : courses) {
            if (!cours.getActive())
                System.out.println(cours.displayGrade());
        }
    }

    // Prints all active courses this student is enrolled in
    // see variable active in class CreditCourse
    public void printActiveCourses()
    {
        for (CreditCourse cours : courses) {
            if (cours.getActive())
                System.out.println(cours.getDescription());
        }
    }

    // Drop a course (given by courseCode)
    // Find the credit course in courses arraylist above and remove it
    // only remove it if it is an active course
    public void removeActiveCourse(String courseCode)
    {
        for (int i = 0;i<courses.size();i++) {
            if (courses.get(i).getActive()){
                if(courses.get(i).getCode().equalsIgnoreCase(courseCode))
                    courses.remove(i);
            }
        }
    }

    public String toString()
    {
        return "Student ID: " + id + " Name: " + name;
    }

    // override equals method inherited from superclass Object
    // if student names are equal *and* student ids are equal (of "this" student
    // and "other" student) then return true
    // otherwise return false
    // Hint: you will need to cast other parameter to a local Student reference variable
    public boolean equals(Object other)
    {
        var o = (Student)other;
        if(this.name.equalsIgnoreCase(o.name) && this.id.equalsIgnoreCase(o.id))
            return true;
        else
            return false;
    }


    @Override
    public int compareTo(Object o) {
        var other = (Student)o;
        return this.name.compareTo(other.name);
    }
    //get Grade from a student's credit course
    public double getGrade(String courseCode) {
        for (CreditCourse cours : courses) {
            if (courseCode.equalsIgnoreCase(cours.getCode()))
                return cours.grade;
        }
        return 0.0;
    }
}
