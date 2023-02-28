package com.api.semear.Api.Semear.domain.course.repository;

import com.api.semear.Api.Semear.domain.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


    List<Course> findByUser_Id(Long id);
}
