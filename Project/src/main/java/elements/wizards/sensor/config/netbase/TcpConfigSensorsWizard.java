package main.java.elements.wizards.sensor.config.netbase;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.netbase.TcpData;
import main.java.data.sensors.netbase.TcpData.Builder;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;

import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.*;

public final class TcpConfigSensorsWizard extends ConfigSensorsWizard {
    public TcpConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultSensor(final String nameSensor) {
        super.createDefaultNetbaseSensor(nameSensor);
    }

    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), TcpData.class);

        final TcpData data = (TcpData) sensorData;

        data.getCaption().ifPresent( caption ->
            setTexteditValue(SENSOR_CAPTION, caption)
        );
        data.getPort().ifPresent( port ->
            setNumberEditValue(PORT, port)
        );
        data.getTimeout().ifPresent( timeout ->
            setNumberEditValue(TIMEOUT, timeout)
        );
    }

    @Override
    protected final TcpData getData() {
        final TcpData result = new TcpData(
            Builder.of()
                .setCaption(getTexteditValue(SENSOR_CAPTION))
                .setPortString(getNumberEditString(PORT))
                .setTimeoutString(getNumberEditString(TIMEOUT))
        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
