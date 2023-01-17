package com.manu.GradeR.GradeTest;

import com.manu.GradeR.Dao.StudentGradeDao;
import com.manu.GradeR.Dao.StudentGradeDaoService;
import com.manu.GradeR.Dao.StudentGradeDaoWrapper;
import com.manu.GradeR.Grade.Grade;
import com.manu.GradeR.Grade.GradeRepository;
import com.manu.GradeR.SchoolClass.SchoolClass;
import com.manu.GradeR.SchoolClass.SchoolClassRepository;
import com.manu.GradeR.SchoolClass.SchoolClassService;
import com.manu.GradeR.Student.Student;
import com.manu.GradeR.Student.StudentRepository;
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
    GradeTestRepository gradeTestRepository;

    @Autowired
    SchoolClassRepository schoolClassRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentGradeDaoService studentGradeDaoService;

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    SchoolClassService schoolClassService;

    @Autowired
    GradeTestService gradeTestService;

    @PostMapping("/schoolclasses/{schoolclassid}/newgradetest")
    public String createNewGradeTest(RedirectAttributes redirectAttributes,
                                     @PathVariable Long schoolclassid,
                                     GradeTest gradeTestFormData) {

        SchoolClass schoolClass = schoolClassRepository.getReferenceById(schoolclassid);
        GradeTest gradeTest = gradeTestFormData;
        gradeTest.assignToSchoolClass(schoolClass);
        gradeTestRepository.save(gradeTest);

        for (Student student : schoolClass.getStudents()) {
            Grade grade = new Grade();
            grade.setGradeTest(gradeTest);
            grade.setStudent(student);
            gradeRepository.save(grade);
        }

        redirectAttributes.addAttribute("schoolclassid", schoolclassid);
        return "redirect:/schoolclasses/{schoolclassid}";
    }

    @GetMapping("/schoolclasses/{schoolclassid}/{gradetestid}")
    public String openGradeTestPage(Model model,
                                    @PathVariable Long schoolclassid,
                                    @PathVariable Long gradetestid) {

        GradeTest gradeTest = gradeTestRepository.findById(gradetestid).get();

        StudentGradeDaoWrapper studentGradeDaoWrapper = new StudentGradeDaoWrapper();
        studentGradeDaoWrapper.setStudentGradeDaoList(studentRepository.getStudentsWithGradesFromSpecificGradeTest(gradetestid,schoolclassid));
        model.addAttribute("studentDaosWrapper", studentGradeDaoWrapper);
        model.addAttribute("currentGradeTest", gradeTest);
        return "grade_test";
    }

    @PostMapping("/schoolclasses/{schoolclassid}/{gradetestid}")
    public String updateGradesInGradeTest(StudentGradeDaoWrapper studentGradeDaoWrapper, Model model,
                                          @PathVariable Long gradetestid) {

        List<StudentGradeDao> studentGradeDaos = studentGradeDaoWrapper.getStudentGradeDaoList();

        studentGradeDaos.forEach(s -> studentGradeDaoService.updateStudentsGradeFromDao(s,gradetestid));
        GradeTest gradeTest = gradeTestService.findById(gradetestid);

        model.addAttribute("currentGradeTest", gradeTest);
        model.addAttribute("studentDaosWrapper", studentGradeDaoWrapper);
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

    @GetMapping("/schoolclasses/{schoolclassid}/weightings")
    public String showWeightings(Model model, @PathVariable Long schoolclassid) {

        SchoolClass schoolClass = schoolClassRepository.findById(schoolclassid).get();
        List<GradeTest> writtenGradeTests = gradeTestRepository.findByGradeTypeAndSchoolClass(GradeTestType.WRITTEN, schoolClass);
        List<GradeTest> oralGradeTests = gradeTestRepository.findByGradeTypeAndSchoolClass(GradeTestType.ORAL, schoolClass);

        model.addAttribute("currentSchoolClass", schoolClass);
        model.addAttribute("writtenGradeTests", writtenGradeTests);
        model.addAttribute("oralGradeTests", oralGradeTests);

        return "weightings";
    }

    @PostMapping("/schoolclasses/{schoolclassid}/weightings")
    public ResponseEntity updateWeightingOnGradeTest(GradeTest gradeTestFormData) {

        GradeTest gradeTest = gradeTestRepository.getReferenceById(gradeTestFormData.getId());
        gradeTest.setWeighting(gradeTestFormData.getWeighting());
        gradeTestRepository.save((gradeTest));

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/schoolclasses/{schoolclassid}/weightings/class")
    public ResponseEntity updateWeightingOnSchoolClass (@PathVariable Long schoolclassid,
                                                        @RequestBody SchoolClass schoolClassFormData) {
        SchoolClass schoolClass = schoolClassService.getReferenceById(schoolclassid);
        schoolClass.setWrittenWeighting(schoolClassFormData.getWrittenWeighting());
        schoolClass.setOralWeighting(schoolClassFormData.getOralWeighting());

        schoolClassService.save(schoolClass);
        return new ResponseEntity(HttpStatus.OK);
    }
}



