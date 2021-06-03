package ru.dpopkov.tasktracker.repositories.inmemory;

import ru.dpopkov.tasktracker.model.Task;
import ru.dpopkov.tasktracker.repositories.TaskRepository;

public class SimpleTaskRepository extends SimpleBaseRepository<Task> implements TaskRepository {
}
