package util.data;

import java.util.UUID;

/**
 * Clase de utilidad encargada de la generación de datos aleatorios y valores por defecto
 * para garantizar la independencia y repetibilidad de los casos de prueba.
 *
 * @author Alejandra Correa
 * @version 1.0
 */
public class RandomDataGenerator {

    /**
     * Genera un correo electrónico dinámico y único utilizando un identificador UUID parcial.
     *
     * @return Cadena de texto con el formato de correo generado (e.g. {@code wdio.test.a1b2c3d4@mailinator.com}).
     */
    public static String generateRandomEmail(){
        String uniqueId = UUID.randomUUID().toString().substring(0,8);
        return "wdio.test."+uniqueId+"@mailinator.com";
    }

    /**
     * Proporciona una contraseña estándar para las pruebas de autenticación.
     *
     * @return Cadena de texto con la contraseña por defecto {@code "Test1234!"}.
     */
    public static String defaultPassword(){
        return "Test1234!";
    }
}