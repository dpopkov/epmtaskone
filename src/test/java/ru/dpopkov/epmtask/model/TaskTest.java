package ru.dpopkov.epmtask.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    @DisplayName("When closing task then task is closed")
    void testClose() {
        Task task = new Task();
        assertTrue(task.isOpen());
        assertFalse(task.isClosed());

        task.close();

        assertFalse(task.isOpen());
        assertTrue(task.isClosed());
    }
}
