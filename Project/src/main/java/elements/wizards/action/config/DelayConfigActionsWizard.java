package main.java.elements.wizards.action.config;

import main.java.data.actions.ActionData;
import main.java.data.actions.DelayData;
import main.java.data.actions.DelayData.Builder;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;

import static main.java.elements.wizards.action.config.ConfigActionsWizardTextedit.ACTION_CAPTION;
import static main.java.elements.wizards.action.config.ConfigActionsWizardTextedit.DELAY_TIME;

public final class DelayConfigActionsWizard extends ConfigActionsWizard {
    public DelayConfigActionsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultAction(String nameAction) {
        super.createGeneralDefaultAction(nameAction);
    }

    @Override
    final void editData(ActionData actionData) {
        checkDataClass(actionData.getClass(), DelayData.class);

        final DelayData data = (DelayData) actionData;

        data.getCaption().ifPresent( caption ->
            setTexteditValue(ACTION_CAPTION, caption)
        );
        data.getTime().ifPresent( time ->
            setNumberEditValue(DELAY_TIME, time)
        );
    }

    @Override
    protected final ActionData getData() {
        final DelayData resultData = new DelayData(
            Builder.of()
                .setCaption   (getTexteditValue   (ACTION_CAPTION))
                .setTimeString(getNumberEditString(DELAY_TIME))
        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return resultData;
    }
}
