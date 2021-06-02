package ru.dpopkov.tasktracker.ui.actions;

import ru.dpopkov.tasktracker.Action;
import ru.dpopkov.tasktracker.ActionType;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Actions {

    private final EnumMap<ActionType, Action> map = new EnumMap<>(ActionType.class);

    public Actions(Iterable<Action> actionIterable) {
        for (Action a : actionIterable) {
            add(a);
        }
    }

    public void add(Action action) {
        map.put(action.type(), action);
    }

    public Action get(ActionType type) {
        return map.get(type);
    }

    public List<ActionType> getActionTypes() {
        return new ArrayList<>(map.keySet());
    }
}
