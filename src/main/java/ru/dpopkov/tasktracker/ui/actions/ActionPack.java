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

public class ActionPack implements Iterable<Action> {

    private final UiInput input;
    private final UiOutput output;
    private final TaskTracker tracker;
    private final Collection<Action> actions = new ArrayList<>();

    public ActionPack(UiInput input, UiOutput output, TaskTracker tracker) {
        this.input = input;
        this.output = output;
        this.tracker = tracker;
        actions.add(new AddUserAction());
        actions.add(new ShowAllUsersAction());
    }

    @Override
    public Iterator<Action> iterator() {
        return actions.iterator();
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

    private class ShowAllUsersAction  extends BaseAction {

        public ShowAllUsersAction() {
            super(input, output, tracker, ActionType.SHOW_ALL_USERS, "Show all users");
        }

        @Override
        public void execute() {
            final TaskTracker tracker = getTracker();
            for (User user : tracker.getAllUsers()) {
                getOutput().print(String.format("%2d : %s : %s%n",
                        user.getId(), user.getFirstName(), user.getLastName()));
            }
        }
    }
}
