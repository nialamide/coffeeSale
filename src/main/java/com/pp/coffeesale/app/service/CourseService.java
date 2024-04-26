package com.pp.coffeesale.app.service;


import com.pp.coffeesale.app.repo.CourseRepository;
import com.pp.coffeesale.domain.сourse.Course;
import com.pp.coffeesale.domain.сourse.Lecture;
import com.pp.coffeesale.domain.сourse.Test;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final AdministratorService administratorService;
    private final TestService testService;
    private final LectureService lectureService;


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

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void updateCourse(Long id, String courseName, String authorEmail, List<Long> testsId, List<Long> lecturesId) {
        courseRepository.findById(id).ifPresent(course -> {
            log.info("course name: " + course.getCourseName());
            log.info("course name: " + courseName);
            course.setCourseName(courseName);
            course.setHr(administratorService.getAdminByEmail(authorEmail).get());
            List<Test> tests = new ArrayList<>();
//            testsId.forEach(testId -> {
//                testService.getTestById(testId).ifPresent(tests::add);
//            });
            List<Lecture> lectures = new ArrayList<>();
//            lecturesId.forEach(lectureId -> {
//                lectureService.getLectureById(lectureId).ifPresent(lectures::add);
//            });
            course.replaceTest(tests);
            course.replaceLecture(lectures);
            courseRepository.save(course);
        });
    }
}
