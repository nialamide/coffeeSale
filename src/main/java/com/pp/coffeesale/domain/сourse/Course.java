package com.pp.coffeesale.domain.сourse;


import com.pp.coffeesale.domain.users.Administrator;
import com.pp.coffeesale.domain.users.Personal;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@ToString
@Table(name = "course")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Test> tests;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<Lecture> lectures;


    @ManyToOne
    @JoinColumn(name = "personal_id")
    private Personal personal;

    @ManyToOne
    @JoinColumn(name = "administrator_id")
    private Administrator hr;

    @Enumerated(EnumType.STRING)
    private CourseState state;

    private LocalDateTime startDate;


    public void setLectures(List<Lecture> lectures) {
        this.lectures.addAll(lectures);
    }

    public void setLectures(Lecture lectures) {
        this.lectures.add(lectures);
    }

    public void setTests(Test tests) {
        this.tests.add(tests);
    }

    public void replaceTest(List<Test> tests) {
        this.tests = tests;
    }

    public void replaceLecture(List<Lecture> lectures) {
        this.lectures = lectures;
    }
}

