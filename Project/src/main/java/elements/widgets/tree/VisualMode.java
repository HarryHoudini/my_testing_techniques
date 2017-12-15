package main.java.elements.widgets.tree;

import lombok.AllArgsConstructor;
import main.java.utility.interfaces.MyLocatorInterface;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;

@AllArgsConstructor
public enum VisualMode implements MyLocatorInterface {
    MAIN_HOSTNAME     ("hostname", Field.MAIN),
    MAIN_IP_ADRESS    ("ip",       Field.MAIN),
    MAIN_MAC_ADRESS   ("mac",      Field.MAIN),
    MAIN_CAPTION      ("caption",  Field.MAIN),
    ADD_IP_ADRESS     ("ip",       Field.ADDITIONAL),
    ADD_MAC_ADRESS    ("mac",      Field.ADDITIONAL),
    ADD_CAPTION       ("caption",  Field.ADDITIONAL),
    ADD_WITHOUT_FIELD ("none",     Field.ADDITIONAL);

    private final String caption;
    private final Field field;
    private enum Field {MAIN, ADDITIONAL}

    public By getLocator(){
        String xpath;
        if (field == Field.MAIN){
            xpath = ".//*[@data-qa-id = 'mainField']";
        }
        else{
            xpath = ".//*[@data-qa-id = 'additionalField']";

        }
     // O-0021
        return byXpath(xpath + String.format("//label[child::input[@data-qa-id = '%s']]", this.caption));
    }

    public By getNewLocator(){
        String xpath;
        if (field == Field.MAIN){
            xpath = ".//*[@data-qa-id = 'mainField']";
        }
        else{
            xpath = ".//*[@data-qa-id = 'additionalField']";

        }
        // O-0021
        return byXpath(xpath + String.format("//input[@data-qa-id = '%s']", this.caption));
    }
    public String getCaption(){
        return this.caption;
    }
}
