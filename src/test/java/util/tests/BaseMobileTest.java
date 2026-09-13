package util.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseMobileTest {
    protected static AndroidDriver driver;
    protected static final Logger log = LoggerFactory.getLogger(BaseMobileTest.class);
    private static final String APPIUM_SERVER_URL= "http://127.0.0.1:4723";
    private static final String APP_PACKAGE = "com.wdiodemoapp";
    private static final String APP_ACTIVITY = "com.wdiodemoapp.MainActivity";

    @BeforeClass
    public void setUpStartApp(){
        environmentSetUp();
       log.info("Sesion de Appium iniciada correctamente");
    }

    protected void environmentSetUp(){
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("emulator-5554")
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY)
                .setAutoGrantPermissions(true)
                .setNoReset(true)
                .setNewCommandTimeout(Duration.ofSeconds(120));
        try {
            driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), options);
        }catch (MalformedURLException e){
            throw new RuntimeException("URL del servidor de Appium invalidad: "+APPIUM_SERVER_URL,e);
        }
    }

    @AfterClass
    public void mobileApplicationEnd(){
        if(driver!=null){
            driver.quit();
            log.info("Sesion de appium cerrada correctamente");
        }
    }

    public AndroidDriver getDriver(){
        return driver;
    }
}
