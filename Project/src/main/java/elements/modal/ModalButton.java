package main.java.elements.modal;

import lombok.AllArgsConstructor;
import main.java.utility.interfaces.MyLocatorInterface;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;

@AllArgsConstructor
public enum ModalButton implements MyLocatorInterface {
    SAVE           ("Save"),
    CANCEL         ("Cancel"),
    OK             ("Ok"),
    ENTER          ("Enter"),
    NEW_CREDENTIAL ("New credential");

    private final String caption;
    public By getLocator(){
        // O-0007
        final String xPath = String.format(".//button[contains(text(), '%s')]", this.caption);
        return byXpath(xPath);
    }

    public final String getCaption(){
        return caption.trim();
    }
}
