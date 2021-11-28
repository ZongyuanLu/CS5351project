package com.techtter.blog.scrum.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddScrumDTO {


    private String phone;
    private String title;
    private String author;
    private Integer duration;
}
