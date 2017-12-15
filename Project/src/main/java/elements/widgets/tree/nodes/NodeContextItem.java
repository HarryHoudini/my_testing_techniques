package main.java.elements.widgets.tree.nodes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.java.utility.interfaces.MyContextItemInterface;

@AllArgsConstructor
@Getter
public enum NodeContextItem implements MyContextItemInterface {
    EDIT_ROOT     ("Edit root"),
    EDIT_GROUP    ("Edit group"),
    EDIT_DEVICE   ("Edit device"),
    ADD_GROUP     ("Add group"),
    ADD_DEVICE    ("Add device"),
    ADD_SENSOR    ("Add sensor"),
    ADD_ACTION    ("Add action"),
    DELETE_GROUP  ("Delete group"),
    DELETE_DEVICE ("Delete device");

    private String stringOfMenuItem;

}
