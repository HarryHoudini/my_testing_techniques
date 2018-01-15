import interfaces.MyLocatorInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;

import java.util.function.Supplier;

import static com.codeborne.selenide.Selectors.byXpath;

@Getter
@AllArgsConstructor
public enum SelectWizardSensor implements MyLocatorInterface {
    HTTP_CONTENT          ("netbase.httpcontent",        HttpContentConfigSensorsWizard   ::new),
    // other sensors
    SYSTEM_SERVICE_STATUS ("system.servicestatus",       ServiceStatusConfigSensorsWizard ::new);

    final String caption;
    final Supplier<? extends ConfigSensorsWizard> configWizardConstructor;

    public By getLocator(){
        final String xpath = String.format(".//*[@data-ol-accord-item = '%s']", this.caption);
        return byXpath(xpath);
    }
}
