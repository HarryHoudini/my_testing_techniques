package main.java.elements.wizards.sensor.config.winbase;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.winbase.PhysicalMemoryData;
import main.java.data.sensors.winbase.WinbaseData.Builder;
import main.java.data.sensors.winbase.WinbaseData.ConditionType;
import main.java.elements.simple_elements.Select;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.elements.wizards.action.config.ConfigActionsWizardTextedit.ACTION_CAPTION;
import static main.java.elements.wizards.sensor.DefaultValueSensorsConstant.PHYSICAL_MEMORY_CONST;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.PHYSICAL_MEMORY;

public final class PhysicalMemoryConfigSensorsWizard extends ConfigSensorsWizard {

    private final Select<ConditionType> conditionSelect = new Select<>(
        this.rootElement.$(byAttribute("data-control-name","condition")),
        ConditionType.class);

    public PhysicalMemoryConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultSensor(final String sensorCaption){
        setTexteditValue  (ConfigSensorsWizardTextedit.SENSOR_CAPTION,  sensorCaption);
        setNumberEditValue(PHYSICAL_MEMORY, PHYSICAL_MEMORY_CONST);
        clickCloseModalButton(FooterWizardButton.SAVE);
    }

    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), PhysicalMemoryData.class);

        final PhysicalMemoryData data = (PhysicalMemoryData) sensorData;

        data.getCaption().ifPresent( caption ->
            setTexteditValue(ACTION_CAPTION, caption)
        );
        data.getExpectedValue().ifPresent( size ->
            setNumberEditValue(PHYSICAL_MEMORY, size)
        );
        data.getOperator().ifPresent(this.conditionSelect::selectItem);
    }

    @Override
    protected final PhysicalMemoryData getData() {
        final PhysicalMemoryData result = new PhysicalMemoryData(
            Builder.of()
                .setCaption(getTexteditValue(ACTION_CAPTION))
                .setExpectedValueString(getNumberEditString(PHYSICAL_MEMORY))
                .setOperator(this.conditionSelect.getSelectedOption())

        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
