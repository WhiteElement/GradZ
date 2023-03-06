package com.manu.GradeR.Grade;

import com.manu.GradeR.GradeTest.GradeTestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    GradeRepository gradeRepository;

    GradeService() {};

    public List<Grade> getAllGradesFromStudentByType(Long studentid, GradeTestType type) {
        return gradeRepository.getAllGradesFromStudentByType(studentid, type);
    }

    public void save(Grade grade) {
        gradeRepository.save(grade);
    }

    public Grade getGradeByStudentIdAndGradeTestId(Long studentid, Long gradetestid) {
        return gradeRepository.getGradeByStudentIdAndGradeTestId(studentid, gradetestid);
    }
}
