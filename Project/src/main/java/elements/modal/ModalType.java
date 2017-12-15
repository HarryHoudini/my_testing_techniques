package main.java.elements.modal;

import lombok.AllArgsConstructor;
import main.java.utility.interfaces.MyLocatorInterface;
import org.openqa.selenium.By;

@AllArgsConstructor
public enum ModalType implements MyLocatorInterface{
    DELETE_NODE           ("ol-node-delete-modal"),
    EDIT_NODE             ("ol-node-edit-modal"),
    ADD_NODE              ("ol-node-create-modal"),
    MOVE_NODE             ("ol-node-move-modal"),
    LOGIN_PAGE            ("ol-sign-in"),
    CREATE_NEW_CREDENTIAL ("ol-credential-record-create-modal");

    private final String tagName;

    public By getLocator(){
        return By.tagName(this.tagName);
    }

}

