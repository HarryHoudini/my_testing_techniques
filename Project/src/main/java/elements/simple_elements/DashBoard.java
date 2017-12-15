package main.java.elements.simple_elements;

import lombok.AllArgsConstructor;
import main.java.elements.base.BasePageElement;
import main.java.utility.interfaces.MyLocatorInterface;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.*;

public class DashBoard extends BasePageElement {
    private DashBoard() {
        super($(By.tagName("ol-dash-board-navigation-bar")));
    }

    public static DashBoard of(){
        return new DashBoard();
    }

    @AllArgsConstructor
    public enum DashBoardPanel implements MyLocatorInterface{
        HOME            ("Home"),
        SCANNER         ("Scanner"),
        SCENARIO_BUILDER("Scenario builder"),
        OVERVIEW        ("Overview"),
        ACTIVITY_LOG    ("Activity Log"),
        NETWORK_MAP     ("Network Map"),
        NETWORK_TREE    ("Network tree"),
        SENSORS         ("Sensors"),
        ACTIONS         ("Actions");

        private final String title;
        public By getLocator(){
            return byTitle(this.title);
        }

        public final String getCaption(){
            return title.trim();
        }
    }

    public void getDefaultPanel(DashBoardPanel panel){
        this.rootElement.$(panel.getLocator()).click();
    }

    public void getUserPanel(String title){
        this.rootElement.$(byTitle(title)).click();
    }
}
