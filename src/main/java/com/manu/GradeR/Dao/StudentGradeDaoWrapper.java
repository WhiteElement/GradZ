package com.manu.GradeR.Dao;

import java.util.ArrayList;
import java.util.List;

public class StudentGradeDaoWrapper {

    private List<StudentGradeDao> StudentGradeDaoList;

    public StudentGradeDaoWrapper() {
    }

    public ArrayList<StudentGradeDao> getStudentGradeDaoList() {
        return (ArrayList<StudentGradeDao>) StudentGradeDaoList;
    }

    public void setStudentGradeDaoList(List<StudentGradeDao> studentGradeDaoList) {
        StudentGradeDaoList = studentGradeDaoList;
    }
}
