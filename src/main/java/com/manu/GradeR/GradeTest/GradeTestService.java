package com.manu.GradeR.GradeTest;

import com.manu.GradeR.SchoolClass.SchoolClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class GradeTestService {

    @Autowired
    GradeTestRepository gradeTestRepository;

    public HashMap<Long, Float> getWeightingsMap(SchoolClass schoolClass, GradeTestType type) {
        List<GradeTest> allGradeTests = gradeTestRepository.findByGradeTypeAndSchoolClass(type,schoolClass);

        HashMap<Long, Float> weightingsmap = new HashMap<>();
        for(GradeTest gradeTest : allGradeTests) {
            weightingsmap.put(gradeTest.getId(),gradeTest.getWeighting());
        }
        return weightingsmap;
    }
}
