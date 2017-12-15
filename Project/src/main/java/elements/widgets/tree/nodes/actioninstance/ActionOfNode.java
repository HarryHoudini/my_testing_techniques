package main.java.elements.widgets.tree.nodes.actioninstance;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.vavr.Tuple;
import lombok.Getter;
import lombok.Setter;
import main.java.data.actions.ActionData;
import main.java.elements.base.ContextPageElement;
import main.java.elements.context_menu.ContextMenu;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.action.ActionType;
import main.java.elements.wizards.action.config.ConfigActionsWizard;
import main.java.utility.utils.MyConditions;

import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.Thread.*;

@Setter
@Getter
public final class ActionOfNode extends ContextPageElement {

    private final String id;
    private final ActionType actionType;

    public ActionOfNode(SelenideElement root, ActionType type) {
        super(root);
        this.id = root.getAttribute("id");
        this.actionType = type;
    }

    public ActionOfNode renew(){
        return new ActionOfNode($(byId(this.id)), this.actionType);
    }

    @Override
    public ContextMenu callContextMenu() {
        try {
            sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        renew()
            .getRootElement()
            .shouldBe(Condition.visible)
            .contextClick();
        return ContextMenu.of();
    }

    public ActionOfNode editCorrectData(ActionData data){
        callContextMenu().clickOnMenuItem(ActionContextItem.EDIT_ACTION);
        ConfigActionsWizard configWizard = this.actionType.getConfigWizardConstructor().apply(WizardType.EDIT_ACTION);
        configWizard.enterCorrectData(data);
        return this;
    }

    public ActionOfNode editWrongData(ActionData data){
        callContextMenu().clickOnMenuItem(ActionContextItem.EDIT_ACTION);
        ConfigActionsWizard configWizard = this.actionType.getConfigWizardConstructor().apply(WizardType.EDIT_ACTION);
        configWizard.enterWrongData(data);
        return this;
    }

    public ConfigActionsWizard getConfigWizards(){
        return this.actionType
            .getConfigWizardConstructor()
            .apply(WizardType.EDIT_ACTION);
    }

    private boolean hasData(ActionData expectedData){
        callContextMenu().clickOnMenuItem(ActionContextItem.EDIT_ACTION);
        ConfigActionsWizard configWizard = this.actionType.getConfigWizardConstructor().apply(WizardType.EDIT_ACTION);
        return configWizard.hasData(expectedData);
    }

    public ActionOfNode shouldHasData(ActionData expectedData) {
        if (MyConditions.waitingBy( (action, data) -> action.renew().hasData(data),
            Tuple.of(this, expectedData))
            ) {
            return this;
        }
        throw new NoSuchElementException( this + " has not data: " + expectedData);
    }

    @Override
    public String toString() {
        return "ActionOfNode{" +
            "actionType=" + actionType + "; id=" + this.rootElement.getAttribute("id") +
            '}';
    }
}
