package com.manu.GradeR.Dao;

public class StudentGradeDao {

    private Long Id;
    private String firstName;
    private String lastName;
    private Float Grade;

    public StudentGradeDao(Long id, String firstName, String lastName, Float grade) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        Grade = grade;
    }

    public StudentGradeDao() {
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

    public Float getGrade() {
        return Grade;
    }

    public void setGrade(Float grade) {
        Grade = grade;
    }
}
