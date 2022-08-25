package com.aalzatea.todo;

import com.aalzatea.commons.test.beans.generators.BeanGenerator;
import com.aalzatea.todo.entities.Task;
import com.aalzatea.todo.exceptions.business.DataValidationException;
import com.aalzatea.todo.gateways.TaskGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateTaskUseCaseTest {

    @Mock
    private TaskGateway taskGateway;

    @InjectMocks
    private CreateTaskUseCase underTest;

    @Test
    @DisplayName("Create a task")
    void testCreateTask() {
        var dueDate = Date.from(LocalDateTime.now().plus(1, ChronoUnit.DAYS).atZone(ZoneId.systemDefault()).toInstant());
        var task = BeanGenerator.generateBean(Task.class)
                .toBuilder()
                .dueDate(dueDate)
                .build();

        doNothing().when(taskGateway).createTask(any(Task.class));

        underTest.createTask(task);

        verify(taskGateway).createTask(any(Task.class));
    }

    @Test
    @DisplayName("Create a task with a wrong field value")
    void testCreateTaskWithOneWrongFieldValue() {
        var dueDate = Date.from(LocalDateTime.now().minus(1, ChronoUnit.DAYS).atZone(ZoneId.systemDefault()).toInstant());
        var task = BeanGenerator.generateBean(Task.class)
                .toBuilder()
                .dueDate(dueDate)
                .build();

        assertThrows(DataValidationException.class, () -> underTest.createTask(task));

        verify(taskGateway, times(0)).createTask(any(Task.class));
    }

    @Test
    @DisplayName("Create a task with passing a null task")
    void testCreateTaskWithPassingNullTask() {
        assertThrows(IllegalArgumentException.class, () -> underTest.createTask(null));

        verify(taskGateway, times(0)).createTask(any(Task.class));
    }

    @Test
    @DisplayName("Create a task but method throws exception")
    void testCreateTaskWithThrowingException() {
        var dueDate = Date.from(LocalDateTime.now().plus(1, ChronoUnit.DAYS).atZone(ZoneId.systemDefault()).toInstant());
        var task = BeanGenerator.generateBean(Task.class)
                .toBuilder()
                .dueDate(dueDate)
                .build();

        doThrow(IllegalArgumentException.class).when(taskGateway).createTask(any(Task.class));

        assertThrows(IllegalArgumentException.class, () -> underTest.createTask(task));

        verify(taskGateway).createTask(any(Task.class));
    }
}