package com.pp.coffeesale.domain.сourse;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Questions")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String answer;

    private int grade;

    @Enumerated(EnumType.STRING)
    private QuestionState state;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Test test;
}
