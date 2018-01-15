import com.codeborne.selenide.SelenideElement;
import other_classes.BasePageElement;
import other_classes.ContextMenu;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;


public class NodeOfNetworkTree extends BasePageElement {

    public NodeOfNetworkTree() {
        super($(byName("node")));
    }

    public NodeOfNetworkTree addDefaultSensor(SelectWizardSensor sensor, String nameSensor) {
        rootElement.contextClick();
        ContextMenu.of().clickOnMenuItem("add sensor");
        sensor
            .getConfigWizardConstructor()
            .get()
            .createDefaultSensor(nameSensor);
        return this;
    }
}