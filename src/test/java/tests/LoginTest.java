package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import screens.LoginScreen;
import util.data.RandomDataGenerator;
import util.tests.BaseMobileTest;

/**
 * Contiene los casos de prueba automatizados para validar el flujo de inicio de sesión exitoso (Login).
 *
 * @author Alejandra Correa
 * @version 1.0
 */
public class LoginTest extends BaseMobileTest {

    /**
     * Valida que un usuario registrado previamente pueda iniciar sesión de manera exitosa en la aplicación.
     * <p>
     * Como precondición, realiza primero un registro dinámico para mantener la independencia del test.
     * Posteriormente, completa el formulario de Login y aserta que aparezca el mensaje de éxito
     * con los textos "Success" y "You are logged in!".
     * </p>
     */
    @Test(description = "Un usuario previamente registrado puede loguearse exitosamente")
    public void successfulLogin() {
        String email = RandomDataGenerator.generateRandomEmail();
        String password = RandomDataGenerator.defaultPassword();

        LoginScreen loginScreen = homeScreen.goToLogin();
        loginScreen.signUp(email, password);

        Assert.assertTrue(loginScreen.isConfirmationDialogDisplayed(),
                "Debería confirmarse el signup previo antes de intentar loguear");
        loginScreen.acceptDialog();

        Assert.assertTrue(loginScreen.isLoginFormDisplayed(),
                "El formulario de Login debería estar visible antes de completarlo");

        loginScreen.login(email, password);

        Assert.assertTrue(loginScreen.isConfirmationDialogDisplayed(),
                "Debería aparecer el diálogo nativo de confirmación tras un login exitoso");

        Assert.assertEquals(loginScreen.getDialogTitle(), "Success",
                "El título del diálogo no es el esperado");
        Assert.assertEquals(loginScreen.getDialogMessage(), "You are logged in!",
                "El mensaje del diálogo no es el esperado");

        loginScreen.acceptDialog();
    }
}