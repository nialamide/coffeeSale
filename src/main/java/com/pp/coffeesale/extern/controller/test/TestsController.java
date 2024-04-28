package com.pp.coffeesale.extern.controller.test;

import com.pp.coffeesale.app.service.LectureService;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/tests")
@RequiredArgsConstructor
@Slf4j
public class TestsController {

    public final TestService testService;
    private final QuestionService questionService;
    private final LectureService lectureService;

    @GetMapping
    public String getALlLectures(Model model) {
        model.addAttribute("tests", this.testService.getAllTest());
        model.addAttribute("lecture", this.lectureService.getAllLectures());
        return "tests/tests";
    }

    @GetMapping("create")
    public String createLecture(Model model) {
        model.addAttribute("questions", this.questionService.getAllQuestions());
        return "tests/newTest";
    }

    @PostMapping("create")
    public String create(TestDTO testDTO, @RequestParam(value = "test", required = false) List<Long> selectedQuestionIds) {
        if (testDTO != null) {
            Test test = new Test();
            test.setTitle(testDTO.getTitle());
            log.info("selectedQuestionIds: " + selectedQuestionIds);
            if (selectedQuestionIds != null && !selectedQuestionIds.isEmpty()) {
                selectedQuestionIds.forEach(questionId -> {
                    log.info(String.valueOf(questionId));
                    questionService.getQuestionById(questionId).ifPresent(test::setQuestions);
                });
            }
            test.setCreateDate(LocalDate.now());
            log.info("Создался новый тест");
            this.testService.createTest(test);
            return "redirect:/tests/%d".formatted(test.getId());
        }
        return "redirect:/tests/";
    }

}
