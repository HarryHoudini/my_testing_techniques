package main.java.elements.wizards.sensor.config.winbase;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.winbase.CpuUsageData;
import main.java.data.sensors.winbase.WinbaseData.Builder;
import main.java.data.sensors.winbase.WinbaseData.ConditionType;
import main.java.elements.simple_elements.Select;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.elements.wizards.sensor.DefaultValueSensorsConstant.CPU_USAGE_CONST;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.*;


public final class CpuUsageConfigSensorsWizard extends ConfigSensorsWizard {

    private final Select<ConditionType> conditionSelect = new Select<>(
        this.rootElement.$(byAttribute("data-control-name","condition")),
        ConditionType.class);

    public CpuUsageConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultSensor(final String sensorCaption){
        setTexteditValue  (SENSOR_CAPTION, sensorCaption);
        setNumberEditValue(CPU_USAGE,      CPU_USAGE_CONST);
        clickCloseModalButton       (FooterWizardButton.SAVE);
    }

    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), CpuUsageData.class);

        final CpuUsageData data = (CpuUsageData) sensorData;

        data.getCaption().ifPresent( caption ->
            setTexteditValue(SENSOR_CAPTION, caption)
        );
        data.getExpectedValue().ifPresent( cpu ->
            setNumberEditValue(CPU_USAGE, cpu)
        );
        data.getOperator().ifPresent(this.conditionSelect::selectItem);
    }

    @Override
    protected final CpuUsageData getData() {
        final CpuUsageData result = new CpuUsageData(
            Builder.of()
                .setCaption(getTexteditValue(SENSOR_CAPTION))
                .setExpectedValueString(getNumberEditString(CPU_USAGE))
                .setOperator(this.conditionSelect.getSelectedOption())

        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
