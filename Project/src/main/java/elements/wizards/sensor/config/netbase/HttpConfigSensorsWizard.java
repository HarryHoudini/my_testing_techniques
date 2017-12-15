package main.java.elements.wizards.sensor.config.netbase;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.netbase.HttpData;
import main.java.data.sensors.netbase.HttpData.Builder;
import main.java.elements.simple_elements.Checkbox;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.elements.wizards.action.config.ConfigActionsWizardTextedit.ACTION_CAPTION;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.*;

public final class HttpConfigSensorsWizard extends ConfigSensorsWizard {

    private final Checkbox secureConnectionCheckbox =
        new Checkbox(this.rootElement.$(byAttribute("data-control-name","secureconnection")));

    public HttpConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultSensor(final String nameSensor) {
        super.createDefaultNetbaseSensor(nameSensor);
    }

    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), HttpData.class);

        final HttpData data = (HttpData) sensorData;

        data.getCaption().ifPresent( caption ->
            setTexteditValue(ACTION_CAPTION, caption)
        );
        data.getPort().ifPresent( port ->
            setNumberEditValue(PORT, port)
        );
        data.getTimeout().ifPresent( timeout ->
            setNumberEditValue(TIMEOUT, timeout)
        );
        data.getSecureConnection().ifPresent( check -> {
            if(check != this.secureConnectionCheckbox.isChecked()){
                this.secureConnectionCheckbox.click();
            }
        });
    }

    @Override
    protected final HttpData getData() {
        final HttpData result = new HttpData(
            Builder.of()
                .setCaption(getTexteditValue(ACTION_CAPTION))
                .setPortString(getNumberEditString(PORT))
                .setTimeoutString(getNumberEditString(TIMEOUT))
                .setSecureConnection(this.secureConnectionCheckbox.isChecked())
        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
