package main.java.elements.wizards.sensor.config;

import main.java.data.sensors.SensorData;
import main.java.elements.wizards.BaseWizard;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;

import static main.java.elements.wizards.sensor.DefaultValueSensorsConstant.FIRST_FILE_PATH_CONST;

public abstract class ConfigSensorsWizard extends BaseWizard {

    public ConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    protected final void createDefaultNetbaseSensor(final String sensorCaption){
        setTexteditValue(ConfigSensorsWizardTextedit.SENSOR_CAPTION, sensorCaption);
        clickCloseModalButton(FooterWizardButton.SAVE);
    }

    protected final void createDefaultFilesystemSensor(final String sensorCaption){
        setTexteditValue(ConfigSensorsWizardTextedit.SENSOR_CAPTION, sensorCaption);
        setTexteditValue(ConfigSensorsWizardTextedit.FILE_PATH,      FIRST_FILE_PATH_CONST);
        clickCloseModalButton(FooterWizardButton.SAVE);
    }

    protected void checkDataClass(final Class<? extends SensorData> className,
                               final Class<? extends SensorData> extendClass) throws IllegalArgumentException{
        if (!className.equals(extendClass)){
            String message = className.getSimpleName() + " sould be " + extendClass.getSimpleName();
            throw new IllegalArgumentException(message);
        }
    }

    // abstract methods

    public abstract void createDefaultSensor(final String nameSensor);

    protected abstract void editData(final SensorData data);

    protected abstract SensorData getData();

    public boolean hasData(SensorData data){
        return getData().equals(data);
    }

    // enter data methods

    public void enterWrongData(SensorData data){
        editData(data);
        clickButton(FooterWizardButton.SAVE);
    }

    public void enterCorrectData(SensorData data){
        editData(data);
        clickCloseModalButton(FooterWizardButton.SAVE);
    }

    // common textEdit methods

    protected final ConfigSensorsWizard setNumberEditValue(final ConfigSensorsWizardTextedit numberEdit,
                                                           final String newValue) {
        super.setNumberEditValue(numberEdit, newValue);
        return this;
    }

    protected final String getNumberEditString(final ConfigSensorsWizardTextedit numberEdit){
        return super.getNumberEditString(numberEdit);
    }

    protected final Double getNumberEditValue(final ConfigSensorsWizardTextedit numberEdit){
        return super.getNumberEditValue(numberEdit);
    }

    protected final ConfigSensorsWizard setTexteditValue(final ConfigSensorsWizardTextedit textedit,
                                                         final String newValue){
        super.setTexteditValue(textedit, newValue);
        return this;
    }

    protected final String getTexteditValue(final ConfigSensorsWizardTextedit textedit) {
        return super.getTexteditValue(textedit);
    }

    protected final boolean isEnabledTextedit (final ConfigSensorsWizardTextedit textedit){
        return super.isEnabledTextedit(textedit);
    }
}
