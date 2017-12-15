package main.java.elements.wizards.action.config;

import main.java.data.actions.ActionData;
import main.java.data.actions.ServiceControlData;
import main.java.data.actions.ServiceControlData.Builder;
import main.java.data.actions.ServiceControlData.ServiceAction;
import main.java.elements.simple_elements.Checkbox;
import main.java.elements.simple_elements.Select;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.action.DefaultValueActionsConstant;

import static com.codeborne.selenide.Selectors.byAttribute;
import static main.java.elements.wizards.action.config.ConfigActionsWizardTextedit.ACTION_CAPTION;
import static main.java.elements.wizards.action.config.ConfigActionsWizardTextedit.SERVICE_NAME;

public final class ServiceControlConfigActionsWizard extends ConfigActionsWizard {

    private final Select<ServiceAction> serviceActionSelect = new Select<>(
        this.rootElement.$(byAttribute("data-control-name","serviceaction")),
            ServiceAction.class);

    private final Checkbox localActionCheckbox =
        new Checkbox(this.rootElement.$(byAttribute("data-control-name","localaction")));

    public ServiceControlConfigActionsWizard(WizardType wizardType) {
        super(wizardType);
    }

    @Override
    public final void createDefaultAction(String actionCaption){
        setTexteditValue(ACTION_CAPTION, actionCaption);
        setTexteditValue(SERVICE_NAME, DefaultValueActionsConstant.SERVICE_NAME_CONST);
        clickCloseModalButton(FooterWizardButton.SAVE);
    }

    @Override
    final void editData(final ActionData actionData) {
        checkDataClass(actionData.getClass(), ServiceControlData.class);

        final ServiceControlData data = (ServiceControlData) actionData;
        data.getCaption().ifPresent( caption ->
            setTexteditValue(ACTION_CAPTION, caption)
        );
        data.getServiceName().ifPresent( path ->
            setTexteditValue(SERVICE_NAME, path)
        );
        data.getServiceAction().ifPresent(this.serviceActionSelect::selectItem);
        data.getLocalAction().ifPresent((check) -> {
            if(check != this.localActionCheckbox.isChecked()){
                this.localActionCheckbox.click();
            }
        });
    }

    @Override
    protected final ActionData getData() {
        final ServiceControlData resultData;
        resultData = new ServiceControlData(
            Builder.of()
                .setCaption    (getTexteditValue(ACTION_CAPTION))
                .setServiceName(getTexteditValue(SERVICE_NAME))
                .setServiceAction(this.serviceActionSelect.getSelectedOption())
                .setLocalAction  (this.localActionCheckbox.isChecked())
        );
        clickCloseModalButton(FooterWizardButton.SAVE);
        return resultData;
    }
}
