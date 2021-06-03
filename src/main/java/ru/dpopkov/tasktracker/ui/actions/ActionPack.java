package ru.dpopkov.tasktracker.ui.actions;

import ru.dpopkov.tasktracker.Action;
import ru.dpopkov.tasktracker.ActionType;
import ru.dpopkov.tasktracker.TaskTracker;
import ru.dpopkov.tasktracker.model.Project;
import ru.dpopkov.tasktracker.model.User;
import ru.dpopkov.tasktracker.ui.Messages;
import ru.dpopkov.tasktracker.ui.UiInput;
import ru.dpopkov.tasktracker.ui.UiOutput;

import java.util.*;

/**
 * Contains pack of {@link Action} instances that are using shared input/output and {@link TaskTracker} instance.
 */
public class ActionPack implements Iterable<Action> {

    private final String enterUserId = Messages.INSTANCE.get("enter_user_id");
    private final String enterProjectId = Messages.INSTANCE.get("enter_project_id");
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
        actions.add(new AddProjectAction());
        actions.add(new FindProjectByIdAction());
        actions.add(new DeleteProjectAction());
        actions.add(new ShowAllProjectsAction());
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
            int userId = readId(enterUserId);
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
            int userId = readId(enterUserId);
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
                getOutput().println(format(user));
            }
        }

    }

    private class AddProjectAction extends BaseAction {

        private final String projectName = Messages.INSTANCE.get("enter_project_name");
        private final String projectDescription = Messages.INSTANCE.get("enter_project_description");

        public AddProjectAction() {
            super(input, output, tracker, ActionType.ADD_PROJECT);
        }

        @Override
        public void execute() {
            getOutput().prompt(projectName);
            String name = getInput().readString();
            getOutput().prompt(projectDescription);
            String description = getInput().readString();
            getTracker().createProject(name, description);
        }
    }

    private class FindProjectByIdAction extends BaseAction {
        private final String cannotFind = Messages.INSTANCE.get("cannot_find_project_id");

        protected FindProjectByIdAction() {
            super(input, output, tracker, ActionType.FIND_PROJECT_BY_ID);
        }

        @Override
        public void execute() {
            int projectId = readId(enterProjectId);
            Optional<Project> result = tracker.getProjectById(projectId);
            if (result.isPresent()) {
                getOutput().println(format(result.get()));
            } else {
                getOutput().println(cannotFind + " " + projectId);
            }
        }
    }

    private class DeleteProjectAction extends BaseAction {

        private final String projectDeleted = Messages.INSTANCE.get("project_deleted");
        private final String cannotFind = Messages.INSTANCE.get("cannot_find_project_id");

        protected DeleteProjectAction() {
            super(input, output, tracker, ActionType.DELETE_PROJECT);
        }

        @Override
        public void execute() {
            int projectId = readId(enterProjectId);
            boolean deleted = tracker.deleteProject(projectId);
            if (deleted) {
                getOutput().println(projectDeleted);
            } else {
                getOutput().println(cannotFind + " " + projectId);
            }
        }
    }

    private class ShowAllProjectsAction extends BaseAction {

        protected ShowAllProjectsAction() {
            super(input, output, tracker, ActionType.SHOW_ALL_PROJECTS);
        }

        @Override
        public void execute() {
            List<Project> all = tracker.getAllProjects();
            all.forEach(p -> getOutput().println(format(p)));
        }
    }

    // todo: Simplify action classes -> remove input/output/tracker

    private int readId(String idPrompt) {
        output.prompt(idPrompt);
        return input.readInt();
    }

    private String format(User user) {
        return String.format("%2d : %s : %s", user.getId(), user.getFirstName(), user.getLastName());
    }

    private String format(Project project) {
        return String.format("%2d : %s : %s", project.getId(), project.getName(), project.getDescription());
    }
}
