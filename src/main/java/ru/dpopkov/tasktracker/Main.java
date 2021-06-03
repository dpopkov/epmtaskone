package ru.dpopkov.tasktracker;

import ru.dpopkov.tasktracker.repositories.inmemory.SimpleProjectRepository;
import ru.dpopkov.tasktracker.repositories.inmemory.SimpleTaskRepository;
import ru.dpopkov.tasktracker.repositories.inmemory.SimpleUserRepository;
import ru.dpopkov.tasktracker.services.*;
import ru.dpopkov.tasktracker.ui.*;
import ru.dpopkov.tasktracker.ui.actions.Actions;
import ru.dpopkov.tasktracker.ui.actions.ActionPack;

public class Main {

    public static void main(String[] args) {
        TaskTracker taskTracker = initTaskTracker();
        UiCycle uiCycle = initUiCycle(taskTracker);
        uiCycle.run();
    }

    private static TaskTracker initTaskTracker() {
        ProjectService projectService = new ProjectServiceImpl(new SimpleProjectRepository());
        UserService userService = new UserServiceImpl(new SimpleUserRepository());
        TaskService taskService = new TaskServiceImpl(new SimpleTaskRepository());
        return new TaskTracker(userService, projectService, taskService);
    }

    private static UiCycle initUiCycle(TaskTracker taskTracker) {
        UiInput input = new ConsoleUiInput();
        UiOutput output = new ConsoleUiOutput();
        ActionPack pack = new ActionPack(input, output, taskTracker);
        Actions actions = new Actions(pack);
        Menu menu = new TextualMenu(actions, output, input);
        return new UiCycle(actions, menu);
    }
}
