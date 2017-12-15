package main.java.elements.wizards.sensor.config.winbase;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.winbase.VirtualMemoryData;
import main.java.data.sensors.winbase.WinbaseData.Builder;
import main.java.data.sensors.winbase.WinbaseData.ConditionType;
import main.java.elements.simple_elements.Select;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.elements.wizards.action.config.ConfigActionsWizardTextedit.ACTION_CAPTION;
import static main.java.elements.wizards.sensor.DefaultValueSensorsConstant.VIRTUAL_MEMORY_CONST;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.VIRTUAL_MEMORY;

public class VirtualMemoryConfigSensorsWizard extends ConfigSensorsWizard {

    private final Select<ConditionType> conditionSelect = new Select<>(
        this.rootElement.$(byAttribute("data-control-name","condition")),
        ConditionType.class);

    public VirtualMemoryConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    public final void createDefaultSensor(final String sensorCaption){
        setTexteditValue  (ConfigSensorsWizardTextedit.SENSOR_CAPTION, sensorCaption);
        setNumberEditValue(VIRTUAL_MEMORY, VIRTUAL_MEMORY_CONST);
        clickCloseModalButton(FooterWizardButton.SAVE);
    }

    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), VirtualMemoryData.class);

        final VirtualMemoryData data = (VirtualMemoryData) sensorData;

        data.getCaption().ifPresent( caption ->
            setTexteditValue(ACTION_CAPTION, caption)
        );
        data.getExpectedValue().ifPresent( memory ->
            setNumberEditValue(VIRTUAL_MEMORY, memory)
        );
        data.getOperator().ifPresent(this.conditionSelect::selectItem);
    }

    @Override
    protected final VirtualMemoryData getData() {
        final VirtualMemoryData result = new VirtualMemoryData(
            Builder.of()
                .setCaption(getTexteditValue(ACTION_CAPTION))
                .setExpectedValueString(getNumberEditString(VIRTUAL_MEMORY))
                .setOperator(this.conditionSelect.getSelectedOption())

        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
