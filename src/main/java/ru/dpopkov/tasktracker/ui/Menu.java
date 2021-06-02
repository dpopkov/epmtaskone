package ru.dpopkov.tasktracker.ui;

import ru.dpopkov.tasktracker.ActionType;

/**
 * Represents a menu allowing to show and select actions.
 */
public interface Menu {

    /** Initializes the menu and prepares it before showing. */
    void prepareForShow();

    /** Shows all menu items. */
    void show();

    /** Allows to select a menu option. */
    ActionType select();
}
