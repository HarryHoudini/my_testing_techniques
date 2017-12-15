package main.java.elements.widgets.tree.nodes.actioninstance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.java.utility.interfaces.MyContextItemInterface;

@AllArgsConstructor
@Getter
public enum SensorContextItem implements MyContextItemInterface {
    EDIT_SENSOR              ("Edit sensor"),
    RUN_SENSOR               ("Run sensor"),
    ADD_SUCCESS_NOTIFICATION ("Add SUCCESS notification"),
    ADD_ERROR_NOTIFICATION   ("Add ERROR notification"),
    ENABLE_SENSOR            ("Enable sensor"),
    DISABLE_SENSOR           ("Disable sensor"),
    DELETE_SENSOR            ("Delete sensor");

    private String stringOfMenuItem;
}
