package ru.dpopkov.tasktracker;

public class Main {

    public static void main(String[] args) {
        MessageProvider messageProvider = new MessageProvider();
        System.out.println("Message: " + messageProvider.getMessage());
    }
}
