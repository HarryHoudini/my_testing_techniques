package main.java.data.sensors.filesystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.java.utility.interfaces.MyLocatorInterface;
import main.java.utility.interfaces.MySelectOptionsInterface;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byValue;

@AllArgsConstructor
@Getter
public enum UnitSize implements MyLocatorInterface, MySelectOptionsInterface {
    BYTES     ("string:B"),
    KILOBYTES ("string:KB"),
    MEGABYTES ("string:MB"),
    GIGABYTES ("string:GB"),
    TERABYTES ("string:TB");

    private final String value;

    public By getLocator(){
        return byValue(this.value);
    }
}
