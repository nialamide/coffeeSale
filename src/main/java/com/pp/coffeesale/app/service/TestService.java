package com.pp.coffeesale.app.service;


import com.pp.coffeesale.app.repo.TestRepository;
import com.pp.coffeesale.domain.сourse.Question;
import com.pp.coffeesale.domain.сourse.Test;
import com.pp.coffeesale.extern.record.UpdateTestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestService {


    private final TestRepository testRepository;
    private final QuestionService questionService;


    public void createTest(Test test) {
        if (test != null) {
            testRepository.save(test);
            log.info("test created");
        } else log.info("Error test created");
    }

    public Optional<Test> getTestById(Long id) {
        Optional<Test> test = testRepository.findById(id);
        if (test.isPresent()) {
            log.info("test found with id: " + id);
            return test;
        }
        log.info("Error test found with id: " + id);
        return Optional.empty();
    }

    public List<Test> getAllTest() {
        return testRepository.findAll();
    }

    public void updateTest(Long id, String title, List<Long> questions, UpdateTestDTO updateTestDTO) {
        getTestById(id).ifPresent(test -> {
            test.setTitle(title);
            List<Question> questionList = List.of();
            if (questions != null) {
                questions.forEach(questionId -> {
                    questionService.getQuestionById(questionId).ifPresent(questionList::add);
                });
            }
            log.info(questionList.toString());
            test.setQuestions(questionList);
            test.setCreateDate(LocalDate.now());
        });
    }
}
