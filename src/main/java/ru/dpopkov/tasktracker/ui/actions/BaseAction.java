package ru.dpopkov.tasktracker.ui.actions;

import ru.dpopkov.tasktracker.Action;
import ru.dpopkov.tasktracker.ActionType;
import ru.dpopkov.tasktracker.TaskTracker;
import ru.dpopkov.tasktracker.ui.Messages;
import ru.dpopkov.tasktracker.ui.UiInput;
import ru.dpopkov.tasktracker.ui.UiOutput;

public abstract class BaseAction implements Action {
    private final UiInput input;
    private final UiOutput output;
    private final TaskTracker tracker;
    private final ActionType type;
    private final String description;

    protected BaseAction(UiInput input, UiOutput output, TaskTracker tracker, ActionType type) {
        this.input = input;
        this.output = output;
        this.tracker = tracker;
        this.type = type;
        description = Messages.INSTANCE.get(type.messageNameForI18n());
    }

    @Override
    public ActionType type() {
        return type;
    }

    @Override
    public String description() {
        return description;
    }

    public UiInput getInput() {
        return input;
    }

    public UiOutput getOutput() {
        return output;
    }

    public TaskTracker getTracker() {
        return tracker;
    }
}
