package main.java.elements.wizards.sensor.config.netbase;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.netbase.PopData;
import main.java.data.sensors.netbase.PopData.Builder;
import main.java.data.sensors.netbase.PopData.ComparisonOperator;
import main.java.elements.simple_elements.Checkbox;
import main.java.elements.simple_elements.Select;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.*;

public final class PopConfigSensorsWizard extends ConfigSensorsWizard {

    private final Checkbox secureConnectionCheckbox =
        new Checkbox(this.rootElement.$(byAttribute("data-control-name","secureconnection")));

    private final Checkbox authorizationCheckbox = new Checkbox(
        this.rootElement.$(byAttribute("data-control-name", "authorizationrequired")));

    private final Checkbox maildropSizeCheckbox =
        new Checkbox(this.rootElement.$(byAttribute("data-control-name","checkmaildropsize")));

    private final Select<ComparisonOperator> conditionSelect = new Select<>(
        this.rootElement.$(byAttribute("data-control-name","relation")),
        ComparisonOperator.class);

    public PopConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultSensor(final String nameSensor) {
        super.createDefaultNetbaseSensor(nameSensor);
    }

    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), PopData.class);

        final PopData data = (PopData) sensorData;

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
        data.getOperator().ifPresent( operator -> {
            boolean isEnabled = this.conditionSelect.isEnabled();
            if (!isEnabled) {
                this.maildropSizeCheckbox.click();
            }
            this.conditionSelect.selectItem(operator);
            if (isEnabled != this.maildropSizeCheckbox.isChecked()) {
                this.maildropSizeCheckbox.click();
            }
        });
        data.getMailDropSize().ifPresent( size -> {
            boolean isEnabled = isEnabledTextedit(MAILDROP_SIZE);
            if (!isEnabled) {
                this.maildropSizeCheckbox.click();
            }
            setNumberEditValue(MAILDROP_SIZE, size);
            if (isEnabled != this.maildropSizeCheckbox.isChecked()) {
                this.maildropSizeCheckbox.click();
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
        data.getCheckMailDropSize().ifPresent( check -> {
            if (check != this.maildropSizeCheckbox.isChecked()) {
                this.maildropSizeCheckbox.click();
            }
        });
    }

    @Override
    protected final PopData getData() {
        final PopData result = new PopData(
            Builder.of()
                .setCaption(getTexteditValue(SENSOR_CAPTION))
                .setPortString(getNumberEditString(PORT))
                .setTimeoutString(getNumberEditString(TIMEOUT))
                .setUsername(getTexteditValue(USERNAME))
                .setPassword(getTexteditValue(PASSWORD))
                .setMailDropSizeString(getNumberEditString(MAILDROP_SIZE))
                .setOperator(this.conditionSelect.getSelectedOption())
                .setSecureConnection(this.secureConnectionCheckbox.isChecked())
                .setAuthorizationRequired(this.authorizationCheckbox.isChecked())
                .setCheckMailDropSize(this.maildropSizeCheckbox.isChecked())

        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
