package com.manu.GradeR.Student;

import com.manu.GradeR.Grade.Grade;
import com.manu.GradeR.Grade.GradeRepository;
import com.manu.GradeR.GradeTest.GradeTest;
import com.manu.GradeR.SchoolClass.SchoolClass;
import com.manu.GradeR.SchoolClass.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SchoolClassRepository schoolClassRepository;

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    StudentService studentService;

    @GetMapping("schoolclasses/{schoolclassid}/students")
    public String showStudentList(Model model, @PathVariable Long schoolclassid) {

        SchoolClass currentClass = schoolClassRepository.getReferenceById(schoolclassid);
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

        SchoolClass schoolClass = schoolClassRepository.getReferenceById(schoolclassid);
        Student student = studentFormData;
        student.addToClass(schoolClass);
        studentRepository.save(student);

        if(schoolClass.getGradeTests().size() != 0) {
            for (GradeTest gradeTest : schoolClass.getGradeTests()) {
                //if notwendig?
                //if( !gradeRepository.GradeEntryExists(gradeTest.getId(), student.getId()) ) {
                    Grade grade = new Grade();
                    grade.setGradeTest(gradeTest);
                    grade.setStudent(student);
                    gradeRepository.save(grade);
               // }
            }
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
