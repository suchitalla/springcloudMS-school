package com.example.school.repository;

import com.example.school.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {

    public School findSchoolById(Integer id);

    public School findSchoolBySchoolName(String name);

    public School deleteBySchoolName(String name);


}
