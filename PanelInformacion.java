/**
 * CLASE PanelInformacion
 */

package TrabajoFinal2024;

import java.awt.*;
import javax.swing.*;

public class PanelInformacion extends JPanel {
    //Atributos
    private static JTextArea areaVisualizacionInformacion;
        private final JScrollPane barraDesplazamiento;
        
    private final JButton botonVolver;
    
    private final JSplitPane separadorCentral, separadorSur;
    
    private final Color negro = Color.BLACK, blanco = Color.WHITE;
    
    private final String texto = 
            "\n Información juego Choco UIB:\n\n"
            + " Esta aplicación ha sido realizada en el contexto de la asignatura Programación II del primer curso de los\n"
            + " estudios de Ingeniería Informática de la Universitat de les Illes Balears para el curso académico 2023-2024.\n"
            + " Representa la práctica que han de realizar los estudiantes de la asignatura mencionada. Los objetivos de\n"
            + " esta práctica pasan por trabajar con un entorno gráfico e interactivo utilizando las prestaciones que ofrecen\n"
            + " las librerías gráficas de Java y la aplicación de los conceptos de objetos y tipos de datos abstractos\n"
            + " correspondientes a la Programación Orientada a Objetos (POO).\n\n"
            + " Manual de usuario del juego:\n\n"
            + " Choco UIB es una variante del Tetris original. Tetris es un juego de puzzle clásico donde el jugador manipula\n"
            + " piezas geométricas que caen desde la parte superior de la pantalla para completar líneas horizontales y\n"
            + " evitar que las piezas se acumulen hasta llegar a la parte superior. En esta variante, el jugador tiene un\n"
            + " control más directo sobre la selección y colocación de las piezas que simulan ser onzas de chocolate,\n"
            + " utilizando el ratón para arrastrar y soltar las piezas en la posición deseada, es decir, no caen desde la\n"
            + " parte superior de la pantalla, la agarramos de la parte derecha de la pantalla. Además, hay un botón que\n"
            + " permite cambiar aleatoriamente entre las diferentes piezas disponibles y dos botones más para cambiar el\n"
            + " sentido de la pieza, uno para rotarla hacia la derecha y otro para rotarla hacia la izquierda. El objetivo\n"
            + " del juego es  completar tantas líneas como sea posible para acumular puntos y evitar que el área de juego\n"
            + " se llene por completo.\n\n"
            + " Reglas del juego:\n\n"
            + " Cuando una fila se completa sin espacios, esa línea desaparece y se obtienen puntos. Cuantas más líneas\n"
            + " completes simultáneamente, más puntos obtienes. Evite que las piezas lleguen a la parte superior de la\n"
            + " pantalla.\n\n"
            + " ¿Cómo funcionan los botones del juego?\n\n"
            + " Botón de nueva forma:\n\n"
            + " En lugar de tener todas las piezas visibles y seleccionables desde el principio,\n"
            + " este botón te permite cambiar aleatoriamente entre las diferentes piezas disponibles. Puede usar este botón\n"
            + " para explorar sus opciones y elegir la pieza que mejor se adapte a la situación actual en el juego. El botón\n"
            + " de cambio de pieza te brinda la oportunidad de adaptar tu estrategia en función de las piezas disponibles.\n"
            + " Puedes usarlo para buscar una pieza específica que necesites en ese momento o para tratar de planificar tus\n"
            + " movimientos futuros.\n\n"
            + " Botones de rotación de las piezas:\n\n"
            + " Después de seleccionar la pieza  que desee usar, puede rotarla manualmente pulsando el botón para rotar a\n"
            + " la derecha o el botón para rotar a la izquierda según la dirección en la que desees rotarla. Esto te\n"
            + " permite ajustar la orientación de la pieza antes de colocarla.\n\n"
            + " Dificultad del juego:\n\n"
            + " En Choco UIB el jugador puede escoger el tiempo que desee, la dificultad del juego puede aumentar con el\n"
            + " tiempo, lo que significa que cuanto menos segundos escoja el jugador menos tiempo tendrá para colocar las\n"
            + " piezas, requiriendo decisiones más rápidas y precisas.\n\n";
    
    //Método constructor
    public PanelInformacion(){
        //Layout del JPanel: Border
        setLayout(new BorderLayout());
        
        ////////MANIPULADOR DE EVENTOS GestorEventos
        GestorEventos gestorEventos = new GestorEventos();
        
        areaVisualizacionInformacion = new JTextArea(texto, 1, 1);
        areaVisualizacionInformacion.setEditable(false);
        areaVisualizacionInformacion.setFont(new Font("Arial", Font.BOLD, 25));
        barraDesplazamiento = new JScrollPane(areaVisualizacionInformacion);
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