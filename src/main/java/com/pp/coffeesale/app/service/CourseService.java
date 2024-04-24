package com.pp.coffeesale.app.service;


import com.pp.coffeesale.app.repo.CourseRepository;
import com.pp.coffeesale.domain.Course.Course;
import com.pp.coffeesale.domain.Course.Lecture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void saveCourse(Course course) {
        if (course != null) {
            courseRepository.save(course);
            log.info("Course saved");
        } else log.info("Error course saved");
    }

    public Optional<Course> getCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            log.info("Found course with id: " + id);
            return course;
        }
        log.info("Error found course with id: " + id);
        return course;
    }

    public void addLectureToCourse(Long courseId, Lecture lecture) {
        getCourseById(courseId).ifPresent(course -> {
            course.setLectures(lecture);
        });
    }
}
