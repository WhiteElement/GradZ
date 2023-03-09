package com.manu.GradeR.service;

import com.manu.GradeR.Dao.StudentGradeDao;
import com.manu.GradeR.model.Grade;
import com.manu.GradeR.repository.GradeRepository;
import com.manu.GradeR.model.GradeTest;
import com.manu.GradeR.repository.GradeTestRepository;
import com.manu.GradeR.model.Student;
import com.manu.GradeR.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentGradeDaoService {

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GradeTestRepository gradeTestRepository;

    public void updateStudentsGradeFromDao(StudentGradeDao dao, Long gradeTestId) {

            Student student = studentRepository.findById(dao.getId()).get();
            Grade grade = (Grade) gradeRepository.getGradeFromStudentInGradeTest(student, gradeTestId).orElseGet(() -> new Grade());
            GradeTest gradeTest = gradeTestRepository.findById(gradeTestId).get();

            grade.setGradeTest(gradeTest);
            grade.setStudent(student);
            grade.setGrade(dao.getGrade());

            gradeRepository.save(grade);
    }
}
