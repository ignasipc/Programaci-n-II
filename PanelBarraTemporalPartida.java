/**
 * CLASE JPanel PanelPartida
 * 
 * Incluye arriba el PanelJuego y abajo la Barra de prograsión temporal, separadas
 * por una barra de separación
 */

package TrabajoFinal2024;

import java.awt.*;
import javax.swing.*;

public class PanelBarraTemporalPartida extends JPanel {
    //Atributos
    private final PanelBotonesPartida panelBotonesJuego;
    private final JSplitPane separadorCentral;
    public static BarraProgresionTemporal barraTemporal;
        public static Timer cronometro;
        private static final int DIMENSION_BARRA=100;
        private static int tiempoActual = 30;
        
    private static GestorEventos gestorEventos;
    
    //Metodo Constructor
    public PanelBarraTemporalPartida(){
        //Layout del JPanel: Border
        setLayout(new BorderLayout());
        
        ////////MANIPULADOR DE EVENTOS GestorEventos
        gestorEventos = new GestorEventos();
        
        
        ////////////////////////////////////////////////////////////////////////
        //Declaracion JPanel PanelBotonesPartida
        panelBotonesJuego = new PanelBotonesPartida();
        
        
        ////////////////////////////////////////////////////////////////////////
        //Declaracion JProgressBar barraTemporal
        barraTemporal = new BarraProgresionTemporal(DIMENSION_BARRA);
        //ASIGNACIÓN VALORES MÍNIMO Y MÁXIMO A LA JProgressBar barraTemporal
        barraTemporal.setValorMinimo(0);
        barraTemporal.setValorMaximo(100);
        //ASIGNACIÓN VALOR INICIAL A LA JProgressBar barraTemporal
        barraTemporal.setValorBarraTemporal(0);
        
        
        ////////////////////////////////////////////////////////////////////////
        //Declaracion Timer cronometro
        cronometro = new Timer(tiempoActual*10,gestorEventos);
        cronometro.setActionCommand("CRONOMETRO");
        
        
        ////////////////////////////////////////////////////////////////////////
        ////Declaracion Componente JSplitPane separadorCentral
        separadorCentral = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        
        separadorCentral.setEnabled(false);
        
        separadorCentral.setBottomComponent(barraTemporal);
        
        add(panelBotonesJuego,BorderLayout.CENTER);
        add(separadorCentral, BorderLayout.SOUTH);
    }
    
    //Método para cambiar el tiempo de partida
    public static void setTiempo(int nuevoTiempo){
        tiempoActual = nuevoTiempo;
        cronometro = new Timer(nuevoTiempo*10,gestorEventos);
        cronometro.setActionCommand("CRONOMETRO");
    }
    
    //Método que devuelve el tiempo de partida actual
    public static int getTiempoActual(){
        return tiempoActual;
    }
}