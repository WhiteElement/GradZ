package com.manu.GradeR.SchoolClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassService {

    @Autowired
    SchoolClassRepository schoolClassRepository;

    SchoolClassService(){}

    public SchoolClass getReferenceById(Long schoolclassid) {
        return schoolClassRepository.getReferenceById(schoolclassid);
    }

    public void save(SchoolClass schoolClass) {
        schoolClassRepository.save(schoolClass);
    }
}