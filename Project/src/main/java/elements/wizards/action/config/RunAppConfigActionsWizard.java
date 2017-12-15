package main.java.elements.wizards.action.config;

import main.java.data.actions.ActionData;
import main.java.data.actions.RunApplicationData;
import main.java.data.actions.RunApplicationData.Builder;
import main.java.elements.simple_elements.Checkbox;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.action.DefaultValueActionsConstant;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.elements.wizards.action.config.ConfigActionsWizardTextedit.*;

public final class RunAppConfigActionsWizard extends ConfigActionsWizard {

    private final Checkbox completionCheckbox =
        new Checkbox(this.rootElement.$(byAttribute("data-control-name","waitforcompletion")));

    public RunAppConfigActionsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultAction(String actionCaption){
        setTexteditValue(ACTION_CAPTION, actionCaption);
        setTexteditValue(APP_PATH,       DefaultValueActionsConstant.APP_PATH_CONST);
        setTexteditValue(APP_PARAMS,     DefaultValueActionsConstant.APP_PARAMS_CONST);
        clickCloseModalButton(FooterWizardButton.SAVE);
    }

    @Override
    final void editData(ActionData actionData) {
        checkDataClass(actionData.getClass(), RunApplicationData.class);

        final RunApplicationData data = (RunApplicationData) actionData;
        data.getCaption().ifPresent( caption ->
            setTexteditValue(ACTION_CAPTION, caption)
        );
        data.getApplicationName().ifPresent( name ->
            setTexteditValue(APP_PATH, name)
        );
        data.getApplicationParams().ifPresent( params ->
            setTexteditValue(APP_PARAMS, params)
        );
        data.getApplicationDir().ifPresent( dir ->
            setTexteditValue(APP_DIR, dir)
        );
        data.getWaitForCompletion().ifPresent((check) -> {
            if(check != this.completionCheckbox.isChecked()){
                this.completionCheckbox.click();
            }
        });
    }

    @Override
    protected final ActionData getData() {
        final RunApplicationData resultData;
        resultData = new RunApplicationData(
            Builder.of()
                .setCaption          (getTexteditValue(ACTION_CAPTION))
                .setApplicationName  (getTexteditValue(APP_PATH))
                .setApplicationParams(getTexteditValue(APP_PARAMS))
                .setApplicationDir   (getTexteditValue(APP_DIR))
                .setWaitForCompletion(this.completionCheckbox.isChecked())
        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return resultData;
    }
}
