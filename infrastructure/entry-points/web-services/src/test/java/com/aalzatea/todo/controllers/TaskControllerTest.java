package com.aalzatea.todo.controllers;

import com.aalzatea.commons.test.beans.generators.BeanGenerator;
import com.aalzatea.todo.CreateTaskUseCase;
import com.aalzatea.todo.DeleteTaskUseCase;
import com.aalzatea.todo.GetTaskUseCase;
import com.aalzatea.todo.GetTasksUseCase;
import com.aalzatea.todo.UpdateTaskUseCase;
import com.aalzatea.todo.controllers.dtos.TaskDto;
import com.aalzatea.todo.entities.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private GetTasksUseCase getTasksUseCase;

    @Mock
    private GetTaskUseCase getTaskUseCase;

    @Mock
    private CreateTaskUseCase createTaskUseCase;

    @Mock
    private UpdateTaskUseCase updateTaskUseCase;

    @Mock
    private DeleteTaskUseCase deleteTaskUseCase;

    @InjectMocks
    private TaskController underTest;

    @Test
    @DisplayName("Get all tasks")
    @SuppressWarnings("unchecked")
    void testGetAllTasks() {
        var tasks = (List<Task>) BeanGenerator.generateBeanWithGenericTypes(List.class, Task.class);

        when(getTasksUseCase.getAllTasks()).thenReturn(tasks);

        var result = underTest.getAllTasks();

        assertNotNull(result);
        assertNotEquals(0, result.size());
        assertEquals(tasks.size(), result.size());

        verify(getTasksUseCase).getAllTasks();
    }

    @Test
    @DisplayName("Get all tasks return a null list")
    void testGetAllTasksWithNullListReturned() {
        when(getTasksUseCase.getAllTasks()).thenReturn(null);

        assertThrows(NullPointerException.class, () -> underTest.getAllTasks());

        verify(getTasksUseCase).getAllTasks();
    }

    @Test
    @DisplayName("Get all tasks throws an error")
    void testGetAllTasksWithThrowingError() {
        when(getTasksUseCase.getAllTasks()).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> underTest.getAllTasks());

        verify(getTasksUseCase).getAllTasks();
    }

    @Test
    @DisplayName("Get a specific task")
    void testGetTask() {
        var task = BeanGenerator.generateBean(Task.class);

        when(getTaskUseCase.getTask(task.getId())).thenReturn(task);

        var result = underTest.getTask(task.getId());

        assertNotNull(result);
        assertEquals(task.getId(), result.getId());

        verify(getTaskUseCase).getTask(task.getId());
    }

    @Test
    @DisplayName("Get a specific task but null value is returned")
    void testGetTaskWithNullValueReturned() {
        var taskId = BeanGenerator.generateBean(Integer.class);

        when(getTaskUseCase.getTask(taskId)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> underTest.getTask(taskId));

        verify(getTaskUseCase).getTask(taskId);
    }

    @Test
    @DisplayName("Get a specific task but throws an error")
    void testGetTaskWithThrowingError() {
        var taskId = BeanGenerator.generateBean(Integer.class);

        when(getTaskUseCase.getTask(taskId)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> underTest.getTask(taskId));

        verify(getTaskUseCase).getTask(taskId);
    }

    @Test
    @DisplayName("Add a task")
    void testAddTask() {
        var taskDto = BeanGenerator.generateBean(TaskDto.class);

        doNothing().when(createTaskUseCase).createTask(any(Task.class));

        underTest.addTask(taskDto);

        verify(createTaskUseCase).createTask(any(Task.class));
    }

    @Test
    @DisplayName("Add a task but passing a null Dto")
    void testAddTaskWithPassingNullDto() {
        assertThrows(NullPointerException.class, () -> underTest.addTask(null));

        verify(createTaskUseCase, times(0)).createTask(any(Task.class));
    }

    @Test
    @DisplayName("Add a task but throws an error")
    void testAddTaskWithThrowingError() {
        var taskDto = BeanGenerator.generateBean(TaskDto.class);

        doThrow(IllegalArgumentException.class).when(createTaskUseCase).createTask(any(Task.class));

        assertThrows(IllegalArgumentException.class, () -> underTest.addTask(taskDto));

        verify(createTaskUseCase).createTask(any(Task.class));
    }

    @Test
    @DisplayName("Update a task")
    void testEditTask() {
        var taskDto = BeanGenerator.generateBean(TaskDto.class);

        doNothing().when(updateTaskUseCase).updateTask(any(Task.class));

        underTest.editTask(taskDto);

        verify(updateTaskUseCase).updateTask(any(Task.class));
    }

    @Test
    @DisplayName("Update a task passing a null task")
    void testEditTaskWithPassingNullTask() {
        assertThrows(NullPointerException.class, () -> underTest.editTask(null));

        verify(updateTaskUseCase, times(0)).updateTask(any(Task.class));
    }

    @Test
    @DisplayName("Update a task but throws an error")
    void testEditTaskWithThrowingError() {
        var taskDto = BeanGenerator.generateBean(TaskDto.class);

        doThrow(IllegalArgumentException.class).when(updateTaskUseCase).updateTask(any(Task.class));

        assertThrows(IllegalArgumentException.class, () -> underTest.editTask(taskDto));

        verify(updateTaskUseCase).updateTask(any(Task.class));
    }

    @Test
    @DisplayName("Delete a task")
    void testDeleteTask() {
        var taskId = BeanGenerator.generateBean(Integer.class);

        doNothing().when(deleteTaskUseCase).deleteTask(taskId);

        underTest.deleteTask(taskId);

        verify(deleteTaskUseCase).deleteTask(taskId);
    }

    @Test
    @DisplayName("Delete a task but throws an error")
    void testDeleteTaskWithThrowingError() {
        var taskId = BeanGenerator.generateBean(Integer.class);

        doThrow(IllegalArgumentException.class).when(deleteTaskUseCase).deleteTask(taskId);

        assertThrows(IllegalArgumentException.class, () -> underTest.deleteTask(taskId));

        verify(deleteTaskUseCase).deleteTask(taskId);
    }
}