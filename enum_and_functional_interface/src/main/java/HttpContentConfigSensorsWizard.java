import enums_of_locator.ConfigSensorsWizardTextedit;
import enums_of_locator.FooterWizardButton;

public final class HttpContentConfigSensorsWizard extends ConfigSensorsWizard {

    private static final String URL_PATH_CONST = "url";

    public HttpContentConfigSensorsWizard() {
        super();
    }

    public void createDefaultSensor(String sensorCaption){
        setTexteditValue      (ConfigSensorsWizardTextedit.SENSOR_CAPTION, sensorCaption);
        setTexteditValue      (ConfigSensorsWizardTextedit.URL_PATH,       URL_PATH_CONST);
        clickCloseModalButton (FooterWizardButton.SAVE);
    }
}
