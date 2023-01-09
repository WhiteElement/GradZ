package com.manu.GradeR.Grade;

import com.manu.GradeR.GradeTest.GradeTest;
import com.manu.GradeR.GradeTest.GradeTestRepository;
import com.manu.GradeR.Student.Student;
import com.manu.GradeR.Student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GradeController {

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GradeTestRepository gradeTestRepository;

    @PostMapping("/schoolclasses/{schoolclassid}/{gradetestid}/{studentid}")
    public ResponseEntity<?> addSingleGrade(Grade gradeFormData,
                                            @PathVariable Long gradetestid,
                                            @PathVariable Long studentid) {
        System.out.println(gradeFormData.getGrade());
        Grade grade = gradeFormData;
        Student student = studentRepository.getReferenceById(studentid);
        GradeTest gradeTest = gradeTestRepository.getReferenceById(gradetestid);

        grade.setStudent(student);
        grade.setGradeTest(gradeTest);

        gradeRepository.save(grade);

        return ResponseEntity.ok().build();
    }


}
