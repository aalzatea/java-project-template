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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteTaskUseCaseTest {

    @Mock
    private TaskGateway taskGateway;

    @InjectMocks
    private DeleteTaskUseCase underTest;

    @Test
    @DisplayName("Delete a task")
    void testDeleteTask() {
        var task = BeanGenerator.generateBean(Task.class);

        when(taskGateway.findTaskById(task.getId())).thenReturn(task);
        doNothing().when(taskGateway).deleteTask(task.getId());

        underTest.deleteTask(task.getId());

        verify(taskGateway).findTaskById(task.getId());
        verify(taskGateway).deleteTask(task.getId());
    }

    @Test
    @DisplayName("Delete a task passing a null ID")
    void testDeleteTaskWithNullID() {
        Integer taskId = null;

        when(taskGateway.findTaskById(taskId)).thenReturn(null);

        assertThrows(DataNotFoundException.class, () -> underTest.deleteTask(taskId));

        verify(taskGateway).findTaskById(taskId);
        verify(taskGateway, times(0)).deleteTask(taskId);
    }

    @Test
    @DisplayName("Delete a task but task does not exist")
    void testDeleteTaskWithTaskNotFound() {
        var taskId = BeanGenerator.generateBean(Integer.class);

        when(taskGateway.findTaskById(taskId)).thenReturn(null);

        assertThrows(DataNotFoundException.class, () -> underTest.deleteTask(taskId));

        verify(taskGateway).findTaskById(taskId);
        verify(taskGateway, times(0)).deleteTask(taskId);
    }

    @Test
    @DisplayName("Delete a task but find task by ID throws an error")
    void testDeleteTaskWithFindTaskByIDThrowingError() {
        var taskId = BeanGenerator.generateBean(Integer.class);

        when(taskGateway.findTaskById(taskId)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> underTest.deleteTask(taskId));

        verify(taskGateway).findTaskById(taskId);
        verify(taskGateway, times(0)).deleteTask(taskId);
    }

    @Test
    @DisplayName("Delete a task but delete method throws an error")
    void testDeleteTaskWithDeleteMethodThrowingError() {
        var task = BeanGenerator.generateBean(Task.class);

        when(taskGateway.findTaskById(task.getId())).thenReturn(task);
        doThrow(IllegalArgumentException.class).when(taskGateway).deleteTask(task.getId());

        assertThrows(IllegalArgumentException.class, () -> underTest.deleteTask(task.getId()));

        verify(taskGateway).findTaskById(task.getId());
        verify(taskGateway).deleteTask(task.getId());
    }
}