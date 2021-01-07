package com.exceptions;

import java.security.GeneralSecurityException;
import java.util.LinkedList;
import java.util.List;

public class Group {
    private int maxOccupancy;
    private List<Student> students = new LinkedList<>();
    private double average;

    public Group(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public int getMaxOccupancy(){
        return maxOccupancy;
    }

    public void add(Student student) throws GroupOccupancyExceededException {
        if(students.size() < maxOccupancy)
            students.add(student);
        else
            throw new GroupOccupancyExceededException();

    }

    private boolean existe(Student studentAInsertar) {
        for(Student student : students){
            if(student.equals(studentAInsertar))
                return true;
        }
        return false;
    }

    public List<Student> getStudents() {
        return students;
    }

    public int availability() {
        return maxOccupancy - students.size();
    }

    public double getAverage() {
        double average = 0;
        for(Student student : students) {
            if(student.getGrade() != null)
                average += student.getGrade();
            else
                try {
                    throw new MissedGradeException();
                } catch (MissedGradeException e) {
                    e.printStackTrace();
                }
        }
        return average / students.size();
    }
}
