package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import util.screens.BaseScreen;

public class LoginScreen extends BaseScreen {

    @AndroidFindBy(accessibility = "button-login-container")
    private WebElement tabLoginToggle;

    @AndroidFindBy(uiAutomator = "text(\"Sign up\")")
    private WebElement tabSignUpToggle;

    @AndroidFindBy(accessibility = "input-email")
    private WebElement inputEmail;

    @AndroidFindBy(accessibility = "input-password")
    private WebElement inputPassword;

    @AndroidFindBy(accessibility = "input-repeat-password")
    private WebElement inputRepeatPassword;

    @AndroidFindBy(accessibility = "button-LOGIN")
    private WebElement btnLogin;

    @AndroidFindBy(accessibility = "button-SIGN UP")
    private WebElement btnSignUp;

    @AndroidFindBy(id = "android:id/alertTitle")
    private WebElement dialogTitle;

    @AndroidFindBy(id = "android:id/message")
    private WebElement dialogMessage;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement dialogOkButton;

    public LoginScreen(AndroidDriver driver){
        super(driver);
    }

    public void goToLoginTab(){
        click(tabLoginToggle);
    }
    public void goToSignUpTab(){
        click(tabSignUpToggle);
    }

    public void login(String email, String password){
        goToLoginTab();
        sendKey(inputEmail,email);
        sendKey(inputPassword,password);
        click(btnLogin);
    }

    public void signUp(String email, String password){
        goToSignUpTab();
        sendKey(inputEmail,email);
        sendKey(inputPassword,password);
        sendKey(inputRepeatPassword,password);
        click(btnSignUp);
    }

    public boolean isLoginFormDisplayed(){
        goToLoginTab();;
        return isElementAvailable(inputEmail)&& isElementAvailable(inputPassword)&& isElementAvailable(btnLogin);
    }

    public boolean isSignUpFormDisplayed(){
        goToSignUpTab();
        return isElementAvailable(inputEmail)&&isElementAvailable(inputRepeatPassword)&&isElementAvailable(btnSignUp);
    }

    public boolean isConfirmationDialogDisplayed() {
        return isElementAvailable(dialogTitle) && isElementAvailable(dialogMessage);
    }

    public String getDialogTitle() {
        return dialogTitle.getText();
    }

    public String getDialogMessage() {
        return dialogMessage.getText();
    }

    public void acceptDialog() {
        click(dialogOkButton);
    }


}
