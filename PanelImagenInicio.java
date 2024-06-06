/**
 * CLASE JPanel PanelImagen
 */

package TrabajoFinal2024;

import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelImagenInicio extends JPanel {
    //Atributo
    private BufferedImage imagen=null;
    
    //Método constructor
    public PanelImagenInicio(String nombre) {
        try {
            imagen = ImageIO.read(new File(nombre));
        } catch (IOException e) {
            System.err.println("IMAGEN NO ENCONTRADA");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(imagen,null,0,0);
    }

    //La componente decide su tamaño en función de la dimensión de
    //imagen
    @Override
    public Dimension getPreferredSize() {
        if (imagen == null) {
            return new Dimension(200, 200);
        } else {
            return new Dimension(imagen.getWidth(), imagen.getHeight());
        }
    }
}