package main.java.elements.base;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import main.java.elements.simple_elements.TextEdit;
import main.java.utility.interfaces.MyLocatorInterface;

import static com.codeborne.selenide.Selenide.$;

@Getter
@EqualsAndHashCode
public abstract class BasePageElement {
    protected SelenideElement rootElement;

    public BasePageElement(SelenideElement root) {
        this.rootElement = root;
    }

    public BasePageElement(MyLocatorInterface rootLocator) {
        this.rootElement = $(rootLocator.getLocator());
    }

    protected final boolean isEnabledButton( final MyLocatorInterface button){
        return !this.rootElement.$(button.getLocator()).is(Condition.attribute("disabled"));
    }

    public final BasePageElement clickCloseModalButton(final MyLocatorInterface button) throws AssertionError {
        if (!isEnabledButton(button)){
            throw new AssertionError(String.format("Button %s is Disabled", button.getCaption()));
        }
        this.rootElement.$(button.getLocator()).click();
        shouldNotBeVisible();
        return this;
    }

    public final BasePageElement clickButton(final MyLocatorInterface button) throws AssertionError {
        if (!isEnabledButton(button)){
            throw new AssertionError(String.format("Button %s is Disabled", button.getCaption()));
        }
        this.rootElement.$(button.getLocator()).click();
        return this;
    }

    protected final BasePageElement setTexteditValue(final MyLocatorInterface textedit, final String newValue){
        TextEdit.of(this.rootElement.$(textedit.getLocator()))
            .setTextEditLine(newValue);
        return this;
    }

    protected final BasePageElement clearTextedit(final MyLocatorInterface textedit){
        TextEdit.of(this.rootElement.$(textedit.getLocator())).clearEditLine();
        return this;
    }

    protected final String getTexteditValue(final MyLocatorInterface textedit){
        return TextEdit.of(this.rootElement.$(textedit.getLocator()))
            .getTextEditLine();
    }

    protected final String getValidMessageValue(final MyLocatorInterface textedit){
        return TextEdit.of(this.rootElement.$(textedit.getLocator()))
            .getErrorValidMessage();
    }

    protected final boolean isEnabledTextedit(final MyLocatorInterface textedit){
        return TextEdit.of(this.rootElement.$(textedit.getLocator()))
            .isEnabled();
    }

    protected final BasePageElement setNumberEditValue(final MyLocatorInterface numberEdit, final String newValue){
        this.rootElement.$(numberEdit.getLocator()).setValue(newValue);
        return this;
    }

    protected final String getNumberEditString(final MyLocatorInterface numberEdit){
        return this.rootElement.$(numberEdit.getLocator()).getValue();
    }

    protected final Double getNumberEditValue(final MyLocatorInterface numberEdit){
        return Double.parseDouble(getNumberEditString(numberEdit));
    }

    protected final boolean isEnabledNumberEdit(final MyLocatorInterface numberEdit){
        return this.rootElement.$(numberEdit.getLocator()).isEnabled();
    }

    protected final BasePageElement setTextAreaValue(final MyLocatorInterface textArea, final String newValue){
        SelenideElement area = this.rootElement.$(textArea.getLocator());
        area.setValue(newValue);
        return this;
    }

    protected final String getTextAreaValue(final MyLocatorInterface textArea){
        SelenideElement area = this.rootElement.$(textArea.getLocator());
        return area.getValue();
    }

    protected final boolean isEnabledTextArea(final MyLocatorInterface textArea){
        return this.rootElement.$(textArea.getLocator()).isEnabled();
    }

    public final BasePageElement shouldNotBeVisible(){
        this.rootElement.shouldNotBe(Condition.visible);
        return this;
    }

    public final BasePageElement shouldBeVisible(){
        this.rootElement.shouldBe(Condition.visible);
        return this;
    }
}


