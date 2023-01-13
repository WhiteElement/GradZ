package com.manu.GradeR.Student;

import com.manu.GradeR.Dao.StudentAverageDao;
import com.manu.GradeR.Grade.Grade;
import com.manu.GradeR.Grade.GradeRepository;
import com.manu.GradeR.Grade.GradeService;
import com.manu.GradeR.GradeTest.GradeTest;
import com.manu.GradeR.GradeTest.GradeTestService;
import com.manu.GradeR.GradeTest.GradeTestType;
import com.manu.GradeR.SchoolClass.SchoolClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    GradeService gradeService;

    @Autowired
    GradeTestService gradeTestService;

    StudentService() {
    }

    public List<Student> getAllStudentsFromClassOrdered(SchoolClass schoolClass){
        return studentRepository.findAllFromClassOrderByLastName(schoolClass);
    }


    public List<StudentAverageDao> getAllStudentsWithAverages(SchoolClass schoolClass) {

        List<Student> students = getAllStudentsFromClassOrdered(schoolClass);
        List<StudentAverageDao> studentAverageDaos = new ArrayList<>();

        //alle schr noten ausgefüllt
        if(schoolClass.areAllWeightingsFilledOut(GradeTestType.WRITTEN)) {

            HashMap<Long,Float> weightingsmap = gradeTestService.getWeightingsMap(schoolClass, GradeTestType.WRITTEN);
            Float weightingsTotal = 0f;
            for(Long key : weightingsmap.keySet()) {
                weightingsTotal += weightingsmap.get(key);
            }

            for(Student student : students) {
                StudentAverageDao studentAverageDao = new StudentAverageDao();
                studentAverageDao.setId(student.getId());
                studentAverageDao.setFirstName(student.getFirstName());
                studentAverageDao.setLastName(student.getLastName());
                studentAverageDao.setGrades(student.getGrades());

                Float gradeSum = 0f;
                boolean hasEmptyGrades = false;

                for(Grade grade : gradeRepository.getAllGradesFromStudentByType(student, GradeTestType.WRITTEN)) {
                    //calc written avg
                    if(grade.getGrade() == null) {
                        hasEmptyGrades = true;
                        break;
                    } else {
                        gradeSum += weightingsmap.get(grade.getGradeTest().getId()) * grade.getGrade();
                    }
                }
                if(hasEmptyGrades) {
                    studentAverageDao.setWrittenAverage(null);
                } else {
                    studentAverageDao.setWrittenAverage(gradeSum / weightingsTotal);
                }
                studentAverageDaos.add(studentAverageDao);
                System.out.println(studentAverageDao.toString());
            }
        }
        return studentAverageDaos;
    }


//
//
//    public void calcGradeAverages() {
//
//        if(schoolClass.areAllWeightingsFilledOut(GradeTestType.WRITTEN)) {
//
//            for(GradeTest gradeTest : schoolClass.getGradeTests()) {
//                float gradeSum;
//                if(gradeTest.getGradeType() == GradeTestType.WRITTEN) {
//                    for(Grade grade : this.grades
//
//                    gradeTest.getId()
//                }
//            }
//
//
//        } else {
//            this.writtenAverage = null;
//        }
//
//        if(schoolClass.areAllWeightingsFilledOut(GradeTestType.ORAL)) {
//            //calc average
//        } else {
//            this.oralAverage = null;
//        }
//
//        if ((this.oralAverage != null) && (this.writtenAverage != null)) {
//            //calc totalAverage
//
//        } else {
//            this.totalAverage = null;
//        }
//    }
}
