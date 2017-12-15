package main.java.elements.wizards.action.config;

import main.java.data.actions.ActionData;
import main.java.data.actions.ExecuteScriptData;
import main.java.data.actions.ExecuteScriptData.Builder;
import main.java.data.actions.ExecuteScriptData.ScriptType;
import main.java.elements.simple_elements.Select;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.action.DefaultValueActionsConstant;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.elements.wizards.action.config.ConfigActionsWizardTextedit.ACTION_CAPTION;
import static main.java.elements.wizards.action.config.ConfigActionsWizardTextedit.SCRIPT;

public final class ExecuteScriptConfigActionsWizard extends ConfigActionsWizard {

    private final Select<ScriptType> scriptTypeSelect = new Select<>(
        this.rootElement.$(byAttribute("data-control-name","scripttype")),
        ScriptType.class);

    public ExecuteScriptConfigActionsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultAction(String actionCaption){
        setTexteditValue(ACTION_CAPTION, actionCaption);
        setTextAreaValue(SCRIPT, DefaultValueActionsConstant.SCRIPT_CONST);
        clickCloseModalButton(FooterWizardButton.SAVE);
    }

    @Override
    final void editData(ActionData actionData) {
        checkDataClass(actionData.getClass(), ExecuteScriptData.class);

        final ExecuteScriptData data = (ExecuteScriptData) actionData;
        data.getCaption().ifPresent( caption ->
            setTexteditValue(ACTION_CAPTION, caption)
        );
        data.getScript().ifPresent( script ->
            setTextAreaValue(SCRIPT, script)
        );
        data.getScriptType().ifPresent(this.scriptTypeSelect::selectItem);
    }

    @Override
    protected final ActionData getData() {

        final ExecuteScriptData resultData = new ExecuteScriptData(
            Builder.of()
                .setCaption   (getTexteditValue(ACTION_CAPTION))
                .setScript    (getTextAreaValue(SCRIPT))
                .setScriptType(this.scriptTypeSelect.getSelectedOption())
        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return resultData;
    }
}
