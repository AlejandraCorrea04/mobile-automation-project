package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import util.screens.BaseScreen;

/**
 * Representa la pantalla de "Drag and Drop" (Arrastrar y Soltar) de la aplicación.
 * Mapea las piezas de arrastre y las zonas de soltado para verificar la interfaz y realizar interacciones de arrastre.
 *
 * @author Alejandra Correa
 * @version 1.0
 */
public class DragScreen extends BaseScreen {

    /**
     * Identificadores de accesibilidad para las zonas donde se deben soltar las piezas (Drop Zones).
     */
    private static final String[] DROP_ZONE_IDS = {
            "drop-l1", "drop-c1", "drop-r1",
            "drop-l2", "drop-c2", "drop-r2",
            "drop-l3", "drop-c3", "drop-r3"
    };

    /**
     * Identificadores de accesibilidad para las piezas que se pueden arrastrar (Drag Pieces).
     */
    private static final String[] DRAG_PIECE_IDS = {
            "drag-l1", "drag-c1", "drag-r1",
            "drag-l2", "drag-c2", "drag-r2",
            "drag-l3", "drag-c3", "drag-r3"
    };

    /**
     * Elemento web/móvil que representa el título principal de la pantalla de Drag and Drop.
     */
    @AndroidFindBy(uiAutomator = "text(\"Drag and Drop\")")
    private WebElement title;

    /**
     * Constructor de la clase DragScreen.
     *
     * @param driver Instancia activa del {@link AndroidDriver} utilizada para interactuar con la pantalla.
     */
    public DragScreen(AndroidDriver driver){
        super(driver);
    }

    /**
     * Verifica que la pantalla de Drag and Drop esté desplegada correctamente.
     * <p>
     * Comprueba la disponibilidad del título principal, así como la visibilidad
     * de cada una de las zonas de destino (Drop Zones) y piezas de arrastre (Drag Pieces).
     * </p>
     *
     * @return {@code true} si el título y todos los elementos de arrastre/soltado están visibles en pantalla;
     *         {@code false} en caso contrario.
     */
    public boolean isDisplayed() {
        if (!isElementAvailable(title)) {
            return false;
        }
        for (String zoneId : DROP_ZONE_IDS) {
            WebElement zone = driver.findElement(AppiumBy.accessibilityId(zoneId));
            if (!zone.isDisplayed()) {
                return false;
            }
        }
        for (String pieceId : DRAG_PIECE_IDS) {
            WebElement piece = driver.findElement(AppiumBy.accessibilityId(pieceId));
            if (!piece.isDisplayed()) {
                return false;
            }
        }
        return true;
    }
}