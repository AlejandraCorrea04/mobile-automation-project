package util.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import java.util.List;
import util.screens.CustomWait;

public class BaseScreen {
    protected final AndroidDriver driver;
    protected final CustomWait customWait;
    protected final Logger log= LoggerFactory.getLogger(BaseScreen.class);

    public BaseScreen(AndroidDriver driver){
        this.driver=driver;
        this.customWait= new CustomWait(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public void tapBack(){
        log.info("Tap back");
        driver.navigate().back();
    }

    public void click(WebElement element, int timeoutSeconds){
        customWait.waitForClickable(element,timeoutSeconds).click();
    }

    public void  click(WebElement element){
        customWait.waitForClickable(element).click();
    }

    public void sendKey(WebElement element, String text){
        WebElement el = customWait.waitForVisible(element);
        el.clear();
        el.sendKeys(text);
    }

    public boolean isElementAvailable(WebElement element){
        return customWait.isVisibleWithin(element,5);
    }
    public void scroll(String direction, int times){
        for (int i=0;i<times;i++){
            String uiSelector ="new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
            if(direction.equalsIgnoreCase("up")){
                uiSelector = "new UiScrollable(new UiSelector().scrollable(true)).scrollBackward()";
            }
            driver.findElement(AppiumBy.androidUIAutomator(uiSelector));         }
    }

    public void scrollDown(int times){
        scroll("down",times);
    }

    public void scrollUp(int times){
        scroll("up",times);
    }

    public WebElement scrollToText(String text){
        String uiSelector =String.format(
                "new UiScrollable(new UiSelector().scrollable(true))"
                        + ".scrollIntoView(new UiSelector().textContains(\"%s\"))", text);
        return driver.findElement(AppiumBy.androidUIAutomator(uiSelector));
    }

    public void swipeVertical(float percent) {
        Dimension size = driver.manage().window().getSize();
        int StartX = size.width / 2;
        int StartY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.8 - size.height * percent);
        swipe(StartX, StartY, StartX, endY);
    }

    public void swipeHorizontal(int percentagesStartX, int percentageEndX, int percentageY){
        Dimension size = driver.manage().window().getSize();
        int y = size.height*percentageY/100;
        int startX= size.width*percentagesStartX/100;
        int endX= size.width * percentageEndX/100;
        swipe(startX, y, endX, y);
    }

    private void swipe (int startX, int startY, int endX, int endY){
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 0)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(sequence));
    }
} 
