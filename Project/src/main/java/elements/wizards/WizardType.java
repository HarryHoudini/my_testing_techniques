package main.java.elements.wizards;

import lombok.AllArgsConstructor;
import main.java.utility.interfaces.MyLocatorInterface;
import org.openqa.selenium.By;

@AllArgsConstructor
public enum WizardType implements MyLocatorInterface{
    CREATE_SENSOR("ol-wizard-generic-ai-create"),
    CREATE_ACTION("ol-wizard-generic-ai-create"),
    EDIT_SENSOR  ("ol-wizard-generic-ai-edit"),
    EDIT_ACTION  ("ol-wizard-generic-ai-edit");

    private final String tagName;

    public By getLocator(){
        return By.tagName(this.tagName);
    }

}

