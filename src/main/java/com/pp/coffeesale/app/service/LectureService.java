package com.pp.coffeesale.app.service;


import com.pp.coffeesale.app.repo.LectureRepository;
import com.pp.coffeesale.domain.Course.Lecture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class LectureService {

    private final LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public void createLecture(Lecture lecture) {
        if (lecture != null) {
            lectureRepository.save(lecture);
            log.info("Lecture created");
        } else log.info("Error lecture created");
    }

    public Optional<Lecture> getLectureById(Long id) {
        Optional<Lecture> lecture = lectureRepository.findById(id);
        if (lecture.isPresent()) {
            log.info("Lecture found");
            return lecture;
        }
        log.info("Error lecture found");
        return Optional.empty();
    }
}
