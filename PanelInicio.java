/**
 * CLASE PanelInicio
 */

package TrabajoFinal2024;

import java.awt.*;
import javax.swing.*;

public class PanelInicio extends JPanel{
    //Atributos
    private final JPanel panelBotones;
        private final JButton [] botones;
            private final String [] literalesBotones = {"NUEVA PARTIDA", "CONFIGURACIÓN", "HISTORIAL", "HISTORIAL SELECTIVO", "INFORMACIÓN", "SALIR"};
            private final int numeroSelecciones = literalesBotones.length;

        private final JSplitPane separadorCentral, separadorSur;
        
        private final PanelImagenInicio panelImagenInicio;
    
    private final Color negro = Color.BLACK, blanco = Color.WHITE;
    
    //Metodo constructor
    public PanelInicio(){
        //Layout del JPanel: Border
        setLayout(new BorderLayout());
        
        ////////MANIPULADOR DE EVENTOS GestorEventos
        GestorEventos gestorEventos = new GestorEventos();
        
        
        ////////////////////////////////////////////////////////////////////////
        ////Declaracion Componente JPanel panelBotones                          //Set FONT
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2,numeroSelecciones-1));
        
            botones = new JButton[numeroSelecciones];
            
            for (int i = 0; i < numeroSelecciones; i++) {
                botones[i] = new JButton(literalesBotones[i]);
                botones[i].setBackground(blanco);
                botones[i].setForeground(negro);
                botones[i].addActionListener(gestorEventos);
                botones[i].setFont(new Font("Arial",Font.BOLD,26));
                
                //Añadimos los JButton al JPanel
                panelBotones.add(botones[i]);
            }
            
            
        ////////////////////////////////////////////////////////////////////////
        //Declaracion JPanel PanelImagenInicio graphics2D_Imagenes_v5_1
        panelImagenInicio = new PanelImagenInicio("imagenes/tableta.png");
        
        
        ////////////////////////////////////////////////////////////////////////
        ////Declaracion Componentes JSplitPane
        
        separadorCentral = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        separadorSur = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        
        separadorCentral.setEnabled(false);
        separadorSur.setEnabled(false);
        
        separadorCentral.setTopComponent(panelImagenInicio);
        separadorSur.setTopComponent(panelBotones);
        
        add(separadorCentral, BorderLayout.CENTER);
        add(separadorSur,BorderLayout.SOUTH);
    }
}