package com.exceptions;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class StatisticsTest {


    @Test
    public void testStudentConstructor() {
        // Given:
        Student juan = new Student("Juan", "97249");
        Student ana = new Student("Ana", "98257");

        // Then:
        assertEquals("Name is not the expected", "Juan", juan.getName());
        assertEquals("Id is not the expected", "97249", juan.getId());
        assertEquals("Name is not the expected", "Ana", ana.getName());
        assertEquals("Id is not the expected", "98257", ana.getId());
    }

    @Test
    public void testGroupConstructor() {
        // Given:
        Group group = new Group(10);

        // Then:
        assertEquals("Max occupancy is not correct", 10, group.getMaxOccupancy());
    }

    @Test
    public void given2StudentsAddedToAGroup_whenGetStudents_thenListHasTwoElements() throws GroupOccupancyExceededException {
        // Given:
        Student juan = new Student("Juan", "97249");
        Student ana = new Student("Ana", "98257");
        Group group = new Group(5);

        group.add(juan);
        group.add(ana);

        // When:
        List<Student> studentList = group.getStudents();

        // Then:
        assertEquals("Students in group should be 2", 2, studentList.size());
        assertEquals("Availability for group should be 3", 3, group.availability());
    }

    @Test(expected = GroupOccupancyExceededException.class)
    public void givenOccupancyOf3_when4StudentsAdded_thenExceptionIsThrown() {
        // Given:
        Student juan = new Student("Juan", "97249");
        Student ana = new Student("Ana", "98257");
        Student mario = new Student("Juan", "98249");
        Student lucia = new Student("Ana", "92556");
        Group group = new Group(3);

        try {
            group.add(juan);
        } catch (GroupOccupancyExceededException e) {
            e.printStackTrace();
        }
        try {
            group.add(ana);
        } catch (GroupOccupancyExceededException e) {
            e.printStackTrace();
        }
        try {
            group.add(mario);
        } catch (GroupOccupancyExceededException e) {
            e.printStackTrace();
        }

        // When:
        try {
            group.add(lucia);
        } catch (GroupOccupancyExceededException e) {
            e.printStackTrace();
        }

        // Then: GroupOccupancyExceededException was thrown
    }

    @Test(expected = GroupOccupancyExceededException.class)
    public void givenOccupancyOf3_whenStudentsDuplicatedAdded_thenStudentIsNotDuplicatedOnGroup() throws GroupOccupancyExceededException {
        // Given:
        Student juan = new Student("Juan", "97249");
        Student ana = new Student("Ana", "98257");
        Student mario = new Student("Juan", "98249");
        Group group = new Group(3);

        group.add(juan);
        group.add(ana);
        group.add(mario);

        // When:
        group.add(ana);

        // Then:
        List<Student> studentList = group.getStudents();
        assertEquals("Students in group should be 3", 3, studentList.size());
        assertEquals("Availability for group should be 0", 0, group.availability());
    }

    @Test
    public void givenStudentsWithGrade_whenAverageIsRequested_thenAverageIsRetrieved() throws GroupOccupancyExceededException {
        // Given:
        Student juan = new Student("Juan", "97249");
        Student ana = new Student("Ana", "98257");
        Student mario = new Student("Juan", "98249");
        Group group = new Group(3);

        group.add(juan);
        group.add(ana);
        group.add(mario);

        juan.setGrade(98);
        ana.setGrade(77);
        mario.setGrade(86);

        // When:
        double average = group.getAverage();

        // Then:
        assertEquals("Average is not the expected", 87, average, 0.1);
    }

    @Test(expected = MissedGradeException.class)
    public void givenNotAllStudentsWithGrade_whenAverageIsRequested_thenExceptionIsThrown() throws GroupOccupancyExceededException {
        // Given:
        Student juan = new Student("Juan", "97249");
        Student ana = new Student("Ana", "98257");
        Student mario = new Student("Juan", "98249");
        Group group = new Group(3);

        group.add(juan);
        group.add(ana);
        group.add(mario);

        juan.setGrade(98);
        ana.setGrade(77);

        // When:
        double average = group.getAverage();

        // Then: MissedGradeException was thrown

    }
}
