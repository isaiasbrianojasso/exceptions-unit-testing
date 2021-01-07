package com.exceptions;

public class Student {
    private String id,nombre;//parametros de id y nombre de alumno
    private int average,grade=0;//defino el el grado entero
    public Student(String nombre,String id) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return nombre;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Integer getGrade(){
        return grade;
    }
    public void setAverage(int average) {
        this.average = average;
    }

    public int getAverage() {
        return average;
    }
}
