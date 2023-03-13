package com.api.semear.Api.Semear.domain.cart;

import com.api.semear.Api.Semear.domain.course.model.Course;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Course> courses;
    private double total;

    public ShoppingCart() {
        courses = new ArrayList<>();
        total = 0.0;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void addCourse(Course course) {
        courses.add(course);
        total += course.getPrice();
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        total -= course.getPrice();
    }

    public void clear() {
        courses.clear();
        total = 0.0;
    }
}
