package com.manu.GradeR.Grade;

import com.manu.GradeR.GradeTest.GradeTestType;
import com.manu.GradeR.Student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    GradeRepository gradeRepository;

    GradeService() {};

    public List<Grade> getAllGradesFromStudentByType(Student student, GradeTestType type) {
        return gradeRepository.getAllGradesFromStudentByType(student, type);
    }

}
