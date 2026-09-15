package util.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import screens.HomeScreen;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * Clase base abstracta para la ejecución de pruebas automatizadas en dispositivos móviles.
 * <p>
 * Se encarga de la configuración inicial del entorno de Appium, instanciación del {@link AndroidDriver},
 * manejo del ciclo de vida de la aplicación y la posterior liberación de recursos.
 * </p>
 *
 * @author Alejandra Correa
 * @version 1.0
 */
public abstract class BaseMobileTest {

    /**
     * Instancia compartida del controlador de Appium para interacciones en Android.
     */
    protected static AndroidDriver driver;

    /**
     * Instancia de la pantalla principal (Home) para el inicio de navegación en cada suite.
     */
    protected static HomeScreen homeScreen;

    /**
     * Logger de SLF4J para el seguimiento de eventos en las clases de prueba.
     */
    protected static final Logger log = LoggerFactory.getLogger(BaseMobileTest.class);

    /**
     * URL por defecto del servidor local de Appium.
     */
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";

    /**
     * Nombre del paquete de la aplicación Android (Package Name).
     */
    private static final String APP_PACKAGE = "com.wdiodemoapp";

    /**
     * Nombre de la actividad principal de la aplicación (Activity Name).
     */
    private static final String APP_ACTIVITY = "com.wdiodemoapp.MainActivity";

    /**
     * Método de configuración inicial de TestNG ejecutado antes de la clase de prueba.
     * Prepara el entorno del driver e inicializa el objeto {@link HomeScreen}.
     */
    @BeforeClass
    public void setUpStartApp() {
        environmentSetUp();
        homeScreen = new HomeScreen(driver);
        log.info("Sesion de Appium iniciada y HomeScreen cargado correctamente");
    }

    /**
     * Configura las opciones de UiAutomator2 y establece la conexión con el servidor de Appium.
     * <p>
     * Reinicia el estado de la aplicación mediante la terminación y posterior activación del proceso.
     * </p>

     * @throws RuntimeException Si la URL del servidor Appium tiene un formato inválido.
     */
    protected void environmentSetUp() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("emulator-5554")
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY)
                .setAutoGrantPermissions(true)
                .setNoReset(true)
                .setNewCommandTimeout(Duration.ofSeconds(120));

        try {
            driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), options);
            driver.terminateApp(APP_PACKAGE);
            driver.activateApp(APP_PACKAGE);
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL del servidor de Appium invalida: " + APPIUM_SERVER_URL, e);
        }
    }

    /**
     * Método de limpieza final de TestNG ejecutado al concluir las pruebas de la clase.
     * Finaliza la sesión del driver y libera los recursos del dispositivo móvil.
     */
    @AfterClass
    public void mobileApplicationEnd() {
        if (driver != null) {
            driver.quit();
            log.info("Sesion de appium cerrada correctamente");
        }
    }

    /**
     * Obtiene la instancia activa del driver de Android.
     *
     * @return El {@link AndroidDriver} en uso.
     */
    public AndroidDriver getDriver() {
        return driver;
    }
}