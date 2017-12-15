package main.java.elements.widgets.tree.nodes;

import com.codeborne.selenide.SelenideElement;
import io.vavr.Tuple;
import main.java.elements.context_menu.ContextMenu;
import main.java.elements.modal.BaseModal;
import main.java.elements.modal.ModalButton;
import main.java.elements.modal.ModalTextedit;
import main.java.elements.modal.ModalType;
import main.java.elements.modal.networkTree.DeviceModal;
import main.java.elements.modal.networkTree.DeviceType;
import main.java.data.DeviceAttributes;
import main.java.utility.utils.MyConditions;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

public class DeviceOfNetworkTree extends NodeOfNetworkTree {
    public DeviceOfNetworkTree(SelenideElement root) {
        super(root);
    }

    // methods of tree

    public static String getMainFieldText(WebElement node){
        SelenideElement selenideElement = $(byId(node.getAttribute("id")));
        return selenideElement.$$(byClassName("_ol_caption"))
            .filter(not(cssClass("_ol_hide_info")))
            .stream()
            .findFirst()
            .orElseThrow(() -> new NotFoundException("Can not find main field for node" + node))
            .getText()
            .trim();
    }


    public String getMainFieldText(){
        return DeviceOfNetworkTree.getMainFieldText(rootElement);
    }

    public static String getAdditionalFieldText(WebElement node){
        SelenideElement selenideElement = $(byId(node.getAttribute("id")));
        return selenideElement.$(byClassName("_ol_hide_info")).getText().trim();
    }

    public final String getAdditionalFieldText(){
        return DeviceOfNetworkTree.getAdditionalFieldText(rootElement);
    }

    public void deleteDevice() {
        callContextMenu().clickOnMenuItem(NodeContextItem.DELETE_DEVICE);
        BaseModal.of(ModalType.DELETE_NODE)
            .shouldBeVisible()
            .clickCloseModalButton(ModalButton.OK);
    }

    // attributes methods

    @Override
    protected DeviceModal callEditModal(){
        super.callEditModal();
        return DeviceModal.deviceModal(ModalType.EDIT_NODE);
    }

    @Override
    protected NodeContextItem getNameEditMenuItem() {
        return NodeContextItem.EDIT_DEVICE;
    }

    private DeviceAttributes getAttributes() {
        DeviceModal modal = callEditModal();
        DeviceAttributes attributes;
        final String caption            = modal.getTexteditValue(ModalTextedit.CAPTION);
        final Optional<DeviceType> type = modal.getDeviceType ();
        final String hostname           = modal.getTexteditValue(ModalTextedit.HOSTNAME);
        final Boolean isStaticIP        = modal.isStaticIP ();
        final String ip                 = modal.getTexteditValue(ModalTextedit.IP_ADRESS);
        final String mac                = modal.getTexteditValue(ModalTextedit.MAC_ADRESS);
        final String description        = modal.getDescription ();
        attributes = new DeviceAttributes(caption, type, hostname, isStaticIP, ip, mac, description);
        modal.clickCloseModalButton(ModalButton.SAVE);
        return attributes;
    }

    private String getTextEditAttr(ModalTextedit textedit){
        DeviceModal modal = callEditModal();
        String result = modal.getTexteditValue(textedit);
        modal.clickCloseModalButton(ModalButton.SAVE);
        return result;
    }

    @Override
    public String getCaption(){
        return getTextEditAttr(ModalTextedit.CAPTION);
    }

    /**
     * return null, if if nothing is selected in the Select "Device type"
     * throw exeption IllegalArgumentException, if the expectedCaption is not in the list
     */
    private Optional<DeviceType> getDeviceType(){
        final DeviceModal modal = callEditModal();
        final Optional<DeviceType> result = modal.getDeviceType();
        modal
            .clickCloseModalButton(ModalButton.SAVE);
        return result;
    }

    public DeviceOfNetworkTree editAttributes(DeviceAttributes newAttributes){
        final ContextMenu contextMenu = callContextMenu();
        contextMenu
            .clickOnMenuItem(getNameEditMenuItem());
        DeviceModal.deviceModal(ModalType.EDIT_NODE).setAll(newAttributes);
        return this;
    }

    // other methods

    @Override
    public final DeviceOfNetworkTree select(){
        super.select();
        return this;
    }

    @Override
    public final DeviceOfNetworkTree renew(){
        return new DeviceOfNetworkTree(super.renewRootElement());
    }

    // conditions methods

    public DeviceOfNetworkTree shoudHasTextEditAttr(ModalTextedit textedit, String valueAttribute) throws NoSuchElementException {
        if(MyConditions.waitingBy((device, value) -> device.renew().getTextEditAttr(textedit).equals(value),
                                  Tuple.of(this, valueAttribute))) {
            return this;
        }
        throw new NoSuchElementException(String.format("%s of %s has not %s", textedit.getCaption(), this, valueAttribute));
    }

    public DeviceOfNetworkTree shoudHasType( DeviceType expectedType) throws NoSuchElementException {
        if(MyConditions.waitingBy(
            (device, type) -> {
                return device
                    .renew()
                    .getDeviceType()
                    .orElseThrow(() -> new NotFoundException("In Device type string is not value"))
                    .equals(type);
            },
            Tuple.of(this, expectedType)
        )) {
            return this;
        }
        throw new NoSuchElementException(String.format("Device type of %s has not '%s'", this, expectedType.getCaption()));
    }

    public DeviceOfNetworkTree shoudHasAttributes(DeviceAttributes expectedAttrs) {
        if(MyConditions.waitingBy((device, attributes) -> device.renew().getAttributes().isEquals(attributes),
            Tuple.of(this, expectedAttrs))) {
            return this;
        }
        throw new IllegalArgumentException("The device " + this + " with expected attributes");
    }
}
