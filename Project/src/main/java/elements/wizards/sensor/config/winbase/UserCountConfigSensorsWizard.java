package main.java.elements.wizards.sensor.config.winbase;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.winbase.UserCountData;
import main.java.data.sensors.winbase.WinbaseData.Builder;
import main.java.data.sensors.winbase.WinbaseData.ConditionType;
import main.java.elements.simple_elements.Select;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.elements.wizards.action.config.ConfigActionsWizardTextedit.ACTION_CAPTION;
import static main.java.elements.wizards.sensor.DefaultValueSensorsConstant.USER_COUNT_CONST;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.USER_COUNT;

public final class UserCountConfigSensorsWizard extends ConfigSensorsWizard {

    private final Select<ConditionType> conditionSelect = new Select<>(
        this.rootElement.$(byAttribute("data-control-name","condition")),
        ConditionType.class);

    public UserCountConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    public final void createDefaultSensor(final String sensorCaption){
        setTexteditValue  (ConfigSensorsWizardTextedit.SENSOR_CAPTION, sensorCaption);
        setNumberEditValue(USER_COUNT,     USER_COUNT_CONST);
        clickCloseModalButton(FooterWizardButton.SAVE);
    }

    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), UserCountData.class);

        final UserCountData data = (UserCountData) sensorData;

        data.getCaption().ifPresent( caption ->
            setTexteditValue(ACTION_CAPTION, caption)
        );
        data.getExpectedValue().ifPresent( number ->
            setNumberEditValue(USER_COUNT, number)
        );
        data.getOperator().ifPresent(this.conditionSelect::selectItem);
    }

    @Override
    protected final UserCountData getData() {
        final UserCountData result = new UserCountData(
            Builder.of()
                .setCaption(getTexteditValue(ACTION_CAPTION))
                .setExpectedValueString(getNumberEditString(USER_COUNT))
                .setOperator(this.conditionSelect.getSelectedOption())

        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
