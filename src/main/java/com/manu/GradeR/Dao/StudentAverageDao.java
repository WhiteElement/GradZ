package com.manu.GradeR.Dao;

import com.manu.GradeR.Grade.Grade;

import java.util.List;

public class StudentAverageDao {

    private Long Id;
    private String firstName;
    private String lastName;
    private List<Grade> grades;
    private Float writtenAverage;
    private Float oralAverage;
    private Float totalAverage;

    public StudentAverageDao(){}

    public StudentAverageDao(Long id, String firstName, String lastName, List<Grade> grades, Float writtenAverage, Float oralAverage, Float totalAverage) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grades = grades;
        this.writtenAverage = writtenAverage;
        this.oralAverage = oralAverage;
        this.totalAverage = totalAverage;
    }

    @Override
    public String toString() {
        return "StudentAverageDao{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public Float getWrittenAverage() {
        return writtenAverage;
    }

    public void setWrittenAverage(Float writtenAverage) {
        this.writtenAverage = writtenAverage;
    }

    public Float getOralAverage() {
        return oralAverage;
    }

    public void setOralAverage(Float oralAverage) {
        this.oralAverage = oralAverage;
    }

    public Float getTotalAverage() {
        return totalAverage;
    }

    public void setTotalAverage(Float totalAverage) {
        this.totalAverage = totalAverage;
    }
}
