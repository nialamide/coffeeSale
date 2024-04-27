package com.pp.coffeesale.app.service;


import com.pp.coffeesale.app.repo.PersonalRepository;
import com.pp.coffeesale.app.repo.QuestionRepository;
import com.pp.coffeesale.domain.users.Personal;
import com.pp.coffeesale.domain.сourse.Course;
import com.pp.coffeesale.domain.сourse.QuestionState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonalService {


    private final PersonalRepository personalRepository;
    private final CourseService courseService;
    private final QuestionService questionService;
    private final QuestionRepository questionRepository;
    private final TestService testService;

    public void createPersonal(Personal personal) {
        if (personal != null) {
            personalRepository.save(personal);
            log.info("Created personal");
        }
        log.info("Error creating personal");
    }

    public Optional<Personal> getPersonalById(Long id) {
        Optional<Personal> personal = personalRepository.findById(id);
        if (personal.isPresent()) {
            log.info("Found personal");
        }
        log.info("Error getting personal");
        return personal;
    }

    public List<Personal> getAll() {
        return personalRepository.findAll();
    }


    public void addCourse(Long courseId, Long personalId) {
        Optional<Personal> personal = getPersonalById(personalId);
        Optional<Course> course = courseService.getCourseById(courseId);
        if (personal.isPresent() && course.isPresent()) {
            personal.get().getCourses().add(course.get());
            course.get().getTests().forEach(test -> test.setPersonal(personal.get()));
            personalRepository.save(personal.get());
        }
    }

    public void completeTest(Long courseId, Long testId, Long personalId) {

    }

    public void getAnswerForQuestion(Long questionId, String answer) {
        questionService.getQuestionById(questionId).ifPresent(question -> {
            question.setRightAnswer(answer);
            if (question.getRightAnswer().equals(answer)) {
                question.setState(QuestionState.COMPLETED);
            } else {
                question.setState(QuestionState.UNCOMPLETED);
            }
            questionRepository.save(question);
        });
    }
}
