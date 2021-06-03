package ru.dpopkov.tasktracker.services;

import ru.dpopkov.tasktracker.model.Task;
import ru.dpopkov.tasktracker.repositories.TaskRepository;

public class TaskServiceImpl extends AbstractBaseService<Task> implements TaskService {

    public TaskServiceImpl(TaskRepository repository) {
        super(repository);
    }
}
