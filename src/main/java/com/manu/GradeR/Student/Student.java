package com.manu.GradeR.Student;

import com.manu.GradeR.Grade.Grade;
import com.manu.GradeR.GradeTest.GradeTest;
import com.manu.GradeR.GradeTest.GradeTestType;
import com.manu.GradeR.SchoolClass.SchoolClass;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String firstName;
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    private SchoolClass schoolClass;

    @OneToMany ( cascade = CascadeType.ALL, mappedBy = "student")
    private List<Grade> grades = new ArrayList<>();

    @Transient
    private Float oralAverage;
    @Transient
    private Float writtenAverage;
    @Transient
    private Float totalAverage;

    public Student(Long id, String firstName, String lastName) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(){}

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

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public List<Grade> getGrades() {return grades; }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", schoolClass=" + schoolClass +
                ", grades=" + grades +
                '}';
    }

    public Float getOralAverage() {
        return oralAverage;
    }

    public void setOralAverage(Float oralAverage) {
        this.oralAverage = oralAverage;
    }

    public Float getWrittenAverage() {
        return writtenAverage;
    }

    public void setWrittenAverage(Float writtenAverage) {
        this.writtenAverage = writtenAverage;
    }

    public Float getTotalAverage() {
        return totalAverage;
    }

    public void setTotalAverage(Float totalAverage) {
        this.totalAverage = totalAverage;
    }

    public void addToClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

}
