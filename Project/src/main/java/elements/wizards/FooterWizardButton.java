package main.java.elements.wizards;

import lombok.AllArgsConstructor;
import main.java.utility.interfaces.MyLocatorInterface;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;

@AllArgsConstructor
public enum FooterWizardButton implements MyLocatorInterface {
    PREVIOUS   ("Previous"),
    NEXT       ("Next"),
    SAVE       ("Save");

    private final String caption;
    public By getLocator(){
        // O-0016
        final String xpath = String.format(".//ol-modal-footer//button[contains(text(), '%s')]", caption);
        return byXpath(xpath);
    }

    public final String getCaption(){
        return this.caption.trim();
    }
}
