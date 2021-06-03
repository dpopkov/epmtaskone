package ru.dpopkov.tasktracker;

import ru.dpopkov.tasktracker.model.Project;
import ru.dpopkov.tasktracker.model.User;
import ru.dpopkov.tasktracker.services.ProjectService;
import ru.dpopkov.tasktracker.services.UserService;

import java.util.List;
import java.util.Optional;

public class TaskTracker {

    private final UserService userService;
    private final ProjectService projectService;

    public TaskTracker(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    public void createUser(String firstName, String lastName) {
        User user = new User(firstName, lastName);
        userService.create(user);
    }

    public Optional<User> getUserById(int id) {
        return userService.findById((long) id);
    }

    public boolean deleteUser(int id) {
        return userService.delete((long) id);
    }

    public List<User> getAllUsers() {
        return userService.getAll();
    }

    public void createProject(String name, String description) {
        Project project = new Project(name, description);
        projectService.create(project);
    }

    public Optional<Project> getProjectById(int id) {
        return projectService.findById((long) id);
    }

    public boolean deleteProject(int id) {
        return projectService.delete((long) id);
    }

    public List<Project> getAllProjects() {
        return projectService.getAll();
    }
}
