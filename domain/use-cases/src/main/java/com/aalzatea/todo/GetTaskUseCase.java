package com.aalzatea.todo;

import com.aalzatea.todo.exceptions.business.DataNotFoundException;
import com.aalzatea.todo.gateways.TaskGateway;
import com.aalzatea.todo.entities.Task;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class GetTaskUseCase {

    private final TaskGateway taskGateway;

    public Task getTask(Integer id) {
        var task = taskGateway.findTaskById(id);

        if(Objects.isNull(task))
            throw DataNotFoundException.Type.DATA_FOR_SOMETHING_NOT_FOUND_EXCEPTION.buildWithMsgParameters("Task", id);

        return task;
    }
}
