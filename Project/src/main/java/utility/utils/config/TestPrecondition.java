package main.java.utility.utils.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import main.java.constants.DependingConstants;
import main.java.elements.modal.LoginPage;
import main.java.elements.simple_elements.DashBoard;
import main.java.elements.simple_elements.DashBoard.DashBoardPanel;
import main.java.xml.OControlRunner;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.open;
import static main.java.constants.DependingConstants.*;

public class TestPrecondition {
    private static void configBrouser(){
        Configuration.fastSetValue = true;
        switch (BROWSER_TYPE){
            case CHROME:
                Configuration.browser = "chrome";
                break;
            case FIREFOX:
                Configuration.browser = "gecko";
                break;
        }
    }

    public static void startOnNetworkTreePanel(){
        TestPrecondition.configBrouser();
        open(WEBSERVER_URL);
        OControlRunner.of().resetData();
        LoginPage.enter("admin", "admin");
        DashBoard.of().getDefaultPanel(DashBoardPanel.NETWORK_TREE);
    }

    public static void setContext(ITestContext context){
        context.setAttribute(DependingConstants.NAME_ATTR_URL, WebDriverRunner.url());
    }
}
