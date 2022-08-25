package com.aalzatea.todo;

import com.aalzatea.todo.gateways.TaskGateway;
import com.aalzatea.todo.entities.Task;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetTasksUseCase {

    private final TaskGateway taskGateway;

    public List<Task> getAllTasks() {
        return taskGateway.getAllTasks();
    }
}
