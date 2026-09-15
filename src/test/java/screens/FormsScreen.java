package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import util.screens.BaseScreen;

/**
 * Representa la pantalla de Formularios (Forms) dentro de la aplicación.
 * Mapea los diferentes componentes de entrada como campos de texto, switches, listas desplegables y botones.
 *
 * @author Alejandra Correa
 * @version 1.0
 */
public class FormsScreen extends BaseScreen {

    /**
     * Título principal de la sección de componentes de formulario.
     */
    @AndroidFindBy(uiAutomator = "text(\"Form components\")")
    private WebElement title;

    /**
     * Campo de entrada de texto interactivo.
     */
    @AndroidFindBy(accessibility = "text-input")
    private WebElement inputField;

    /**
     * Elemento donde se refleja el texto ingresado en el campo de entrada.
     */
    @AndroidFindBy(accessibility = "input-text-result")
    private WebElement inputResult;

    /**
     * Interruptor o switch de activación/desactivación.
     */
    @AndroidFindBy(accessibility = "switch")
    private WebElement switchToggle;

    /**
     * Etiqueta de texto descriptiva asociada al switch.
     */
    @AndroidFindBy(accessibility = "switch-text")
    private WebElement switchText;

    /**
     * Menú desplegable (Dropdown) de selección.
     */
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"text_input\")")
    private WebElement dropdown;

    /**
     * Botón con estado activo.
     */
    @AndroidFindBy(accessibility = "button-Active")
    private WebElement btnActive;

    /**
     * Botón con estado inactivo.
     */
    @AndroidFindBy(accessibility = "button-Inactive")
    private WebElement btnInactive;

    /**
     * Constructor de la clase FormsScreen.
     *
     * @param driver Instancia activa de {@link AndroidDriver} utilizada para interactuar con la pantalla.
     */
    public FormsScreen(AndroidDriver driver) {
        super(driver);
    }

    /**
     * Verifica que la pantalla de Formularios y sus componentes estén visibles y en su estado inicial correcto.
     * <p>
     * Comprueba la visibilidad de todos los elementos (título, campo de texto, resultado, switch, dropdown y botones),
     * además de asertar que el switch no esté seleccionado y que el menú desplegable esté deshabilitado por defecto.
     * </p>
     *
     * @return {@code true} si todos los componentes son visibles y cumplen con su estado por defecto;
     *         {@code false} en caso contrario.
     */
    public boolean isDisplayed() {
        boolean titleVisible = isElementAvailable(title);
        boolean inputFieldVisible = isElementAvailable(inputField);
        boolean inputResultVisible = isElementAvailable(inputResult);
        boolean switchVisible = isElementAvailable(switchToggle);
        boolean switchTextVisible = isElementAvailable(switchText);
        boolean dropdownVisible = customWait.isVisibleWithin(dropdown, 10);
        boolean btnActiveVisible = isElementAvailable(btnActive);
        boolean btnInactiveVisible = isElementAvailable(btnInactive);

        boolean allVisible = titleVisible && inputFieldVisible && inputResultVisible
                && switchVisible && switchTextVisible && dropdownVisible
                && btnActiveVisible && btnInactiveVisible;

        if (!allVisible) {
            return false;
        }

        return !switchToggle.isSelected() && !dropdown.isEnabled();
    }
}