package com.aalzatea.todo;

import com.aalzatea.todo.entities.Task;
import com.aalzatea.todo.gateways.TaskGateway;
import com.aalzatea.todo.validations.BeanValidator;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
public class CreateTaskUseCase {

    private final TaskGateway taskGateway;

    public void createTask(Task task) {
        BeanValidator.validateBean(task);

        var taskFilled = task.toBuilder()
                .id(getId())
                .creationDate(new Date())
                .build();

        taskGateway.createTask(taskFilled);
    }

    private Integer getId() {
        return taskGateway.getLastTaskId() + 1;
    }
}
