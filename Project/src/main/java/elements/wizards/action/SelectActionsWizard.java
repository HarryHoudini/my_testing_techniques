package main.java.elements.wizards.action;

import main.java.elements.simple_elements.SelectItemRow;
import main.java.elements.wizards.BaseWizard;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;

public class SelectActionsWizard extends BaseWizard {
    private SelectActionsWizard() {
        super(WizardType.CREATE_ACTION);
    }
    // select button *[data-ol-accord-item = "netbase.ping"] *.ol_btn_select

    public static SelectActionsWizard of(){
        return new SelectActionsWizard();
    }

    public void selectAction(ActionType action){
        SelectItemRow row = new SelectItemRow(
            this.rootElement.$(action.getLocator())
        );
        row.select();
        clickButton(FooterWizardButton.NEXT);
    }
}
