package main.java.elements.widgets.tree.nodes.actioninstance;

import com.codeborne.selenide.SelenideElement;
import io.vavr.Tuple;
import lombok.Getter;
import lombok.Setter;
import main.java.data.sensors.SensorData;
import main.java.elements.base.ContextPageElement;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.SensorType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;
import main.java.utility.utils.MyConditions;

import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

@Setter
@Getter
public final class SensorOfNode extends ContextPageElement {

    private final String id;
    private SensorType sensorType;

    public SensorOfNode(final SelenideElement root, final SensorType type) {
        super(root);
        this.id = root.getAttribute("id");
        this.sensorType = type;
    }

    public SensorOfNode renew(){
        return new SensorOfNode($(byId(this.id)), this.sensorType);
    }

    public SensorOfNode editCorrectData(SensorData data){
        callContextMenu().clickOnMenuItem(SensorContextItem.EDIT_SENSOR);
        ConfigSensorsWizard configWizard = this.sensorType.getConfigWizardConstructor().apply(WizardType.EDIT_SENSOR);
        configWizard.enterCorrectData(data);
        return this;
    }
    public SensorOfNode editWrongData(SensorData data){
        callContextMenu().clickOnMenuItem(SensorContextItem.EDIT_SENSOR);
        ConfigSensorsWizard configWizard = this.sensorType.getConfigWizardConstructor().apply(WizardType.EDIT_SENSOR);
        configWizard.enterWrongData(data);
        return this;
    }

    public ConfigSensorsWizard getConfigWizards(){
        return this.sensorType
            .getConfigWizardConstructor()
            .apply(WizardType.EDIT_SENSOR);
    }

    private boolean hasData (SensorData expectedData){
        callContextMenu().clickOnMenuItem(SensorContextItem.EDIT_SENSOR);
        ConfigSensorsWizard configWizard = this.sensorType.getConfigWizardConstructor().apply(WizardType.EDIT_SENSOR);
        return configWizard.hasData(expectedData);
    }

    public SensorOfNode shouldHasData(SensorData expectedData) {
        if (MyConditions.waitingBy(SensorOfNode::hasData,
            Tuple.of(this, expectedData))
            ) {
            return this;
        }
        throw new NoSuchElementException( this + " has not data: " + expectedData);
    }

    @Override
    public String toString() {
        return "SensorOfNode{" +
            "sensorType=" + sensorType + "; id=" + this.rootElement.getAttribute("id") +
            '}';
    }
}
