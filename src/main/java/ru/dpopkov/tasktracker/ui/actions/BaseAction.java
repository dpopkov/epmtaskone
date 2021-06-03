package ru.dpopkov.tasktracker.ui.actions;

import ru.dpopkov.tasktracker.Action;
import ru.dpopkov.tasktracker.ActionType;
import ru.dpopkov.tasktracker.ui.Messages;

public abstract class BaseAction implements Action {
    private final ActionType type;
    private final String description;

    protected BaseAction(ActionType type) {
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
}
