package main.java.utility.utils;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

public final class RenewSelenideElement {
    private RenewSelenideElement(){
        throw new UnsupportedOperationException("Illegal access to private constructor!");
    }

    public static SelenideElement renewById(SelenideElement element){
        return $(byId(element.getAttribute("id")));
    }
}
