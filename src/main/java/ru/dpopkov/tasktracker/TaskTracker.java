package ru.dpopkov.tasktracker;

import ru.dpopkov.tasktracker.model.User;
import ru.dpopkov.tasktracker.services.UserService;

import java.util.List;

public class TaskTracker {

    private final UserService userService;

    public TaskTracker(UserService userService) {
        this.userService = userService;
    }

    public void createUser(String firstName, String lastName) {
        User user = new User(firstName, lastName);
        userService.create(user);
    }

    public List<User> getAllUsers() {
        return userService.getAll();
    }
}
