package com.pp.coffeesale.domain.Course;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "Lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String urlContent;

    @Enumerated(EnumType.STRING)
    private LectureState state;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Course course;
}

