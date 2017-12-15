package main.java.elements.simple_elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class Checkbox {
    private SelenideElement input;
    private SelenideElement root;


    public Checkbox(SelenideElement rootElement) {
        this.input = rootElement.$(By.tagName("input"));
        this.root = rootElement;
    }

    public Checkbox click(){
//        this.input.click();
        root.click();
        return this;
    }

    public boolean isChecked(){
        return this.input.is(Condition.checked);
    }
}
