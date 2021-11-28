package com.techtter.blog.scrum.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "task")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(position = 1)
    private Long id;

    @Column(name = "scrum_id")
    @ApiModelProperty(position = 2)
    private Long scrum_id;

    @Column(name = "sprint_num")
    @ApiModelProperty(position = 3)
    private Integer sprint_num;

    @Column(name = "workload")
    @ApiModelProperty(position = 4)
    private Integer workload;

    @Column(name = "progress")
    @ApiModelProperty(position = 5)
    private Integer progress;

    @Column(name = "title")
    @ApiModelProperty(position = 6)
    private String title;

    @Column(name = "color")
    @ApiModelProperty(position = 7)
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @ApiModelProperty(position = 8)
    private TaskStatus status;

    @Column(name = "description")
    @ApiModelProperty(position = 9)
    private String description;

    @Column(name = "complete_date")
    @ApiModelProperty(position = 10)
    private Date complete_date;

    @Column(name = "user_id")
    @ApiModelProperty(position = 11)
    private Integer user_id;
}
