package util.screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomWait {

    private static final int DEFAULT_TIMEOUT_SECONDS = 10;
    private final AndroidDriver driver;

    public CustomWait(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForClickable(WebElement element) {
        return waitForClickable(element, DEFAULT_TIMEOUT_SECONDS);
    }
    public WebElement waitForClickable(WebElement element, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.elementToBeClickable(element));
    }


    public WebElement waitForVisible(WebElement element) {
        return waitForVisible(element, DEFAULT_TIMEOUT_SECONDS);
    }

    public WebElement waitForVisible(WebElement element, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isVisibleWithin(WebElement element, int timeoutSeconds) {
        try {
            waitForVisible(element, timeoutSeconds);
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
}

