package main.java.elements.widgets.tree;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.java.utility.interfaces.MyLocatorInterface;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;

@AllArgsConstructor
@Getter
public enum FieldOfSort implements MyLocatorInterface {
    MAIN_FIELD      ("mainField"),
    ADDITIONAL_FIELD("additionalField");

    private final String value;

    public By getLocator(){
        // O-0021
        // return byAttribute("data-qa-id", getValue());
        return byXpath(String.format(".//label[child::input[@data-qa-id = '%s']]", getValue()));
    }
}

