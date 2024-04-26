package com.pp.coffeesale.domain.сourse;

import com.pp.coffeesale.domain.users.Personal;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Tests")
@Data
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "personal_id")
    private Personal personal;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Course course;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    @Enumerated(EnumType.STRING)
    private TestState testState;

    private LocalDate createDate;

    public void setQuestions(Question question) {
        this.questions.add(question);
    }

    public void setQuestions(List<Question> questions) {
        this.questions.addAll(questions);
    }
}

