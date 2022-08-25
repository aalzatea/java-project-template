package com.aalzatea.todo.gateways;

import com.aalzatea.todo.entities.Task;

import java.util.List;

public interface TaskGateway {

    List<Task> getAllTasks();

    Task findTaskById(Integer id);

    Integer getLastTaskId();

    void createTask(Task task);

    void updateTask(Task task);

    void deleteTask(Integer id);
}
