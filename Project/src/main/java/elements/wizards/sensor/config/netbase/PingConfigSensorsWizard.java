package main.java.elements.wizards.sensor.config.netbase;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.netbase.PingData;
import main.java.data.sensors.netbase.PingData.Builder;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;

import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.*;

public final class PingConfigSensorsWizard extends ConfigSensorsWizard {
    public PingConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public void createDefaultSensor(String nameSensor) {
        super.createDefaultNetbaseSensor(nameSensor);
    }

    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), PingData.class);

        PingData data = (PingData) sensorData;

        data.getCaption().ifPresent( caption ->
            setTexteditValue(SENSOR_CAPTION, caption)
        );
        data.getPacketSize().ifPresent( size ->
            setNumberEditValue(PACKET_SIZE, size)
        );
        data.getPacketCount().ifPresent( count ->
            setNumberEditValue(PACKET_COUNT, count)
        );
        data.getTimeout().ifPresent( timeout ->
            setNumberEditValue(TIMEOUT, timeout)
        );
        data.getTtl().ifPresent( ttl ->
            setNumberEditValue(TTL, ttl)
        );
    }

    @Override
    protected final PingData getData() {
        final PingData result = new PingData(
            Builder.of()
                .setCaption(getTexteditValue(SENSOR_CAPTION))
                .setPacketSizeString(getNumberEditString(PACKET_SIZE))
                .setPacketCountString(getNumberEditString(PACKET_COUNT))
                .setTimeoutString(getNumberEditString(TIMEOUT))
                .setTtlString(getNumberEditString(TTL))
        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
