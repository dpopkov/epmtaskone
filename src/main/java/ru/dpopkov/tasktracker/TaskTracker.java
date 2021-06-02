package ru.dpopkov.tasktracker;

import ru.dpopkov.tasktracker.model.User;
import ru.dpopkov.tasktracker.services.UserService;

import java.util.List;
import java.util.Optional;

public class TaskTracker {

    private final UserService userService;

    public TaskTracker(UserService userService) {
        this.userService = userService;
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
}
