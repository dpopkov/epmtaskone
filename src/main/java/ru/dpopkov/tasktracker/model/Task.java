package ru.dpopkov.tasktracker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Duration;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class Task extends BaseEntity {

    private String name;
    private String description;
    private Project project;
    private State state = State.OPEN;
    private Duration time;
    private Long superTaskId;
    private User createdBy;
    private User assignedTo;

    public Task(String name, String description, Project project, Duration time) {
        this.name = name;
        this.description = description;
        this.project = project;
        this.time = time;
    }

    public void close() {
        setState(State.CLOSED);
    }

    public boolean isOpen() {
        return state == State.OPEN;
    }

    public boolean isClosed() {
        return state == State.CLOSED;
    }

    public enum State {
        OPEN,
        CLOSED
    }
}
