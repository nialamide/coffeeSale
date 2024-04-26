package com.pp.coffeesale.extern.controller.questions;


import com.pp.coffeesale.app.service.QuestionService;
import com.pp.coffeesale.domain.сourse.Question;
import com.pp.coffeesale.extern.record.QuestionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lectures")
@RequiredArgsConstructor
@Slf4j
public class QuestionsController {

    public final QuestionService questionService;

    @GetMapping
    public String getALlQuestions(Model model) {
        model.addAttribute("question", this.questionService.getAllQuestions());
        return "questions/newQuestion";
    }

    @GetMapping("create")
    public String createQuestions() {
        return "questions/newQuestion";
    }

    @PostMapping("create")
    public String create(QuestionDTO questionDTO) {
        if (questionDTO != null) {
            Question question = new Question();
            question.setAnswer(question.getAnswer());
            question.setTitle(questionDTO.getTitle());
            question.setDescription(questionDTO.getDescription());
            this.questionService.createQuestion(question);
            log.info("Создался новый вопрос");
            return "redirect:/questions/%d".formatted(question.getId());
        }
        return "redirect:/questions";
    }
}
