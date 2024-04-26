package com.pp.coffeesale.app.service;


import com.pp.coffeesale.app.repo.LectureRepository;
import com.pp.coffeesale.domain.сourse.Lecture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Lecture> getAllLectures() {
        log.info("Lectures found");
        return lectureRepository.findAll();
    }

    public List<Lecture> getAllLecturesForCourse(Long courseId) {
        return lectureRepository.findAllByCourseId(courseId);
    }

    public void updateLecture(Long id, String title, String description, String urlContent) {
        getLectureById(id).ifPresent(lecture -> {
            lecture.setTitle(title);
            lecture.setDescription(description);
            lecture.setUrlContent(urlContent);
            lectureRepository.save(lecture);
            log.info("Lecture updated");
        });
    }
}
