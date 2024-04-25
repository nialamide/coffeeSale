package com.pp.coffeesale.extern.controller;


import com.pp.coffeesale.app.service.CourseService;
import com.pp.coffeesale.domain.сourse.Course;
import com.pp.coffeesale.extern.controller.record.UpdateCourseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("courses/{courseId:\\d+}")
@RequiredArgsConstructor
public class CourseController {

    public final CourseService courseService;

    @ModelAttribute("course")
    public Course getProduct(@PathVariable("courseId") Long courseId) {
        return this.courseService.getCourseById(courseId).orElseThrow();
    }

    @GetMapping
    public String getCourseById() {
        return "courses/course";
    }

    @GetMapping("/edit")
    public String getCourseEditById() {
        return "courses/editCourse";
    }

    @PostMapping("/edit")
    public String editCourse(@ModelAttribute(name = "course") Course course, UpdateCourseDTO updateCourseDTO) {
        this.courseService.updateCourse(course.getId(), updateCourseDTO.getCourseName(),
                updateCourseDTO.getAuthorEmail(), updateCourseDTO.getTestId(), updateCourseDTO.getLectureId());
        return "redirect:/courses/%d".formatted(course.getId());
    }
}
