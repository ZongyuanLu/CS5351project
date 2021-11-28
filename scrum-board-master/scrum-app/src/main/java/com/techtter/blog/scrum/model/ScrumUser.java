package com.techtter.blog.scrum.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@Table(name ="scrum_user")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = ScrumUser.class)
public class ScrumUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(position = 1)
    private Long id;

    @Column(name = "scrumid")
    @ApiModelProperty(position = 2)
    private Long scrumId;

    @Column(name = "userid")
    @ApiModelProperty(position = 3)
    private int userId;

    @Column(name = "roleid")
    @ApiModelProperty(position = 4)
    private int roleId;
}
