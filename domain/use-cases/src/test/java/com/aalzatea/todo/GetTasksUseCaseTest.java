package com.aalzatea.todo;

import com.aalzatea.commons.test.beans.generators.BeanGenerator;
import com.aalzatea.todo.entities.Task;
import com.aalzatea.todo.gateways.TaskGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetTasksUseCaseTest {

    @Mock
    private TaskGateway taskGateway;

    @InjectMocks
    private GetTasksUseCase underTest;

    @Test
    @DisplayName("Get all tasks saved in data source")
    void testGetAllTasks() {
        var tasks = BeanGenerator.generateBeanWithGenericTypes(List.class, Task.class);

        when(taskGateway.getAllTasks()).thenReturn(tasks);

        var result = underTest.getAllTasks();

        assertNotNull(result);
        assertEquals(tasks.size(), result.size());
        assertEquals(tasks, result);

        verify(taskGateway).getAllTasks();
    }

    @Test
    @DisplayName("Get all tasks method return a null")
    void testGetAllTasksWithNullListReturned() {
        when(taskGateway.getAllTasks()).thenReturn(null);

        var result = underTest.getAllTasks();

        assertNull(result);

        verify(taskGateway).getAllTasks();
    }

    @Test
    @DisplayName("Get all tasks throws an error")
    void testGetAllTasksThrowingError() {
        when(taskGateway.getAllTasks()).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> underTest.getAllTasks());

        verify(taskGateway).getAllTasks();
    }
}