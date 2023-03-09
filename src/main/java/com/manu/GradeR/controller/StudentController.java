package com.manu.GradeR.controller;

import com.manu.GradeR.model.Grade;
import com.manu.GradeR.model.Student;
import com.manu.GradeR.service.GradeService;
import com.manu.GradeR.model.GradeTest;
import com.manu.GradeR.model.SchoolClass;
import com.manu.GradeR.service.SchoolClassService;
import com.manu.GradeR.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    SchoolClassService schoolClassService;

    @Autowired
    GradeService gradeService;


    @Autowired
    StudentService studentService;

    @GetMapping("schoolclasses/{schoolclassid}/students")
    public String showStudentList(Model model, @PathVariable Long schoolclassid) {

        SchoolClass currentClass = schoolClassService.getReferenceById(schoolclassid);
        List<Student> students = studentService.getAllStudentsFromClassOrdered(currentClass);
        model.addAttribute("students", students);
        model.addAttribute("currentClass", currentClass);

        return "student_list";
    }

    @PostMapping("schoolclasses/{schoolclassid}/students")
    public ResponseEntity updateStudent(@RequestBody Student studentFormData) {

        Student student = studentService.findById(studentFormData.getId());
        student.setId(studentFormData.getId());
        student.setFirstName(studentFormData.getFirstName());
        student.setLastName(studentFormData.getLastName());
        studentService.save(student);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("schoolclasses/{schoolclassid}/students")
    public ResponseEntity deleteStudent(@RequestParam("studentid") Long studentid) {

        studentService.deleteById(studentid);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("schoolclasses/{schoolclassid}/newstudent")
    public ResponseEntity newStudent(@PathVariable Long schoolclassid, Student studentFormData) {

        SchoolClass schoolClass = schoolClassService.getReferenceById(schoolclassid);
        Student student = studentFormData;
        student.addToClass(schoolClass);
        studentService.save(student);

        if(schoolClass.getGradeTests().size() != 0) {
            for (GradeTest gradeTest : schoolClass.getGradeTests()) {
                    Grade grade = new Grade();
                    grade.setGradeTest(gradeTest);
                    grade.setStudent(student);
                    gradeService.save(grade);
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
