package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import screens.LoginScreen;
import util.data.RandomDataGenerator;
import util.tests.BaseMobileTest;

public class SignupTest extends BaseMobileTest {

    @Test(description = "Un usuario puede registrarse exitosamente con un email random")
    public void successfulSignup() {
        String email = RandomDataGenerator.generateRandomEmail();
        String password = RandomDataGenerator.defaultPassword();

        LoginScreen loginScreen = homeScreen.goToLogin();

        Assert.assertTrue(loginScreen.isSignUpFormDisplayed(),
                "El formulario de Sign up debería estar visible antes de completarlo");

        loginScreen.signUp(email, password);

        Assert.assertTrue(loginScreen.isConfirmationDialogDisplayed(),
                "Debería aparecer el diálogo nativo de confirmación tras un signup exitoso");
        Assert.assertEquals(loginScreen.getDialogTitle(), "Signed Up!",
                "El título del diálogo no es el esperado");
        Assert.assertEquals(loginScreen.getDialogMessage(), "You successfully signed up!",
                "El mensaje del diálogo no es el esperado");

        loginScreen.acceptDialog();
    }
}