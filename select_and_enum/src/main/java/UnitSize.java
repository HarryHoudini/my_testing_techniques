import interfaces.MyLocatorInterface;
import interfaces.MySelectOptionsInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
