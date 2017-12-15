package main.java.elements.wizards.action.config;

import main.java.data.actions.ActionData;
import main.java.data.actions.RestartComputerData;
import main.java.data.actions.RestartComputerData.Builder;
import main.java.elements.simple_elements.Checkbox;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.action.DefaultValueActionsConstant;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.elements.wizards.action.config.ConfigActionsWizardTextedit.*;

public final class RestartComputerConfigActionsWizard extends ConfigActionsWizard {


    private final Checkbox shutdownCheckbox =
        new Checkbox(this.rootElement.$(byAttribute("data-control-name","shutdown")));

    private final Checkbox forceCheckbox =
        new Checkbox(this.rootElement.$(byAttribute("data-control-name","force")));

    public RestartComputerConfigActionsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultAction(String actionCaption){
        setTexteditValue(ACTION_CAPTION, actionCaption);
        setTexteditValue(MESSAGE_TEXT, DefaultValueActionsConstant.MESSAGE_TEXT_CONST);
        clickCloseModalButton(FooterWizardButton.SAVE);
    }

    @Override
    final void editData(ActionData actionData) {
        checkDataClass(actionData.getClass(), RestartComputerData.class);

        final RestartComputerData data = (RestartComputerData) actionData;

        data.getCaption().ifPresent( caption ->
            setTexteditValue(ACTION_CAPTION, caption)
        );
        data.getMessageText().ifPresent( text ->
            setTexteditValue(MESSAGE_TEXT, text)
        );
        data.getTimeout().ifPresent( timeout ->
            setNumberEditValue(TIMEOUT, timeout)
        );
        data.getForce().ifPresent((check) -> {
            if(check != this.forceCheckbox.isChecked()){
                this.forceCheckbox.click();
            }
        });
        data.getShutdown().ifPresent((check) -> {
            if(check != this.shutdownCheckbox.isChecked()){
                this.shutdownCheckbox.click();
            }
        });
    }

    @Override
    protected final ActionData getData() {
        final RestartComputerData resultData= new RestartComputerData(
            Builder.of()
                .setCaption      (getTexteditValue  (ACTION_CAPTION))
                .setMessageText  (getTexteditValue  (MESSAGE_TEXT))
                .setTimeoutString(getNumberEditString(TIMEOUT))
                .setForce        (this.forceCheckbox.isChecked())
                .setShutdown     (this.shutdownCheckbox.isChecked())
        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return resultData;
    }
}
