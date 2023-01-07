package com.manu.GradeR.GradeTest;

import com.manu.GradeR.SchoolClass.SchoolClass;
import com.manu.GradeR.SchoolClass.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GradeTestController {

    @Autowired
    GradeTestRepository gradeTestRepository;

    @Autowired
    SchoolClassRepository schoolClassRepository;

    @PostMapping("/schoolclasses/{schoolclassid}/newgradetest")
    public String createNewGradeTest(RedirectAttributes redirectAttributes,
                                     @PathVariable Long schoolclassid,
                                     GradeTest gradeTestFormData) {

        SchoolClass schoolClass = schoolClassRepository.getReferenceById(schoolclassid);
        GradeTest gradeTest = gradeTestFormData;
        gradeTest.assignToSchoolClass(schoolClass);
        gradeTestRepository.save(gradeTest);

        redirectAttributes.addAttribute("schoolclassid", schoolclassid);
        return "redirect:/schoolclasses/{schoolclassid}";
    }

    @GetMapping("/schoolclasses/{schoolclassid}/{gradetestid}")
    public String openGradeTestPage(Model model,
                                    @PathVariable Long schoolclassid,
                                    @PathVariable Long gradetestid) {

        return "grade_test";
    }
}



