package main.java.elements.wizards.sensor.config.winbase;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.winbase.ProcessCountData;
import main.java.data.sensors.winbase.WinbaseData.Builder;
import main.java.data.sensors.winbase.WinbaseData.ConditionType;
import main.java.elements.simple_elements.Select;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.elements.wizards.action.config.ConfigActionsWizardTextedit.ACTION_CAPTION;
import static main.java.elements.wizards.sensor.DefaultValueSensorsConstant.PROCESS_COUNT_CONST;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.PROCESS_COUNT;

public class ProcessCountConfigSensorsWizard extends ConfigSensorsWizard {

    private final Select<ConditionType> conditionSelect = new Select<>(
        this.rootElement.$(byAttribute("data-control-name","condition")),
        ConditionType.class);

    public ProcessCountConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultSensor(final String sensorCaption){
        setTexteditValue  (ConfigSensorsWizardTextedit.SENSOR_CAPTION, sensorCaption);
        setNumberEditValue(PROCESS_COUNT,  PROCESS_COUNT_CONST);
        clickCloseModalButton(FooterWizardButton.SAVE);
    }

    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), ProcessCountData.class);

        final ProcessCountData data = (ProcessCountData) sensorData;

        data.getCaption().ifPresent( caption ->
            setTexteditValue(ACTION_CAPTION, caption)
        );
        data.getExpectedValue().ifPresent( number ->
            setNumberEditValue(PROCESS_COUNT, number)
        );
        data.getOperator().ifPresent(this.conditionSelect::selectItem);
    }

    @Override
    protected final ProcessCountData getData() {
        final ProcessCountData result = new ProcessCountData(
            Builder.of()
                .setCaption(getTexteditValue(ACTION_CAPTION))
                .setExpectedValueString(getNumberEditString(PROCESS_COUNT))
                .setOperator(this.conditionSelect.getSelectedOption())

        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
