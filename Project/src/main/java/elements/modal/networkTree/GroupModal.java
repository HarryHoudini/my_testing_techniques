package main.java.elements.modal.networkTree;

import com.codeborne.selenide.SelenideElement;
import main.java.elements.modal.BaseModal;
import main.java.elements.modal.ModalTextedit;
import main.java.elements.modal.ModalType;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class GroupModal extends BaseModal {

    public static GroupModal groupModal(ModalType type){
        return new GroupModal(type);
    }

    public GroupModal(ModalType type){
        super($(type.getLocator()));
        if(type != ModalType.ADD_NODE && type != ModalType.EDIT_NODE){
            throw new IllegalArgumentException(type + "is wrong for GroupModal");
        }
    }

    // O-0006
    private SelenideElement descriprionTextedit = this.rootElement
            .$x(".//*[@data-control-name='description']")
            .$(By.tagName("textarea"));

    // descriptionTexedit methods

    public GroupModal setDescription(String newDescription){
        this.descriprionTextedit.setValue(newDescription);
        return this;
    }

    public GroupModal clearDescription(){
        this.descriprionTextedit.clear();
        return this;
    }

    public String getDescription(){
        return getTextAreaValue(ModalTextedit.DESCRIPTION);
    }

    public CredentialsModal selectCredentialsModal(){
        // O-0008
        final SelenideElement credentialTab =
            this.rootElement.$(byAttribute("title", "Configure credentials"));
        credentialTab.click();
        return new CredentialsModal(getRootElement());
    }
}
