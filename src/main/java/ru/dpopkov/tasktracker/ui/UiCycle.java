package ru.dpopkov.tasktracker.ui;

import ru.dpopkov.tasktracker.Action;
import ru.dpopkov.tasktracker.ActionType;
import ru.dpopkov.tasktracker.ui.actions.Actions;

/**
 * Provides UI cycle that should be started and can be stopped.
 */
public class UiCycle {

    private final Actions actions;
    private final Menu menu;

    private boolean isRunning = true;

    public UiCycle(Actions actions, Menu menu) {
        this.actions = actions;
        this.menu = menu;
        this.actions.add(new ExitAction());
    }

    public void stop() {
        isRunning = false;
    }

    public void run() {
        menu.prepareForShow();
        while (isRunning) {
            menu.show();
            ActionType type = menu.select();
            Action action = actions.get(type);
            action.execute();
        }
    }

    private class ExitAction implements Action {

        private final String description = Messages.INSTANCE.get("exit");

        @Override
        public ActionType type() {
            return ActionType.EXIT_APP;
        }

        @Override
        public String description() {
            return description;
        }

        @Override
        public void execute() {
            stop();
        }
    }
}
