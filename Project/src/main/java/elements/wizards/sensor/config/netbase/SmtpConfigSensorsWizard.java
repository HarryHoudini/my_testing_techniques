package main.java.elements.wizards.sensor.config.netbase;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.netbase.SmtpData;
import main.java.data.sensors.netbase.SmtpData.Builder;
import main.java.elements.simple_elements.Checkbox;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.*;

public final class SmtpConfigSensorsWizard extends ConfigSensorsWizard {

    private final Checkbox secureConnectionCheckbox =
        new Checkbox(this.rootElement.$(byAttribute("data-control-name","secureconnection")));
    private final Checkbox authorizationCheckbox = new Checkbox(this.rootElement.$(byAttribute("data-control-name",
        "authorizationrequired")));

    public SmtpConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultSensor(final String nameSensor) {
        super.createDefaultNetbaseSensor(nameSensor);
    }

    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), SmtpData.class);

        final SmtpData data = (SmtpData) sensorData;

        data.getCaption().ifPresent( caption ->
            setTexteditValue(SENSOR_CAPTION, caption)
        );
        data.getPort().ifPresent( port ->
            setNumberEditValue(PORT, port)
        );
        data.getTimeout().ifPresent( timeout ->
            setNumberEditValue(TIMEOUT, timeout)
        );
        data.getUsername().ifPresent( username -> {
            boolean isEnabled = isEnabledTextedit(USERNAME);
            if (!isEnabled) {
                this.authorizationCheckbox.click();
            }
            setTexteditValue(USERNAME, username);
            if (isEnabled != this.authorizationCheckbox.isChecked()) {
                this.authorizationCheckbox.click();
            }
        });
        data.getPassword().ifPresent( password -> {
            boolean isEnabled = isEnabledTextedit(PASSWORD);
            if (!isEnabled) {
                this.authorizationCheckbox.click();
            }
            setTexteditValue(PASSWORD, password);
            if (isEnabled != this.authorizationCheckbox.isChecked()) {
                this.authorizationCheckbox.click();
            }
        });
        data.getSecureConnection().ifPresent( check -> {
            if (check != this.secureConnectionCheckbox.isChecked()) {
                this.secureConnectionCheckbox.click();
            }
        });
        data.getAuthorizationRequired().ifPresent( check -> {
            if (check != this.authorizationCheckbox.isChecked()) {
                this.authorizationCheckbox.click();
            }
        });
    }

    @Override
    protected final SmtpData getData() {
        final SmtpData result = new SmtpData(
            Builder.of()
                .setCaption(getTexteditValue(SENSOR_CAPTION))
                .setPortString(getNumberEditString(PORT))
                .setTimeoutString(getNumberEditString(TIMEOUT))
                .setUsername(getTexteditValue(USERNAME))
                .setPassword(getTexteditValue(PASSWORD))
                .setSecureConnection(this.secureConnectionCheckbox.isChecked())
                .setAuthorizationRequired(this.authorizationCheckbox.isChecked())
        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
