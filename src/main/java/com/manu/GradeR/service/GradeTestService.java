package com.manu.GradeR.service;

import com.manu.GradeR.model.Grade;
import com.manu.GradeR.model.GradeTest;
import com.manu.GradeR.model.GradeTestType;
import com.manu.GradeR.model.SchoolClass;
import com.manu.GradeR.model.Student;
import com.manu.GradeR.repository.GradeTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class GradeTestService {

    @Autowired
    GradeTestRepository gradeTestRepository;

    @Autowired
    GradeService gradeService;

    public GradeTest findById(Long gradetestid) {
        return gradeTestRepository.findById(gradetestid).get();
    }

    public HashMap<Long, Float> getWeightingsMap(SchoolClass schoolClass, GradeTestType type) {
        List<GradeTest> allGradeTests = gradeTestRepository.findByGradeTypeAndSchoolClass(type,schoolClass);

        HashMap<Long, Float> weightingsmap = new HashMap<>();
        for(GradeTest gradeTest : allGradeTests) {
            weightingsmap.put(gradeTest.getId(),gradeTest.getWeighting());
        }
        return weightingsmap;
    }

    public void save(GradeTest gradeTest) {
        gradeTestRepository.save(gradeTest);
    }

    public void delete(Long id) {
        gradeTestRepository.deleteById(id);
    }

    public List<GradeTest> findByGradeTypeAndSchoolClass(GradeTestType type, SchoolClass schoolClass) {
        return gradeTestRepository.findByGradeTypeAndSchoolClass(type, schoolClass);
    }

    public void createGradesForAllStudents(SchoolClass schoolClass, GradeTest gradeTest) {

        for (Student student : schoolClass.getStudents()) {
            Grade grade = new Grade();
            grade.setGradeTest(gradeTest);
            grade.setStudent(student);
            gradeService.save(grade);
        }
    }

    public GradeTest getReferenceById(Long id) {
        return gradeTestRepository.getReferenceById(id);
    }
}
