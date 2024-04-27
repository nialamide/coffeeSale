package com.pp.coffeesale.extern.record;

import lombok.Data;

@Data
public class UpdateQuestionDto {

    private String title;

    private String description;

    private String answer;

    private String rightAnswer;

}
