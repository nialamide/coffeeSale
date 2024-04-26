package com.pp.coffeesale.extern.controller.lectures;


import com.pp.coffeesale.app.service.LectureService;
import com.pp.coffeesale.domain.сourse.Lecture;
import com.pp.coffeesale.extern.record.LectureDto;
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
public class LecturesController {

    public final LectureService lectureService;

    @GetMapping
    public String getALlLectures(Model model) {
        model.addAttribute("lectures", this.lectureService.getAllLectures());
        return "lectures/lectures";
    }

    @GetMapping("create")
    public String createLecture() {
        return "lectures/newLecture";
    }

    @PostMapping("create")
    public String create(LectureDto lectureDto) {
        if (lectureDto != null) {
            Lecture lecture = new Lecture();
            lecture.setTitle(lectureDto.getTitle());
            lecture.setDescription(lectureDto.getDescription());
            lecture.setUrlContent(lectureDto.getUrlContent());
            lectureService.createLecture(lecture);
            log.info("Создалась новая лекция");
            return "redirect:/lectures/%d".formatted(lecture.getId());
        }
        return "redirect:/lectures";
    }
}
