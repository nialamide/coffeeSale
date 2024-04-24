package com.pp.coffeesale.domain.Course;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Questions")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private int grade;

    @Enumerated(EnumType.STRING)
    private QuestionState state;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Test test;
}
