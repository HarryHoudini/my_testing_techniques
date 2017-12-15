package main.java.elements.wizards.sensor;

import main.java.elements.simple_elements.SelectItemRow;
import main.java.elements.wizards.BaseWizard;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;

public class SelectSensorsWizard extends BaseWizard {
    private SelectSensorsWizard() {
        super(WizardType.CREATE_SENSOR);
    }
    // select button *[data-ol-accord-item = "netbase.ping"] *.ol_btn_select

    public static SelectSensorsWizard of(){
        return new SelectSensorsWizard();
    }

    public SelectSensorsWizard selectTab(SelectWizardTab tab){
        this.rootElement.$(tab.getLocator()).click();
        return this;
    }

    public void selectSensor(SensorType sensor){
        selectTab(sensor.getType());
        SelectItemRow.selectItemRow(this.rootElement.$(sensor.getLocator())).select();
        clickButton(FooterWizardButton.NEXT);
    }
}
