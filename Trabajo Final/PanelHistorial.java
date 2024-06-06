/**
 * CLASE PanelHistorial
 */

package TrabajoFinal2024;

import java.awt.*;
import javax.swing.*;

public class PanelHistorial extends JPanel{
    //Atributos
    public static JTextArea areaVisualizacionHistorial;
        private final JScrollPane barraDesplazamiento;
        
    private final JButton botonVolver;
    
    private final JSplitPane separadorCentral, separadorSur;
    
    private final Color negro = Color.BLACK, blanco = Color.WHITE;

    //Método constructor
    public PanelHistorial(){
        //Layout del JPanel: Border
        setLayout(new BorderLayout());
        
        ////////MANIPULADOR DE EVENTOS GestorEventos
        GestorEventos gestorEventos = new GestorEventos();
        
        areaVisualizacionHistorial = new JTextArea("", 1, 1);
        areaVisualizacionHistorial.setEditable(false);
        areaVisualizacionHistorial.setFont(new Font("Monospaced", Font.BOLD, 16));
        barraDesplazamiento = new JScrollPane(areaVisualizacionHistorial);
        add(barraDesplazamiento);
        
        
        botonVolver = new JButton("VOLVER AL MENÚ PRINCIPAL");
        botonVolver.setBackground(blanco);
        botonVolver.setForeground(negro);
        botonVolver.setFont(new Font("Arial",Font.BOLD,26));
        botonVolver.addActionListener(gestorEventos);
        
        
        ////////////////////////////////////////////////////////////////////////
        ////Declaracion Componentes JSplitPane
        
        separadorCentral = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        separadorSur = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        
        separadorCentral.setEnabled(false);
        separadorSur.setEnabled(false);
        
        separadorCentral.setTopComponent(barraDesplazamiento);
        separadorSur.setTopComponent(botonVolver);
        
        add(separadorCentral, BorderLayout.CENTER);
        add(separadorSur,BorderLayout.SOUTH);
    }
}
