package com.pp.coffeesale.extern.controller;


import com.pp.coffeesale.app.service.AdministratorService;
import com.pp.coffeesale.app.service.CourseService;
import com.pp.coffeesale.app.service.LectureService;
import com.pp.coffeesale.app.service.TestService;
import com.pp.coffeesale.domain.users.Administrator;
import com.pp.coffeesale.domain.сourse.Course;
import com.pp.coffeesale.extern.controller.record.CoursesDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("courses")
@RequiredArgsConstructor
@Slf4j
public class CoursesController {

    public final CourseService courseService;
    public final AdministratorService administratorService;
    public final LectureService lectureService;
    public final TestService testService;

    @GetMapping()
    public String getAllCourses(Model model) {
        model.addAttribute("courses", this.courseService.getAllCourses());
        return "courses/courses";
    }

    @GetMapping("create")
    public String getNewCourse() {
        return "courses/newCourse";
    }

    @PostMapping("create")
    public String saveNewCourse(CoursesDTO coursesDTO) {
        Optional<Administrator> administrator = administratorService.getAdminByEmail(coursesDTO.getAuthorEmail());
        log.info("administrator email: {}", coursesDTO.getAuthorEmail());
        if (administrator.isPresent()) {
            Course course = new Course();
            course.setHr(administrator.get());
            course.setCourseName(coursesDTO.getCourseName());
            course.setId(coursesDTO.getId());
            coursesDTO.getLectureId().stream()
                    .map(id -> {
                        lectureService.getLectureById(id).ifPresent(course::setLectures);
                        return null;
                    });
            coursesDTO.getTestId().stream().map(id -> {
                testService.getTestById(id).ifPresent(course::setTests);
                return null;
            });
            log.info(String.valueOf(course.getHr()));
            courseService.saveCourse(course);
            return "redirect:/courses";
        }
        throw new NullPointerException("Администратор не найден");
    }
}
