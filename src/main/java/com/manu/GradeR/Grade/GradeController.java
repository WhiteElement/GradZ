package com.manu.GradeR.Grade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GradeController {

    @Autowired
    GradeService gradeService;

    @PostMapping("/schoolclasses/{schoolclassid}/{gradetestid}/{studentid}")
    public ResponseEntity<?> addSingleGrade(Grade gradeFormData,
                                            @PathVariable Long gradetestid,
                                            @PathVariable Long studentid) {

        Grade grade = gradeService.getGradeByStudentIdAndGradeTestId(studentid, gradetestid);

        grade.setGrade(gradeFormData.getGrade());
        gradeService.save(grade);

        return ResponseEntity.ok().build();
    }

}
