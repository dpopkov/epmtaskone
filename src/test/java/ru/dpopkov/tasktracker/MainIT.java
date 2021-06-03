package ru.dpopkov.tasktracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class MainIT {

    private static final String NL = System.lineSeparator();

    @Test
    @DisplayName("Running Main should print expected textual menu representation to System.out")
    void testMain() {
        String exitCommand = "9" + NL;
        ByteArrayInputStream input = new ByteArrayInputStream(exitCommand.getBytes());
        System.setIn(input);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        String expected = String.join(NL, "",
                "MENU:",
                " 1: Add new user",
                " 2: Find user by ID",
                " 3: Delete user",
                " 4: Show all users",
                " 5: Add project",
                " 6: Find project by ID",
                " 7: Delete project",
                " 8: Show all projects",
                " 9: Exit",
                "Enter number of action: ");

        Locale.setDefault(Locale.US);
        Main.main(new String[0]);

        String actual = buffer.toString();
        assertEquals(expected, actual);
    }
}
