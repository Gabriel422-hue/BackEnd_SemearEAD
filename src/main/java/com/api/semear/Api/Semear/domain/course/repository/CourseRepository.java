package com.api.semear.Api.Semear.domain.course.repository;

import com.api.semear.Api.Semear.domain.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {


    List<Course> findByUser_Id(UUID id);

    Optional<Course> findById(UUID id);
}
