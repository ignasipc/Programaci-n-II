/*
CLASE BarraProgresionTemporal
 */

package TrabajoFinal2024;
import java.awt.*;
import javax.swing.JProgressBar;

public class BarraProgresionTemporal extends JProgressBar {
    private int valorMinimo=0;
    private int valorMaximo=100;
    private final int ANCHO_BARRA=40;
    // Convertir el color marrón de formato hexadecimal a Color
    private final Color marron = new Color(123, 68, 16);

    //Método Constructor
    public BarraProgresionTemporal(int dimension) {
        super();
        //ASIGNACIÓN VALORES MÍNIMO Y MÁXIMO A LA JProgressBar barraTemporal
        setMinimum(valorMinimo);
        setMaximum(valorMaximo);
        //ASIGNACIÓN VALOR INICIAL A LA JProgressBar barraTemporal
        setValue(0);
        //ACTIVACIÓN VISUALIZACIÓN VALOR EN LA JProgressBar barraTemporal
        setStringPainted(true);
        //DIMENSIONAMIENTO JProgressBar barraTemporal
        setPreferredSize(new Dimension(dimension,ANCHO_BARRA));
        setFont(new Font("Arial",Font.BOLD,23));
        //ASIGNACIÓN COLORES DE FONDO Y TRAZADO JProgressBar barraTemporal
        setForeground(marron);
        setBackground(Color.WHITE);
    }
    
    //Métodos Get's y Set's
    public int getValorMaximo() {
        return valorMaximo;
    }
    
    public void setValorMaximo(int valor) {
        valorMaximo=valor;
        setMaximum(valorMaximo);
    }
    
    public int getValorMinimo() {
        return valorMinimo;
    }
    
    public void setValorMinimo(int valor) {
        valorMinimo=valor;
        setMinimum(valorMinimo);
    }
    
    public void setValorBarraTemporal(int valor) {
        setValue(valor);
    }
    
    public int getValorBarraTemporal() {
        return getValue();
    }
}