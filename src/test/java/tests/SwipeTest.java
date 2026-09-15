package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SwipeScreen;
import util.tests.BaseMobileTest;

/**
 * Contiene los casos de prueba automatizados para validar el flujo de gestos de deslizamiento (Swipe)
 * horizontal en tarjetas y desplazamiento (Scroll) vertical.
 *
 * @author Alejandra Correa
 * @version 1.0
 */
public class SwipeTest extends BaseMobileTest {

    /**
     * Valida la interacción con la pantalla de Swipe:
     * <p>
     * 1. Desliza hacia la derecha y aserta que la tarjeta anterior quede oculta.<br>
     * 2. Desliza hasta la última tarjeta disponible y verifica que sea la única visible.<br>
     * 3. Realiza scroll vertical hacia abajo hasta hallar el mensaje secreto "You found me!!!" y valida su texto.
     * </p>
     */
    @Test(description = "El usuario puede swipear las cards hasta la última y encontrar el texto oculto swipeando vertical")
    public void swipeCardsAndFindHiddenText() {
        SwipeScreen swipeScreen = homeScreen.goToSwipe();
        Assert.assertTrue(swipeScreen.isDisplayed(),
                "La pantalla de Swipe debería mostrar al menos una card al entrar");

        String firstCardId = swipeScreen.getCurrentCardId();
        swipeScreen.swipeCardRight();
        Assert.assertTrue(swipeScreen.isOldCardHidden(firstCardId),
                "La card anterior debería quedar oculta tras el swipe");

        swipeScreen.swipeUntilLastCard(10);
        Assert.assertEquals(swipeScreen.countReadyCards(), 1,
                "Al llegar a la última card, debería ser la única visible en pantalla");

        swipeScreen.swipeUpUntilHiddenTextVisible(20);
        Assert.assertTrue(swipeScreen.isHiddenTextDisplayed(),
                "Después de swipear verticalmente debería aparecer el texto oculto");
        Assert.assertEquals(swipeScreen.getHiddenTextValue(), "You found me!!!",
                "El texto encontrado no coincide con el esperado");
    }
}