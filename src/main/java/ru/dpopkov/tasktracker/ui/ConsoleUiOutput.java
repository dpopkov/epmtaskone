package ru.dpopkov.tasktracker.ui;

public class ConsoleUiOutput implements UiOutput {

    @Override
    public void print(String s) {
        System.out.print(s);
    }

    @Override
    public void println(String s) {
        System.out.println(s);
    }
}
