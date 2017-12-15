package main.java.elements.simple_elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import main.java.utility.interfaces.MyLocatorInterface;
import main.java.utility.interfaces.MySelectOptionsInterface;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.Optional;

import static com.codeborne.selenide.Selectors.byAttribute;

public final class Select <T extends Enum & MySelectOptionsInterface> {
    private Class<T> enumType;
    private SelenideElement select;

    @SuppressWarnings("unchecked")
    public Select(SelenideElement rootElement, Class<T> className) {
        this.select = rootElement.$(By.tagName("select"));
        this.enumType = className;
    }

    public Select<T> selectItem(MyLocatorInterface item){
        this.select.click();
        this.select.$(item.getLocator()).click();
        return this;
    }

    public boolean isSelected(MyLocatorInterface item){
        return this.select.$(item.getLocator()).has(Condition.attribute("selected"));
    }

    public final boolean isEnabled(){
        return this.select.isEnabled();
    }

    private SelenideElement getSelectedElement(){
        return this.select.$(byAttribute("selected", "selected"));
    }

    public  T getSelectedOption(){
        final Optional<T> selectedItem = Arrays.stream(this.enumType.getEnumConstants())
            .filter(enumElement -> {
                final String value = getSelectedElement().getValue();
                return enumElement.getValue().equals(value);
            })
            .findFirst();
        return selectedItem.orElseThrow(() -> new IllegalArgumentException(this + "hasn't selected Item"));
    }
}
