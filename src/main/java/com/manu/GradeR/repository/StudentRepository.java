package com.manu.GradeR.repository;

import com.manu.GradeR.Dao.StudentGradeDao;
import com.manu.GradeR.model.Grade;
import com.manu.GradeR.model.SchoolClass;
import com.manu.GradeR.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("from Student where schoolClass = :schoolclass order by lastName asc")
    public List<Student> findAllFromClassOrderByLastName(@Param("schoolclass") SchoolClass schoolclass);

    @Query("select new com.manu.GradeR.Dao.StudentGradeDao(s.Id, s.firstName, s.lastName, g.Grade)" +
            " from Student s left join Grade g" +
            " on s.Id = g.student.Id" +
            " and g.gradeTest.Id = :gradetestid or g.gradeTest.Id = null" +
            " where s.schoolClass.Id = :schoolclassid" +
            " order by s.lastName asc"
    )
    public List<StudentGradeDao> getStudentsWithGradesFromSpecificGradeTest(@Param("gradetestid") Long gradetestid,
                                                                            @Param("schoolclassid") Long schoolclassid);

    @Query("select new com.manu.GradeR.model.Grade(g.Grade)" +
            " from Student s left join Grade g" +
            " on s.Id = g.student.Id" +
            " and g.gradeTest.Id = :gradetestid or g.gradeTest.Id = null" +
            " where s.schoolClass.Id = :schoolclassid" +
            " order by s.lastName asc"
    )
    public List<Grade> getGrades(@Param("gradetestid") Long gradetestid,
                                @Param("schoolclassid") Long schoolclassid);


}
