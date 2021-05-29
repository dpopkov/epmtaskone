package ru.dpopkov.tasktracker.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    private final User user = new User("Jane", "Doe");
    private final Project project = new Project("Name", "Description");

    @Test
    @DisplayName("Field ID is used in equals()")
    void testEqualsById() {
        Project other = new Project("Name", "Description");
        other.setId(10L);
        assertNotEquals(project, other);
        project.setId(10L);
        assertEquals(project, other);
    }

    @Test
    @DisplayName("When add user then project contains user and user contains project")
    void testAddUser() {
        assertFalse(project.getUsers().contains(user));
        assertFalse(user.getProjects().contains(project));

        project.addUser(user);

        assertTrue(project.getUsers().contains(user));
        assertTrue(user.getProjects().contains(project));
    }

    @Test
    @DisplayName("When remove user then project does not contain user and user does not contain project")
    void testRemoveUser() {
        project.addUser(user);
        assertTrue(project.getUsers().contains(user));
        assertTrue(user.getProjects().contains(project));

        project.removeUser(user);

        assertFalse(project.getUsers().contains(user));
        assertFalse(user.getProjects().contains(project));
    }
}
