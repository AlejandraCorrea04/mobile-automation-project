package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import screens.*;
import util.tests.BaseMobileTest;

/**
 * Contiene los casos de prueba automatizados para verificar la navegación principal del menú inferior (Bottom Menu Bar).
 *
 * @author Alejandra Correa
 * @version 1.0
 */
public class NavigationTest extends BaseMobileTest {

    /**
     * Valida la navegación secuencial a través de todas las secciones del menú inferior
     * (Home, Webview, Login, Forms, Swipe y Drag), asertando que cada pantalla cargue y despliegue sus elementos correctamente.
     */
    @Test(description = "El usuario puede navegar por todas las secciones de la barra inferior y cada una carga correctamente")
    public void navigateThroughBottomMenuBar(){
        Assert.assertTrue(homeScreen.isHomeScreenDisplayed(),"Deberia empezar en la pantalla Home");

        WebviewScreen webviewScreen = homeScreen.goToWebview();
        Assert.assertTrue(webviewScreen.isDisplayed(),"La seccion webview deberia mostrar en Webview cargado");

        LoginScreen loginScreen = homeScreen.goToLogin();
        Assert.assertTrue(loginScreen.isDisplayed(),"La seccion login deberia estar visible");

        FormsScreen formsScreen = homeScreen.goToForms();
        Assert.assertTrue(formsScreen.isDisplayed(),"La seccion Forms deberia estar visible");

        SwipeScreen swipeScreen = homeScreen.goToSwipe();
        Assert.assertTrue(swipeScreen.isDisplayed(),"La seccion Swipe deberia mostrar al menos una card");

        DragScreen dragScreen = homeScreen.goToDrag();
        Assert.assertTrue(dragScreen.isDisplayed(), "La seccion Drag deberia estar visible");
    }
}