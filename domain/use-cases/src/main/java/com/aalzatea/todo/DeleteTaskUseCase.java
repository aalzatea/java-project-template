package com.aalzatea.todo;

import com.aalzatea.todo.exceptions.business.DataNotFoundException;
import com.aalzatea.todo.gateways.TaskGateway;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class DeleteTaskUseCase {

    private final TaskGateway taskGateway;

    public void deleteTask(Integer id) {
        var taskFound = Optional.ofNullable(taskGateway.findTaskById(id))
                .orElseThrow(() -> DataNotFoundException.Type.DATA_FOR_SOMETHING_NOT_FOUND_EXCEPTION.buildWithMsgParameters(id));

        taskGateway.deleteTask(taskFound.getId());
    }
}
