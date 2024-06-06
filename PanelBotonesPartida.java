/**
 * CLASE JPanel PanelJuego
 * 
 * Incluye arriba a la izquierda el juego, arriba a la derecha los botones y
 * abajo el nombre del usuario y su puntuación
 */

package TrabajoFinal2024;

import java.awt.*;
import javax.swing.*;

public class PanelBotonesPartida extends JPanel{
    //Atributos
    public static PanelAreaJuego partida;

    private final JPanel panelBotones;
        private final JButton [] botones;
        private final String [] literalesIconos = {"iconos/iconoBotonRotarL.png", "iconos/iconoBotonRotarR.png", "iconos/iconoBotonNuevaForma.png"};
        private final String [] literalesBotones = {"ROTAR IZQUIERDA", "ROTAR DERECHA", "NUEVA FORMA"};
    
    private final JPanel panelInformacionPartida;
        public static JLabel literalNombreUsuario, literalPuntuacion;
        public static JTextField nombreUsuario, puntuacion;
        
    private final JSplitPane separadorSur, separadorEste;
    
    //Declaraciones privadas del JFrame
    private final Color negro = Color.BLACK, blanco = Color.WHITE;
    private final int TAMAÑOLITERALES = 30;
    
    //Método constructor
    public PanelBotonesPartida(){
        //Layout del JPanel: Border
        setLayout(new BorderLayout());
        
        ////////MANIPULADOR DE EVENTOS GestorEventos
        GestorEventos gestorEventos = new GestorEventos();
        
        
        ////////////////////////////////////////////////////////////////////////
        //Declaracion JPanel PanelBotonesPartida
        partida = new PanelAreaJuego();
        
        
        ////////////////////////////////////////////////////////////////////////
        //Declaracion JPanel panelBotones
        panelBotones = new JPanel();
        //Layout del JPanel: Grid
        panelBotones.setLayout(new GridLayout(literalesIconos.length,1));

            //Declaraciones Componente JButton botones
            botones = new JButton[literalesIconos.length];

            for (int i = 0; i < literalesIconos.length; i++) {
                botones[i] = new JButton(new ImageIcon(literalesIconos[i]));
                botones[i].setActionCommand(literalesBotones[i]);
                botones[i].setBackground(blanco);
                botones[i].setForeground(negro);
                botones[i].addActionListener(gestorEventos);

                //Añadimos los JButton al JPanel
                panelBotones.add(botones[i]);
            }
        
        
        ////////////////////////////////////////////////////////////////////////
        //Declaracion JPanel panelInformacionPartida
        panelInformacionPartida = new JPanel();
        //Layout del JPanel: Grid
        panelInformacionPartida.setLayout(new GridLayout(1,4));
            
            literalNombreUsuario = new JLabel("                    JUGADOR:");
            literalNombreUsuario.setForeground(negro);
            literalNombreUsuario.setFont(new Font("Arial",Font.BOLD,TAMAÑOLITERALES));
            nombreUsuario = new JTextField(VentanaTF.usuario);
            nombreUsuario.setEditable(false);
            nombreUsuario.setForeground(negro);
            nombreUsuario.setFont(new Font("Arial",Font.BOLD,TAMAÑOLITERALES));
            
            literalPuntuacion = new JLabel("                       PUNTOS:");
            literalPuntuacion.setForeground(negro);
            literalPuntuacion.setFont(new Font("Arial",Font.BOLD,TAMAÑOLITERALES));
            puntuacion = new JTextField(VentanaTF.puntos);
            puntuacion.setEditable(false);
            puntuacion.setForeground(negro);
            puntuacion.setFont(new Font("Arial",Font.BOLD,TAMAÑOLITERALES));
            
        panelInformacionPartida.add(literalNombreUsuario);
        panelInformacionPartida.add(nombreUsuario);
        panelInformacionPartida.add(literalPuntuacion);
        panelInformacionPartida.add(puntuacion);
        
        
        ////////////////////////////////////////////////////////////////////////
        ////Declaracion Componentes JSplitPane
        separadorSur = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        separadorEste = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        
        separadorSur.setEnabled(false);
        separadorEste.setEnabled(false);
        
        separadorEste.setRightComponent(panelBotones);
        
        separadorSur.setBottomComponent(panelInformacionPartida);
        
        add(partida);
        add(separadorEste, BorderLayout.EAST);
        add(separadorSur, BorderLayout.SOUTH);
    }
}
