package main.java.elements.wizards.action.config;

import lombok.AllArgsConstructor;
import main.java.elements.simple_elements.EditlineType;
import main.java.utility.interfaces.MyLocatorInterface;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;

@AllArgsConstructor
public enum ConfigActionsWizardTextedit implements MyLocatorInterface {
    ACTION_CAPTION  ("caption",           EditlineType.TEXT_EDIT),
    MESSAGE_TEXT    ("text",              EditlineType.TEXT_EDIT),
    TIMEOUT         ("timeout",           EditlineType.NUMBER_EDIT),
    APP_PATH        ("applicationname",   EditlineType.TEXT_EDIT),
    APP_PARAMS      ("applicationparams", EditlineType.TEXT_EDIT),
    APP_DIR         ("applicationdir",    EditlineType.TEXT_EDIT),
    DELAY_TIME      ("time",              EditlineType.NUMBER_EDIT),
    SERVICE_NAME    ("servicename",       EditlineType.TEXT_EDIT),
    SCRIPT          ("script",            EditlineType.TEXT_AREA);

    String nameValue;
    EditlineType editlineType;

    public By getLocator(){
        By locator;
        if(editlineType == EditlineType.TEXT_EDIT){
            String xpath = String.format(".//*[@data-control-name='%s']", this.nameValue);
            locator = byXpath(xpath);
        }
        else{
            locator = byName(this.nameValue);
        }
        return locator;
    }

    public String getCaption(){
        return this.name();
    }
}
