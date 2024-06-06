/*
EXEPCIÓN NO PREDEFINIDA
 */

package TrabajoFinal2024;

public class EntradaIncorrecta extends Exception {
    public EntradaIncorrecta (String mensaje) {
        super("¡¡¡¡ "+mensaje+" !!!!");
    }
}
