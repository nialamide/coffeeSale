package com.pp.coffeesale.extern.controller.test;

import com.pp.coffeesale.app.service.QuestionService;
import com.pp.coffeesale.app.service.TestService;
import com.pp.coffeesale.domain.сourse.Test;
import com.pp.coffeesale.extern.record.TestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/lectures")
@RequiredArgsConstructor
@Slf4j
public class TestsController {

    public final TestService testService;
    private final QuestionService questionService;

    @GetMapping
    public String getALlLectures(Model model) {
        model.addAttribute("tests", this.testService.getAllTest());
        return "tests/test";
    }

    @GetMapping("create")
    public String createLecture() {
        return "tests/newTest";
    }

    @PostMapping("create")
    public String create(TestDTO testDTO) {
        if (testDTO != null) {
            Test test = new Test();
            test.setTitle(testDTO.getTitle());
            testDTO.getQuestions().stream()
                    .map(id -> {
                        questionService.getQuestionById(id).ifPresent(test::setQuestions);
                        return null;
                    });
            test.setCreateDate(LocalDate.now());
            log.info("Создался новый тест");
            return "redirect:/questions/%d".formatted(test.getId());
        }
        return "redirect:/questions";
    }
}
