package com.aalzatea.todo;

import com.aalzatea.commons.test.beans.generators.BeanGenerator;
import com.aalzatea.todo.entities.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class TaskAdapterTest {

    @InjectMocks
    private TaskAdapter underTest;

    @Test
    @DisplayName("Get all tasks from the source data")
    void testGetAllTasks() {
        var tasks = underTest.getAllTasks();

        assertNotNull(tasks);
        assertNotEquals(0, tasks.size());
    }

    @Test
    @DisplayName("Find a task using its ID from the source data")
    void testFindTaskById() {
        var task = underTest.findTaskById(1);

        assertNotNull(task);
        assertEquals(1, task.getId());
    }

    @Test
    @DisplayName("Get last task ID")
    @Order(1)
    void testGetLastTaskId() {
        var lastId = underTest.getLastTaskId();

        assertNotNull(lastId);
        assertEquals(5, lastId);
    }

    @Test
    @DisplayName("Create a task in data source")
    @Order(2)
    void testCreateTask() {
        var task = BeanGenerator.generateBean(Task.class)
                .toBuilder()
                .id(6)
                .build();

        underTest.createTask(task);
    }

    @Test
    @DisplayName("Update a task in source data")
    @Order(3)
    void testUpdateTask() {
        var task = BeanGenerator.generateBean(Task.class)
                .toBuilder()
                .id(6)
                .build();

        underTest.updateTask(task);
    }

    @Test
    @DisplayName("Delete a task in source data")
    @Order(4)
    void testDeleteTask() {
        underTest.deleteTask(6);
    }
}