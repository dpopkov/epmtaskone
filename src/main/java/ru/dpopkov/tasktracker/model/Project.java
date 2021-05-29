package ru.dpopkov.tasktracker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class Project extends BaseEntity {

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

