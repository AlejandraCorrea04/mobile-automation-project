package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import util.screens.BaseScreen;

/**
 * Representa la pantalla principal (Home) de la aplicación y sirve como punto central
 * para la navegación hacia las diferentes secciones mediante la barra inferior.
 * Implementa {@link AlertHandler} para gestionar diálogos del sistema si se presentan.
 *
 * @author Alejandra Correa
 * @version 1.0
 */
public class HomeScreen extends BaseScreen implements AlertHandler {

    /**
     * Elemento de la pestaña "Home" en la barra de navegación inferior.
     */
    @AndroidFindBy(accessibility = "Home")
    private WebElement tabHome;

    /**
     * Elemento de la pestaña "Webview" en la barra de navegación inferior.
     */
    @AndroidFindBy(accessibility = "Webview")
    private WebElement tabWebview;

    /**
     * Elemento de la pestaña "Login" en la barra de navegación inferior.
     */
    @AndroidFindBy(accessibility = "Login")
    private WebElement tabLogin;

    /**
     * Elemento de la pestaña "Forms" en la barra de navegación inferior.
     */
    @AndroidFindBy(accessibility = "Forms")
    private WebElement tabForms;

    /**
     * Elemento de la pestaña "Swipe" en la barra de navegación inferior.
     */
    @AndroidFindBy(accessibility = "Swipe")
    private WebElement tabSwipe;

    /**
     * Elemento de la pestaña "Drag" en la barra de navegación inferior.
     */
    @AndroidFindBy(accessibility = "Drag")
    private WebElement tabDrag;

    /**
     * Botón "OK" de alertas de diálogo nativas del sistema Android.
     */
    @AndroidFindBy(id = "android:id/button1")
    private WebElement btnSystemDialogOk;

    /**
     * Constructor de la clase HomeScreen.
     *
     * @param driver Instancia activa de {@link AndroidDriver} utilizada para la interacción con la aplicación.
     */
    public HomeScreen(AndroidDriver driver) {
        super(driver);
    }

    /**
     * Controla y cierra las alertas emergentes del sistema en caso de estar presentes,
     * evitando lanzamientos de excepciones si el diálogo no se encuentra desplegado.
     */
    @Override
    public void alertControl() {
        var alertButtons = driver.findElements(AppiumBy.id("android:id/button1"));
        if (!alertButtons.isEmpty()) {
            alertButtons.get(0).click();
        }
    }

    /**
     * Navega hacia la pantalla de Webview haciendo clic en su pestaña correspondiente.
     *
     * @return Nueva instancia de {@link WebviewScreen}.
     */
    public WebviewScreen goToWebview() {
        click(tabWebview);
        return new WebviewScreen(driver);
    }

    /**
     * Navega hacia la pantalla de Formularios haciendo clic en su pestaña correspondiente.
     *
     * @return Nueva instancia de {@link FormsScreen}.
     */
    public FormsScreen goToForms() {
        click(tabForms);
        return new FormsScreen(driver);
    }

    /**
     * Navega hacia la pantalla de Drag and Drop haciendo clic en su pestaña correspondiente.
     *
     * @return Nueva instancia de {@link DragScreen}.
     */
    public DragScreen goToDrag() {
        click(tabDrag);
        return new DragScreen(driver);
    }

    /**
     * Navega hacia la pantalla de Login/Registro haciendo clic en su pestaña correspondiente.
     *
     * @return Nueva instancia de {@link LoginScreen}.
     */
    public LoginScreen goToLogin() {
        click(tabLogin);
        return new LoginScreen(driver);
    }

    /**
     * Navega hacia la pantalla de Swipe haciendo clic en su pestaña correspondiente.
     *
     * @return Nueva instancia de {@link SwipeScreen}.
     */
    public SwipeScreen goToSwipe() {
        click(tabSwipe);
        return new SwipeScreen(driver);
    }

    /**
     * Verifica si la pantalla principal (Home) está visible mediante la presencia de su pestaña activa.
     *
     * @return {@code true} si la pestaña de Home se encuentra disponible; {@code false} en caso contrario.
     */
    public boolean isHomeScreenDisplayed() {
        return isElementAvailable(tabHome);
    }
}