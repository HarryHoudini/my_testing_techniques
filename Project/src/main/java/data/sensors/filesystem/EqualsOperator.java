package main.java.data.sensors.filesystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.java.utility.interfaces.MyLocatorInterface;
import main.java.utility.interfaces.MySelectOptionsInterface;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byValue;

@AllArgsConstructor
@Getter
public enum EqualsOperator implements MyLocatorInterface, MySelectOptionsInterface {
    EQUALS    ("string:equal"),
    NOT_EQUALS("string:nonequal");

    private String value;

    public By getLocator(){
        return byValue(this.value);
    }
}