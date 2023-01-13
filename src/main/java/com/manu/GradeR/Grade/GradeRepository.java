package com.manu.GradeR.Grade;

import com.manu.GradeR.GradeTest.GradeTest;
import com.manu.GradeR.GradeTest.GradeTestType;
import com.manu.GradeR.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("from Grade where student = :student and gradeTest.Id = :gradetestid")
    public Optional getGradeFromStudentInGradeTest(@Param("student") Student student,
                                                   @Param("gradetestid") Long gradetestid);

    @Query("from Grade where student.Id = :studentid and gradeTest.Id = :gradetestid")
    public Grade getGradeByStudentIdAndGradeTestId(@Param("studentid") Long studentid,
                                                   @Param("gradetestid") Long gradetestid);

    @Query("select count(g) > 0 from Grade g where student.Id = :student and gradeTest.Id = :gradetestid")
    Boolean GradeEntryExists(@Param("gradetestid") Long gradeTestId, @Param("student") Long studentid);

    @Query("from Grade where student.Id = :studentid and gradeTest.gradeType = :type")
    List<Grade> getAllGradesFromStudentByType(@Param("studentid") Long studentid, @Param("type") GradeTestType type);
}
