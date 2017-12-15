package main.java.elements.wizards.sensor.config.system;

import main.java.data.sensors.SensorData;
import main.java.data.sensors.system.ServiceStatusData;
import main.java.data.sensors.system.ServiceStatusData.ServiceStatus;
import main.java.elements.simple_elements.Select;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.elements.wizards.sensor.DefaultValueSensorsConstant.*;
import static main.java.elements.wizards.sensor.config.ConfigSensorsWizardTextedit.*;

public final class ServiceStatusConfigSensorsWizard extends ConfigSensorsWizard {

    private final Select<ServiceStatus> serviceStatusSelect = new Select<>(
        this.rootElement.$(byAttribute("data-control-name","servicestatus")),
        ServiceStatus.class);

    public ServiceStatusConfigSensorsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultSensor(final String sensorCaption){
        setTexteditValue(SENSOR_CAPTION, sensorCaption);
        setTexteditValue(SERVICE_NAME, SERVICE_NAME_CONST);
        clickCloseModalButton(FooterWizardButton.SAVE);
    }


    @Override
    protected final void editData(final SensorData sensorData) {
        checkDataClass(sensorData.getClass(), ServiceStatusData.class);

        final ServiceStatusData data = (ServiceStatusData) sensorData;
        data.getCaption().ifPresent( caption ->
            setTexteditValue(SENSOR_CAPTION, caption)
        );
        data.getServiceName().ifPresent( name ->
            setTexteditValue(SERVICE_NAME, name)
        );
        data.getStatus().ifPresent(this.serviceStatusSelect::selectItem);
    }

    @Override
    protected final ServiceStatusData getData() {
        final ServiceStatusData result = new ServiceStatusData(
            ServiceStatusData.Builder.of()
                .setCaption(getTexteditValue(SENSOR_CAPTION))
                .setServiceName(getTexteditValue(SERVICE_NAME))
                .setStatus(this.serviceStatusSelect.getSelectedOption())
        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return result;
    }
}
