package main.java.elements.widgets.tree;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.vavr.Tuple;
import main.java.elements.modal.BaseModal;
import main.java.elements.modal.ModalButton;
import main.java.elements.modal.ModalType;
import main.java.elements.modal.networkTree.DeviceModal;
import main.java.elements.widgets.BaseWidget;
import main.java.elements.widgets.tree.nodes.DeviceOfNetworkTree;
import main.java.elements.widgets.tree.nodes.GroupOfNetworkTree;
import main.java.elements.widgets.tree.nodes.NodeOfNetworkTree;
import main.java.elements.widgets.tree.nodes.RootOfNetworkTree;
import main.java.data.DeviceAttributes;
import main.java.utility.utils.MyComparators;
import main.java.utility.utils.MyConditions;
import main.java.utility.utils.MyStreamMethods;
import main.java.utility.utils.MyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

public class NetworkTreeWidget extends BaseWidget {

    private final SelenideElement addDeviceButton =
        this.rootElement.$(byAttribute("data-qa-id", "addDevice"));

    private final SelenideElement sortByButton =
        this.rootElement.$(byAttribute("data-qa-id", "filterMode"));

    private final SelenideElement visualModeButton =
        this.rootElement.$(byAttribute("data-qa-id", "visualMode"));

    private final SelenideElement searchControl = this.rootElement.$(By.tagName("ol-input-search"));

    private final SelenideElement searchTextedit = this.searchControl.$(By.tagName("input"));

    private final SelenideElement clearSearchButton = this.searchControl.$(byClassName("js_del_heandler"));

    private final SelenideElement searchButton = this.searchControl.$(byClassName("js_search_heandler"));

    private final SelenideElement reverseSortCheckbox =
        this.rootElement.$(by("data-qa-id", "isReverse"));

    private final SelenideElement nodesTree = this.rootElement.$(byClassName("ol_nodes_list"));

    public NetworkTreeWidget(SelenideElement widgetRoot) {
        super(widgetRoot);
    }

    public NetworkTreeWidget() {
        this($(byClassName("ol_wdgt_nodesTree")));
    }

    // Add Device
    public NetworkTreeWidget addNewDevice(DeviceAttributes attributes) {
        addDeviceButton.click();
        DeviceModal addDeviceModal = new DeviceModal(ModalType.ADD_NODE);
        addDeviceModal.setAll(attributes);
        return this;
    }

    // Getting Elements of Tree

    private ElementsCollection getAllNodes(){ return rootElement.$$(byClassName("ol_nodes_list_item")); }

    public final ElementsCollection getAllDevicesOfTree() { return getAllNodes().filter(cssClass("_ol_device")); }

    public final DeviceOfNetworkTree getDeviceByMainField(String mainFieldText){
        SelenideElement device = getAllDevicesOfTree()
            .filter(MyConditions.mainFieldDevice(mainFieldText))
            .shouldHaveSize(1)
            .first();
        return new DeviceOfNetworkTree(device);
    }

    public final DeviceOfNetworkTree getDeviceByAdditionalField(String additionalFieldText){
        SelenideElement device = getAllDevicesOfTree()
            .filter(MyConditions.additionalFieldDevice(additionalFieldText))
            .shouldHaveSize(1)
            .first();
        return new DeviceOfNetworkTree(device);
    }

    public final GroupOfNetworkTree getGroup(String nameGroup){
        SelenideElement group = getAllNodes()
            .filter(cssClass("_ol_group"))
            .filter(MyConditions.nameGroup(nameGroup))
            .shouldHaveSize(1)
            .first();
        return new GroupOfNetworkTree(group);
    }

    public final RootOfNetworkTree getRoot(){
        String idRoot = String.format("%s-1", this.nodesTree.getAttribute("id"));
        return new RootOfNetworkTree(this.nodesTree.$(byId(idRoot)));
    }

    /**
     * This method find parent for subNode
     * if depth of subNode == 2, parent - Root of network tree
     * if depth > 2 - get all nodes of tree, drop element after subNode and find last element
     * with depth less than one subNode
     */
    private RootOfNetworkTree getParent(final NodeOfNetworkTree subNode) {

        final int depth = subNode.getDepth();
        if (depth == 1){
            throw new NotFoundException("Only root can has depth 1");
        }
        else if (depth == 2){
            SelenideElement element =this.getAllNodes()
                .filter(Condition.cssClass("_ol_depth_1"))
                .shouldHaveSize(1)
                .first();
            return new RootOfNetworkTree(element);
        }
        final int parentDepth = depth - 1;
        final String classDepth = "_ol_depth_" + parentDepth;
        Stream<SelenideElement> streamElements =
            MyStreamMethods.dropRightUntil(getAllNodes(),
                element -> element.equals(subNode.getRootElement())
            ).stream()
                .filter( s -> s.has(cssClass("_ol_group")))
                .filter( s -> s.has(cssClass(classDepth)));
        return new GroupOfNetworkTree(MyStreamMethods.getLast(streamElements)
            .orElseThrow(
                () -> new NoSuchElementException("This element has not parent")
            ));
    }

    // Change the Tree view

    public final NetworkTreeFooter getFooter(){
        return new NetworkTreeFooter(this.rootElement);
    }

    public final NetworkTreeWidget collapseAllGroup(){
        SelenideElement collapseButton = this.rootElement.$(byAttribute("data-qa-id", "collapse"));
        collapseButton.click();
        return this;
    }

    public final NetworkTreeWidget expandAllGroup(){
        SelenideElement expandButton = this.rootElement.$(byAttribute("data-qa-id", "expand"));
        expandButton.click();
        return this;
    }

    public final NetworkTreeWidget setVisualMode(VisualMode mode) {
        this.visualModeButton.click();
        this.visualModeButton.$(mode.getLocator()).click();
        this.visualModeButton.click();
        return this;
    }

    public final boolean isCheckedVisualMode(VisualMode mode){
        // O-0022
        return this.visualModeButton.$(mode.getNewLocator()).isSelected();
    }

    // Search's methods

    public final NetworkTreeWidget searching(String searchString) {
        searchTextedit.setValue(searchString);
        searchButton.click();
        return this;
    }

    public final NetworkTreeWidget clearSearchString(){
        clearSearchButton.click();
        return this;
    }

    // Sorting methods

    public NetworkTreeWidget sortBy(FieldOfSort fieldOfSort) {
        this.sortByButton.click();
        this.sortByButton.$(fieldOfSort.getLocator()).click();
        return this;
    }

    public final boolean isMainFieldSorted(boolean isReverse){
        final List<String> mainFields = getAllDevicesOfTree()
            .filter(cssClass("_ol_depth_2"))
            .stream()
            .map(DeviceOfNetworkTree::getMainFieldText)
            .collect(Collectors.toList());
        Comparator<String> comparator;
        if (isCheckedVisualMode(VisualMode.MAIN_IP_ADRESS)){
            comparator = MyComparators::ipComparator;
        }
        else{
            comparator = String::compareTo;
        }
        if(isReverse){
            comparator = comparator.reversed();
        }
        final List<String> sortedMainFields = mainFields
            .stream()
            .sorted(comparator)
            .collect(Collectors.toList());
        return mainFields.equals(sortedMainFields);
    }

    public final boolean isAdditionalFieldSorted(boolean isReverse){
        final List<String> additionalFields = getAllDevicesOfTree()
            .filter(cssClass("_ol_depth_2"))
            .stream()
            .map(DeviceOfNetworkTree::getAdditionalFieldText)
            .collect(Collectors.toList());
        Comparator<String> comparator;
        if (isCheckedVisualMode(VisualMode.ADD_IP_ADRESS)){
            comparator = MyComparators::ipComparator;
        }
        else{
            comparator = String::compareTo;
        }
        if(isReverse){
            comparator = comparator.reversed();
        }
        final List<String> sortedAdditionalFields = additionalFields
            .stream()
            .sorted(comparator)
            .collect(Collectors.toList());
        return additionalFields.equals(sortedAdditionalFields);
    }

    private boolean isReverseSorting(){
        // O-0022
        return this.reverseSortCheckbox.$(byClassName("ol-icon-check")).has(cssClass("_ol_checked"));
    }

    public final NetworkTreeWidget setReverseSorting(boolean enable) {
        if (isReverseSorting() != enable){
            this.sortByButton.click();
            this.reverseSortCheckbox.click();
        }
        return this;
    }

    // Moving nodes

    public NetworkTreeWidget moveTo(NodeOfNetworkTree node, NodeOfNetworkTree group){
        return moveTo(Optional.ofNullable(node), group);
    }

    private NetworkTreeWidget moveTo(Optional<NodeOfNetworkTree> node, NodeOfNetworkTree group){
        node.ifPresent(nodeOfTree -> {
            MyUtils.myDragAndDrop(nodeOfTree.getRootElement(), group.getRootElement());
            BaseModal moveModal = new BaseModal(ModalType.MOVE_NODE);
            moveModal
                .clickCloseModalButton(ModalButton.OK);
        });
        return this;
    }

    public NetworkTreeWidget moveTo(List<NodeOfNetworkTree> nodes, NodeOfNetworkTree group){
        // Perform group selection of nodes - click() on the first + click() with CTRL for the rest
        final int FIRST_INDEX = 0;
        final int SECOND_INDEX = 1;
        nodes.get(FIRST_INDEX).select();
        MyStreamMethods
            .drop(nodes, SECOND_INDEX)
            .ifPresent(dropList -> {
                dropList.forEach( element -> {
                    actions()
                        .keyDown(Keys.CONTROL)
                        .click(element.getRootElement())
                        .keyUp(Keys.CONTROL)
                        .build()
                        .perform();
                });
            });
        moveTo(MyStreamMethods.getLast(nodes), group);
        return this;
    }

    // Conditions methods

    public NetworkTreeWidget shoudBeParent(RootOfNetworkTree group, NodeOfNetworkTree device) throws NoSuchElementException{

        if(MyConditions.waitingBy((grp, dvc) -> getParent(dvc.renew()).equals(grp.renew()),
            Tuple.of(group, device))){
            return this;
        }
        throw new NoSuchElementException(String.format("%s is not parent %s", group, device));
    }


    public NetworkTreeWidget shoudHasDeviceWithMainFieldText(String mainFieldText) throws NoSuchElementException {
        if (MyConditions.waitingBy((tree, text) ->
                tree.getAllDevicesOfTree().stream()
                    .map(DeviceOfNetworkTree::new)
                    .anyMatch( s -> s.getMainFieldText().equals(text)),
            Tuple.of(this, mainFieldText))) {
            return this;
        }
        throw new NoSuchElementException( this + " has not device with main field text " + mainFieldText);
    }

    public NetworkTreeWidget shoudHasDeviceWithAdditionalFieldText(String additionalFieldText) throws NoSuchElementException {
        if (MyConditions.waitingBy((tree, text) ->
                tree.getAllDevicesOfTree().stream()
                    .map(DeviceOfNetworkTree::new)
                    .anyMatch( s -> s.getAdditionalFieldText().equals(text)),
            Tuple.of(this, additionalFieldText))) {
            return this;
        }
        throw new NoSuchElementException( this + " has not device with additional field text " + additionalFieldText);
    }
}