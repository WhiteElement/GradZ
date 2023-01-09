package com.manu.GradeR.Grade;

import com.manu.GradeR.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("from Grade where student = :student and gradeTest.Id = :gradetestid")
    public Optional getGradeFromStudentInGradeTest(@Param("student") Student student,
                                                   @Param("gradetestid") Long gradetestid);
}
