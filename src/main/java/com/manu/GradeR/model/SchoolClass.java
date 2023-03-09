package com.manu.GradeR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String className;
    private String subject;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schoolClass")
    private List<GradeTest> gradeTests = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schoolClass")
    private List<Student> students = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Float writtenWeighting;
    private Float oralWeighting;

    public SchoolClass() {
    }

    public SchoolClass(Long id, String className, String subject) {
        Id = id;
        this.className = className;
        this.subject = subject;
    }

    public List<GradeTest> getGradeTests() {
        return gradeTests;
    }

    public void setGradeTests(List<GradeTest> gradeTests) {
        this.gradeTests = gradeTests;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "Id=" + Id +
                ", className='" + className + '\'' +
                ", subject='" + subject + '\'' +
                ", gradeTests=" + gradeTests +
                ", students=" + students +
                '}';
    }

    public boolean areAllWeightingsFilledOut(GradeTestType requestedType) {
        for (GradeTest gradeTest : this.gradeTests) {
            if (gradeTest.getGradeType() == requestedType) {
                if(gradeTest.getWeighting() == null) {
                    return false;
                }
            }  else {continue;}
        }
        return true;
    }

    public Float getWrittenWeighting() {
        return writtenWeighting;
    }

    public void setWrittenWeighting(Float writtenWeighting) {
        this.writtenWeighting = writtenWeighting;
    }

    public Float getOralWeighting() {
        return oralWeighting;
    }

    public void setOralWeighting(Float oralWeighting) {
        this.oralWeighting = oralWeighting;
    }

}



