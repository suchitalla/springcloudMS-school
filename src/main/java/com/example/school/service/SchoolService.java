package com.example.school.service;

import com.example.school.client.StudentClient;
import com.example.school.dto.FullSchoolResponse;
import com.example.school.entity.School;
import com.example.school.entity.Student;
import com.example.school.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository srp;
    private final StudentClient client;
    public void save(School s){
        srp.save(s);
    }
    public School update(School s) throws Exception{
        School sfind = srp.findSchoolBySchoolName(s.getSchoolName());
        if(sfind != null){
            srp.delete(sfind);
            return srp.save(s);
        }else{
                throw new Exception("School Not Found");
        }
    }

    public void delete(String schoolname) throws Exception{

        School sfind = srp.findSchoolBySchoolName(schoolname);

        if( sfind != null){
             srp.delete(sfind);
        }else{
            throw new Exception("School Not Found");
        }
    }

    public School findSchoolById(Integer id){
        return srp.findSchoolById(id);
    }

    public School findSchoolBySchoolName(String name){
        return srp.findSchoolBySchoolName(name);
    }

    public List<School> findAllSchools(){
        return srp.findAll();
    }

    public FullSchoolResponse findSchoolsWithStudents(Integer schoolId) {
        School school = srp.findById(schoolId)
                .orElse(
                        School.builder()
                                .schoolName("NOT_FOUND")
                                .email("NOT_FOUND")
                                .build()
                );
        List<Student> students = client.findAllStudentsBySchool(schoolId);
        return FullSchoolResponse.builder()
                .name(school.getSchoolName())
                .email(school.getEmail())
                .students(students)
                .build();
    }
}
