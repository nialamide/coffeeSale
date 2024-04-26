package com.pp.coffeesale.app.service;

import com.pp.coffeesale.app.repo.QuestionRepository;
import com.pp.coffeesale.domain.сourse.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {

    public final QuestionRepository questionRepository;


    public void createQuestion(Question question) {
        log.info("Вопрос сохранен");
        questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            log.info("Question was founded with id:{}", id);
        } else {
            log.info("Question not found with id:{}", id);
        }
        return question;
    }

    public void updateQuestion(Long id, String title, String description, String answer) {
        questionRepository.findById(id).ifPresent(question -> {
            question.setTitle(title);
            question.setDescription(description);
            question.setAnswer(answer);
            questionRepository.save(question);
            log.info("Question was updated with id:{}", id);
        });
    }
}
