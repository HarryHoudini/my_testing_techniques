package main.java.elements.wizards.sensor.config.netbase;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.netbase.FtpData;
import main.java.data.sensors.netbase.FtpData.Builder;
import main.java.elements.simple_elements.Checkbox;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.elements.wizards.action.config.ConfigActionsWizardTextedit.ACTION_CAPTION;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.*;

public final class FtpConfigSensorsWizard extends ConfigSensorsWizard {

    private final Checkbox secureConnectionCheckbox =
        new Checkbox(this.rootElement.$(byAttribute("data-control-name","secureconnection")));
    private final Checkbox activeModeCheckbox =
        new Checkbox(this.rootElement.$(byAttribute("data-control-name","active")));

    public FtpConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultSensor(final String nameSensor) {
        super.createDefaultNetbaseSensor(nameSensor);
    }

    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), FtpData.class);

        final FtpData data = (FtpData) sensorData;

        data.getCaption().ifPresent( caption ->
            setTexteditValue(ACTION_CAPTION, caption)
        );
        data.getPort().ifPresent( port ->
            setNumberEditValue(PORT, port)
        );
        data.getTimeout().ifPresent( timeout ->
            setNumberEditValue(TIMEOUT, timeout)
        );
        data.getUsername().ifPresent( username ->
            setTexteditValue(USERNAME, username)
        );
        data.getPassword().ifPresent( password ->
            setTexteditValue(PASSWORD, password)
        );
        data.getArgument().ifPresent( argument ->{
            boolean isEnabled = isEnabledTextedit(FTP_ARGUMENT);
            if (!isEnabled){
                this.activeModeCheckbox.click();
            }
            setTexteditValue(FTP_ARGUMENT, argument);
            if(isEnabled != this.activeModeCheckbox.isChecked()){
                this.activeModeCheckbox.click();
            }
        });
        data.getSecureConnection().ifPresent( check -> {
            if(check != this.secureConnectionCheckbox.isChecked()){
                this.secureConnectionCheckbox.click();
            }
        });
        data.getEnabledActiveMode().ifPresent( check -> {
            if(check != this.activeModeCheckbox.isChecked()){
                this.activeModeCheckbox.click();
            }
        });
    }

    @Override
    protected final FtpData getData() {
        final FtpData result = new FtpData(
            Builder.of()
                .setCaption(getTexteditValue(ACTION_CAPTION))
                .setPortString(getNumberEditString(PORT))
                .setTimeoutString(getNumberEditString(TIMEOUT))
                .setUsername(getTexteditValue(USERNAME))
                .setPassword(getTexteditValue(PASSWORD))
                .setArgument(getTexteditValue(FTP_ARGUMENT))
                .setSecureConnection(this.secureConnectionCheckbox.isChecked())
                .setEnabledActiveMode(this.activeModeCheckbox.isChecked())

        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
