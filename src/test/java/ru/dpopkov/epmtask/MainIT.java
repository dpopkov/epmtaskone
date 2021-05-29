package ru.dpopkov.epmtask;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainIT {

    @Test
    @DisplayName("Running Main should print expected string to System.out")
    void testMain() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        String expected = "Message: Hello test task" + System.lineSeparator();

        Main.main(new String[0]);

        String actual = buffer.toString();
        assertEquals(expected, actual);
    }
}
