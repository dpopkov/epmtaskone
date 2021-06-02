package ru.dpopkov.tasktracker;

import ru.dpopkov.tasktracker.repositories.inmemory.SimpleUserRepository;
import ru.dpopkov.tasktracker.services.UserServiceImpl;
import ru.dpopkov.tasktracker.ui.*;
import ru.dpopkov.tasktracker.ui.actions.Actions;
import ru.dpopkov.tasktracker.ui.actions.ActionPack;

public class Main {

    public static void main(String[] args) {
        TaskTracker taskTracker = new TaskTracker(new UserServiceImpl(new SimpleUserRepository()));

        UiInput input = new ConsoleUiInput();
        UiOutput output = new ConsoleUiOutput();
        ActionPack pack = new ActionPack(input, output, taskTracker);
        Actions actions = new Actions(pack);

        Menu menu = new TextualMenu(actions, output, input);
        UiCycle uiCycle = new UiCycle(actions, menu);
        uiCycle.run();
    }
}
