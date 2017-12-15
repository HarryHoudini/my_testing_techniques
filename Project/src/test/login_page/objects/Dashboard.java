package test.login_page.objects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class Dashboard {

    private SelenideElement olympusLogo = $(byId("ol_olympus_logo"));
    private SelenideElement logoutDashboardButton = $(byCssSelector("#ol_main_header_user .ol-icon-exit"));


    public void clickButtonLogout() {
        this.logoutDashboardButton.click();
    }

    public void isVisibleLogo() {
        this.olympusLogo.shouldBe(visible);
    }
}
