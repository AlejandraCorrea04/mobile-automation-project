package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import screens.LoginScreen;
import util.data.RandomDataGenerator;
import util.tests.BaseMobileTest;

/**
 * Contiene los casos de prueba automatizados para verificar el proceso de registro de usuarios (Sign Up).
 *
 * @author Alejandra Correa
 * @version 1.0
 */
public class SignupTest extends BaseMobileTest {

    /**
     * Valida que un nuevo usuario pueda completarse el registro (Sign Up) de forma exitosa usando un correo aleatorio.
     * <p>
     * Navega a la vista de registro, diligencia el formulario con datos generados dinámicamente
     * y aserta que aparezca el diálogo emergente de confirmación con los mensajes "Signed Up!"
     * y "You successfully signed up!".
     * </p>
     */
    @Test(description = "Un usuario puede registrarse exitosamente con un email random")
    public void successfulSignup() {
        String email = RandomDataGenerator.generateRandomEmail();
        String password = RandomDataGenerator.defaultPassword();

        LoginScreen loginScreen = homeScreen.goToLogin();
        Assert.assertTrue(loginScreen.isDisplayed(),
                "Precondicion: el usuario deberia estar en la seccion Login");

        loginScreen.goToSignUpTab();
        Assert.assertTrue(loginScreen.isSignUpFormDisplayed(),
                "El formulario de Sign up deberia estar visible tras navegar a la seccion");

        loginScreen.fillSignUpForm(email, password);
        loginScreen.tapSignUpButton();

        Assert.assertTrue(loginScreen.isConfirmationDialogDisplayed(),
                "Deberia aparecer el dialogo nativo de confirmacion tras un signup exitoso");
        Assert.assertEquals(loginScreen.getDialogTitle(), "Signed Up!",
                "El titulo del dialogo no es el esperado");
        Assert.assertEquals(loginScreen.getDialogMessage(), "You successfully signed up!",
                "El mensaje del dialogo no es el esperado");

        loginScreen.acceptDialog();
    }
}