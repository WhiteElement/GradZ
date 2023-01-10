package com.manu.GradeR.Dao;

import com.manu.GradeR.Grade.Grade;

import java.util.List;

public class StudentAllGradesDao {

    private String firstName;
    private String lastName;

    private List<Grade> writtenGrades;

    public StudentAllGradesDao() {
    }

    public StudentAllGradesDao(String firstName, String lastName, List<Grade> writtenGrades) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.writtenGrades = writtenGrades;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Grade> getWrittenGrades() {
        return writtenGrades;
    }

    public void setWrittenGrades(List<Grade> writtenGrades) {
        this.writtenGrades = writtenGrades;
    }
}
