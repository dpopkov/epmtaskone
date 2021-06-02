package ru.dpopkov.tasktracker.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.dpopkov.tasktracker.model.User;
import ru.dpopkov.tasktracker.repositories.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;

    @Test
    @DisplayName("When creating user then uses repository to save user")
    void testCreateUser() {
        User user = new User("first", "last");
        boolean created = userService.create(user);
        assertTrue(created);
        verify(userRepository).save(user);
    }

    @Test
    @DisplayName("findById calls method findById of repository")
    void testFindById() {
        userService.findById(10L);
        verify(userRepository).findById(10L);
    }

    @Test
    @DisplayName("delete calls method deleteById of repository")
    void testDelete() {
        userService.delete(10L);
        verify(userRepository).deleteById(10L);
    }

    @Test
    @DisplayName("getAll calls method getAll of repository")
    void testFindAll() {
        List<User> all = userService.getAll();
        assertNotNull(all);
        verify(userRepository).getAll();
    }
}
