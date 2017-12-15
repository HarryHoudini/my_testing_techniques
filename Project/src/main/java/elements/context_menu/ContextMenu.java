package main.java.elements.context_menu;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import main.java.utility.interfaces.MyContextItemInterface;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ContextMenu {
    private SelenideElement contextMenuContainer = $(byId("ol_context_menu"));

    public static ContextMenu of(){
        return new ContextMenu().shouldBeVisible();
    }

    private ContextMenu shouldBeVisible(){
        contextMenuContainer.shouldBe(Condition.visible);
        return this;
    }

    public void clickOnMenuItem(MyContextItemInterface itemName){
        shouldBeVisible();
        this.contextMenuContainer.$(byText(itemName.getStringOfMenuItem())).click();
    }
}
