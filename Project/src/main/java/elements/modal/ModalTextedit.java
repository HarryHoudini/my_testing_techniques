package main.java.elements.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.java.elements.simple_elements.EditlineType;
import main.java.utility.interfaces.MyLocatorInterface;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;

@Getter
@AllArgsConstructor
public enum ModalTextedit implements MyLocatorInterface {

    // O-0006
    CAPTION     ("caption",     EditlineType.TEXT_EDIT),
    HOSTNAME    ("hostname",    EditlineType.TEXT_EDIT),
    IP_ADRESS   ("ip",          EditlineType.TEXT_EDIT),
    MAC_ADRESS  ("mac",         EditlineType.TEXT_EDIT),
    USERNAME    ("username",    EditlineType.TEXT_EDIT),
    LOGIN       ("login",       EditlineType.TEXT_EDIT),
    PASSWORD    ("password",    EditlineType.TEXT_EDIT),
    DESCRIPTION ("description", EditlineType.TEXT_AREA);

    final String nameValue;
    final EditlineType editlineType;

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
        return this.name().toLowerCase();
    }
}

