package com.manu.GradeR.SchoolClass;

import com.manu.GradeR.GradeTest.GradeTest;
import com.manu.GradeR.GradeTest.GradeTestRepository;
import com.manu.GradeR.GradeTest.GradeTestService;
import com.manu.GradeR.GradeTest.GradeTestType;
import com.manu.GradeR.Student.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@Controller
public class SchoolClassController {

    @Autowired
    GradeTestService gradeTestService;

    @Autowired
    StudentService studentService;

    @Autowired
    SchoolClassService schoolClassService;

    @GetMapping("/")
    public String showSchoolClassPage(Model model) {

        List<SchoolClass> allSchoolClasses = schoolClassService.findAll();

        model.addAttribute("allSchoolClasses", allSchoolClasses);
        model.addAttribute("newSchoolClass", new SchoolClass());

        return "index";
    }

    @PostMapping("/")
    public String newSchoolClass(SchoolClass schoolClassFormData, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/";
        }

        schoolClassService.save(schoolClassFormData);
        return "redirect:/";
    }

    @PostMapping("/updateSchoolClass")
    public ResponseEntity updateSchoolClass(@RequestBody SchoolClass schoolClassFormData) {
        SchoolClass schoolClass = schoolClassService.getReferenceById(schoolClassFormData.getId());
        schoolClass.setClassName(schoolClassFormData.getClassName());
        schoolClass.setSubject(schoolClassFormData.getSubject());
        schoolClassService.save(schoolClass);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/del")
    public ResponseEntity deleteSchoolClass(@RequestParam("id") Long Id) {
        schoolClassService.deleteById(Id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/schoolclasses/{id}")
    public String showSingleClass(@PathVariable Long id, Model model) {
        SchoolClass currentSchoolClass = schoolClassService.getReferenceById(id);

        model.addAttribute("studentavg", studentService.getAllStudentsWithAverages(currentSchoolClass));
        model.addAttribute("newGradeTest", new GradeTest());
        model.addAttribute("currentSchoolClass", currentSchoolClass);
        model.addAttribute("writtenGradeTests", gradeTestService.findByGradeTypeAndSchoolClass(GradeTestType.WRITTEN, currentSchoolClass));
        model.addAttribute("oralGradeTests", gradeTestService.findByGradeTypeAndSchoolClass(GradeTestType.ORAL, currentSchoolClass));

        return "single_class";
    }


}

