package com.manu.GradeR.Dao;

import com.manu.GradeR.model.Grade;
import com.manu.GradeR.service.GradeService;
import com.manu.GradeR.model.GradeTestType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class StudentAverageDao {

    @Autowired
    GradeService gradeService;

    private Long Id;
    private String firstName;
    private String lastName;
    private List<Grade> writtenGrades = new ArrayList<>();
    private List<Grade> oralGrades = new ArrayList<>();
    private Float writtenAverage;
    private Float oralAverage;
    private Float totalAverage;

    public StudentAverageDao(){}

    public StudentAverageDao(Long id, String firstName, String lastName, List<Grade> writtenGrades, List<Grade> oralGrades, Float writtenAverage, Float oralAverage, Float totalAverage) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.writtenGrades = writtenGrades;
        this.oralGrades = oralGrades;
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

    public List<Grade> getWrittenGrades() {
        return writtenGrades;
    }

    public void setWrittenGrades(List<Grade> writtenGrades) {
        this.writtenGrades = writtenGrades;
    }

    public List<Grade> getOralGrades() {
        return oralGrades;
    }

    public void setOralGrades(List<Grade> oralGrades) {
        this.oralGrades = oralGrades;
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

    public void transferAndOrderGrades(List<Grade> grades, GradeTestType type) {

        if(type == GradeTestType.WRITTEN){
            for(Grade grade : grades) {
                this.writtenGrades.add(grade);
            }
        } else {
            for(Grade grade : grades) {
                this.oralGrades.add(grade);
            }
        }
    }


}
