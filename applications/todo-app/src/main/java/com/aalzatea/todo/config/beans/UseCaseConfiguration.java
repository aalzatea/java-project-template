package com.aalzatea.todo.config.beans;

import com.aalzatea.todo.CreateTaskUseCase;
import com.aalzatea.todo.DeleteTaskUseCase;
import com.aalzatea.todo.GetTaskUseCase;
import com.aalzatea.todo.GetTasksUseCase;
import com.aalzatea.todo.UpdateTaskUseCase;
import com.aalzatea.todo.gateways.TaskGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public GetTasksUseCase getTasksUseCase(TaskGateway taskGateway) {
        return new GetTasksUseCase(taskGateway);
    }

    @Bean
    public GetTaskUseCase getTaskUseCase(TaskGateway taskGateway) {
        return new GetTaskUseCase(taskGateway);
    }

    @Bean
    public CreateTaskUseCase createTaskUseCase(TaskGateway taskGateway) {
        return new CreateTaskUseCase(taskGateway);
    }

    @Bean
    public UpdateTaskUseCase updateTaskUseCase(TaskGateway taskGateway) {
        return new UpdateTaskUseCase(taskGateway);
    }

    @Bean
    public DeleteTaskUseCase deleteTaskUseCase(TaskGateway taskGateway) {
        return new DeleteTaskUseCase(taskGateway);
    }
}
