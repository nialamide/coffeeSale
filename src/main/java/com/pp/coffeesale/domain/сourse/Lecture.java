package com.pp.coffeesale.domain.сourse;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "Lecture")
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @JoinColumn(name = "course_id")
    private Course course;

}

