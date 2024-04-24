package com.pp.coffeesale.app.service;

import com.pp.coffeesale.app.repo.AdministratorRepository;
import com.pp.coffeesale.domain.Course.Course;
import com.pp.coffeesale.domain.Course.Lecture;
import com.pp.coffeesale.domain.Course.LectureState;
import com.pp.coffeesale.domain.users.Administrator;
import com.pp.coffeesale.domain.users.Personal;
import com.pp.coffeesale.domain.users.UsersRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AdministratorService {

    private final AdministratorRepository administratorRepository;
    private final PersonalService personalService;
    private final CourseService courseService;
    private final LectureService lectureService;

    @Autowired
    public AdministratorService(AdministratorRepository administratorRepository,
                                PersonalService personalService, CourseService courseService, LectureService lectureService) {
        this.administratorRepository = administratorRepository;
        this.personalService = personalService;
        this.courseService = courseService;
        this.lectureService = lectureService;
    }

    public void createAdministrator(Administrator administrator) {
        administratorRepository.save(administrator);
        log.info("Administrator created");
    }

    public Optional<Administrator> findAdministratorById(Long id) {
        Optional<Administrator> administrator = administratorRepository.findById(id);
        if (administrator.isPresent()) {
            log.info("Administrator found with id: " + id);
            return administrator;
        }
        log.info("Administrator not found with id: " + id);
        return administrator;
    }

    /**
     * Создание нового персонала
     *
     * @param administratorId
     * @param name
     */
    public void createPersonal(Long administratorId, String name) {
        findAdministratorById(administratorId).ifPresent(administrator -> {
            personalService.createPersonal(Personal.builder()
                    .role(UsersRole.TRAINEE)
                    .name(name).build());
            log.info("Personal created by administrator with id: " + administratorId);
        });
        log.info("Error creating personal with id: " + administratorId);
    }

    /**
     * Добавление нового курса
     *
     * @param administratorId
     * @return
     */
    public void createCourse(Long administratorId, String courseName) {
        findAdministratorById(administratorId).ifPresent(administrator -> {
            Course course = new Course();
            course.setHrId(administrator);
            course.setCourseName(courseName);
            courseService.saveCourse(course);
            log.info("Course created by administrator with id: " + administratorId);
        });
        log.info("Error creating course with id: " + administratorId);

    }


    public void createLectureForCourse(Long courseId, String description, String title, String content) {
        courseService.getCourseById(courseId).ifPresent(course -> {
            Lecture lecture = Lecture.builder()
                    .course(course)
                    .state(LectureState.UNCHECKED)
                    .title(title)
                    .description(description)
                    .urlContent(content)
                    .build();
            lectureService.createLecture(lecture);
            log.info("Lecture created by administrator with id: " + courseId);
            course.setLectures(lecture);
            log.info("Course added lecture with id: " + lecture.getId());
        });
    }

}
