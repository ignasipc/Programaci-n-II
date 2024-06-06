/**
 * CLASE VentanaTF
*/

package TrabajoFinal2024;

import java.awt.*;
import javax.swing.*;

class VentanaTF extends JFrame{    
    //Declaraciones componentes del JFrame
    private final JMenuBar barraMenu;
        private final JMenu menu;
            private final JMenuItem [] itemsMenu;
            
    private final JToolBar iconosMenu;
        private final JButton [] iconosBotones;
        private final String [] literalesIconos = {"iconos/iconoNuevaPartida.jpg", "iconos/iconoConfiguracion.jpg", 
            "iconos/iconoHistorial.jpg","iconos/iconoHistorialSelectivo.jpg", "iconos/iconoInformacion.jpg", "iconos/iconoSalir.jpg",};
    
    private final String [] literalesBotones = {"NUEVA PARTIDA", "CONFIGURACIÓN", "HISTORIAL", "HISTORIAL SELECTIVO", "INFORMACIÓN", "SALIR"};
    private final int numeroSelecciones = literalesBotones.length;
    
    private final JSplitPane separadorNorte;
    
    public static JPanel panelVisualizaciones;
        private final PanelInicio panelInicio;
        private final PanelBarraTemporalPartida panelPartida;
        private final PanelHistorial panelHistorial;
        private final PanelInformacion panelInformacion;
    
    
    //Declaraciones privadas del JFrame
    private final Color negro = Color.BLACK, blanco = Color.WHITE;
    
    
    //Declaraciones publicas del JFrame
    public static boolean juegoActivado = false;
    public static String usuario = null;
    public static int puntos = 0;
    
    
    //Metodo Constructor
    public VentanaTF(){
        //Declaracion del JFrame ventanaTF
        super("PRÁCTICA PROGRAMACIÓN II - 2023-2024 - UIB");
        //Layout del JFrame: Border
        setLayout(new BorderLayout());
        
        ////////MANIPULADOR DE EVENTOS GestorEventos
        GestorEventos gestorEventos = new GestorEventos();
        
        
        ////////////////////////////////////////////////////////////////////////
        ////Declaracion Componente JMenuBar barraMenu IG_25
        barraMenu=new JMenuBar();
        barraMenu.setBackground(blanco);
        barraMenu.setForeground(negro);
        
        //Declaracion Componente JMenu menu
        menu = new JMenu("MENÚ");
        menu.setFont(new Font("Arial",Font.BOLD,20));
        
            //Declaraciones Componentes JMenuItem
            itemsMenu = new JMenuItem[numeroSelecciones];
            
            for (int i = 0; i < numeroSelecciones; i++) {
                itemsMenu[i] = new JMenuItem(literalesBotones[i]);
                itemsMenu[i].setBackground(blanco);
                itemsMenu[i].setForeground(negro);
                itemsMenu[i].addActionListener(gestorEventos);
                itemsMenu[i].setFont(new Font("Arial",Font.BOLD,15));
                
                //Añadimos los JMenuItems al JMenu
                menu.add(itemsMenu[i]);
            }
            
        //Añadimos el JMenu al JMenuBar
        barraMenu.add(menu);
        setJMenuBar(barraMenu);
        
        
        ////////////////////////////////////////////////////////////////////////
        ////Declaracion Componente JToolBar iconosMenu IG_24
        iconosMenu = new JToolBar();
        iconosMenu.setBorder(BorderFactory.createLineBorder(blanco));
        iconosMenu.setBackground(blanco);
        iconosMenu.setForeground(negro);
        
            //Declaraciones Componente JButton iconosBotones
            iconosBotones = new JButton[numeroSelecciones];
            
            for (int i = 0; i < numeroSelecciones; i++) {
                iconosBotones[i] = new JButton(new ImageIcon(literalesIconos[i]));
                iconosBotones[i].setActionCommand(literalesBotones[i]);
                iconosBotones[i].setBackground(blanco);
                iconosBotones[i].setForeground(negro);
                iconosBotones[i].addActionListener(gestorEventos);
                
                //Añadimos los JButton al JToolBar
                iconosMenu.add(iconosBotones[i]);
            }
        
        //Añadimos el JToolBar a la parte Norte del JFrame
        add(iconosMenu,BorderLayout.NORTH);
        
        
        ////////////////////////////////////////////////////////////////////////
        ////Declaracion Componente JPanel panelVisualizacion
        panelVisualizaciones = new JPanel();
        //Layout del JPanel: Card
        panelVisualizaciones.setLayout(new CardLayout());
        
        
        ////////////////////////////////////////////////////////////////////////
        ////Declaracion Componente JPanel PanelInicio
        panelInicio = new PanelInicio();
        
        //Introduccion del JPanel panelInicio en el Contenedor panelVisualizacion
        panelVisualizaciones.add(panelInicio, "Panel Inicio");
        
        
        ////////////////////////////////////////////////////////////////////////
        ////Declaracion Componente JPanel PanelInicio
        panelPartida = new PanelBarraTemporalPartida();
        
        //Introduccion del JPanel panelInicio en el Contenedor panelVisualizacion
        panelVisualizaciones.add(panelPartida, "Panel Partida");
        
        
        ////////////////////////////////////////////////////////////////////////
        ////Declaracion Componente JPanel PanelHistorial
        panelHistorial = new PanelHistorial();
        
        //Introduccion del JPanel panelInicio en el Contenedor panelVisualizacion
        panelVisualizaciones.add(panelHistorial, "Panel Historial");
        
        
        ////////////////////////////////////////////////////////////////////////
        ////Declaracion Componente JPanel PanelInformacion
        panelInformacion = new PanelInformacion();
        
        //Introduccion del JPanel panelInicio en el Contenedor panelVisualizacion
        panelVisualizaciones.add(panelInformacion, "Panel Informacion");
        
        
        ////////////////////////////////////////////////////////////////////////
        ////Declaracion Componente JSplitPane
        separadorNorte = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        
        separadorNorte.setEnabled(false);
        
        separadorNorte.setTopComponent(iconosMenu);
        separadorNorte.setBottomComponent(panelVisualizaciones);

        add(separadorNorte, BorderLayout.CENTER);
        
        
        //Declaraciones del JFrame Ventana
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    
    //Método para cambiar el nombre de usuario
    public static void cambiarUsuario(String nuevoUsuario){
        usuario = nuevoUsuario;
        PanelBotonesPartida.nombreUsuario.setText(usuario);
    }
    
    //Método para cambiar la puntuacion al iniciar la partida
    public static void cambiarPuntuacion(int nuevaPuntuacion){
        puntos = nuevaPuntuacion;
        PanelBotonesPartida.puntuacion.setText(String.valueOf(puntos));
    }
}
