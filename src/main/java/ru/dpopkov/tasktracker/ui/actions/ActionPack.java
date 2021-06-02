package ru.dpopkov.tasktracker.ui.actions;

import ru.dpopkov.tasktracker.Action;
import ru.dpopkov.tasktracker.ActionType;
import ru.dpopkov.tasktracker.TaskTracker;
import ru.dpopkov.tasktracker.model.User;
import ru.dpopkov.tasktracker.ui.UiInput;
import ru.dpopkov.tasktracker.ui.UiOutput;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

public class ActionPack implements Iterable<Action> {

    private final UiInput input;
    private final UiOutput output;
    private final TaskTracker tracker;
    private final Collection<Action> actions = new ArrayList<>();

    public ActionPack(UiInput input, UiOutput output, TaskTracker tracker) {
        this.input = input;
        this.output = output;
        this.tracker = tracker;
        initActions();
    }

    @Override
    public Iterator<Action> iterator() {
        return actions.iterator();
    }

    private void initActions() {
        actions.add(new AddUserAction());
        actions.add(new ShowUserByIdAction());
        actions.add(new DeleteUserAction());
        actions.add(new ShowAllUsersAction());
    }

    private class AddUserAction extends BaseAction {

        public AddUserAction() {
            super(input, output, tracker, ActionType.ADD_USER, "Add new user");
        }

        @Override
        public void execute() {
            getOutput().print("Enter first name: ");
            String firstName = getInput().readString();
            getOutput().print("Enter last name: ");
            String lastName = getInput().readString();
            getTracker().createUser(firstName, lastName);
        }
    }

    private class ShowUserByIdAction extends BaseAction {

        public ShowUserByIdAction() {
            super(input, output, tracker, ActionType.FIND_USER_BY_ID, "Find user by ID");
        }

        @Override
        public void execute() {
            final TaskTracker tracker = getTracker();
            getOutput().print("Enter user ID: ");
            int userId = getInput().readInt();
            Optional<User> result = tracker.getUserById(userId);
            if (result.isPresent()) {
                getOutput().print(format(result.get()));
            } else {
                getOutput().println("Cannot find user with ID " + userId);
            }
        }
    }

    private class DeleteUserAction extends BaseAction {

        public DeleteUserAction() {
            super(input, output, tracker, ActionType.DELETE_USER, "Delete user");
        }

        @Override
        public void execute() {
            final TaskTracker tracker = getTracker();
            getOutput().print("Enter user ID: ");
            int userId = getInput().readInt();
            boolean result = tracker.deleteUser(userId);
            if (result) {
                getOutput().println("User deleted");
            } else {
                getOutput().println("Cannot find user with ID " + userId);
            }
        }
    }

    private class ShowAllUsersAction extends BaseAction {

        public ShowAllUsersAction() {
            super(input, output, tracker, ActionType.SHOW_ALL_USERS, "Show all users");
        }

        @Override
        public void execute() {
            final TaskTracker tracker = getTracker();
            for (User user : tracker.getAllUsers()) {
                getOutput().print(format(user));
            }
        }

    }

    private String format(User user) {
        return String.format("%2d : %s : %s%n", user.getId(), user.getFirstName(), user.getLastName());
    }
}
