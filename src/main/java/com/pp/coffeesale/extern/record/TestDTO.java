package com.pp.coffeesale.extern.record;

import com.pp.coffeesale.domain.users.Personal;
import com.pp.coffeesale.domain.сourse.Course;
import com.pp.coffeesale.domain.сourse.TestState;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TestDTO {

    private Long id;

    private String title;

    private Personal personal;

    private Course course;

    private List<Long> questions;

    private TestState testState;

    private LocalDate createDate;
}
