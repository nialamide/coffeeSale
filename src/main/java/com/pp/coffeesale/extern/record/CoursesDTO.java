package com.pp.coffeesale.extern.record;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.List;

@Data
public class CoursesDTO {

    Long id;

    @NotEmpty
    @Pattern(regexp = "^[A-Z][a-z][А-Я][а-я]+\\s[A-Z][a-z][А-Я][а-я]+$")
    String courseName;

    @NotEmpty
    @Nonnull
    String authorEmail;

    @PositiveOrZero
    List<Long> lectureId;

    @PositiveOrZero
    List<Long> testId;
}
