package com.api.semear.Api.Semear.domain.course.repository;

import com.api.semear.Api.Semear.domain.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {


    List<Course> findbyUser_Id(Long id);
}
