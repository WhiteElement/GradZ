package com.manu.GradeR.Student;

import com.manu.GradeR.Dao.StudentAverageDao;
import com.manu.GradeR.Dao.StudentGradeDao;
import com.manu.GradeR.Grade.Grade;
import com.manu.GradeR.Grade.GradeRepository;
import com.manu.GradeR.Grade.GradeService;
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

        for(Student student : students) {
            StudentAverageDao studentAverageDao = new StudentAverageDao();
            studentAverageDao.setId(student.getId());
            studentAverageDao.setFirstName(student.getFirstName());
            studentAverageDao.setLastName(student.getLastName());
            studentAverageDao.transferAndOrderGrades(gradeService.getAllGradesFromStudentByType(student.getId(), GradeTestType.WRITTEN), GradeTestType.WRITTEN);
            studentAverageDao.transferAndOrderGrades(gradeService.getAllGradesFromStudentByType(student.getId(), GradeTestType.ORAL), GradeTestType.ORAL);
            studentAverageDaos.add(studentAverageDao);
        }

        if(schoolClass.areAllWeightingsFilledOut(GradeTestType.WRITTEN)) {
            calculateAverage(schoolClass, studentAverageDaos, GradeTestType.WRITTEN);
        }
        if(schoolClass.areAllWeightingsFilledOut(GradeTestType.ORAL)) {
            calculateAverage(schoolClass, studentAverageDaos, GradeTestType.ORAL);
        }
        return studentAverageDaos;
    }

    private void calculateAverage(SchoolClass schoolClass, List<StudentAverageDao> studentAverageDaos, GradeTestType type) {
        HashMap<Long,Float> weightingsmap = gradeTestService.getWeightingsMap(schoolClass, type);
        Float weightingsTotal = 0f;
        for(Long key : weightingsmap.keySet()) {
            weightingsTotal += weightingsmap.get(key);
        }

        for(StudentAverageDao student : studentAverageDaos) {

            Float gradeSum = 0f;
            boolean hasEmptyGrades = false;

            List<Grade> grades = gradeRepository.getAllGradesFromStudentByType(student.getId(), type);

            if(!studentHasGrades(grades)) {
                if(type == GradeTestType.WRITTEN) {
                    student.setWrittenAverage(null);
                } else {
                    student.setOralAverage(null);
                }
            } else {
                for(Grade grade : grades) {
                    if(grade.getGrade() == null) {
                        hasEmptyGrades = true;
                        break;
                    } else {
                        gradeSum += weightingsmap.get(grade.getGradeTest().getId()) * grade.getGrade();
                    }
                }

                if (type == GradeTestType.WRITTEN) {
                    if(hasEmptyGrades) {
                        student.setWrittenAverage(null);
                    } else {
                        student.setWrittenAverage(gradeSum / weightingsTotal);
                    }
                } else {
                    if(hasEmptyGrades) {
                        student.setOralAverage(null);
                    } else {
                        student.setOralAverage(gradeSum / weightingsTotal);
                    }
                }
                student.setTotalAverage(calculateTotalAverage(student, schoolClass));
            }


        }
    }

    private boolean studentHasGrades(List<Grade> grades) {
        if(grades.size() == 0) {
            return false;
        }
        return true;
    }

    private Float calculateTotalAverage(StudentAverageDao student, SchoolClass schoolClass) {
        if(schoolClass.getWrittenWeighting() == null || schoolClass.getOralWeighting() == null) {
            return null;
        }

        if(student.getOralAverage() == null || student.getWrittenAverage() == null ) {
            return null;
        }

        return ((student.getWrittenAverage() * schoolClass.getWrittenWeighting()) +
                (student.getOralAverage() * schoolClass.getOralWeighting())) /
                (schoolClass.getWrittenWeighting() + schoolClass.getOralWeighting());
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).get();
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public void deleteById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public List<StudentGradeDao> getStudentsWithGradesFromSpecificGradeTest(Long gradetestid, Long schoolclassid) {
        return studentRepository.getStudentsWithGradesFromSpecificGradeTest(gradetestid, schoolclassid);
    }
}
