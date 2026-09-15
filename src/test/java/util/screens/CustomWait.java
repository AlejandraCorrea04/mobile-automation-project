package util.screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Clase de utilidad encargada de gestionar las esperas explícitas (Explicit Waits) de la aplicación.
 * <p>
 * Facilita la sincronización de elementos de la interfaz de usuario, controlando su estado
 * de visibilidad, clickabilidad y presencia temporal.
 * </p>
 *
 * @author Alejandra Correa
 * @version 1.0
 */
public class CustomWait {

    /**
     * Tiempo límite de espera predeterminado en segundos.
     */
    private static final int DEFAULT_TIMEOUT_SECONDS = 10;

    /**
     * Instancia activa del controlador de Appium para Android.
     */
    private final AndroidDriver driver;

    /**
     * Constructor de la clase CustomWait.
     *
     * @param driver Instancia activa de {@link AndroidDriver}.
     */
    public CustomWait(AndroidDriver driver) {
        this.driver = driver;
    }

    /**
     * Espera a que un elemento sea clickable utilizando el tiempo de espera por defecto.
     *
     * @param element El {@link WebElement} a esperar.
     * @return El {@link WebElement} una vez sea habilitado para interacciones de clic.
     */
    public WebElement waitForClickable(WebElement element) {
        return waitForClickable(element, DEFAULT_TIMEOUT_SECONDS);
    }

    /**
     * Espera a que un elemento sea clickable dentro de un tiempo de espera personalizado.
     *
     * @param element        El {@link WebElement} a esperar.
     * @param timeoutSeconds Tiempo máximo de espera en segundos.
     * @return El {@link WebElement} listo para recibir clics.
     */
    public WebElement waitForClickable(WebElement element, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Espera a que un elemento se vuelva visible en pantalla usando el tiempo por defecto.
     *
     * @param element El {@link WebElement} a esperar.
     * @return El {@link WebElement} cuando se despliega en la interfaz.
     */
    public WebElement waitForVisible(WebElement element) {
        return waitForVisible(element, DEFAULT_TIMEOUT_SECONDS);
    }

    /**
     * Espera a que un elemento se vuelva visible en pantalla dentro de un límite de tiempo especificado.
     *
     * @param element        El {@link WebElement} a esperar.
     * @param timeoutSeconds Tiempo máximo de espera en segundos.
     * @return El {@link WebElement} cuando se despliega en la interfaz.
     */
    public WebElement waitForVisible(WebElement element, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Comprueba si un elemento se vuelve visible dentro del tiempo límite asignado sin capturar excepciones de Timeout.
     *
     * @param element        El {@link WebElement} a verificar.
     * @param timeoutSeconds Tiempo límite para confirmar la visibilidad en segundos.
     * @return {@code true} si el elemento se despliega dentro del tiempo; {@code false} si se agota el tiempo (Timeout).
     */
    public boolean isVisibleWithin(WebElement element, int timeoutSeconds) {
        try {
            waitForVisible(element, timeoutSeconds);
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
}