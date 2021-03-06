package ru.dpopkov.tasktracker;

public enum ActionType {
    ADD_USER,
    FIND_USER_BY_ID,
    DELETE_USER,
    SHOW_ALL_USERS,
    ADD_PROJECT,
    FIND_PROJECT_BY_ID,
    DELETE_PROJECT,
    SHOW_ALL_PROJECTS,
    ADD_TASK,
    FIND_TASK_BY_ID,
    DELETE_TASK,
    SHOW_ALL_TASKS,
    ASSIGN_USER_TO_PROJECT,
    EXIT_APP;

    /**
     * Name of the internationalized message string used as a description of an {@link Action}.
     */
    public String messageNameForI18n() {
        return name().toLowerCase();
    }
}
