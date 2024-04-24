package com.pp.coffeesale.domain.Course;


import com.pp.coffeesale.domain.users.Administrator;
import com.pp.coffeesale.domain.users.Personal;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "course")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Test> tests;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private List<Lecture> lectures;

    @ManyToOne
    @JoinColumn(name = "personal_id")
    private Personal personal;

    @ManyToOne
    @JoinColumn(name = "administrator_id")
    private Administrator hrId;

    @Enumerated(EnumType.STRING)
    private CourseState state;

    private LocalDateTime startDate;


    public void setLectures(List<Lecture> lectures) {
        this.lectures.addAll(lectures);
    }

    public void setLectures(Lecture lectures) {
        this.lectures.add(lectures);
    }
}

