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
public class ScrumUserDTO {

    @ApiModelProperty(position = 1)
    private Long scrumId;

    @ApiModelProperty(position = 2)
    private int userId;

    @ApiModelProperty(position = 3)
    private int roleId;
}
