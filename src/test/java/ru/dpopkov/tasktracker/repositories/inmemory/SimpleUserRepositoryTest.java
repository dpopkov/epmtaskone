package ru.dpopkov.tasktracker.repositories.inmemory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dpopkov.tasktracker.model.User;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class SimpleUserRepositoryTest {

    private final SimpleUserRepository repository = new SimpleUserRepository();

    @Test
    @DisplayName("Saves user and assigns ID")
    void testSaveUser() {
        User user = new User("first", "last");
        repository.save(user);

        Optional<User> result = repository.findById(user.getId());
        assertTrue(result.isPresent());
        User found = result.get();
        assertNotNull(found.getId());
        assertEquals(user, found);
    }

    @Test
    @DisplayName("Returns all saved users")
    void testGetGetAll() {
        User u1 = new User("1", "2");
        User u2 = new User("3", "4");
        repository.save(u1);
        repository.save(u2);
        List<User> list = repository.getAll();
        assertThat(list, containsInAnyOrder(u1, u2));
    }

    @Test
    @DisplayName("Deletes user by ID")
    void testDeleteById() {
        User u1 = new User("1", "2");
        repository.save(u1);
        assertTrue(repository.findById(u1.getId()).isPresent());

        boolean deleted = repository.deleteById(u1.getId());
        assertTrue(deleted);
        assertFalse(repository.findById(u1.getId()).isPresent());
    }
}
