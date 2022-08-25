package com.aalzatea.todo;

import com.aalzatea.todo.entities.Task;
import com.aalzatea.todo.gateways.TaskGateway;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class TaskAdapter implements TaskGateway {

    private static final List<Task> TASKS;

    static {
        var immutableTasks = Arrays.asList(
                Task.builder().id(1).creationDate(new Date()).description("Fill the thermos with water").build(),
                Task.builder().id(2).creationDate(new Date()).description("Finish the adventure topic survey").build(),
                Task.builder().id(3).creationDate(new Date()).description("Document the base template project").build(),
                Task.builder().id(4).creationDate(new Date()).description("Check the bag whether all items are ok or are not").build(),
                Task.builder().id(5).creationDate(new Date()).description("Go to the grocery and buy some vegetables").build()
        );

        TASKS = new ArrayList<>(immutableTasks);
    }

    @Override
    public List<Task> getAllTasks() {
        return TASKS;
    }

    @Override
    public Task findTaskById(Integer id) {
        return TASKS.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Integer getLastTaskId() {
        return TASKS.stream()
                .map(Task::getId)
                .max(Comparator.naturalOrder())
                .orElse(null);
    }

    @Override
    public void createTask(Task task) {
        TASKS.add(task);
    }

    @Override
    public void updateTask(Task task) {
        for(int i = 0; i < TASKS.size(); i++) {
            if(TASKS.get(i).getId().equals(task.getId())) {
                TASKS.set(i, task);
            }
        }
    }

    @Override
    public void deleteTask(Integer id) {
        var taskFound = TASKS.stream()
                .filter(task -> Objects.equals(task.getId(), id))
                .findFirst();

        if(taskFound.isEmpty())
            return;

        TASKS.remove(taskFound.get());
    }
}
