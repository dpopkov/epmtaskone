package ru.dpopkov.tasktracker;

public interface Action {

    ActionType type();

    String description();

    void execute();
}
