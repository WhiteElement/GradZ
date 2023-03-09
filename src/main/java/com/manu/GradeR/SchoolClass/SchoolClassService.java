package com.manu.GradeR.SchoolClass;

import com.manu.GradeR.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<SchoolClass> findAll() {
        return schoolClassRepository.findAll();
    }

    public void deleteById(Long id) {
        schoolClassRepository.deleteById(id);
    }

    public SchoolClass findById(Long schoolclassid) {
        return schoolClassRepository.getReferenceById(schoolclassid);
    }

    public List<SchoolClass> findAllFromOneTeacher(User user) {
        return schoolClassRepository.findByUser(user);
    }
}
