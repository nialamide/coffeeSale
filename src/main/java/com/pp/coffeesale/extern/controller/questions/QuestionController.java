package com.pp.coffeesale.extern.controller.questions;


import com.pp.coffeesale.app.service.QuestionService;
import com.pp.coffeesale.domain.сourse.Question;
import com.pp.coffeesale.extern.record.UpdateQuestionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("questions/{questionId}")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    public final QuestionService questionService;

    @ModelAttribute("question")
    public Question getProduct(@PathVariable("questionId") Long questionId) {
        return this.questionService.getQuestionById(questionId).orElseThrow();
    }

    @GetMapping
    public String getQuestionById() {
        return "questions/question";
    }

    @GetMapping("/edit")
    public String getQuestionEditById() {
        return "questions/editQuestion";
    }

    @PostMapping("/edit")
    public String editQuestion(@ModelAttribute(name = "question") Question question, UpdateQuestionDto updateQuestionDto) {
        log.info("edit question");
        this.questionService.updateQuestion(question.getId(), updateQuestionDto.getTitle(),
                updateQuestionDto.getDescription(), updateQuestionDto.getRightAnswer());
        return "redirect:/questions/%d".formatted(question.getId());
    }
}
