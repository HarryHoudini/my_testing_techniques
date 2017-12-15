package main.java.elements.wizards.sensor.config.filesystem;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.filesystem.FolderExistenceData;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;

import static main.java.elements.wizards.action.config.ConfigActionsWizardTextedit.ACTION_CAPTION;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.FILE_PATH;

public final class FolderExistenceConfigSensorsWizard extends ConfigSensorsWizard {
    public FolderExistenceConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultSensor(final String nameSensor) {
        super.createDefaultFilesystemSensor(nameSensor);
    }

    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), FolderExistenceData.class);

        final FolderExistenceData data = (FolderExistenceData) sensorData;

        data.getCaption().ifPresent( caption ->
            setTexteditValue(ACTION_CAPTION, caption)
        );
        data.getPath().ifPresent( path ->
            setTexteditValue(FILE_PATH, path)
        );
    }

    @Override
    protected FolderExistenceData getData() {
        final FolderExistenceData result = new FolderExistenceData(
            FolderExistenceData.Builder.of()
                .setCaption(getTexteditValue(ACTION_CAPTION))
                .setPath(getTexteditValue(FILE_PATH))
        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
