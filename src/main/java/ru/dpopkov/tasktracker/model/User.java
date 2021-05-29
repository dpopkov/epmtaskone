package ru.dpopkov.tasktracker.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class User {

    private Long id;
    private String firstName;
    private String lastName;

    /**
     * All projects this user is assigned to.
     * This field is excluded from from some methods to avoid java.lang.StackOverflowError
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Project> projects = new HashSet<>();

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void removeProject(Project project) {
        projects.remove(project);
    }
}
