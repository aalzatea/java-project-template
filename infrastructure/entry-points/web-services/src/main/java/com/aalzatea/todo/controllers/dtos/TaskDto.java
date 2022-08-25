package com.aalzatea.todo.controllers.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class TaskDto {

    private Integer id;

    @NotBlank
    private String description;

    private Date dueDate;
}
