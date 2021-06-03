package ru.dpopkov.tasktracker.services;

import ru.dpopkov.tasktracker.model.Project;
import ru.dpopkov.tasktracker.repositories.ProjectRepository;

public class ProjectServiceImpl extends AbstractBaseService<Project> implements ProjectService {

    public ProjectServiceImpl(ProjectRepository repository) {
        super(repository);
    }
}
