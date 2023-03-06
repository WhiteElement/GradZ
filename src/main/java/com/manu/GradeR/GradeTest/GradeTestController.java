package com.manu.GradeR.GradeTest;

import com.manu.GradeR.Dao.StudentGradeDao;
import com.manu.GradeR.Dao.StudentGradeDaoService;
import com.manu.GradeR.Dao.StudentGradeDaoWrapper;
import com.manu.GradeR.SchoolClass.SchoolClass;
import com.manu.GradeR.SchoolClass.SchoolClassService;
import com.manu.GradeR.Student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class GradeTestController {

    @Autowired
    StudentGradeDaoService studentGradeDaoService;

    @Autowired
    SchoolClassService schoolClassService;

    @Autowired
    GradeTestService gradeTestService;

    @Autowired
    StudentService studentService;

    @PostMapping("/schoolclasses/{schoolclassid}/newgradetest")
    public String createNewGradeTest(RedirectAttributes redirectAttributes,
                                     @PathVariable Long schoolclassid,
                                     GradeTest gradeTestFormData) {

        SchoolClass schoolClass = schoolClassService.getReferenceById(schoolclassid);
        GradeTest gradeTest = gradeTestFormData;
        gradeTest.assignToSchoolClass(schoolClass);
        gradeTestService.save(gradeTest);

        gradeTestService.createGradesForAllStudents(schoolClass, gradeTest);

        redirectAttributes.addAttribute("schoolclassid", schoolclassid);
        return "redirect:/schoolclasses/{schoolclassid}";
    }

    @GetMapping("/schoolclasses/{schoolclassid}/{gradetestid}")
    public String openGradeTestPage(Model model,
                                    @PathVariable Long schoolclassid,
                                    @PathVariable Long gradetestid) {

        GradeTest gradeTest = gradeTestService.findById(gradetestid);

        StudentGradeDaoWrapper studentGradeDaoWrapper = new StudentGradeDaoWrapper();
        studentGradeDaoWrapper.setStudentGradeDaoList(studentService.getStudentsWithGradesFromSpecificGradeTest(gradetestid,schoolclassid));
        model.addAttribute("studentDaosWrapper", studentGradeDaoWrapper);
        model.addAttribute("currentGradeTest", gradeTest);
        model.addAttribute("currentClass", schoolClassService.findById(schoolclassid));
        return "grade_test";
    }

    @PostMapping("/schoolclasses/{schoolclassid}/{gradetestid}")
    public String updateGradesInGradeTest(StudentGradeDaoWrapper studentGradeDaoWrapper, Model model,
                                          @PathVariable Long gradetestid,
                                          @PathVariable Long schoolclassid) {

        List<StudentGradeDao> studentGradeDaos = studentGradeDaoWrapper.getStudentGradeDaoList();

        studentGradeDaos.forEach(s -> studentGradeDaoService.updateStudentsGradeFromDao(s,gradetestid));
        GradeTest gradeTest = gradeTestService.findById(gradetestid);

        model.addAttribute("currentGradeTest", gradeTest);
        model.addAttribute("studentDaosWrapper", studentGradeDaoWrapper);
        model.addAttribute("currentClass", schoolClassService.findById(schoolclassid));

        return "grade_test";
    }

    @PostMapping("/schoolclasses/{schoolclassid}/{gradetestid}/class")
    public ResponseEntity updateGradeTest(@RequestBody GradeTest gradeTestformData) {

        GradeTest gradeTest = gradeTestService.findById(gradeTestformData.getId());
        gradeTest.setTestName(gradeTestformData.getTestName());
        gradeTest.setTestDescription(gradeTestformData.getTestDescription());
        gradeTestService.save(gradeTest);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/schoolclasses/{schoolclassid}/{gradetestid}")
    public ResponseEntity deleteGradeTest(@PathVariable Long gradetestid) {
        gradeTestService.delete(gradetestid);

        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping("/schoolclasses/{schoolclassid}/weightings")
    public ResponseEntity updateWeightingOnGradeTest(GradeTest gradeTestFormData) {

        GradeTest gradeTest = gradeTestService.getReferenceById(gradeTestFormData.getId());
        gradeTest.setWeighting(gradeTestFormData.getWeighting());
        gradeTestService.save((gradeTest));

        return new ResponseEntity(HttpStatus.OK);
    }
}



