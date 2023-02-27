package com.manu.GradeR.Dao;

import com.manu.GradeR.Grade.Grade;
import com.manu.GradeR.Grade.GradeRepository;
import com.manu.GradeR.GradeTest.GradeTest;
import com.manu.GradeR.GradeTest.GradeTestRepository;
import com.manu.GradeR.Student.Student;
import com.manu.GradeR.Student.StudentRepository;
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
