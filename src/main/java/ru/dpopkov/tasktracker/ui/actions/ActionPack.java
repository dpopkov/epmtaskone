package ru.dpopkov.tasktracker.ui.actions;

import ru.dpopkov.tasktracker.Action;
import ru.dpopkov.tasktracker.ActionType;
import ru.dpopkov.tasktracker.TaskTracker;
import ru.dpopkov.tasktracker.model.Project;
import ru.dpopkov.tasktracker.model.Task;
import ru.dpopkov.tasktracker.model.User;
import ru.dpopkov.tasktracker.ui.Messages;
import ru.dpopkov.tasktracker.ui.UiInput;
import ru.dpopkov.tasktracker.ui.UiOutput;

import java.time.Duration;
import java.util.*;

/**
 * Contains pack of {@link Action} instances that are using shared input/output and {@link TaskTracker} instance.
 */
public class ActionPack implements Iterable<Action> {

    private final String enterUserId = Messages.INSTANCE.get("enter_user_id");
    private final String enterProjectId = Messages.INSTANCE.get("enter_project_id");
    private final String cannotFindProject = Messages.INSTANCE.get("cannot_find_project_id");
    private final String enterTaskId = Messages.INSTANCE.get("enter_task_id");
    private final String cannotFindTask = Messages.INSTANCE.get("cannot_find_task_id");
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

    private void initActions() {
        actions.add(new AddUserAction());
        actions.add(new ShowUserByIdAction());
        actions.add(new DeleteUserAction());
        actions.add(new ShowAllUsersAction());
        actions.add(new AddProjectAction());
        actions.add(new FindProjectByIdAction());
        actions.add(new DeleteProjectAction());
        actions.add(new ShowAllProjectsAction());
        actions.add(new AddTaskAction());
        actions.add(new FindTaskByIdAction());
        actions.add(new DeleteTaskAction());
        actions.add(new ShowAllTasksAction());
    }

    @Override
    public Iterator<Action> iterator() {
        return actions.iterator();
    }

    public UiInput getInput() {
        return input;
    }

    public UiOutput getOutput() {
        return output;
    }

    public TaskTracker getTracker() {
        return tracker;
    }

    private class AddUserAction extends BaseAction {

        private final String firstPrompt = Messages.INSTANCE.get("enter_first_name");
        private final String lastPrompt = Messages.INSTANCE.get("enter_last_name");

        public AddUserAction() {
            super(ActionType.ADD_USER);
        }

        @Override
        public void execute() {
            String firstName = readString(firstPrompt);
            String lastName = readString(lastPrompt);
            getTracker().createUser(firstName, lastName);
        }
    }

    private class ShowUserByIdAction extends BaseAction {

        private final String cannotFindUser = Messages.INSTANCE.get("cannot_find_user_with_id");

        public ShowUserByIdAction() {
            super(ActionType.FIND_USER_BY_ID);
        }

        @Override
        public void execute() {
            int userId = readInt(enterUserId);
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
            super(ActionType.DELETE_USER);
        }

        @Override
        public void execute() {
            int userId = readInt(enterUserId);
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
            super(ActionType.SHOW_ALL_USERS);
        }

        @Override
        public void execute() {
            List<User> all = getTracker().getAllUsers();
            all.forEach(u -> getOutput().println(format(u)));
        }
    }

    private class AddProjectAction extends BaseAction {

        private final String projectName = Messages.INSTANCE.get("enter_project_name");
        private final String projectDescription = Messages.INSTANCE.get("enter_project_description");

        public AddProjectAction() {
            super(ActionType.ADD_PROJECT);
        }

        @Override
        public void execute() {
            String name = readString(projectName);
            String description = readString(projectDescription);
            getTracker().createProject(name, description);
        }
    }

    private class FindProjectByIdAction extends BaseAction {

        protected FindProjectByIdAction() {
            super(ActionType.FIND_PROJECT_BY_ID);
        }

        @Override
        public void execute() {
            int projectId = readInt(enterProjectId);
            Optional<Project> result = tracker.getProjectById(projectId);
            if (result.isPresent()) {
                getOutput().println(format(result.get()));
            } else {
                getOutput().println(cannotFindProject + " " + projectId);
            }
        }
    }

    private class DeleteProjectAction extends BaseAction {

        private final String projectDeleted = Messages.INSTANCE.get("project_deleted");

        protected DeleteProjectAction() {
            super(ActionType.DELETE_PROJECT);
        }

        @Override
        public void execute() {
            int projectId = readInt(enterProjectId);
            boolean deleted = tracker.deleteProject(projectId);
            if (deleted) {
                getOutput().println(projectDeleted);
            } else {
                getOutput().println(cannotFindProject + " " + projectId);
            }
        }
    }

    private class ShowAllProjectsAction extends BaseAction {

        protected ShowAllProjectsAction() {
            super(ActionType.SHOW_ALL_PROJECTS);
        }

        @Override
        public void execute() {
            List<Project> all = tracker.getAllProjects();
            all.forEach(p -> getOutput().println(format(p)));
        }
    }

    private class AddTaskAction extends BaseAction {

        private final String enterName = Messages.INSTANCE.get("enter_task_name");
        private final String enterDescription = Messages.INSTANCE.get("enter_task_description");
        private final String enterTime = Messages.INSTANCE.get("enter_time_in_hours");

        protected AddTaskAction() {
            super(ActionType.ADD_TASK);
        }

        @Override
        public void execute() {
            int projectId = readInt(enterProjectId);
            Optional<Project> foundProject = tracker.getProjectById(projectId);
            if (foundProject.isPresent()) {
                String name = readString(enterName);
                String description = readString(enterDescription);
                int hours = readInt(enterTime);
                tracker.createTask(name, description, foundProject.get(), Duration.ofHours(hours));
            } else {
                getOutput().println(cannotFindProject + " " + projectId);
            }
        }
    }

    private class FindTaskByIdAction extends BaseAction {

        protected FindTaskByIdAction() {
            super(ActionType.FIND_TASK_BY_ID);
        }

        @Override
        public void execute() {
            int taskId = readInt(enterTaskId);
            Optional<Task> result = tracker.getTaskById(taskId);
            if (result.isPresent()) {
                getOutput().println(format(result.get()));
            } else {
                getOutput().println(cannotFindTask + " " + taskId);
            }
        }
    }

    private class DeleteTaskAction extends BaseAction {

        private final String taskDeleted = Messages.INSTANCE.get("task_deleted");

        protected DeleteTaskAction() {
            super(ActionType.DELETE_TASK);
        }

        @Override
        public void execute() {
            int taskId = readInt(enterTaskId);
            boolean deleted = tracker.deleteTask(taskId);
            if (deleted) {
                getOutput().println(taskDeleted);
            } else {
                getOutput().println(cannotFindTask + " " + taskId);
            }
        }
    }

    private class ShowAllTasksAction extends BaseAction {

        protected ShowAllTasksAction() {
            super(ActionType.SHOW_ALL_TASKS);
        }

        @Override
        public void execute() {
            List<Task> all = tracker.getAllTasks();
            all.forEach(t -> getOutput().println(format(t)));
        }
    }

    private String readString(String prompt) {
        getOutput().prompt(prompt);
        return getInput().readString();
    }

    private int readInt(String intPrompt) {
        output.prompt(intPrompt);
        return input.readInt();
    }

    private String format(User user) {
        return String.format("%2d : %s : %s", user.getId(), user.getFirstName(), user.getLastName());
    }

    private String format(Project project) {
        return String.format("%2d : %s : %s", project.getId(), project.getName(), project.getDescription());
    }

    private String format(Task task) {
        return String.format("%s : %d : %s : %s : %s", task.getProject().getName(), task.getId(), task.getName(),
                task.getDescription(), task.getTime().toString());
    }
}
