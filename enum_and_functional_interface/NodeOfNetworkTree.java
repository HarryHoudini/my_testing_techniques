package test.network_tree_winget;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.vavr.Tuple;
import main.data.Credential;
import main.elements.context_menu.ContextMenu;
import main.elements.modal_windows.ModalButton;
import main.elements.modal_windows.ModalTextedit;
import main.elements.modal_windows.ModalType;
import main.elements.modal_windows.networkTree.CredentialsModal;
import main.elements.modal_windows.networkTree.GroupModal;
import main.elements.widgets.BasePageElement;
import main.elements.widgets.node_tree.nodes.NodeTreeContextMenuItem;
import main.elements.wizards.action_wizard.SelectActionsWizard;
import main.elements.wizards.action_wizard.SelectWizardAction;
import main.elements.wizards.sensor_wizards.SelectSensorsWizard;
import main.elements.wizards.sensor_wizards.SelectWizardSensor;
import main.utility.utils.MyConditions;
import one.util.streamex.StreamEx;
import org.openqa.selenium.JavascriptExecutor;

import java.util.NoSuchElementException;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;


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