package screens;

/**
 * Interfaz que define el contrato para la gestión de alertas y diálogos emergentes
 * (pop-ups/dialogs) dentro de la aplicación móvil.
 * <p>
 * Implementa el manejo estandarizado de ventanas de confirmación, avisos del sistema
 * o alertas nativas que interrumpen la navegación regular de las pantallas.
 * </p>
 *
 * @author Alejandra Correa
 * @version 1.0
 */
public interface AlertHandler {

    /**
     * Controla e interactúa con la alerta emergente desplegada en pantalla.
     * <p>
     * Se encarga de verificar la presencia del diálogo y llevar a cabo la acción
     * requerida para procesarlo (e.g. aceptar, descartar o cerrar la alerta).
     * </p>
     */
    void alertControl();
}