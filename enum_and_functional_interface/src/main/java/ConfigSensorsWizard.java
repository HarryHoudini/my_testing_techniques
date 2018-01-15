
import com.codeborne.selenide.SelenideElement;
import other_classes.BasePageElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public abstract class ConfigSensorsWizard extends BasePageElement {


    public ConfigSensorsWizard() {
        super($(byName("sensorWizard")));
    }

    public ConfigSensorsWizard(SelectWizardSensor sensor) {
        super($(sensor.getLocator()));
    }

    public abstract void createDefaultSensor(final String nameSensor);
}
