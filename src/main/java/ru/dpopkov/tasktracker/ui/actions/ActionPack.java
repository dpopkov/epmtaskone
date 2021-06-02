package ru.dpopkov.tasktracker.ui.actions;

import ru.dpopkov.tasktracker.Action;
import ru.dpopkov.tasktracker.ActionType;
import ru.dpopkov.tasktracker.TaskTracker;
import ru.dpopkov.tasktracker.model.User;
import ru.dpopkov.tasktracker.ui.Messages;
import ru.dpopkov.tasktracker.ui.UiInput;
import ru.dpopkov.tasktracker.ui.UiOutput;

import java.util.*;

public class ActionPack implements Iterable<Action> {

    private final String enterUserId = Messages.INSTANCE.get("enter_user_id");
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

        private final String firstPrompt = Messages.INSTANCE.get("enter_first_name");
        private final String lastPrompt = Messages.INSTANCE.get("enter_last_name");

        public AddUserAction() {
            super(input, output, tracker, ActionType.ADD_USER);
        }

        @Override
        public void execute() {
            getOutput().prompt(firstPrompt);
            String firstName = getInput().readString();
            getOutput().prompt(lastPrompt);
            String lastName = getInput().readString();
            getTracker().createUser(firstName, lastName);
        }
    }

    private class ShowUserByIdAction extends BaseAction {

        private final String cannotFindUser = Messages.INSTANCE.get("cannot_find_user_with_id");

        public ShowUserByIdAction() {
            super(input, output, tracker, ActionType.FIND_USER_BY_ID);
        }

        @Override
        public void execute() {
            int userId = readUserId();
            Optional<User> result = getTracker().getUserById(userId);
            if (result.isPresent()) {
                getOutput().print(format(result.get()));
            } else {
                getOutput().println(cannotFindUser + " " + userId);
            }
        }
    }

    private class DeleteUserAction extends BaseAction {

        private final String cannotFindUser = Messages.INSTANCE.get("cannot_find_user_with_id");
        private final String userDeleted = Messages.INSTANCE.get("user_deleted");

        public DeleteUserAction() {
            super(input, output, tracker, ActionType.DELETE_USER);
        }

        @Override
        public void execute() {
            int userId = readUserId();
            boolean result = getTracker().deleteUser(userId);
            if (result) {
                getOutput().println(userDeleted);
            } else {
                getOutput().println(cannotFindUser + " " + userId);
            }
        }
    }

    private class ShowAllUsersAction extends BaseAction {

        public ShowAllUsersAction() {
            super(input, output, tracker, ActionType.SHOW_ALL_USERS);
        }

        @Override
        public void execute() {
            final TaskTracker tracker = getTracker();
            for (User user : tracker.getAllUsers()) {
                getOutput().print(format(user));
            }
        }

    }

    private int readUserId() {
        output.prompt(enterUserId);
        return input.readInt();
    }

    private String format(User user) {
        return String.format("%2d : %s : %s%n", user.getId(), user.getFirstName(), user.getLastName());
    }
}
