package ru.dpopkov.epmtask;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageProviderTest {

    @Test
    @DisplayName("Should return 'Hello test task' string")
    void testGetMessage() {
        MessageProvider provider = new MessageProvider();
        String actual = provider.getMessage();
        assertEquals("Hello test task", actual);
    }
}
