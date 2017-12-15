package main.java.elements.wizards.action;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.action.config.*;
import main.java.utility.interfaces.MyLocatorInterface;
import org.openqa.selenium.By;

import java.util.function.Function;

import static com.codeborne.selenide.Selectors.byXpath;

@Getter
@AllArgsConstructor
public enum ActionType implements MyLocatorInterface {
    CANCEL_RESTART   ("system.cancelrestartcomputer", CancelRestartConfigActionsWizard  ::new),
    DELAY            ("system.delay",                 DelayConfigActionsWizard          ::new),
    RESTART_COMPUTER ("system.restartcomputer",       RestartComputerConfigActionsWizard::new),
    RUN_APP          ("system.runapplication",        RunAppConfigActionsWizard         ::new),
    SERVICE_CONTROL  ("system.servicecontrol",        ServiceControlConfigActionsWizard ::new),
    EXECUTE_SCRIPT   ("system.executescript",         ExecuteScriptConfigActionsWizard  ::new);


    final String caption;
    final Function<WizardType, ? extends ConfigActionsWizard> configWizardConstructor;

    // O-0018
    public By getLocator(){
        final String xpath = String.format(".//*[@data-ol-accord-item = '%s']", this.caption);
        return byXpath(xpath);
    }

    public String getCaption(){
        return this.name().toLowerCase();
    }
}
