package main.java.elements.widgets.tree.nodes;

import com.codeborne.selenide.SelenideElement;
import main.java.elements.modal.BaseModal;
import main.java.elements.modal.ModalButton;
import main.java.elements.modal.ModalType;
import main.java.data.DeviceAttributes;

public class GroupOfNetworkTree extends RootOfNetworkTree {
    public GroupOfNetworkTree(SelenideElement root) {
        super(root);
    }

    // adding methods
    @Override
    public GroupOfNetworkTree addGroup(String nameFolder) {
        super.addGroup(nameFolder);
        return this;
    }

    @Override
    public GroupOfNetworkTree addNewDevice(DeviceAttributes attributes){
        super.addNewDevice(attributes);
        return this;
    }

    @Override
    public final GroupOfNetworkTree select(){
        super.select();
        return this;
    }

    public void deleteGroup(){
        callContextMenu().clickOnMenuItem(NodeContextItem.DELETE_GROUP);
        BaseModal.of(ModalType.DELETE_NODE)
            .clickCloseModalButton(ModalButton.OK);
    }

    @Override
    public final GroupOfNetworkTree renew(){
        return new GroupOfNetworkTree(super.renewRootElement());
    }

    // context menu methods
    @Override
    protected NodeContextItem getNameEditMenuItem() {
        return NodeContextItem.EDIT_GROUP;
    }
}
