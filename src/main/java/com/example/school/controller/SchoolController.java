package com.example.school.controller;

import com.example.school.dto.FullSchoolResponse;
import com.example.school.entity.School;
import com.example.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService ss;
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<School> getAllSchools(){
        return ss.findAllSchools();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public School getSchool(@PathVariable("Id") Integer id){
        return ss.findSchoolById(id);
    }

    @GetMapping("/{schoolname}")
    @ResponseStatus(HttpStatus.FOUND)
    public School getSchool(@PathVariable("schoolname") String schoolname){
        return ss.findSchoolBySchoolName(schoolname);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addSchool(@RequestBody School sch){
        ss.save(sch);
    }

    @PutMapping
    public ResponseEntity<School> updateSchool(@RequestBody School sch){
        try {
            return new ResponseEntity<>(ss.update(sch), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{schoolname}")
    public ResponseEntity<School> deleteSchool(@PathVariable("schoolname") String schoolname){
        try {
            ss.delete(schoolname);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/with-students/{school-id}")
    public ResponseEntity<FullSchoolResponse> findAllStudentsBySchoolId(
            @PathVariable("school-id") Integer schoolId
    ) {
        return ResponseEntity.ok(ss.findSchoolsWithStudents(schoolId));
    }
}
