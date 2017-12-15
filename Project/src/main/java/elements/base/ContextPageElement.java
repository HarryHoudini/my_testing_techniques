package main.java.elements.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import main.java.elements.context_menu.ContextMenu;
import org.openqa.selenium.JavascriptExecutor;

public abstract class ContextPageElement extends BasePageElement {
    public ContextPageElement(SelenideElement root) {
        super(root);
    }

    public ContextMenu callContextMenu() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (Configuration.browser.equals("gecko")) {
            this.rootElement.click();
            final JavascriptExecutor js = (JavascriptExecutor) this.rootElement.getWrappedDriver();
            /* Call the context menu via javascript for SelenideElement
             * final String script = String.format(
             *       "$($('#%s')[0]).trigger('contextmenu')", this.rootElement.getAttribute("id")
             */
            js.executeScript("trigger('contextmenu')");
        } else {
            this.rootElement.contextClick();
        }

        return ContextMenu.of();
    }
}
