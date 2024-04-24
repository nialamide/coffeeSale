package com.pp.coffeesale.app.service;


import com.pp.coffeesale.app.repo.TestRepository;
import com.pp.coffeesale.domain.Course.Test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TestService {


    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }


    public void createTest(Test test) {
        if (test != null) {
            testRepository.save(test);
            log.info("Test created");
        } else log.info("Error test created");
    }

    public Optional<Test> getTestById(Long id) {
        Optional<Test> test = testRepository.findById(id);
        if (test.isPresent()) {
            log.info("Test found with id: " + id);
            return test;
        }
        log.info("Error test found with id: " + id);
        return Optional.empty();
    }
}
