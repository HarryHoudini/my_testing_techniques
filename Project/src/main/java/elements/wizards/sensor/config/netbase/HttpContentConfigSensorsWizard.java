package main.java.elements.wizards.sensor.config.netbase;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.netbase.HttpContentData;
import main.java.data.sensors.netbase.HttpContentData.Builder;
import main.java.elements.simple_elements.Checkbox;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.elements.wizards.sensor.DefaultValueSensorsConstant.*;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.*;

public final class HttpContentConfigSensorsWizard extends ConfigSensorsWizard {

    private final Checkbox secureConnectionCheckbox =
        new Checkbox(this.rootElement.$(byAttribute("data-control-name","secureconnection")));
    private final Checkbox conditionCheckbox =
        new Checkbox(this.rootElement.$(byAttribute("data-control-name","condition")));

    public HttpContentConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    public final void createDefaultSensor(final String sensorCaption){
        setTexteditValue(ConfigSensorsWizardTextedit.SENSOR_CAPTION, sensorCaption);
        setTexteditValue(URL_PATH, URL_PATH_CONST);
        clickCloseModalButton(FooterWizardButton.SAVE);
    }

    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), HttpContentData.class);

        final HttpContentData data = (HttpContentData) sensorData;
        data.getCaption().ifPresent( caption ->
            setTexteditValue(SENSOR_CAPTION, caption)
        );
        data.getPort().ifPresent( port ->
            setNumberEditValue(PORT, port)
        );
        data.getTimeout().ifPresent( timeout ->
            setNumberEditValue(TIMEOUT, timeout)
        );
        data.getUrlPath().ifPresent( url ->
            setTexteditValue(URL_PATH, url)
        );
        data.getTargetValue().ifPresent( value ->
            setTextAreaValue(TARGET_VALUE, value)
        );
        data.getSecureConnection().ifPresent( check -> {
            if(check != this.secureConnectionCheckbox.isChecked()){
                this.secureConnectionCheckbox.click();
            }
        });
        data.getConditional().ifPresent( check -> {
            if(check != this.conditionCheckbox.isChecked()){
                this.conditionCheckbox.click();
            }
        });
    }

    @Override
    protected final HttpContentData getData() {
        final HttpContentData result = new HttpContentData(
            Builder.of()
                .setCaption(getTexteditValue(SENSOR_CAPTION))
                .setPortString(getNumberEditString(PORT))
                .setTimeoutString(getNumberEditString(TIMEOUT))
                .setUrlPath(getTexteditValue(URL_PATH))
                .setTargetValue(getTextAreaValue(TARGET_VALUE))
                .setSecureConnection(this.secureConnectionCheckbox.isChecked())
                .setConditional(this.conditionCheckbox.isChecked())

        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
