package com.manu.GradeR.repository;

import com.manu.GradeR.model.GradeTest;
import com.manu.GradeR.model.GradeTestType;
import com.manu.GradeR.model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeTestRepository extends JpaRepository<GradeTest, Long> {

    @Query
    public List<GradeTest> findByGradeType(GradeTestType type);

    @Query("from GradeTest where gradeType = :gradeType and schoolClass = :schoolclass order by Id asc")
    public List<GradeTest> findByGradeTypeAndSchoolClass(@Param("gradeType") GradeTestType gradeType,
                                                         @Param("schoolclass") SchoolClass schoolClass);
}
