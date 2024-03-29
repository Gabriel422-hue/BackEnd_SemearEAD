package com.api.semear.Api.Semear.controllers;


import com.api.semear.Api.Semear.domain.course.model.Course;
import com.api.semear.Api.Semear.domain.course.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
@Validated
public class CourseController {

    private final CourseService courseService;


    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable UUID id){
        Course course = this.courseService.findById(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Course>> findAllByUser(){
        List<Course> course = this.courseService.findAllByUser();
        return ResponseEntity.ok().body(course);
    }


    @PostMapping
    @Validated
    public ResponseEntity<Void> create (@Valid @RequestBody Course course) throws AccessDeniedException {
        this.courseService.create(course);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update (@Valid @RequestBody Course course, @PathVariable UUID id ){
        course.setId(id);
        this.courseService.uptade(course);
        return ResponseEntity.noContent().build();
    }

}
