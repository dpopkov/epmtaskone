package ru.dpopkov.tasktracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainIT {

    private static final String NL = System.lineSeparator();

    @Test
    @DisplayName("Running Main should print expected textual menu representation to System.out")
    void testMain() {
        String inputCommand = "3" + NL;
        ByteArrayInputStream input = new ByteArrayInputStream(inputCommand.getBytes());
        System.setIn(input);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        String expected = String.join(NL, "",
                "MENU:",
                " 1: Add new user",
                " 2: Show all users",
                " 3: Exit",
                "Enter number of action: ");

        Main.main(new String[0]);

        String actual = buffer.toString();
        assertEquals(expected, actual);
    }
}
