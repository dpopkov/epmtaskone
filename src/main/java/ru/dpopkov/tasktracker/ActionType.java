package ru.dpopkov.tasktracker;

public enum ActionType {
    ADD_USER,
    FIND_USER_BY_ID,
    DELETE_USER,
    SHOW_ALL_USERS,
    EXIT_APP;

    /**
     * Name of the internationalized message string used as a description of an {@link Action}.
     */
    public String messageNameForI18n() {
        return name().toLowerCase();
    }
}
