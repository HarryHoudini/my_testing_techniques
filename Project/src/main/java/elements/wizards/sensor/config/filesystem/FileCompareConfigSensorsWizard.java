package main.java.elements.wizards.sensor.config.filesystem;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.filesystem.EqualsOperator;
import main.java.data.sensors.filesystem.FileCompareData;
import main.java.elements.simple_elements.Checkbox;
import main.java.elements.simple_elements.Select;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.data.sensors.filesystem.FileCompareData.*;
import static main.java.elements.wizards.sensor.DefaultValueSensorsConstant.FIRST_FILE_PATH_CONST;
import static main.java.elements.wizards.sensor.DefaultValueSensorsConstant.SECOND_FILE_PATH_CONST;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.*;

public final class FileCompareConfigSensorsWizard extends ConfigSensorsWizard{

    private final Select<EqualsOperator> conditionSelect = new Select<>(
        this.rootElement.$(byAttribute("data-control-name","condition")),
        EqualsOperator.class);

    private final Checkbox checkContentCheckbox =
        new Checkbox(this.rootElement.$(byAttribute("data-control-name","checkcontent")));

    public FileCompareConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultSensor(final String sensorCaption){
        setTexteditValue(SENSOR_CAPTION,   sensorCaption);
        setTexteditValue(FIRST_FILE_PATH,  FIRST_FILE_PATH_CONST);
        setTexteditValue(SECOND_FILE_PATH, SECOND_FILE_PATH_CONST);
        clickCloseModalButton(FooterWizardButton.SAVE);
    }

    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), FileCompareData.class);

        final FileCompareData data = (FileCompareData) sensorData;

        data.getCaption().ifPresent( caption ->
            setTexteditValue(SENSOR_CAPTION, caption)
        );
        data.getFirstfile().ifPresent( path ->
            setTexteditValue(FIRST_FILE_PATH, path)
        );
        data.getSecondfile().ifPresent( path ->
            setTexteditValue(SECOND_FILE_PATH, path)
        );
        data.getCondition().ifPresent(this.conditionSelect::selectItem);
        data.getCheckContent().ifPresent( check -> {
            if(check != this.checkContentCheckbox.isChecked()){
                this.checkContentCheckbox.click();
            }
        });
    }

    @Override
    protected final FileCompareData getData() {
        final FileCompareData resultData = new FileCompareData(
            Builder.of()
                .setCaption(getTexteditValue(SENSOR_CAPTION))
                .setFirstfile(getTexteditValue(FIRST_FILE_PATH))
                .setSecondfile(getTexteditValue(SECOND_FILE_PATH))
                .setCondition(this.conditionSelect.getSelectedOption())
                .setCheckContent(this.checkContentCheckbox.isChecked())
        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return resultData;
    }
}
