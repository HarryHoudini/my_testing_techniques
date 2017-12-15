package main.java.elements.wizards.sensor.config.winbase;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.winbase.RegistryKeyValueData;
import main.java.data.sensors.winbase.RegistryKeyValueData.Builder;
import main.java.data.sensors.winbase.RegistryKeyValueData.ConditionType;
import main.java.data.sensors.winbase.RegistryKeyValueData.ValueType;
import main.java.elements.simple_elements.Checkbox;
import main.java.elements.simple_elements.Select;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.elements.wizards.action.config.ConfigActionsWizardTextedit.ACTION_CAPTION;
import static main.java.elements.wizards.sensor.DefaultValueSensorsConstant.REGISTRY_KEY_CONST;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.*;

public final class RegistryKeyConfigSensorsWizard extends ConfigSensorsWizard {

    private final Checkbox valueContentCheckbox =
        new Checkbox(this.rootElement.$(byAttribute("data-control-name","valuecontent")));

    private final Select<ConditionType> conditionSelect = new Select<>(
        this.rootElement.$(byAttribute("data-control-name","condition")),
        ConditionType.class);

    private final Select<ValueType> expectedTypeSelect = new Select<>(
        this.rootElement.$(byAttribute("data-control-name","expectedtype")),
        ValueType.class);

    public RegistryKeyConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    public void createDefaultSensor(String sensorCaption){
        setTexteditValue(ConfigSensorsWizardTextedit.SENSOR_CAPTION, sensorCaption);
        setTexteditValue(REGISTRY_KEY,   REGISTRY_KEY_CONST);
        clickCloseModalButton(FooterWizardButton.SAVE);
    }
    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), RegistryKeyValueData.class);

        final RegistryKeyValueData data = (RegistryKeyValueData) sensorData;

        data.getCaption().ifPresent( caption ->
            setTexteditValue(ACTION_CAPTION, caption)
        );
        data.getKey().ifPresent( key ->
            setTexteditValue(REGISTRY_KEY, key)
        );
        data.getValueName().ifPresent( name ->
            setTexteditValue(REGISTRY_VALUE_NAME, name)
        );
        data.getExpectedType().ifPresent( type -> {
            boolean isEnabled = this.expectedTypeSelect.isEnabled();
            if (!isEnabled) {
                this.valueContentCheckbox.click();
            }
            this.expectedTypeSelect.selectItem(type);
            if (isEnabled != this.valueContentCheckbox.isChecked()) {
                this.valueContentCheckbox.click();
            }
        });
        data.getCondition().ifPresent( condition -> {
            boolean isEnabled = this.conditionSelect.isEnabled();
            if (!isEnabled) {
                this.valueContentCheckbox.click();
            }
            this.conditionSelect.selectItem(condition);
            if (isEnabled != this.valueContentCheckbox.isChecked()) {
                this.valueContentCheckbox.click();
            }
        });
        data.getExpectedValue().ifPresent( value -> {
            boolean isEnabled = isEnabledTextArea(EXPECTED_VALUE);
            if (!isEnabled) {
                this.valueContentCheckbox.click();
            }
            setTextAreaValue(EXPECTED_VALUE, value);
            if (isEnabled != this.valueContentCheckbox.isChecked()) {
                this.valueContentCheckbox.click();
            }
        });
        data.getCheckValueContent().ifPresent(check -> {
            if(check != this.valueContentCheckbox.isChecked()){
                this.valueContentCheckbox.click();
            }
        });
    }

    @Override
    protected final RegistryKeyValueData getData() {
        final RegistryKeyValueData result = new RegistryKeyValueData(
            Builder.of()
                .setCaption(getTexteditValue(ACTION_CAPTION))
                .setKey(getTexteditValue(REGISTRY_KEY))
                .setValueName(getTexteditValue(REGISTRY_VALUE_NAME))
                .setExpectedValue(getTextAreaValue(EXPECTED_VALUE))
                .setCondition(this.conditionSelect.getSelectedOption())
                .setExpextedType(this.expectedTypeSelect.getSelectedOption())
                .setValueContent(this.valueContentCheckbox.isChecked())
        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
