package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import util.screens.BaseScreen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representa la pantalla de Swipe / Desplazamiento de la aplicación.
 * Permite interactuar con tarjetas horizontales y desplazarse verticalmente hasta hallar elementos ocultos.
 *
 * @author Alejandra Correa
 * @version 1.0
 */
public class SwipeScreen extends BaseScreen {

    /**
     * Título principal de la sección de deslizamiento horizontal.
     */
    @AndroidFindBy(uiAutomator = "text(\"Swipe horizontal\")")
    private WebElement title;

    /**
     * Elemento que representa la primera tarjeta visible en el carrusel.
     */
    @AndroidFindBy(accessibility = "card")
    private WebElement firstCard;

    /**
     * Lista de contenedores de tarjetas activas/listas en el carrusel horizontal.
     */
    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@resource-id,'_READY__')]")
    private List<WebElement> readyCardContainers;

    /**
     * Elemento de texto oculto ubicado al final del desplazamiento vertical.
     */
    @AndroidFindBy(uiAutomator = "text(\"You found me!!!\")")
    private WebElement hiddenText;

    /**
     * Constructor de la clase SwipeScreen.
     *
     * @param driver Instancia activa de {@link AndroidDriver} para la interacción con los elementos de la vista.
     */
    public SwipeScreen(AndroidDriver driver) {
        super(driver);
    }

    /**
     * Verifica que la pantalla de Swipe y la primera tarjeta estén cargadas y visibles.
     *
     * @return {@code true} si el título y la primera tarjeta están disponibles; {@code false} en caso contrario.
     */
    public boolean isDisplayed() {
        return isElementAvailable(title, 15) && isElementAvailable(firstCard, 15);
    }

    /**
     * Obtiene el identificador de recurso (resourceId) de la tarjeta actual en foco.
     *
     * @return Cadena de texto con el atributo resourceId de la primera tarjeta de la lista.
     */
    public String getCurrentCardId() {
        return readyCardContainers.get(0).getAttribute("resourceId");
    }

    /**
     * Verifica que una tarjeta anterior ya no sea visible en la lista de contenedores activos.
     *
     * @param oldCardId Identificador de recurso de la tarjeta previa.
     * @return {@code true} si la tarjeta anterior ya no está presente; {@code false} en caso contrario.
     */
    public boolean isOldCardHidden(String oldCardId) {
        return readyCardContainers.stream()
                .noneMatch(el -> oldCardId.equals(el.getAttribute("resourceId")));
    }

    /**
     * Cuenta la cantidad de tarjetas activas o listas desplegadas en el carrusel.
     *
     * @return Número de tarjetas activas.
     */
    public int countReadyCards() {
        return readyCardContainers.size();
    }

    /**
     * Realiza un gesto de deslizamiento horizontal (Swipe Right) sobre el carrusel de tarjetas.
     */
    public void swipeCardRight() {
        swipeHorizontal(80, 20, 50);
    }

    /**
     * Realiza deslizamientos horizontales sucesivos hasta alcanzar la última tarjeta o superar el límite de intentos.
     *
     * @param maxAttempts Número máximo de intentos de desplazamiento permitidos.
     */
    public void swipeUntilLastCard(int maxAttempts) {
        String previousId = getCurrentCardId();
        for (int i = 0; i < maxAttempts; i++) {
            swipeCardRight();
            String currentId = getCurrentCardId();
            if (currentId.equals(previousId)) {
                return;
            }
            previousId = currentId;
        }
    }

    /**
     * Realiza desplazamientos verticales hacia abajo hasta que el texto oculto sea visible o se agoten los intentos.
     *
     * @param maxAttempts Número máximo de desplazamientos verticales a intentar.
     */
    public void swipeUpUntilHiddenTextVisible(int maxAttempts) {
        int attempts = 0;
        while (!isElementAvailable(hiddenText) && attempts < maxAttempts) {
            swipeVertical(0.9f);
            attempts++;
        }
    }

    /**
     * Verifica si el texto oculto "You found me!!!" se encuentra visible en la pantalla.
     *
     * @return {@code true} si el elemento de texto oculto está disponible; {@code false} en caso contrario.
     */
    public boolean isHiddenTextDisplayed() {
        return isElementAvailable(hiddenText);
    }

    /**
     * Obtiene el valor en texto contenido dentro del elemento oculto.
     *
     * @return Cadena con el texto del elemento.
     */
    public String getHiddenTextValue() {
        return hiddenText.getText();
    }
}