package com.techtter.blog.scrum.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrumDTO {

    private Long id;
    private String title;
    private String author;
    private Integer duration;
}
