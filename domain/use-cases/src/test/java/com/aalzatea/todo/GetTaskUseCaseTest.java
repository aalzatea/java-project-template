package com.aalzatea.todo;

import com.aalzatea.commons.test.beans.generators.BeanGenerator;
import com.aalzatea.todo.entities.Task;
import com.aalzatea.todo.exceptions.business.DataNotFoundException;
import com.aalzatea.todo.gateways.TaskGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetTaskUseCaseTest {

    @Mock
    private TaskGateway taskGateway;

    @InjectMocks
    private GetTaskUseCase underTest;

    @Test
    @DisplayName("Get an specific task")
    void testGetTask() {
        var task = BeanGenerator.generateBean(Task.class);

        when(taskGateway.findTaskById(task.getId())).thenReturn(task);

        var result = underTest.getTask(task.getId());

        assertNotNull(result);
        assertEquals(task, result);

        verify(taskGateway).findTaskById(task.getId());
    }

    @Test
    @DisplayName("Get an specific task but does not exist")
    void testGetTaskWithTaskNotFound() {
        var taskId = BeanGenerator.generateBean(Integer.class);

        when(taskGateway.findTaskById(taskId)).thenReturn(null);

        assertThrows(DataNotFoundException.class, () -> underTest.getTask(taskId));

        verify(taskGateway).findTaskById(taskId);
    }

    @Test
    @DisplayName("Get an specific task passing null value")
    void testGetTaskWithPassingNullValue() {
        Integer taskId = null;

        when(taskGateway.findTaskById(taskId)).thenReturn(null);

        assertThrows(DataNotFoundException.class, () -> underTest.getTask(taskId));

        verify(taskGateway).findTaskById(taskId);
    }

    @Test
    @DisplayName("Get an specific but throwing an error")
    void testGetTaskWithThrowingError() {
        var taskId = BeanGenerator.generateBean(Integer.class);;

        when(taskGateway.findTaskById(taskId)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> underTest.getTask(taskId));

        verify(taskGateway).findTaskById(taskId);
    }
}