package com.pp.coffeesale.extern.controller.test;


import com.pp.coffeesale.app.service.TestService;
import com.pp.coffeesale.domain.сourse.Test;
import com.pp.coffeesale.extern.record.UpdateTestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("lectures/{testId}")
@RequiredArgsConstructor
@Slf4j
public class TestController {

    public final TestService testService;

    @ModelAttribute("lecture")
    public Test getProduct(@PathVariable("testId") Long courseId) {
        return this.testService.getTestById(courseId).orElseThrow();
    }

    @GetMapping
    public String getCourseById() {
        return "tests/test";
    }

    @GetMapping("/edit")
    public String getCourseEditById() {
        return "tests/editTest";
    }

    @PostMapping("/edit")
    public String editCourse(@ModelAttribute(name = "test") Test test, UpdateTestDTO updateTestDTO) {
        log.info("edit Test");
        this.testService.updateTest(test.getId(), updateTestDTO.getTitle(),
                updateTestDTO.getQuestions(), updateTestDTO);
        return "redirect:/lectures/%d".formatted(test.getId());
    }
}
