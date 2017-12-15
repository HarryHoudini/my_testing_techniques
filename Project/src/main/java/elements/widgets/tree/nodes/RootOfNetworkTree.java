package main.java.elements.widgets.tree.nodes;

import com.codeborne.selenide.SelenideElement;
import main.java.elements.context_menu.ContextMenu;
import main.java.elements.modal.ModalType;
import main.java.elements.modal.networkTree.DeviceModal;
import main.java.elements.modal.networkTree.GroupModal;
import main.java.data.DeviceAttributes;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static main.java.elements.modal.ModalButton.SAVE;
import static main.java.elements.modal.ModalTextedit.CAPTION;

public class RootOfNetworkTree extends NodeOfNetworkTree {

    public RootOfNetworkTree(SelenideElement root) {
        super(root);
    }

    public static String getCaption(WebElement element){
        SelenideElement selenideElement = $(byId(element.getAttribute("id")));
        return selenideElement.$(byClassName("_ol_caption")).getText();
    }

    public String getCaption(){
        return RootOfNetworkTree.getCaption(rootElement);
    }

    // adding methods
    public RootOfNetworkTree addGroup(String nameFolder) {
        ContextMenu contextMenu = select().callContextMenu();
        contextMenu.clickOnMenuItem(NodeContextItem.ADD_GROUP);
        GroupModal addGroupModal = new GroupModal(ModalType.ADD_NODE);
        addGroupModal
                .setTexteditValue(CAPTION, nameFolder)
                .clickCloseModalButton(SAVE);
        return this;
    }

    public RootOfNetworkTree addNewDevice (DeviceAttributes attributes){
        ContextMenu contextMenu = select().callContextMenu();
        contextMenu.clickOnMenuItem(NodeContextItem.ADD_DEVICE);
        DeviceModal addDeviceModal = new DeviceModal(ModalType.ADD_NODE);
        addDeviceModal.setAll(attributes);
        return this;
    }

    @Override
    public RootOfNetworkTree select(){
        super.select();
        return this;
    }

    @Override
    public RootOfNetworkTree renew(){
        return new RootOfNetworkTree(super.renewRootElement());
    }

    @Override
    public RootOfNetworkTree setDefaultCredential(){
        super.setDefaultCredential();
        return this;
    }

    @Override
    public RootOfNetworkTree setInheritCredential(){
        super.setInheritCredential();
        return this;
    }

    @Override
    protected NodeContextItem getNameEditMenuItem() {
        return NodeContextItem.EDIT_ROOT;
    }
}
