package com.aalzatea.todo.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Builder(toBuilder = true)
@EqualsAndHashCode
@Getter
public class Task {

    private Integer id;

    @NotBlank
    @Length(min = 10, max = 60)
    private String description;

    private Date creationDate;

    @FutureOrPresent
    private Date dueDate;
}
