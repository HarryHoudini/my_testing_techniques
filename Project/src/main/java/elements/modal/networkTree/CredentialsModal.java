package main.java.elements.modal.networkTree;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import main.java.elements.modal.BaseModal;
import main.java.elements.modal.ModalButton;
import main.java.elements.modal.ModalTextedit;
import main.java.elements.modal.ModalType;
import main.java.data.Credential;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byValue;

public class CredentialsModal extends BaseModal {
    public CredentialsModal(SelenideElement root){
        super(root);
    }

    private final SelenideElement credentialSelect = this.rootElement.$(byName("windows"));
    private final SelenideElement defaultCredentialLine = this.credentialSelect.$(byValue("string:credentialrecord.1001"));
    private final SelenideElement inheritCredentialLine = this.credentialSelect.$(byValue("string:inherit"));

    public CredentialsModal setDefaultCredential(){
        this.credentialSelect.click();
        this.defaultCredentialLine.click();
        return this;
    }

    public CredentialsModal setInheritCredential(){
        this.credentialSelect.click();
        this.inheritCredentialLine.click();
        return this;
    }

    public CredentialsModal createNewCredential(Credential credential){
        clickButton(ModalButton.NEW_CREDENTIAL);
        BaseModal createCredentialModal = new BaseModal(ModalType.CREATE_NEW_CREDENTIAL);
        createCredentialModal
            .setTexteditValue(ModalTextedit.CAPTION, credential.getCaption())
            .setTexteditValue(ModalTextedit.USERNAME, credential.getUsername())
            .setTexteditValue(ModalTextedit.PASSWORD, credential.getPassword())
            .clickCloseModalButton(ModalButton.SAVE);
        return this;
    }

    public CredentialsModal selectCredential(Credential credential){
        String label = String.format("%s (%s)", credential.getCaption(), credential.getUsername());
        this.credentialSelect.click();
        this.credentialSelect.$(byAttribute("label", label)).click();
        return this;
    }

    public boolean isDefaultSelected(){
        return this.defaultCredentialLine.has(Condition.attribute("selected"));
    }
}
