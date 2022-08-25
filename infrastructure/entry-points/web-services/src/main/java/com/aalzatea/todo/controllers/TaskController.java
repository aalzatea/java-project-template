package com.aalzatea.todo.controllers;

import com.aalzatea.todo.CreateTaskUseCase;
import com.aalzatea.todo.DeleteTaskUseCase;
import com.aalzatea.todo.GetTaskUseCase;
import com.aalzatea.todo.GetTasksUseCase;
import com.aalzatea.todo.UpdateTaskUseCase;
import com.aalzatea.todo.controllers.dtos.TaskDto;
import com.aalzatea.todo.entities.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final GetTasksUseCase getTasksUseCase;

    private final GetTaskUseCase getTaskUseCase;

    private final CreateTaskUseCase createTaskUseCase;

    private final UpdateTaskUseCase updateTaskUseCase;

    private final DeleteTaskUseCase deleteTaskUseCase;

    @GetMapping
    public List<TaskDto> getAllTasks() {
        return getTasksUseCase.getAllTasks()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable("id") Integer id) {
        var task = getTaskUseCase.getTask(id);
        return convertToDto(task);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTask(@Valid @RequestBody TaskDto taskDto) {
        var task = convertToEntity(taskDto);
        createTaskUseCase.createTask(task);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editTask(@Valid @RequestBody TaskDto taskDto) {
        var task = convertToEntity(taskDto);
        updateTaskUseCase.updateTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Integer id) {
        deleteTaskUseCase.deleteTask(id);
    }

    private TaskDto convertToDto(Task task) {
        var taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setDescription(task.getDescription());
        taskDto.setDueDate(task.getDueDate());

        return taskDto;
    }

    private Task convertToEntity(TaskDto taskDto) {
        return Task.builder()
                .id(taskDto.getId())
                .description(taskDto.getDescription())
                .dueDate(taskDto.getDueDate())
                .build();
    }
}
