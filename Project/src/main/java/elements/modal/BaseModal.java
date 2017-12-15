package main.java.elements.modal;

import com.codeborne.selenide.SelenideElement;
import main.java.elements.base.BasePageElement;
import main.java.utility.interfaces.MyLocatorInterface;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BaseModal extends BasePageElement {

    public BaseModal(SelenideElement root) {
        super(root);
    }

    public BaseModal(ModalType modalType){
        super(modalType);
    }

    public BaseModal(MyLocatorInterface modalType){
        super(modalType);
    }

    public static BaseModal of(ModalType modalType){
        return new BaseModal(modalType);
    }

    public final boolean isEnabledButton(final ModalButton button){
        return super.isEnabledButton(button);
    }

    public final BaseModal clickButton(final ModalButton button) throws AssertionError {
        super.clickButton(button);
        return this;
    }

    public final BaseModal setTexteditValue(final ModalTextedit textedit, final String newValue){
        super.setTexteditValue(textedit, newValue);
        return this;
    }

    public final BaseModal clearTextedit(final ModalTextedit textedit){
        super.clearTextedit(textedit);
        return this;
    }

    public final String getTexteditValue(final ModalTextedit textedit){
        return super.getTexteditValue(textedit);
    }

    public final String getValidMessageValue(final ModalTextedit textedit){
        return super.getValidMessageValue(textedit);
    }

    protected final boolean isEnabledTextedit(final ModalTextedit textedit){
        return super.isEnabledTextedit(textedit);
    }

    public final BaseModal close(){
        this.rootElement.$(By.tagName("ol-modal-close")).click();
        this.shouldNotBeVisible();
        return this;
    }
}
