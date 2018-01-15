package other_classes;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import interfaces.MyLocatorInterface;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public abstract class BasePageElement {
    protected SelenideElement rootElement;

    public BasePageElement(SelenideElement root) {
        this.rootElement = root;
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

    protected final BasePageElement setTexteditValue(final MyLocatorInterface textedit, final String newValue){
        this.rootElement.$(textedit.getLocator()).setValue(newValue);
        return this;
    }

    public final BasePageElement shouldNotBeVisible(){
        this.rootElement.shouldNotBe(Condition.visible);
        return this;
    }
}


