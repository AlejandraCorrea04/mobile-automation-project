package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import screens.LoginScreen;
import util.data.RandomDataGenerator;
import util.tests.BaseMobileTest;

public class LoginTest extends BaseMobileTest {

    @Test(description = "Un usuario previamente registrado puede loguearse exitosamente")
    public void successfulLogin() {
        String email = RandomDataGenerator.generateRandomEmail();
        String password = RandomDataGenerator.defaultPassword();

        LoginScreen loginScreen = homeScreen.goToLogin();
        loginScreen.signUp(email,password);

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
