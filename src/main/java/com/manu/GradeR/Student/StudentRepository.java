package com.manu.GradeR.Student;

import com.manu.GradeR.SchoolClass.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("from Student where schoolClass = :schoolclass order by lastName asc")
    public List<Student> findAllFromClassOrderByLastName(@Param("schoolclass") SchoolClass schoolclass);
}
