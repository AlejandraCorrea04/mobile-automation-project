package util.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.screens.CustomWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase abstracta base para todas las pantallas del patrón Page Object Model (POM).
 * Proporciona métodos reutilizables para la interacción con elementos móviles,
 * esperas explícitas, envío de texto y gestos táctiles (scroll, swipe).
 *
 * @author Alejandra Correa
 * @version 1.0
 */
public abstract class BaseScreen {

    /**
     * Instancia activa de {@link AndroidDriver} para la automatización.
     */
    protected final AndroidDriver driver;

    /**
     * Instancia de {@link CustomWait} encargada del manejo de esperas explícitas.
     */
    protected final CustomWait customWait;

    /**
     * Logger para el registro de eventos y depuración durante la ejecución.
     */
    protected static final Logger log = LoggerFactory.getLogger(BaseScreen.class);

    /**
     * Constructor de la clase BaseScreen.
     * Inicializa el driver, la clase de esperas personalizadas y configura {@link PageFactory} con soporte para Appium.
     *
     * @param driver Instancia activa de {@link AndroidDriver}.
     */
    public BaseScreen(AndroidDriver driver) {
        this.driver = driver;
        this.customWait = new CustomWait(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    /**
     * Simula el toque del botón físico/virtual de retroceso en el dispositivo Android.
     */
    public void tapBack() {
        log.info("Tap back");
        driver.navigate().back();
    }

    /**
     * Realiza un clic sobre un elemento con un tiempo de espera explícito personalizado.
     *
     * @param element        El {@link WebElement} a hacer clic.
     * @param timeoutSeconds Tiempo límite de espera en segundos.
     */
    public void click(WebElement element, int timeoutSeconds) {
        customWait.waitForClickable(element, timeoutSeconds).click();
    }

    /**
     * Realiza un clic sobre un elemento utilizando el tiempo de espera predeterminado.
     *
     * @param element El {@link WebElement} a hacer clic.
     */
    public void click(WebElement element) {
        customWait.waitForClickable(element).click();
    }

    /**
     * Envía una secuencia de teclas a un campo de texto, limpiándolo previamente.
     *
     * @param element El {@link WebElement} de entrada.
     * @param text    El texto a ingresar.
     */
    public void sendKeys(WebElement element, String text) {
        WebElement el = customWait.waitForVisible(element);
        el.clear();
        el.sendKeys(text);
    }

    /**
     * Verifica si un elemento está disponible y visible en pantalla utilizando un tiempo por defecto (5 segundos).
     *
     * @param element El {@link WebElement} a verificar.
     * @return {@code true} si el elemento se encuentra visible; {@code false} en caso contrario.
     */
    public boolean isElementAvailable(WebElement element) {
        return customWait.isVisibleWithin(element, 5);
    }

    /**
     * Realiza un desplazamiento de pantalla (scroll) en una dirección indicada ("up" o "down")
     * una cantidad específica de veces usando {@code UiScrollable}.
     *
     * @param direction Dirección del desplazamiento ("up" o "down").
     * @param times     Número de repeticiones del gesto.
     */
    public void scroll(String direction, int times) {
        for (int i = 0; i < times; i++) {
            String uiSelector = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
            if (direction.equalsIgnoreCase("up")) {
                uiSelector = "new UiScrollable(new UiSelector().scrollable(true)).scrollBackward()";
            }
            driver.findElement(AppiumBy.androidUIAutomator(uiSelector));
        }
    }

    /**
     * Realiza desplazamientos hacia abajo en la pantalla la cantidad de veces indicada.
     *
     * @param times Número de repeticiones del scroll.
     */
    public void scrollDown(int times) {
        scroll("down", times);
    }

    /**
     * Realiza desplazamientos hacia arriba en la pantalla la cantidad de veces indicada.
     *
     * @param times Número de repeticiones del scroll.
     */
    public void scrollUp(int times) {
        scroll("up", times);
    }

    /**
     * Desplaza la pantalla hasta hacer visible un elemento que contenga el texto especificado.
     *
     * @param text Cadena de texto a buscar.
     * @return El {@link WebElement} localizado tras el desplazamiento.
     */
    public WebElement scrollToText(String text) {
        String uiSelector = String.format(
                "new UiScrollable(new UiSelector().scrollable(true))"
                        + ".scrollIntoView(new UiSelector().textContains(\"%s\"))", text);
        return driver.findElement(AppiumBy.androidUIAutomator(uiSelector));
    }

    /**
     * Ejecuta un gesto de deslizamiento vertical personalizado invocando el comando nativo {@code mobile: swipeGesture}.
     *
     * @param percent Porcentaje de fuerza o recorrido del deslizamiento.
     */
    public void swipeVertical(float percent) {
        Dimension size = driver.manage().window().getSize();
        Map<String, Object> params = new HashMap<>();
        params.put("left", 0);
        params.put("top", (int) (size.height * 0.05));
        params.put("width", size.width);
        params.put("height", (int) (size.height * 0.28));
        params.put("direction", "up");
        params.put("percent", percent);
        driver.executeScript("mobile: swipeGesture", params);
    }

    /**
     * Ejecuta un deslizamiento horizontal en la pantalla basándose en porcentajes de las coordenadas X e Y.
     *
     * @param percentageStartX Porcentaje inicial del ancho para la coordenada X.
     * @param percentageEndX   Porcentaje final del ancho para la coordenada X.
     * @param percentageY      Porcentaje de la altura para la coordenada Y.
     */
    public void swipeHorizontal(int percentageStartX, int percentageEndX, int percentageY) {
        Dimension size = driver.manage().window().getSize();
        int y = size.height * percentageY / 100;
        int startX = size.width * percentageStartX / 100;
        int endX = size.width * percentageEndX / 100;
        swipe(startX, y, endX, y);
    }

    /**
     * Limpia un campo de texto y escribe la cadena indicada tras validar su visibilidad.
     *
     * @param element El {@link WebElement} de entrada de texto.
     * @text  text    El texto a escribir.
     */
    public void sendKey(WebElement element, String text){
        WebElement el = customWait.waitForVisible(element);
        el.clear();
        el.sendKeys(text);
    }

    /**
     * Ejecuta la secuencia de bajo nivel mediante {@link PointerInput} para simular un gesto de arrastrar/deslizar táctil.
     *
     * @param startX Coordenada X inicial.
     * @param startY Coordenada Y inicial.
     * @param endX   Coordenada X final.
     * @param endY   Coordenada Y final.
     */
    private void swipe(int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 0)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(sequence));
    }

    /**
     * Verifica si un elemento está disponible y visible en pantalla dentro de un tiempo personalizado.
     *
     * @param element        El {@link WebElement} a verificar.
     * @param timeoutSeconds Tiempo límite de espera en segundos.
     * @return {@code true} si el elemento se vuelve visible dentro del tiempo; {@code false} en caso contrario.
     */
    public boolean isElementAvailable(WebElement element, int timeoutSeconds) {
        return customWait.isVisibleWithin(element, timeoutSeconds);
    }
}