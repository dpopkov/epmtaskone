package ru.dpopkov.epmtask.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class Project {

    private Long id;
    private String name;
    private String description;
    private Set<User> users = new HashSet<>();

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addUser(User user) {
        users.add(user);
        user.addProject(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.removeProject(this);
    }
}
