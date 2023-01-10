package com.manu.GradeR.SchoolClass;

import com.manu.GradeR.Dao.StudentAllGradesDao;
import com.manu.GradeR.Dao.StudentGradeDao;
import com.manu.GradeR.Grade.Grade;
import com.manu.GradeR.GradeTest.GradeTest;
import com.manu.GradeR.GradeTest.GradeTestRepository;
import com.manu.GradeR.GradeTest.GradeTestType;
import com.manu.GradeR.Student.Student;
import com.manu.GradeR.Student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class SchoolClassController {

    @Autowired
    SchoolClassRepository schoolClassRepository;

    @Autowired
    GradeTestRepository gradeTestRepository;

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/")
    public String showSchoolClassPage(Model model) {

        List<SchoolClass> allSchoolClasses = schoolClassRepository.findAll();

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

    @GetMapping("/schoolclasses/{id}")
    public String showSingleClass(@PathVariable Long id, Model model) {

        SchoolClass currentSchoolClass = schoolClassRepository.findById(id).get();

        List<GradeTest> writtenGradeTests = gradeTestRepository.findByGradeTypeAndSchoolClass(GradeTestType.WRITTEN, currentSchoolClass);

        List<Student> students = studentRepository.findAllFromClassOrderByLastName(currentSchoolClass);


//
//        StudentAllGradesDao teststudent = new StudentAllGradesDao();
//        teststudent.setFirstName("Manuel");
//        teststudent.setLastName("Brusche");
//        teststudent.setWrittenGrades(new ArrayList<>(Arrays.asList(new Grade(2f),new Grade(null), new Grade(3f))));
//
//
//        List<Long> writtenSortOrder = new ArrayList<>();
//        for(GradeTest test : writtenGradeTests) {
//            writtenSortOrder.add(test.getId());
//        }
//
//
//
//        for (Student student : students) {
//            Collections.sort(student.getGrades(), Comparator.comparing(item -> writtenSortOrder.indexOf(item)));
//        }



        model.addAttribute("newGradeTest", new GradeTest());
        model.addAttribute("currentSchoolClass", currentSchoolClass);
        model.addAttribute("writtenGradeTests", writtenGradeTests);
        model.addAttribute("oralGradeTests", gradeTestRepository.findByGradeTypeAndSchoolClass(GradeTestType.ORAL, currentSchoolClass));
        model.addAttribute("students", students);
//        model.addAttribute("test", teststudent);

        return "single_class";
    }


}

