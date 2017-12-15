package main.java.elements.simple_elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byName;

/**
 * Created by Saveg on 6/21/2017.
 */
public class TextEdit {
    private SelenideElement root;
    private SelenideElement editLine;
    private SelenideElement clearButton;
    private SelenideElement errorValidLabel;

    public TextEdit(SelenideElement root) {
        this.root            = root;
        this.editLine        = root.$(By.tagName  ("input"));
        this.clearButton     = root.$(byClassName ("icon-ol-close"));
        this.errorValidLabel = root.$(byName      ("property_item_error"));
    }

    public static TextEdit of(SelenideElement root){
        return new TextEdit(root);
    }


    public SelenideElement getClearButton() {
        return clearButton;
    }

    public TextEdit setTextEditLine (String newText){
        editLine.setValue(newText);
        return this;
    }

    public String getTextEditLine(){
        return editLine.getValue();
    }

    public TextEdit clearEditLine(){
        clearButton.click();
        return this;
    }
    public boolean isEnabled(){
        return !editLine.has(Condition.attribute("disabled"));
    }

    public String getErrorValidMessage(){
        return errorValidLabel.getText();
    }
}
