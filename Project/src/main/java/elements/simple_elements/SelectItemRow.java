package main.java.elements.simple_elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byClassName;

/**
 * Created by Saveg on 9/5/2017.
 */
public class SelectItemRow {
    private SelenideElement root;

    private SelenideElement selectButton;

    public SelectItemRow(SelenideElement root) {
        this.root = root;
        this.selectButton = root.$(byClassName("ol_btn_select"));
    }

    public static SelectItemRow selectItemRow(SelenideElement root){
        return new SelectItemRow(root);
    }

    public SelectItemRow select(){
        selectButton.click();
        return  this;
    }
}
