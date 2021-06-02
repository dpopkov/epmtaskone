package ru.dpopkov.tasktracker.ui;

import ru.dpopkov.tasktracker.Action;
import ru.dpopkov.tasktracker.ActionType;
import ru.dpopkov.tasktracker.ui.actions.Actions;

import java.util.List;

/**
 * Allows to show all actions in a numbered list as text and select one action.
 */
public class TextualMenu implements Menu {
    private static final String NL = System.lineSeparator();

    private final Actions actions;
    private final UiOutput output;
    private final UiInput input;
    private List<ActionType> indexedActionTypes;

    public TextualMenu(Actions actions, UiOutput output, UiInput input) {
        this.actions = actions;
        this.output = output;
        this.input = input;
    }

    @Override
    public void prepareForShow() {
        indexedActionTypes = actions.getActionTypes();
    }

    @Override
    public void show() {
        if (indexedActionTypes == null) {
            throw new IllegalStateException("Menu is not prepared for showing. Call prepareForShow() first.");
        }
        output.print(NL);
        output.print("MENU:");
        output.print(NL);
        for (int i = 0; i < indexedActionTypes.size(); i++) {
            Action action = actions.get(indexedActionTypes.get(i));
            output.print(String.format("%2d: %s%n", (i + 1), action.description()));
        }
    }

    @Override
    public ActionType select() {
        output.print("Enter number of action: ");
        int n = input.readInt();
        return indexedActionTypes.get(n - 1);
    }
}
