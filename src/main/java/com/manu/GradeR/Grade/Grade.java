package com.manu.GradeR.Grade;

import com.manu.GradeR.GradeTest.GradeTest;
import com.manu.GradeR.Student.Student;
import jakarta.persistence.*;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private Float Grade;

    @ManyToOne (fetch = FetchType.LAZY)
    private GradeTest gradeTest;

    @ManyToOne (fetch = FetchType.LAZY)
    private Student student;

    public Grade(Long id, Float grade, GradeTest gradeTest) {
        Id = id;
        Grade = grade;
        this.gradeTest = gradeTest;
    }

    public Grade(Float grade) {
        Grade = grade;
    }

    public Grade(){}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Float getGrade() {
        return Grade;
    }

    public void setGrade(Float grade) {
        Grade = grade;
    }

    public GradeTest getGradeTest() {
        return gradeTest;
    }

    public void setGradeTest(GradeTest gradeTest) {
        this.gradeTest = gradeTest;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
