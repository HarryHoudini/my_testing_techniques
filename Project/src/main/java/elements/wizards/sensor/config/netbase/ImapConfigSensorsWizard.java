package main.java.elements.wizards.sensor.config.netbase;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.netbase.ImapData;
import main.java.data.sensors.netbase.ImapData.Builder;
import main.java.elements.simple_elements.Checkbox;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.*;

public final class ImapConfigSensorsWizard extends ConfigSensorsWizard {

    private final Checkbox secureConnectionCheckbox =
        new Checkbox(this.rootElement.$(byAttribute("data-control-name","secureconnection")));
    private final Checkbox authorizationCheckbox = new Checkbox(this.rootElement.$(byAttribute("data-control-name",
        "authorizationrequired")));
    private final Checkbox searchCheckbox =
        new Checkbox(this.rootElement.$(byAttribute("data-control-name","search")));

    public ImapConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public void createDefaultSensor(String nameSensor) {
        super.createDefaultNetbaseSensor(nameSensor);
    }

    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), ImapData.class);

        ImapData data = (ImapData) sensorData;
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
        data.getMailbox().ifPresent( mailbox -> {
            boolean isEnabled = isEnabledTextedit(MAILBOX);
            if (!isEnabled) {
                this.searchCheckbox.click();
            }
            setTexteditValue(MAILBOX, mailbox);
            if (isEnabled != this.searchCheckbox.isChecked()) {
                this.searchCheckbox.click();
            }
        });
        data.getSearchParameter().ifPresent( parameters -> {
            boolean isEnabled = isEnabledTextedit(SEARCH_PARAMETERS);
            if (!isEnabled) {
                this.searchCheckbox.click();
            }
            setTexteditValue(SEARCH_PARAMETERS, parameters);
            if (isEnabled != this.searchCheckbox.isChecked()) {
                this.searchCheckbox.click();
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
        data.getNeedSearch().ifPresent( check -> {
            if (check != this.searchCheckbox.isChecked()) {
                this.searchCheckbox.click();
            }
        });
    }

    @Override
    protected final ImapData getData() {
        final ImapData result = new ImapData(
            Builder.of()
                .setCaption(getTexteditValue(SENSOR_CAPTION))
                .setPortString(getNumberEditString(PORT))
                .setTimeoutString(getNumberEditString(TIMEOUT))
                .setUsername(getTexteditValue(USERNAME))
                .setPassword(getTexteditValue(PASSWORD))
                .setMailbox(getTexteditValue(MAILBOX))
                .setSearchParameter(getTexteditValue(SEARCH_PARAMETERS))
                .setSecureConnection(this.secureConnectionCheckbox.isChecked())
                .setAuthorizationRequired(this.authorizationCheckbox.isChecked())
                .setNeedSearch(this.searchCheckbox.isChecked())

        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
