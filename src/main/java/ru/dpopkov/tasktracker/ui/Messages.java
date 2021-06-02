package ru.dpopkov.tasktracker.ui;

import java.util.ResourceBundle;

public enum Messages {
    INSTANCE;

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    public String get(String messageName) {
        return resourceBundle.getString(messageName);
    }
}
