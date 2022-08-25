package com.aalzatea.todo;

import com.aalzatea.commons.test.beans.generators.BeanGenerator;
import com.aalzatea.todo.entities.Task;
import com.aalzatea.todo.exceptions.business.DataNotFoundException;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateTaskUseCaseTest {

    @Mock
    private TaskGateway taskGateway;

    @InjectMocks
    private UpdateTaskUseCase underTest;

    @Test
    @DisplayName("Update a specific task")
    void updateTask() {
        var dueDate = Date.from(LocalDateTime.now().plus(1, ChronoUnit.DAYS).atZone(ZoneId.systemDefault()).toInstant());
        var task = BeanGenerator.generateBean(Task.class)
                .toBuilder()
                .dueDate(dueDate)
                .build();

        when(taskGateway.findTaskById(task.getId())).thenReturn(task);
        doNothing().when(taskGateway).updateTask(any(Task.class));

        underTest.updateTask(task);

        verify(taskGateway).findTaskById(task.getId());
        verify(taskGateway).updateTask(any(Task.class));
    }

    @Test
    @DisplayName("Update a specific task with a wrong field value")
    void updateTaskWithAWrongFieldValue() {
        var dueDate = Date.from(LocalDateTime.now().minus(1, ChronoUnit.DAYS).atZone(ZoneId.systemDefault()).toInstant());
        var task = BeanGenerator.generateBean(Task.class)
                .toBuilder()
                .dueDate(dueDate)
                .build();

        assertThrows(DataValidationException.class, () -> underTest.updateTask(task));

        verify(taskGateway, times(0)).findTaskById(task.getId());
        verify(taskGateway, times(0)).updateTask(any(Task.class));
    }

    @Test
    @DisplayName("Update a specific task passing a null task")
    void updateTaskPassingNullTask() {
        assertThrows(IllegalArgumentException.class, () -> underTest.updateTask(null));

        verify(taskGateway, times(0)).findTaskById(anyInt());
        verify(taskGateway, times(0)).updateTask(any(Task.class));
    }

    @Test
    @DisplayName("Update a task does not exist")
    void updateTaskWithNotExistTask() {
        var dueDate = Date.from(LocalDateTime.now().plus(1, ChronoUnit.DAYS).atZone(ZoneId.systemDefault()).toInstant());
        var task = BeanGenerator.generateBean(Task.class)
                .toBuilder()
                .dueDate(dueDate)
                .build();

        when(taskGateway.findTaskById(task.getId())).thenReturn(null);

        assertThrows(DataNotFoundException.class, () -> underTest.updateTask(task));

        verify(taskGateway).findTaskById(task.getId());
        verify(taskGateway, times(0)).updateTask(any(Task.class));
    }

    @Test
    @DisplayName("Update a task does not exist")
    void updateTaskWithFindByIdThrowingError() {
        var dueDate = Date.from(LocalDateTime.now().plus(1, ChronoUnit.DAYS).atZone(ZoneId.systemDefault()).toInstant());
        var task = BeanGenerator.generateBean(Task.class)
                .toBuilder()
                .dueDate(dueDate)
                .build();

        when(taskGateway.findTaskById(task.getId())).thenThrow(IllegalArgumentException.class);

        assertThrows(DataNotFoundException.class, () -> underTest.updateTask(task));

        verify(taskGateway).findTaskById(task.getId());
        verify(taskGateway, times(0)).updateTask(any(Task.class));
    }
}