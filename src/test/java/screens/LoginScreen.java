package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import util.screens.BaseScreen;

/**
 * Representa la pantalla de Login y Registro (Sign Up) dentro de la aplicación.
 * Proporciona métodos para interactuar con los formularios de autenticación y los diálogos de confirmación.
 *
 * @author Alejandra Correa
 * @version 1.0
 */
public class LoginScreen extends BaseScreen {

    /**
     * Contenedor principal de la pantalla de Login/Registro.
     */
    @AndroidFindBy(accessibility = "Login-screen")
    private WebElement loginScreenContainer;

    /**
     * Pestaña/Botón para cambiar a la vista de Login.
     */
    @AndroidFindBy(accessibility = "button-login-container")
    private WebElement tabLoginToggle;

    /**
     * Pestaña/Botón para cambiar a la vista de Sign Up (Registro).
     */
    @AndroidFindBy(uiAutomator = "text(\"Sign up\")")
    private WebElement tabSignUpToggle;

    /**
     * Campo de entrada para el correo electrónico.
     */
    @AndroidFindBy(accessibility = "input-email")
    private WebElement inputEmail;

    /**
     * Campo de entrada para la contraseña.
     */
    @AndroidFindBy(accessibility = "input-password")
    private WebElement inputPassword;

    /**
     * Campo de entrada para la confirmación de la contraseña en el formulario de registro.
     */
    @AndroidFindBy(accessibility = "input-repeat-password")
    private WebElement inputRepeatPassword;

    /**
     * Botón para enviar el formulario de inicio de sesión.
     */
    @AndroidFindBy(accessibility = "button-LOGIN")
    private WebElement btnLogin;

    /**
     * Botón para enviar el formulario de registro.
     */
    @AndroidFindBy(accessibility = "button-SIGN UP")
    private WebElement btnSignUp;

    /**
     * Título del diálogo emergente de confirmación/alerta.
     */
    @AndroidFindBy(id = "android:id/alertTitle")
    private WebElement dialogTitle;

    /**
     * Mensaje de texto dentro del diálogo emergente de confirmación/alerta.
     */
    @AndroidFindBy(id = "android:id/message")
    private WebElement dialogMessage;

    /**
     * Botón "OK" del diálogo emergente de confirmación/alerta.
     */
    @AndroidFindBy(id = "android:id/button1")
    private WebElement dialogOkButton;

    /**
     * Constructor de la clase LoginScreen.
     *
     * @param driver Instancia activa de {@link AndroidDriver} para interactuar con los elementos de la pantalla.
     */
    public LoginScreen(AndroidDriver driver){
        super(driver);
    }

    /**
     * Selecciona la pestaña para activar el formulario de inicio de sesión (Login).
     */
    public void goToLoginTab(){
        click(tabLoginToggle);
    }

    /**
     * Selecciona la pestaña para activar el formulario de registro (Sign Up).
     */
    public void goToSignUpTab(){
        click(tabSignUpToggle);
    }

    /**
     * Realiza el proceso completo de inicio de sesión ingresando credenciales y presionando el botón de ingreso.
     *
     * @param email    Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     */
    public void login(String email, String password){
        goToLoginTab();
        sendKey(inputEmail, email);
        sendKey(inputPassword, password);
        click(btnLogin);
    }

    /**
     * Completa los campos del formulario de registro con los datos provistos.
     *
     * @param email    Correo electrónico para la nueva cuenta.
     * @param password Contraseña para la nueva cuenta.
     */
    public void fillSignUpForm(String email, String password){
        sendKey(inputEmail, email);
        sendKey(inputPassword, password);
        sendKey(inputRepeatPassword, password);
    }

    /**
     * Presiona el botón para procesar el registro (Sign Up).
     */
    public void tapSignUpButton(){
        click(btnSignUp);
    }

    /**
     * Realiza el flujo completo de registro de usuario: cambia a la pestaña de Sign Up,
     * diligencia el formulario y confirma el envío.
     *
     * @param email    Correo electrónico para registrar.
     * @param password Contraseña para registrar.
     */
    public void signUp(String email, String password){
        goToSignUpTab();
        fillSignUpForm(email, password);
        tapSignUpButton();
    }

    /**
     * Cambia a la pestaña de inicio de sesión y verifica la disponibilidad de sus campos principales.
     *
     * @return {@code true} si los campos de email, contraseña y el botón de Login están disponibles; {@code false} en caso contrario.
     */
    public boolean isLoginFormDisplayed(){
        goToLoginTab();
        return isElementAvailable(inputEmail)
                && isElementAvailable(inputPassword)
                && isElementAvailable(btnLogin);
    }

    /**
     * Verifica la disponibilidad de los campos pertenecientes al formulario de registro.
     *
     * @return {@code true} si los campos de email, contraseña, repetición de contraseña y botón de Sign Up están disponibles; {@code false} en caso contrario.
     */
    public boolean isSignUpFormDisplayed(){
        return isElementAvailable(inputEmail)
                && isElementAvailable(inputPassword)
                && isElementAvailable(inputRepeatPassword)
                && isElementAvailable(btnSignUp);
    }

    /**
     * Verifica si se encuentra visible un diálogo de confirmación o alerta en la pantalla.
     *
     * @return {@code true} si el título y el mensaje del diálogo están visibles; {@code false} en caso contrario.
     */
    public boolean isConfirmationDialogDisplayed() {
        return isElementAvailable(dialogTitle) && isElementAvailable(dialogMessage);
    }

    /**
     * Obtiene el texto del título del diálogo emergente.
     *
     * @return Cadena de texto con el título del diálogo.
     */
    public String getDialogTitle() {
        return dialogTitle.getText();
    }

    /**
     * Obtiene el texto del mensaje dentro del diálogo emergente.
     *
     * @return Cadena de texto con el mensaje del diálogo.
     */
    public String getDialogMessage() {
        return dialogMessage.getText();
    }

    /**
     * Acepta y cierra el diálogo emergente haciendo clic en su botón de confirmación ("OK").
     */
    public void acceptDialog() {
        click(dialogOkButton);
    }

    /**
     * Verifica que la pantalla de Login/Registro esté cargada y disponible en su estado básico inicial.
     *
     * @return {@code true} si el contenedor, pestañas de cambio y campos iniciales están visibles; {@code false} en caso contrario.
     */
    public boolean isDisplayed() {
        return isElementAvailable(loginScreenContainer)
                && isElementAvailable(tabLoginToggle)
                && isElementAvailable(tabSignUpToggle)
                && isElementAvailable(inputEmail)
                && isElementAvailable(inputPassword)
                && isElementAvailable(btnLogin);
    }
}