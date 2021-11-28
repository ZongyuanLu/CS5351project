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
public class TaskDTO {

    @ApiModelProperty(position = 1)
    private String title;

    @ApiModelProperty(position = 2)
    private String description;

    @ApiModelProperty(position = 3)
    private String color;

    @ApiModelProperty(position = 4)
    private TaskStatus status;

    @ApiModelProperty(position = 5)
    private Integer sprint_num;

    @ApiModelProperty(position = 6)
    private Integer workload;

    @ApiModelProperty(position = 7)
    private Integer progress;

    @ApiModelProperty(position = 8)
    private String complete_date;

    @ApiModelProperty(position = 9)
    private Integer user_id;
}
