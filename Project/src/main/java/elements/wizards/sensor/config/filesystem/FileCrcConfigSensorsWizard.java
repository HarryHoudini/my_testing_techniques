package main.java.elements.wizards.sensor.config.filesystem;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.filesystem.EqualsOperator;
import main.java.data.sensors.filesystem.FileCrcData;
import main.java.data.sensors.filesystem.FileCrcData.Builder;
import main.java.elements.simple_elements.Select;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.elements.wizards.sensor.DefaultValueSensorsConstant.CRC32_CONST;
import static main.java.elements.wizards.sensor.DefaultValueSensorsConstant.FIRST_FILE_PATH_CONST;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.*;

public final class FileCrcConfigSensorsWizard extends ConfigSensorsWizard {

    public final Select<EqualsOperator> conditionSelect = new Select<>(
        this.rootElement.$(byAttribute("data-control-name","condition")),
        EqualsOperator.class);

    public FileCrcConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultSensor(final String sensorCaption){
        setTexteditValue(SENSOR_CAPTION, sensorCaption);
        setTexteditValue(FILE_PATH,      FIRST_FILE_PATH_CONST);
        setTexteditValue(EXPECTED_CRC32, CRC32_CONST);
        clickCloseModalButton(FooterWizardButton.SAVE);
     }

    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), FileCrcData.class);

        final FileCrcData data = (FileCrcData) sensorData;

        data.getCaption().ifPresent( caption ->
            setTexteditValue(SENSOR_CAPTION, caption)
        );
        data.getPath().ifPresent( path ->
            setTexteditValue(FILE_PATH, path)
        );
        data.getCondition().ifPresent(this.conditionSelect::selectItem);
        data.getExpectedValue().ifPresent( value ->
            setTexteditValue(EXPECTED_CRC32, value)
        );
    }

    @Override
    protected final FileCrcData getData() {
        final FileCrcData result = new FileCrcData(
            Builder.of()
                .setCaption(getTexteditValue(SENSOR_CAPTION))
                .setPath(getTexteditValue(FILE_PATH))
                .setCondition(this.conditionSelect.getSelectedOption())
                .setExpectedValue(getTexteditValue(EXPECTED_CRC32))
        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
