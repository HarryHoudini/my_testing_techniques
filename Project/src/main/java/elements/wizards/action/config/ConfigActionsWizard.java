package main.java.elements.wizards.action.config;

import main.java.data.actions.ActionData;
import main.java.elements.wizards.BaseWizard;
import main.java.elements.wizards.FooterWizardButton;
import main.java.elements.wizards.WizardType;

public abstract class ConfigActionsWizard extends BaseWizard {

    public ConfigActionsWizard(WizardType wizardType) {
        super(wizardType);
    }

    void createGeneralDefaultAction(final String actionCaption){
        setTexteditValue(ConfigActionsWizardTextedit.ACTION_CAPTION, actionCaption);
        clickCloseModalButton(FooterWizardButton.SAVE);
    }

    public void checkDataClass(final Class<? extends ActionData> className,
                               final Class<? extends ActionData> extendClass) throws IllegalArgumentException{
        if (!className.equals(extendClass)){
            String message = className.getSimpleName() + " sould be " + extendClass.getSimpleName();
            throw new IllegalArgumentException(message);
        }
    }

    // abstract methods

    public abstract void createDefaultAction(final String nameAction);

    abstract void editData(final ActionData data);

    protected abstract ActionData getData();

    public boolean hasData(ActionData data){
        return getData().equals(data);
    }

    public void enterWrongData(ActionData data){
        editData(data);
        clickButton(FooterWizardButton.SAVE);
    }

    public void enterCorrectData(ActionData data){
        editData(data);
        clickCloseModalButton(FooterWizardButton.SAVE);
    }
    // common textEdit methods

    protected final ConfigActionsWizard setNumberEditValue(final ConfigActionsWizardTextedit numberEdit,
                                                           final String newValue) {
        super.setNumberEditValue(numberEdit, newValue);
        return this;
    }

    protected final String getNumberEditString(final ConfigActionsWizardTextedit numberEdit){
        return super.getNumberEditString(numberEdit);
    }

    protected final Double getNumberEditValue(final ConfigActionsWizardTextedit numberEdit){
        return super.getNumberEditValue(numberEdit);
    }

    protected final ConfigActionsWizard setTexteditValue(final ConfigActionsWizardTextedit textedit,
                                                         final String newValue){
        super.setTexteditValue(textedit, newValue);
        return this;
    }

    protected final String getTexteditValue(final ConfigActionsWizardTextedit textedit) {
        return super.getTexteditValue(textedit);
    }

    protected final boolean isEnabledTextedit (final ConfigActionsWizardTextedit textedit){
        return super.isEnabledTextedit(textedit);
    }
}
