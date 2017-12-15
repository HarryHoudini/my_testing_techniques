package main.java.elements.wizards.sensor.config.netbase;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.netbase.TelnetData;
import main.java.data.sensors.netbase.TelnetData.Builder;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;

import static main.java.elements.wizards.action.config.ConfigActionsWizardTextedit.ACTION_CAPTION;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.PORT;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.TIMEOUT;

public final class TelnetConfigSensorsWizard extends ConfigSensorsWizard {
    public TelnetConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultSensor(final String nameSensor) {
        super.createDefaultNetbaseSensor(nameSensor);
    }

    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), TelnetData.class);

        final TelnetData data = (TelnetData) sensorData;

        data.getCaption().ifPresent( caption ->
            setTexteditValue(ACTION_CAPTION, caption)
        );
        data.getPort().ifPresent( port ->
            setNumberEditValue(PORT, port)
        );
        data.getTimeout().ifPresent( timeout ->
            setNumberEditValue(TIMEOUT, timeout)
        );
    }

    @Override
    protected final TelnetData getData() {
        final TelnetData result = new TelnetData(
            Builder.of()
                .setCaption(getTexteditValue(ACTION_CAPTION))
                .setPortString(getNumberEditString(PORT))
                .setTimeoutString(getNumberEditString(TIMEOUT))
        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
