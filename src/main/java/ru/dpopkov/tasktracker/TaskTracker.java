package ru.dpopkov.tasktracker;

import ru.dpopkov.tasktracker.model.Project;
import ru.dpopkov.tasktracker.model.Task;
import ru.dpopkov.tasktracker.model.User;
import ru.dpopkov.tasktracker.services.ProjectService;
import ru.dpopkov.tasktracker.services.TaskService;
import ru.dpopkov.tasktracker.services.UserService;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class TaskTracker {

    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;

    public TaskTracker(UserService userService, ProjectService projectService, TaskService taskService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
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

    public void createTask(String name, String description, Project project, Duration time) {
        Task task = new Task(name, description, project, time);
        taskService.create(task);
    }

    public Optional<Task> getTaskById(int id) {
        return taskService.findById((long) id);
    }

    public boolean deleteTask(int id) {
        return taskService.delete((long) id);
    }

    public List<Task> getAllTasks() {
        return taskService.getAll();
    }

    public Result assignUserToProject(int userId, int projectId) {
        Optional<User> userFound = getUserById(userId);
        if (userFound.isEmpty()) {
            return Result.USER_NOT_FOUND;
        }
        Optional<Project> projectFound = getProjectById(projectId);
        if (projectFound.isEmpty()) {
            return Result.PROJECT_NOT_FOUND;
        }
        projectFound.get().addUser(userFound.get());
        // todo: save changes if DB is used
        return Result.SUCCESS;
    }

    public enum Result {
        SUCCESS,
        USER_NOT_FOUND,
        PROJECT_NOT_FOUND
    }
}
