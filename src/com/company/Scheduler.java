package com.company;

// Dinh Ngoc Trac Huynh
// 500995716
import jdk.swing.interop.SwingInterOpUtils;

import java.util.*;

public class Scheduler
{
    // In main() after you create a Registry object, create a Scheduler object and pass in the students ArrayList/TreeMap
    // If you do not want to try using a Map then uncomment
    // the line below and comment out the TreeMap line

    //ArrayList<Student> students;

    TreeMap<String,ActiveCourse> schedule;
    String[][] sche = new String[10][10];

    public Scheduler(TreeMap<String,ActiveCourse> courses)
    {
        schedule = courses;
    }


    // set day, start time for the course
    public void setDayAndTime(String courseCode, String day, int startTime, int duration) throws UnknownCourse, InvalidDay, InvalidTime, InvalidDuration, LectureTimeCollision
    {
        courseCode = courseCode.toUpperCase();
        int endTime = startTime + duration*100;
        if (!(schedule.containsKey(courseCode)))
            throw new UnknownCourse("Unknown Course " + courseCode);
        else if(!(day.equalsIgnoreCase("Mon") || day.equalsIgnoreCase("Tue") || day.equalsIgnoreCase("Wed") || day.equalsIgnoreCase("Thur") || day.equalsIgnoreCase("Fri")))
            throw new InvalidDay("Invalid Lecture Day");
        else if (!(duration == 1 || duration == 2 || duration == 3))
            throw new InvalidTime("Invalid Lecture Duration ");
        else if (startTime < 800 || (startTime + duration*100) > 1700)
            throw new InvalidDuration("Invalid Lecture Start Time");
        else {
            for(var course:schedule.values()) {
                int otherClassEndTime = course.getLectureStart() + (course.getLectureDuration() * 100);
                if (day.equalsIgnoreCase(course.getLectureDay())) {
                    if (course.getLectureStart() == startTime)
                        throw new LectureTimeCollision("Lecture Time Collision");
                    if ((startTime > course.getLectureStart()) && (otherClassEndTime > startTime))
                        throw new LectureTimeCollision("Lecture Time Collision");
                    if ((course.getLectureStart() > startTime) && (endTime > course.getLectureStart()))
                        throw new LectureTimeCollision("Lecture Time Collision");
                }
            }
        }
        schedule.get(courseCode).setLectureDay(day);
        schedule.get(courseCode).setLectureStart(startTime);
        schedule.get(courseCode).setLectureDuration(duration);
    }
    // clear Schedule
    public void clearSchedule(String courseCode)
    {
        courseCode = courseCode.toUpperCase();
        if(schedule.containsKey(courseCode)){
            ActiveCourse activeCourse = schedule.get(courseCode);
            activeCourse.setLectureDay("");
            activeCourse.setLectureDuration(0);
            activeCourse.setLectureStart(0);
        }
    }

    public void printSchedule()
    {
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++)
                sche[i][j] = "      ";
        }
        int row;
        sche[0][1] = "  MON ";
        sche[0][3] = " TUE  ";
        sche[0][5] = " WED  ";
        sche[0][7] = "THUR  ";
        sche[0][9] = " FRI  ";
        sche[1][0] = "0800  ";
        sche[2][0] = "0900  ";
        sche[3][0] = "1000  ";
        sche[4][0] = "1100  ";
        sche[5][0] = "1200  ";
        sche[6][0] = "1300  ";
        sche[7][0] = "1400  ";
        sche[8][0] = "1500  ";
        sche[9][0] = "1600  ";
        // find the day, time and replace blank sche[row][column] with course code
        for(int j = 0; j < 10; j++) {
            for(var courseCode: schedule.keySet())
                if (sche[0][j].trim().equalsIgnoreCase(schedule.get(courseCode).getLectureDay())) {
                    row = (schedule.get(courseCode).getLectureStart()/100) - 7;
                    int duration = schedule.get(courseCode).getLectureDuration();
                    for (int i = 0; i < duration; i++)
                        sche[row+i][j] = courseCode;
                }
        }
        // print schedule
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.printf("%-10s%s", sche[i][j], "");
            }
            System.out.println();
        }
    }

}


