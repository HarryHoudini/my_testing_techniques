package main.java.elements.wizards;

import com.codeborne.selenide.Condition;
import main.java.elements.modal.BaseModal;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;

public class BaseWizard extends BaseModal {
//    public BaseWizard() {
//        super($(byId("wizard_actinst_addupd")));
//    }
//
//    public BaseWizard(SelenideElement root) {
//        super(root);
//    }

    public BaseWizard(WizardType wizardType){
        super(wizardType);
    }

    public static BaseWizard of(WizardType wizardType){
        return new BaseWizard(wizardType);
    }

    public final boolean isEnabledButton(final FooterWizardButton button){
        return super.isEnabledButton(button);
    }

    public final BaseWizard clickButton(final FooterWizardButton button) throws AssertionError {
        super.clickButton(button);
        return this;
    }

    public final BaseWizard shouldHasError(){
        rootElement
            .$(byClassName("ol_step_current"))
            .shouldHave(Condition.cssClass("ol_step_error"));
        return this;
    }
}
