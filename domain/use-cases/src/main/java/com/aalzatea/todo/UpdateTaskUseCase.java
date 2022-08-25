package com.aalzatea.todo;

import com.aalzatea.todo.entities.Task;
import com.aalzatea.todo.exceptions.business.DataNotFoundException;
import com.aalzatea.todo.gateways.TaskGateway;
import com.aalzatea.todo.validations.BeanValidator;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UpdateTaskUseCase {

    private final TaskGateway taskGateway;

    public void updateTask(Task task) {
        BeanValidator.validateBean(task);

        var newTask = Optional.ofNullable(taskGateway.findTaskById(task.getId()))
                .map(t -> t.toBuilder()
                        .description(task.getDescription())
                        .dueDate(task.getDueDate())
                        .build())
                .orElseThrow(DataNotFoundException.Type.DATA_NOT_FOUND_EXCEPTION::build);

        taskGateway.updateTask(newTask);
    }
}
