package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import util.screens.BaseScreen;

public class HomeScreen extends BaseScreen implements AlertHandler {

    @AndroidFindBy(accessibility = "Home")
    private WebElement tabHome;

    @AndroidFindBy(accessibility ="Webview")
    private WebElement tabWebview;

    @AndroidFindBy(accessibility = "Login")
    private WebElement tabLogin;

    @AndroidFindBy(accessibility = "Forms")
    private WebElement tabForms;

    @AndroidFindBy(accessibility ="Swipe")
    private WebElement tabSwipe;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement btnSystemDialogOk;

    public HomeScreen(AndroidDriver driver){
        super(driver);
        alertControl();
    }

    @Override
    public void alertControl(){
        if(isElementAvailable(btnSystemDialogOk)){
            click(btnSystemDialogOk);
        }
    }

    public void  goToWebview(){
        click(tabWebview);
    }

    public void goToForms(){
        click(tabForms);
    }
    public void goToDrag(){
        click(tabDrag);
    }

    public LoginScreen goToLogin(){
        click(tabLogin);
        return new LoginScreen(driver);
    }

    public SwipeScreen goToSwipe() {
        click(tabSwipe);
        return new SwipeScreen(driver);
    }

    public boolean isHomeScreenDisplayed(){
        return isElementAvailable(tabHome);
    }
}
