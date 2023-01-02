package com.manu.GradeR.SchoolClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SchoolClassController {

    @Autowired
    SchoolClassRepository schoolClassRepository;

    @GetMapping("/")
    public String showSchoolClassPage(Model model) {

        List<SchoolClass> allSchoolClasses = schoolClassRepository.findAll();
        allSchoolClasses.forEach(s -> System.out.println(s.toString()));

        model.addAttribute("allSchoolClasses", allSchoolClasses);
        model.addAttribute("newSchoolClass", new SchoolClass());

        return "index";
    }

    @PostMapping("/")
    public String newSchoolClass(RedirectAttributes redirectAttributes, SchoolClass schoolClassFormData) {
        schoolClassRepository.save(schoolClassFormData);

        return "redirect:/";
    }

    @PostMapping("/updateSchoolClass")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateSchoolClass(SchoolClass schoolClassFormData) {
        schoolClassRepository.save(schoolClassFormData);
    }
}

