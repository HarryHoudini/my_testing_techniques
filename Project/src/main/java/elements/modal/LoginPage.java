package main.java.elements.modal;

import main.java.elements.simple_elements.Checkbox;

import static com.codeborne.selenide.Selectors.byAttribute;

public class LoginPage extends BaseModal {

    private Checkbox rememberCheckbox =
        new Checkbox(this.rootElement.$(byAttribute("data-control-name","rememberMe")));

    private LoginPage() {
        super(ModalType.LOGIN_PAGE);
    }

    public static LoginPage of(){
        return new LoginPage();
    }

    private void enterLogin(String login, String password, boolean remember){
        setTexteditValue(ModalTextedit.LOGIN, login);
        setTexteditValue(ModalTextedit.PASSWORD, password);
        if (remember != rememberCheckbox.isChecked()){
            rememberCheckbox.isChecked();
        }
        clickButton(ModalButton.ENTER);
    }

    public static void enter(String login, String password){
        LoginPage.of().enterLogin(login, password, false);
    }
}
