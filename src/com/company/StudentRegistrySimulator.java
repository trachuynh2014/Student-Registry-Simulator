package com.company;

// Dinh Ngoc Trac Huynh
// 500995716

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StudentRegistrySimulator
{
    public static void main(String[] args)
    {
        Registry registry = null;
        // handle File not Found exception and Bad File exception
        try {
            registry = new Registry();
        } catch (IOException e) {
            System.out.println("students.txt File Not Found");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Bad File");
            System.exit(0);
        }
        Scheduler schedule = new Scheduler(registry.getCourses());

        Scanner scanner = new Scanner(System.in);
        System.out.print(">");

        while (scanner.hasNextLine())
        {
            String inputLine = scanner.nextLine();
            if (inputLine == null || inputLine.equals("")) continue;

            Scanner commandLine = new Scanner(inputLine);
            String command = commandLine.next();

            if (command == null || command.equals("")) continue;

            else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST"))
            {
                registry.printAllStudents();
            }
            else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT"))
                return;

            else if (command.equalsIgnoreCase("REG"))
            {
                try {
                    // register a new student in registry
                    // get name and student id string
                    String name = commandLine.next();
                    String id = commandLine.next();
                    // e.g. reg JohnBoy 74345
                    // Checks:
                    //  ensure name is all alphabetic characters
                    //  ensure id string is all numeric characters
                    if (isStringOnlyAlphabet(name) && isNumeric(id))
                        registry.addNewStudent(name, id);
                    else if(!isStringOnlyAlphabet(name) && !isNumeric(id))
                        System.out.println("Invalid Characters in Name " + name + " and invalid characters in ID " + id);
                    else if (!isNumeric(id))
                        System.out.println("Invalid Characters in ID " + id);
                    else
                        System.out.println("Invalid Characters in Name " + name);
                } catch (Exception e) {
                    System.out.println("Invalid input");
                }
            }
            else if (command.equalsIgnoreCase("DEL"))
            {
                try {
                    // delete a student from registry
                    // get student id
                    String id = commandLine.next();
                    // ensure numeric
                    // remove student from registry
                    if (isNumeric(id))
                        registry.removeStudent(id);
                } catch (Exception e) {
                    System.out.println("Student ID is missing");
                }
            }

            else if (command.equalsIgnoreCase("ADDC"))
            {
                try {
                    // add a student to an active course
                    // get student id and course code strings
                    String id = commandLine.next();
                    String courseCode = commandLine.next();
                    // add student to course (see class Registry)
                    registry.addCourse(id, courseCode);
                } catch (Exception e) {
                    System.out.println("Invalid input");
                }
            }
            else if (command.equalsIgnoreCase("DROPC"))
            {
                try {
                    // get student id and course code strings
                    String id = commandLine.next();
                    String courseCode = commandLine.next();
                    // drop student from course (see class Registry)
                    registry.dropCourse(id,courseCode);
                } catch (Exception e) {
                    System.out.println("Invalid input");
                }
            }
            else if (command.equalsIgnoreCase("PAC"))
            {
                // print all active courses
                registry.printActiveCourses();
            }
            else if (command.equalsIgnoreCase("PCL"))
            {
                try {
                    // get course code string
                    String courseCode = commandLine.next();
                    // print class list (i.e. students) for this course
                    registry.printClassList(courseCode);
                } catch (Exception e) {
                    System.out.println("Course code is missing");
                }
            }
            else if (command.equalsIgnoreCase("PGR"))
            {
                try {
                    // get course code string
                    String courseCode = commandLine.next();
                    // print name, id and grade of all students in active course
                    registry.printGrades(courseCode);
                } catch (Exception e) {
                    System.out.println("Course code is missing");
                }
            }
            else if (command.equalsIgnoreCase("PSC"))
            {
                try {
                    // get student id string
                    String id = commandLine.next();
                    // print all credit courses of student
                    registry.printStudentCourses(id);
                } catch (Exception e) {
                    System.out.println("Student ID is missing");
                }
            }
            else if (command.equalsIgnoreCase("PST"))
            {
                try {
                    // get student id string
                    String id = commandLine.next();
                    // print student transcript
                    registry.printStudentTranscript(id);
                } catch (Exception e) {
                    System.out.println("Student ID is missing");
                }
            }
            else if (command.equalsIgnoreCase("SFG"))
            {
                try {
                    // get course code, student id, numeric grade
                    String courseCode = commandLine.next();
                    String id = commandLine.next();
                    double gradeLetter = commandLine.nextDouble();
//                // use registry to set final grade of this student (see class Registry)
                    registry.setFinalGrade(courseCode, id, gradeLetter);
                } catch (Exception e) {
                    System.out.println("Invalid input");
                }
            }
            else if (command.equalsIgnoreCase("SCN"))
            {
                try {
                    // get course code
                    String courseCode = commandLine.next();
                    // sort list of students in course by name (i.e. alphabetically)
                    // see class Registry
                    registry.sortCourseByName(courseCode);
                } catch (Exception e) {
                    System.out.println("Course code is missing");
                }
            }
            else if (command.equalsIgnoreCase("SCI"))
            {
                try {
                    // get course code
                    String courseCode = commandLine.next();
                    // sort list of students in course by student id
                    // see class Registry
                    registry.sortCourseById(courseCode);
                } catch (Exception e) {
                    System.out.println("Course code is missing");
                }
            }
            else if (command.equalsIgnoreCase("SCH"))
            {
                try {
                    // get course code, day, start time and duration
                    String courseCode = commandLine.next();
                    String day = commandLine.next();
                    int startTime = Integer.parseInt(commandLine.next());
                    int duration = Integer.parseInt(commandLine.next());
                    // set day and time and duration of the course
                    schedule.setDayAndTime(courseCode, day, startTime, duration);
                } catch (UnknownCourse | InvalidDay | InvalidTime | InvalidDuration | LectureTimeCollision exception) {
                    System.out.println(exception.getMessage());
                } catch (NoSuchElementException e){
                    System.out.println("Invalid input");
                }
            }
            else if (command.equalsIgnoreCase("CSCH"))
            {
                try {
                    // get course code
                    String courseCode = commandLine.next();
                    // remove the course
                    schedule.clearSchedule(courseCode);
                } catch (Exception e) {
                    System.out.println("Course code is missing");
                }
            }
            else if (command.equalsIgnoreCase("PSCH"))
            {
                // print schedule
                schedule.printSchedule();
            }
            System.out.print("\n>");
        }
    }

    private static boolean isStringOnlyAlphabet(String str)
    {
        // write method to check if string str contains only alphabetic characters
        char[] chars = str.toCharArray();
        for(char ch: chars) {
            if (!Character.isLetter(ch))
                return false;
        }
        return true;
    }

    public static boolean isNumeric(String str)
    {
        // write method to check if string str contains only numeric characters
        char[] chars = str.toCharArray();
        for(char ch: chars) {
            if (!Character.isDigit(ch))
                return false;
        }
        return true;
    }


}
