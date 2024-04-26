package com.pp.coffeesale.extern.record;


import lombok.Data;

@Data
public class UpdateLectureDto {
    private Long id;
    private String title;
    private String description;
    private String urlContent;
}
