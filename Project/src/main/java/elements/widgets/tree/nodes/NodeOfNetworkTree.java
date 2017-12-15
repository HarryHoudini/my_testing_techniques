package main.java.elements.widgets.tree.nodes;

import com.codeborne.selenide.*;
import io.vavr.Tuple;
import main.java.data.actions.ActionData;
import main.java.data.sensors.SensorData;
import main.java.elements.base.ContextPageElement;
import main.java.elements.context_menu.ContextMenu;
import main.java.elements.modal.ModalButton;
        import main.java.elements.modal.ModalTextedit;
import main.java.elements.modal.ModalType;
import main.java.elements.modal.networkTree.CredentialsModal;
import main.java.elements.modal.networkTree.GroupModal;
import main.java.elements.widgets.tree.nodes.actioninstance.ActionOfNode;
import main.java.elements.widgets.tree.nodes.actioninstance.SensorOfNode;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.action.SelectActionsWizard;
        import main.java.elements.wizards.action.ActionType;
import main.java.elements.wizards.action.config.ConfigActionsWizard;
import main.java.elements.wizards.sensor.SelectSensorsWizard;
        import main.java.elements.wizards.sensor.SensorType;
import main.java.data.Credential;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;
import main.java.utility.utils.MyConditions;
import one.util.streamex.StreamEx;

import java.util.NoSuchElementException;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selectors.byId;
        import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;


public abstract class NodeOfNetworkTree extends ContextPageElement {
    private final String id;

    NodeOfNetworkTree(SelenideElement root) {
        super(root);
        this.id = root.getAttribute("id");
    }

    // nodes methods
    public final boolean isSelected() {
        return this.rootElement.is(cssClass("_ol_active"));
    }

    public final boolean isInMultiSelected() {
        return this.rootElement.is(cssClass("_ol_multi_active"));
    }

    public final Integer getDepth() {
        // Get array classes from attribute "class" in format "class1 class2 class3"
        String[] classes = this.rootElement
            .attr("class")
            .split(" ");
        // Find class in format "_ol_depth_33"
        final String depthClass = StreamEx.of(classes)
            .filter(s -> s.matches("_ol_depth_\\d+"))
            .toList()
            .get(0);
        // Parsing depth
        return Integer.parseInt(depthClass.replace("_ol_depth_", ""));
    }

    public NodeOfNetworkTree select() {
        this.rootElement.click();
        return this;
    }

    SelenideElement renewRootElement() {
        return $(byId(this.id));
    }

    public abstract NodeOfNetworkTree renew();

    // context menu methods

    protected abstract NodeContextItem getNameEditMenuItem();

    /**
     * Standart contexClick() performs a context-click at middle of the given element.
     * while clicking can be performed on a sensor or an action
     * This method perform click in top-left cornet of the given element
     */
    @Override
    public final ContextMenu callContextMenu() {
        final int X_OFFSET_CURSOR = 50;
        final int Y_OFFSET_CURSOR = 5;
        renew().getRootElement().click();
        actions()
            .moveToElement(renew().getRootElement(), X_OFFSET_CURSOR, Y_OFFSET_CURSOR)
            .contextClick()
            .build()
            .perform();
        return ContextMenu.of();
    }

    // attributes methods

    protected abstract String getCaption();

    public NodeOfNetworkTree editCaption(String newCaption) {
        callEditModal()
            .setTexteditValue(ModalTextedit.CAPTION, newCaption)
            .clickCloseModalButton(ModalButton.SAVE);
        return this;
    }

    protected GroupModal callEditModal(){
        callContextMenu().clickOnMenuItem(getNameEditMenuItem());
        return GroupModal.groupModal(ModalType.EDIT_NODE);
    }

    private String getDescription(){
        final GroupModal modal = callEditModal();
        final String result = modal.getDescription();
        modal
            .clickCloseModalButton(ModalButton.SAVE);
        return result;
    }

    public final String getId(){
        return this.id;
    }

    // sensor's methods
    public NodeOfNetworkTree addDefaultSensor(final SensorType sensor, final String nameSensor) {
        callContextMenu().clickOnMenuItem(NodeContextItem.ADD_SENSOR);
        SelectSensorsWizard.of()
            .selectSensor(sensor);
        final ConfigSensorsWizard configWizard = sensor.getConfigWizardConstructor().apply(WizardType.CREATE_SENSOR);
        configWizard.createDefaultSensor(nameSensor);
        return this;
    }

    public NodeOfNetworkTree addSensor(final SensorType sensor, final SensorData data){
        callContextMenu().clickOnMenuItem(NodeContextItem.ADD_SENSOR);
        SelectSensorsWizard.of().selectSensor(sensor);
        final ConfigSensorsWizard configWizard = sensor.getConfigWizardConstructor().apply(WizardType.CREATE_SENSOR);
        configWizard.enterCorrectData(data);
        return this;
    }

    private SelenideElement getSensorElement(final String sensorCaption){
        return this.rootElement.$x(
            String.format(".//*[contains(text(), \"%s\")]/parent::li[contains(@class, \"sensor\")]", sensorCaption)
        );
    }

    public SensorOfNode getSensor(final String sensorCaption, final SensorType type){
        return new SensorOfNode(getSensorElement(sensorCaption), type).renew();
    }

    private boolean hasSensor(String sensorCaption){
        return getSensorElement(sensorCaption).exists();
    }

    // action's methods
    public NodeOfNetworkTree addDefaultAction(final ActionType action, final String nameAction){
        callContextMenu().clickOnMenuItem(NodeContextItem.ADD_ACTION);
        SelectActionsWizard.of()
            .selectAction(action);
        final ConfigActionsWizard configWizard = action.getConfigWizardConstructor().apply(WizardType.CREATE_ACTION);
        configWizard.createDefaultAction(nameAction);
        return this;
    }

    public NodeOfNetworkTree addAction(final ActionType action, final ActionData data){
        callContextMenu().clickOnMenuItem(NodeContextItem.ADD_ACTION);
        SelectActionsWizard.of().selectAction(action);
        final ConfigActionsWizard configWizard = action.getConfigWizardConstructor().apply(WizardType.CREATE_ACTION);
        configWizard
            .enterCorrectData(data);
        return this;
    }

    private SelenideElement getActionElement(final String actionCaption){
        return this.rootElement.$x(
            String.format(".//*[contains(text(), \"%s\")]/parent::li[contains(@class, \"action\")]", actionCaption)
        );
    }

    public ActionOfNode getAction(final String actionCaption, final ActionType type){
        return new ActionOfNode(getActionElement(actionCaption), type).renew();
    }

    public boolean hasAction(String actionCaption) {
        return getActionElement(actionCaption).exists();
    }

    // credential's methods
    public NodeOfNetworkTree setDefaultCredential() {
        getCredentialsModal()
            .setDefaultCredential()
            .clickCloseModalButton(ModalButton.SAVE);
        return this;
    }

    public NodeOfNetworkTree setInheritCredential() {
        getCredentialsModal()
            .setInheritCredential()
            .clickCloseModalButton(ModalButton.SAVE);
        return this;
    }

    public NodeOfNetworkTree selectCredential(Credential credential){
        getCredentialsModal()
            .selectCredential(credential)
            .clickCloseModalButton(ModalButton.SAVE);
        return this;
    }

    public NodeOfNetworkTree createNewCredential(Credential credential){
        getCredentialsModal()
            .createNewCredential(credential)
            .clickCloseModalButton(ModalButton.SAVE);
        return this;
    }

    private CredentialsModal getCredentialsModal() {
        return callEditModal().selectCredentialsModal();
    }

    public boolean isDefaultCredentialSelected() {
        CredentialsModal credentialsModal = getCredentialsModal();
        final boolean result = credentialsModal.isDefaultSelected();
        credentialsModal
            .clickCloseModalButton(ModalButton.SAVE);
        return result;
    }

    // conditions methods
    public NodeOfNetworkTree shouldHasCaption(String caption) throws NoSuchElementException {
        if (MyConditions.waitingBy((node, name) -> node.renew().getCaption().equals(name),
                                    Tuple.of(this, caption))) {
            return this;
        }
        throw new NoSuchElementException( this + " has not caption " + caption);
    }

    public NodeOfNetworkTree shouldHasDescription(String description) throws NoSuchElementException {
        if (MyConditions.waitingBy((node, desc) -> node.renew().getDescription().equals(desc),
                                   Tuple.of(this, description))) {
            return this;
        }
        throw new NoSuchElementException( this + " has not description " + description);
    }

    public NodeOfNetworkTree shouldHasSensor(String sensorName) {
        if (MyConditions.waitingBy(NodeOfNetworkTree::hasSensor,
            Tuple.of(this, sensorName))
        ) {
            return this;
        }
        throw new NoSuchElementException( this + " has not sensor " + sensorName);
    }

    public NodeOfNetworkTree shouldHasAction(String actionName) {
        if (MyConditions.waitingBy(NodeOfNetworkTree::hasAction,
            Tuple.of(this, actionName))
            ) {
            return this;
        }
        throw new NoSuchElementException( this + " has not action " + actionName);
    }

    @Override
    public String toString() {
        return "NodeOfNetworkTree{" +
            "id='" + id + '\'' +
            '}';
    }
}



