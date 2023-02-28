package com.api.semear.Api.Semear.controllers;


import com.api.semear.Api.Semear.domain.course.model.Course;
import com.api.semear.Api.Semear.domain.course.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {


    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id){
        Course course = this.courseService.findById(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Course>> findAllCoursesById (@PathVariable Long userId){
        List<Course> course = this.courseService.findAllByUserId(userId);
        return ResponseEntity.ok().body(course);

    }



    @PostMapping
    @Validated
    public ResponseEntity<Void> create (@Valid @RequestBody Course course){
        this.courseService.create(course);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update (@Valid @RequestBody Course course, @PathVariable Long id ){
        course.setId(id);
        this.courseService.uptade(course);
        return ResponseEntity.noContent().build();
    }




    
}
