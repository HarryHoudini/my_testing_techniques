package test.login_page.objects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.Thread.sleep;


public class LogonPage {
    final String CORRECT_LOGIN = "admin";
    final String CORRECT_PASSWORD = "admin";
    final String INCORRECT_CREDENTIAL = "АдмЫн2min1234567@!";
    final String EMPTY_CREDENTIAL = "";

    private SelenideElement loginTextEdit = $(byName("login"));
    private SelenideElement passwordTextEdit = $(byName("password"));
    private SelenideElement rememberMeCheckBox = $(byCssSelector(".ol_checkbox"));
    private SelenideElement enterButton = $(byClassName("ol_btn"));
    private SelenideElement credentialWarningMessage = $(byCssSelector("#ol_sm_live ._ol_inner"));
    private SelenideElement loginCredentialClearButton = $(byXpath(".//*[@data-control-name='login']//i"));
    private SelenideElement passwordCredentialClearButton = $(byXpath(".//*[@data-control-name='password']//i"));


    public void setCredentials(String login, String password, boolean remember) {
        this.loginTextEdit.setValue(login);
        this.passwordTextEdit.setValue(password);

        if(remember != this.rememberMeCheckBox.is(checked)){
            this.rememberMeCheckBox.click();
        }
        enterButton.click();
    }

    public String getCorrectLogin() {
        return CORRECT_LOGIN;
    }

    public String getCorrectPassword() {
        return CORRECT_PASSWORD;
    }

    public String getIncorrectCredential() {
        return INCORRECT_CREDENTIAL;
    }

    public String getEmptyCredential() {
        return EMPTY_CREDENTIAL;
    }


    //Click button 'x' to clear login credential
    public void clickLoginButtonClear() {
        this.loginTextEdit.hover().click();
        this.loginCredentialClearButton.shouldBe(visible).click();
    }

    //Click button 'x' to clear password credential
    public void clickPasswordButtonClear() {
        this.passwordTextEdit.hover().click();
        this.passwordCredentialClearButton.shouldBe(visible).click();
    }

    //Assert that Enter button can not be clickable
    public void isDisabled() {
        this.enterButton.shouldHave(Condition.attribute("disabled"));
    }


    //Validate warning message about incorrect login credentials
    public void isExistWarnMessage() {
        this.credentialWarningMessage.shouldBe(Condition.visible);
    }
}
