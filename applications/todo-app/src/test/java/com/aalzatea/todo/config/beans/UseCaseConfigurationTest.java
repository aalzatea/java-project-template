package com.aalzatea.todo.config.beans;

import com.aalzatea.todo.gateways.TaskGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UseCaseConfigurationTest {

    @Mock
    private TaskGateway taskGateway;

    @InjectMocks
    private UseCaseConfiguration underTest;

    @Test
    void getTasksUseCase() {
        var tasksUseCase = underTest.getTasksUseCase(taskGateway);

        assertNotNull(tasksUseCase);
    }

    @Test
    void getTaskUseCase() {
        var taskUseCase = underTest.getTaskUseCase(taskGateway);

        assertNotNull(taskUseCase);
    }

    @Test
    void createTaskUseCase() {
        var createTaskUseCase = underTest.createTaskUseCase(taskGateway);

        assertNotNull(createTaskUseCase);
    }

    @Test
    void updateTaskUseCase() {
        var updateTaskUseCase = underTest.updateTaskUseCase(taskGateway);

        assertNotNull(updateTaskUseCase);
    }

    @Test
    void deleteTaskUseCase() {
        var deleteTaskUseCase = underTest.deleteTaskUseCase(taskGateway);

        assertNotNull(deleteTaskUseCase);
    }
}