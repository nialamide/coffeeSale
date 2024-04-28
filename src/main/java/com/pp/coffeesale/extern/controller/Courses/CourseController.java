package com.pp.coffeesale.extern.controller.Courses;


import com.pp.coffeesale.app.service.CourseService;
import com.pp.coffeesale.app.service.LectureService;
import com.pp.coffeesale.app.service.TestService;
import com.pp.coffeesale.domain.сourse.Course;
import com.pp.coffeesale.extern.record.UpdateCourseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("courses/{courseId}")
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    public final CourseService courseService;
    private final LectureService lectureService;
    private final TestService testService;

    @ModelAttribute("course")
    public Course getProduct(@PathVariable("courseId") Long courseId) {
        return this.courseService.getCourseById(courseId).orElseThrow();
    }

    @GetMapping
    public String getCourseById(Model model) {
        model.addAttribute("lectures", this.lectureService.getAllLectures());
        model.addAttribute("tests", this.testService.getAllTest());
        return "courses/course";
    }

    @GetMapping("/edit")
    public String getCourseEditById(Model model) {
        model.addAttribute("lectures", this.lectureService.getAllLectures());
        model.addAttribute("tests", this.testService.getAllTest());
        return "courses/editCourse";
    }

    @PostMapping("/edit")
    public String editCourse(@ModelAttribute(name = "course") Course course, UpdateCourseDTO updateCourseDTO) {
        log.info("editCourse");
        this.courseService.updateCourse(course.getId(), updateCourseDTO.getCourseName(),
                updateCourseDTO.getAuthorEmail(), updateCourseDTO.getTestId(), updateCourseDTO.getLectureId());
        return "redirect:/courses/%d".formatted(course.getId());
    }
}
