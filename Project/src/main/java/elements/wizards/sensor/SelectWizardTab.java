package main.java.elements.wizards.sensor;

import lombok.AllArgsConstructor;
import main.java.utility.interfaces.MyLocatorInterface;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;

@AllArgsConstructor
public enum SelectWizardTab implements MyLocatorInterface {
    NETBASE    ("Netbase sensors ..."),
    FILESYSTEM ("Filesystem sensors ..."),
    WINBASE    ("Windows base sensors ..."),
    SYSTEM     ("System base sensors ...");

    private final String caption;

    public By getLocator(){
        // O-0017
        final String xpath = String.format(".//*[@title = '%s']", caption);
        return byXpath(xpath);
    }

    public final String getCaption(){
        return this.name();
    }
}
