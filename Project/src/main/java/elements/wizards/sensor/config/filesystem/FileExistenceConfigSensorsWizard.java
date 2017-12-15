package main.java.elements.wizards.sensor.config.filesystem;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.filesystem.FileExistenceData;
import main.java.data.sensors.filesystem.FileExistenceData.Builder;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;

import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.*;

public final class FileExistenceConfigSensorsWizard extends ConfigSensorsWizard {
    public FileExistenceConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultSensor(final String nameSensor) {
        super.createDefaultFilesystemSensor(nameSensor);
    }

    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), FileExistenceData.class);

        final FileExistenceData data = (FileExistenceData) sensorData;

        data.getCaption().ifPresent( caption ->
            setTexteditValue(SENSOR_CAPTION, caption)
        );
        data.getPath().ifPresent( path ->
            setTexteditValue(FILE_PATH, path)
        );
    }

    @Override
    protected FileExistenceData getData() {
        final FileExistenceData result = new FileExistenceData(
            Builder.of()
                .setCaption(getTexteditValue(SENSOR_CAPTION))
                .setPath(getTexteditValue(FILE_PATH))
        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
