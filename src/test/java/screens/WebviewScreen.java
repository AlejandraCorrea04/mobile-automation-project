package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import util.screens.BaseScreen;

/**
 * Representa la pantalla de Webview dentro de la aplicación móvil.
 * Permite validar la presencia e interacción con componentes web embebidos en la aplicación.
 *
 * @author Alejandra Correa
 * @version 1.0
 */
public class WebviewScreen extends BaseScreen {

    /**
     * Elemento contenedor del componente nativo Android Webview.
     */
    @AndroidFindBy(className = "android.webkit.WebView")
    private WebElement webView;

    /**
     * Constructor de la clase WebviewScreen.
     *
     * @param driver Instancia activa de {@link AndroidDriver} utilizada para interactuar con la pantalla.
     */
    public WebviewScreen(AndroidDriver driver){
        super(driver);
    }

    /**
     * Verifica que la pantalla Webview esté desplegada y cargada correctamente.
     * <p>
     * Comprueba que el elemento {@code WebView} se encuentre disponible en la vista
     * y que sus dimensiones de ancho y alto sean mayores a cero.
     * </p>
     *
     * @return {@code true} si el Webview está disponible y tiene dimensiones válidas;
     *         {@code false} en caso contrario.
     */
    public boolean isDisplayed(){
        if (!isElementAvailable(webView)) {
            return false;
        }
        Dimension size = webView.getSize();
        return size.getWidth() > 0 && size.getHeight() > 0;
    }
}