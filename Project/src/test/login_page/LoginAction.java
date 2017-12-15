package test.login_page;

import com.codeborne.selenide.Configuration;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.login_page.objects.Dashboard;
import test.login_page.objects.LogonPage;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;


public class LoginAction {

    private static String browser = "CHROME";
    private LogonPage logonWindow = new LogonPage();
    private Dashboard dashboardWindow = new Dashboard();

    @BeforeClass
    public static void setUp() {
        if (browser.equals("FIREFOX")) {
            Configuration.browser = "gecko";
            System.setProperty("webdriver.gecko.driver", "C:\\webdrivers\\geckodriver-v0.18.0-win64\\geckodriver.exe");
        } else if (browser.equals("CHROME")) {
            System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver_win32\\chromedriver.exe");
            System.setProperty("selenide.browser", "chrome");
        }
        try {
            open("https://localhost:3000");
        } catch (org.openqa.selenium.WebDriverException e) {
            $(byId("advancedButton")).click();
            $(byId("exceptionDialogButton")).click();
        }
    }

    @Test
    public void VerifyEmptyLogin() {
        logonWindow.setCredentials(logonWindow.getEmptyCredential(), logonWindow.getCorrectPassword(), false);
        logonWindow.isDisabled();
    }

    @Test
    public void VerifyEmptyCredentials() {
        logonWindow.setCredentials(logonWindow.getEmptyCredential(), logonWindow.getEmptyCredential(), false);
        logonWindow.isDisabled();
    }

    @Test
    public void VerifyEmptyPassword() {
        logonWindow.setCredentials(logonWindow.getCorrectLogin(), logonWindow.getEmptyCredential(), false);
        logonWindow.isDisabled();
    }

    @Test
    public void VerifyIncorrectLogin() {
        logonWindow.setCredentials(logonWindow.getIncorrectCredential(), logonWindow.getCorrectPassword(), false);
        logonWindow.isExistWarnMessage();
    }

    @Test
    public void VerifyIncorrectPassword() {
        logonWindow.setCredentials(logonWindow.getCorrectLogin(), logonWindow.getIncorrectCredential(), false);
        logonWindow.isExistWarnMessage();
    }

    @Test
    public void ClearLoginThenValidCredentials() {
        logonWindow.setCredentials(logonWindow.getIncorrectCredential(), logonWindow.getEmptyCredential(), false);
        logonWindow.clickLoginButtonClear();

        logonWindow.setCredentials(logonWindow.getCorrectLogin(), logonWindow.getCorrectPassword(), false);

        //Check static element after successful enter = static element is Olympus logo
        dashboardWindow.isVisibleLogo();
        dashboardWindow.clickButtonLogout();
    }

    @Test
    public void ClearPasswordThenValidCredentials() {
        logonWindow.setCredentials(logonWindow.getEmptyCredential(), logonWindow.getIncorrectCredential(), false);
        logonWindow.clickPasswordButtonClear();

        //Valid login and password credentials
        logonWindow.setCredentials(logonWindow.getCorrectLogin(), logonWindow.getCorrectPassword(), false);

        //Check static element after successful enter = static element is Olympus logo
        dashboardWindow.isVisibleLogo();
        dashboardWindow.clickButtonLogout();
    }

    @Test
    public void ValidCredentialsWithRemember() {
        logonWindow.setCredentials(logonWindow.getCorrectLogin(), logonWindow.getCorrectPassword(), true);

        //Check static element after successful enter = static element is Olympus logo
        dashboardWindow.isVisibleLogo();
        dashboardWindow.clickButtonLogout();
    }

    @Test
    public void ValidCredentialsWithoutRemember() {
        logonWindow.setCredentials(logonWindow.getCorrectLogin(), logonWindow.getCorrectPassword(), false);

        //Check static element after successful enter = static element is Olympus logo
        dashboardWindow.isVisibleLogo();
        dashboardWindow.clickButtonLogout();
    }
}