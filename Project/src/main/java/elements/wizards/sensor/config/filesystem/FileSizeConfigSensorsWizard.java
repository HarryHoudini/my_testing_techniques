package main.java.elements.wizards.sensor.config.filesystem;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.filesystem.FileSizeData;
import main.java.data.sensors.filesystem.FileSizeData.Builder;
import main.java.data.sensors.filesystem.SizeCondition;
import main.java.data.sensors.filesystem.UnitSize;
import main.java.elements.simple_elements.Select;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.elements.wizards.action.config.ConfigActionsWizardTextedit.ACTION_CAPTION;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.*;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.FILE_PATH;

public final class FileSizeConfigSensorsWizard extends ConfigSensorsWizard {

    private final Select<SizeCondition> conditionSelect = new Select<>(
        this.rootElement.$(byAttribute("data-control-name","relation")),
        SizeCondition.class);
    private final Select<UnitSize> measureSelect = new Select<>(
        this.rootElement.$(byAttribute("data-control-name","ratio")),
        UnitSize.class);

    public FileSizeConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultSensor(final String nameSensor) {
        super.createDefaultNetbaseSensor(nameSensor);
    }

    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), FileSizeData.class);

        final FileSizeData data = (FileSizeData) sensorData;

        data.getCaption().ifPresent( caption ->
            setTexteditValue(ACTION_CAPTION, caption)
        );
        data.getPath().ifPresent( path ->
            setTexteditValue(FILE_PATH, path)
        );
        data.getSize().ifPresent( size ->
            setNumberEditValue(SIZE, size)
        );
        data.getCondition().ifPresent(this.conditionSelect::selectItem);
        data.getUnitOfMeasure().ifPresent(this.measureSelect::selectItem);
    }

    @Override
    protected final FileSizeData getData() {
        final FileSizeData result = new FileSizeData(
            Builder.of()
                .setCaption(getTexteditValue(ACTION_CAPTION))
                .setPath(getTexteditValue(FILE_PATH))
                .setSizeString(getNumberEditString(SIZE))
                .setCondition(this.conditionSelect.getSelectedOption())
                .setUnitOfMeasure(this.measureSelect.getSelectedOption())
        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
