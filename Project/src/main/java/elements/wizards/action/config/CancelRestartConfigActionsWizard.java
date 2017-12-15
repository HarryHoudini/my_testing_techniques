package main.java.elements.wizards.action.config;

import main.java.data.actions.ActionData;
import main.java.data.actions.CancelRestartData;
import main.java.data.actions.CancelRestartData.Builder;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;

import static main.java.elements.wizards.action.config.ConfigActionsWizardTextedit.ACTION_CAPTION;

public final class CancelRestartConfigActionsWizard extends ConfigActionsWizard {

    public CancelRestartConfigActionsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultAction(String nameAction) {
        super.createGeneralDefaultAction(nameAction);
    }

    @Override
    final void editData(ActionData actionData) {
        checkDataClass(actionData.getClass(), CancelRestartData.class);

        final CancelRestartData data = (CancelRestartData) actionData;
        data.getCaption().ifPresent( caption ->
            setTexteditValue(ACTION_CAPTION, caption)
        );
    }

    @Override
    protected final ActionData getData() {
        final CancelRestartData resultData= new CancelRestartData(
            Builder.of()
                .setCaption(getTexteditValue  (ACTION_CAPTION))
        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return resultData;
    }
}
