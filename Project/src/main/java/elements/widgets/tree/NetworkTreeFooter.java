package main.java.elements.widgets.tree;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import main.java.elements.base.BasePageElement;
import main.java.elements.simple_elements.Checkbox;
import main.java.utility.interfaces.MyLocatorInterface;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byAttribute;

public class NetworkTreeFooter extends BasePageElement {

    private final Checkbox undefinedFilterCheckbox =
        new Checkbox(rootElement.$(byAttribute("data-qa-id", "undefined")));

    @AllArgsConstructor
    public enum NetworkTreeFooterCheckbox implements MyLocatorInterface{
        SUCCESS          ("success"),
        INFORMATION      ("information"),
        WARNING          ("warning"),
        CRITICAL         ("critical"),
        UNDEFINED        ("undefined"),
        FILTERING_DEVICES("filteringDevices");

        private final String qaId;
        public By getLocator(){
            return byAttribute("data-qa-id", this.qaId);
        }

        public final String getCaption(){
            return this.qaId;
        }
    }

    public NetworkTreeFooter(SelenideElement widgetRoot) {
        super(widgetRoot.$(By.tagName("ol-nodes-tree-bottom-ctrls-bar")));
    }

    public NetworkTreeFooter setCheckbox(NetworkTreeFooterCheckbox typeCheckbox, boolean value){
        Checkbox checkbox = new Checkbox(this.rootElement.$(typeCheckbox.getLocator()));
        if(value != checkbox.isChecked()){
            checkbox.click();
        }
        return this;
    }



}
