import com.codeborne.selenide.SelenideElement;
import enums_of_locator.FooterWizardButton;

import static enums_of_locator.ConfigSensorsWizardTextedit.SENSOR_CAPTION;
import static enums_of_locator.ConfigSensorsWizardTextedit.SERVICE_NAME;

public final class ServiceStatusConfigSensorsWizard extends ConfigSensorsWizard {

    private static final String SERVICE_NAME_CONST = "Service name";

    public ServiceStatusConfigSensorsWizard() {
        super();
    }

    @Override
    public final void createDefaultSensor(final String sensorCaption){
        setTexteditValue(SENSOR_CAPTION, sensorCaption);
        setTexteditValue(SERVICE_NAME, SERVICE_NAME_CONST);
        clickCloseModalButton(FooterWizardButton.SAVE);
    }
}
