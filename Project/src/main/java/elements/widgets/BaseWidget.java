package main.java.elements.widgets;

import com.codeborne.selenide.SelenideElement;
import main.java.elements.base.BasePageElement;

public abstract class BaseWidget extends BasePageElement {

    protected WidgetHeader widgetHeader;

    public BaseWidget(SelenideElement root) {
        super(root);
    }
}
