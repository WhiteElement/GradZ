package com.manu.GradeR.repository;

import com.manu.GradeR.model.SchoolClass;
import com.manu.GradeR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolClassRepository extends JpaRepository<SchoolClass,Long> {

    public List<SchoolClass> findByUser(User user);
}
