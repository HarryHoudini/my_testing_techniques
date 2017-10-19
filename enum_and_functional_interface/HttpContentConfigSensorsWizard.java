package test.network_tree_winget;

import main.elements.wizards.FooterWizardButton;
import main.elements.wizards.sensor_wizards.config_wizards.ConfigSensorsWizard;
import main.elements.wizards.sensor_wizards.config_wizards.ConfigSensorsWizardTextedit;

import static main.elements.wizards.sensor_wizards.DefaultValueSensorsConstant.URL_PATH_CONST;

public final class HttpContentConfigSensorsWizard extends ConfigSensorsWizard {

    public void createDefaultSensor(String sensorCaption){
        setTexteditValue(ConfigSensorsWizardTextedit.SENSOR_CAPTION, sensorCaption);
        setTexteditValue(ConfigSensorsWizardTextedit.URL_PATH,       URL_PATH_CONST);
        clickButton(FooterWizardButton.SAVE);
        closeIfVisible();
    }
}
