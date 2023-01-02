package com.manu.GradeR.Student;

import com.manu.GradeR.Grade.Grade;
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
}
