package ru.dpopkov.tasktracker.ui;

import java.util.Scanner;

public class ConsoleUiInput implements UiInput {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readString() {
        return scanner.nextLine();
    }

    @Override
    public int readInt() {
        int n = scanner.nextInt();
        scanner.nextLine(); // eat newline character
        return n;
    }
}
