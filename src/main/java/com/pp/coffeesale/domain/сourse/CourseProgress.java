package com.pp.coffeesale.domain.сourse;


import com.pp.coffeesale.domain.users.Personal;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CourseProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "personal_id")
    private Personal personal;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private Float lectureProgress;
    private Float testProgress;
    private boolean isCompleted;
}
