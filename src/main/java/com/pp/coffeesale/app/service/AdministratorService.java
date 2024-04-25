package com.pp.coffeesale.app.service;

import com.pp.coffeesale.app.repo.AdministratorRepository;
import com.pp.coffeesale.app.repo.PersonalRepository;
import com.pp.coffeesale.domain.users.Administrator;
import com.pp.coffeesale.domain.users.Personal;
import com.pp.coffeesale.domain.users.UsersRole;
import com.pp.coffeesale.domain.сourse.Course;
import com.pp.coffeesale.domain.сourse.Lecture;
import com.pp.coffeesale.domain.сourse.LectureState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AdministratorService {

    private final AdministratorRepository administratorRepository;
    private final PersonalRepository personalRepository;
    private final PersonalService personalService;
    private final CourseService courseService;
    private final LectureService lectureService;


    @Autowired
    public AdministratorService(AdministratorRepository administratorRepository,
                                PersonalRepository personalRepository, PersonalService personalService,
                                @Lazy CourseService courseService, LectureService lectureService) {
        this.administratorRepository = administratorRepository;
        this.personalRepository = personalRepository;
        this.personalService = personalService;
        this.courseService = courseService;
        this.lectureService = lectureService;
    }

    public void createAdministrator(Administrator administrator) {
        administratorRepository.save(administrator);
        log.info("AdministratorImpl created");
    }

    public Optional<Administrator> findAdministratorById(Long id) {
        Optional<Administrator> administrator = administratorRepository.findById(id);
        if (administrator.isPresent()) {
            log.info("AdministratorImpl found with id: " + id);
            return administrator;
        }
        log.info("AdministratorImpl not found with id: " + id);
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
            personalService.createPersonal(Personal.builder().role(UsersRole.TRAINEE).name(name).build());
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
            course.setHr(administrator);
            course.setCourseName(courseName);
            courseService.saveCourse(course);
            log.info("Course created by administrator with id: " + administratorId);
        });
        log.info("Error creating course with id: " + administratorId);

    }


    public void createLectureForCourse(Long courseId, String description, String title, String content) {
        courseService.getCourseById(courseId).ifPresent(course -> {
            Lecture lecture = Lecture.builder().course(course).state(LectureState.UNCHECKED).title(title).description(description).urlContent(content).build();
            lectureService.createLecture(lecture);
            log.info("Lecture created by administrator with id: " + courseId);
            course.setLectures(lecture);
            log.info("Course added lecture with id: " + lecture.getId());
        });

    }

    public Optional<Administrator> getAdminByEmail(String email) {
        Optional<Administrator> administrator = administratorRepository.findAdministratorByEmail(email);
        if (administrator.isPresent()) {
            log.info("Администратор с почтой:{} найден", email);
        } else log.info("Администратор с почтой:{} не найден", email);
        return administrator;
    }

    public void saveUser(Administrator administrator) {
        administratorRepository.save(administrator);
    }

    public List<Administrator> getAllAdministratorForHr() {
        return administratorRepository.findAll();
    }

    public List<Personal> getAllPersonalForHr() {
        return personalRepository.findAll();
    }
}
