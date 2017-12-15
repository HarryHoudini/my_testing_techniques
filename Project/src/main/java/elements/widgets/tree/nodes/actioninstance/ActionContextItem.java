package main.java.elements.widgets.tree.nodes.actioninstance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.java.utility.interfaces.MyContextItemInterface;

@AllArgsConstructor
@Getter
public enum ActionContextItem implements MyContextItemInterface {
    EDIT_ACTION              ("Edit action"),
    RUN_ACTION               ("Run action"),
    ADD_SUCCESS_NOTIFICATION ("Add SUCCESS notification"),
    ADD_ERROR_NOTIFICATION   ("Add ERROR notification"),
    ENABLE_ACTION            ("Enable action"),
    DISABLE_ACTION           ("Disable action"),
    DELETE_ACTION            ("Delete action");

    private String stringOfMenuItem;
}
