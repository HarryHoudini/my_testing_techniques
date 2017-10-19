package test.network_tree_winget;

import io.vavr.Tuple;
import main.elements.context_menu.ContextMenu;
import main.elements.widgets.BasePageElement;
import main.elements.widgets.node_tree.nodes.NodeTreeContextMenuItem;
import main.elements.wizards.sensor_wizards.SelectWizardSensor;
import main.utility.utils.MyConditions;

import java.util.NoSuchElementException;

import static com.codeborne.selenide.Condition.text;


public abstract class NodeOfNetworkTree extends BasePageElement {

    /**
     * Standart contexClick() performs a context-click at middle of the given element.
     * while clicking can be performed on a sensor or an action
     * This method perform click in top-left cornet of the given element
     */
    final ContextMenu callContexMenu() {
        //implement contex menu for Node
    }

    // sensor's methods
    public NodeOfNetworkTree addDefaultSensor(SelectWizardSensor sensor, String nameSensor) {
        callContexMenu().clickOnMenuItem(NodeTreeContextMenuItem.ADD_SENSOR);
        sensor
            .getConfigWizardConstructor()
            .get()
            .createDefaultSensor(nameSensor);
        return this;
    }

    NodeOfNetworkTree shoudHasSensor(String sensorName) {
        if (MyConditions.waitingBy((node, sensorCaption) -> {
                return node.
                    getAllSensors().
                    stream().
                    anyMatch((element) -> element.has(text(sensorCaption)));
            },
            Tuple.of(this, sensorName))
            ) {
            return this;
        }
        throw new NoSuchElementException( this + " has not sensor " + sensorName);
    }
}