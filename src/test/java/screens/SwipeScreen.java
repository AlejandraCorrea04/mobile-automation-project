package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import util.screens.BaseScreen;

import java.util.List;

public class SwipeScreen extends BaseScreen {
    @AndroidFindBy(accessibility = "card")
    private List<WebElement> cards;

    @AndroidFindBy(uiAutomator = "text(\"You found me!!!\")")
    private WebElement hiddenText;

    public SwipeScreen(AndroidDriver driver){
        super(driver);
    }

    public int countVisibleCards(){
        return cards.size();
    }

    public void SwipeCardRight(){
        swipeHorizontal(20,80,50);
    }

    public void swipeUpUntilHiddenTextVisible(int maxAttemps){
        int attempts =0;
        while ((!isElementAvailable(hiddenText)&&attempts<maxAttemps)) {
            swipeVertical(0.5f);
            attempts++;
        }

    }

    public boolean isHiddenTextDisplayed(){
        return isElementAvailable(hiddenText);
    }
}
