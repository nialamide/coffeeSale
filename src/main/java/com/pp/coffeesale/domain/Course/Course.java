package com.pp.coffeesale.domain.Course;


import com.pp.coffeesale.domain.users.Administrator;
import com.pp.coffeesale.domain.users.Personal;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Test> tests;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
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
}

