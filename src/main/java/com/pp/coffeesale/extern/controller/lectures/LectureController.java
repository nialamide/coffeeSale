package com.pp.coffeesale.extern.controller.lectures;


import com.pp.coffeesale.app.service.LectureService;
import com.pp.coffeesale.domain.сourse.Lecture;
import com.pp.coffeesale.extern.record.UpdateLectureDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("lectures/{lectureId}")
@RequiredArgsConstructor
@Slf4j
public class LectureController {

    public final LectureService lectureService;

    @ModelAttribute("lecture")
    public Lecture getProduct(@PathVariable("lectureId") Long courseId) {
        return this.lectureService.getLectureById(courseId).orElseThrow();
    }

    @GetMapping
    public String getCourseById() {
        return "lectures/lecture";
    }

    @GetMapping("/edit")
    public String getCourseEditById() {
        return "lectures/editLecture";
    }

    @PostMapping("/edit")
    public String editCourse(@ModelAttribute(name = "lecture") Lecture lecture, UpdateLectureDto updateLectureDto) {
        log.info("editLecture");
        this.lectureService.updateLecture(lecture.getId(), updateLectureDto.getTitle(),
                updateLectureDto.getDescription(), updateLectureDto.getUrlContent());
        return "redirect:/lectures/%d".formatted(lecture.getId());
    }
}
