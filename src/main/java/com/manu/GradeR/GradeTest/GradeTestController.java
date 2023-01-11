package com.manu.GradeR.GradeTest;

import com.manu.GradeR.Dao.StudentGradeDao;
import com.manu.GradeR.Dao.StudentGradeDaoService;
import com.manu.GradeR.Dao.StudentGradeDaoWrapper;
import com.manu.GradeR.Grade.Grade;
import com.manu.GradeR.Grade.GradeRepository;
import com.manu.GradeR.SchoolClass.SchoolClass;
import com.manu.GradeR.SchoolClass.SchoolClassRepository;
import com.manu.GradeR.Student.Student;
import com.manu.GradeR.Student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

        model.addAttribute("studentDaosWrapper", studentGradeDaoWrapper);
        return "grade_test";
    }
}



