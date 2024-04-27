package com.pp.coffeesale.extern.controller.test;


import com.pp.coffeesale.app.repo.QuestionRepository;
import com.pp.coffeesale.app.service.TestService;
import com.pp.coffeesale.domain.сourse.Test;
import com.pp.coffeesale.extern.record.UpdateTestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("tests/{testId}")
@RequiredArgsConstructor
@Slf4j
public class TestController {

    public final TestService testService;
    private final QuestionRepository questionRepository;

    @ModelAttribute("test")
    public Test getProduct(@PathVariable("testId") Long courseId) {
        return this.testService.getTestById(courseId).orElseThrow();
    }

    @GetMapping
    public String getCourseById(Model model, @PathVariable("testId") Long testId) {
        model.addAttribute("questions", questionRepository.findAllByTestId(testId));
        return "tests/test";
    }

    @GetMapping("/edit")
    public String getCourseEditById(Model model) {
        model.addAttribute("questions", questionRepository.findAll());
        return "tests/editTest";
    }

    @PostMapping("/edit")
    public String editCourse(Model model, @ModelAttribute(name = "test") Test test, UpdateTestDTO updateTestDTO) {
        log.info("edit Test");
        model.addAttribute("questions", questionRepository.findAll());
        this.testService.updateTest(test.getId(), updateTestDTO.getTitle(),
                updateTestDTO.getQuestions(), updateTestDTO);
        return "redirect:/tests/%d".formatted(test.getId());
    }
}
