/**
 * CLASE Partida
 */

package TrabajoFinal2024;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelAreaJuego extends JPanel implements MouseMotionListener,MouseListener {
    //DECLARACIÓN DE ATRIBUTOS
    private boolean DESPLAZAMIENTO=false;
    public static Tablero tablero;   
    private static final int DESFASE_X=Tablero.DIMCASILLA-(Tablero.DIMCASILLA/2)-2,DESFASE_Y=Tablero.DIMCASILLA-(Tablero.DIMCASILLA/2)-2; //MUESTRAN LAS CORDENADAS DE LA CASILLA CENTRAL para agarrar la figura
    private static final int COORDENADA_X_INICIAL=Tablero.coordX,COORDENADA_Y_INICIAL=Tablero.coordY;  //COORDENADAS POSICION IMAGEN PARA EL RATON
    public static final int WIDTHCOMPONENT = Tablero.NUMCASILLAS*Tablero.DIMCASILLA+6*Tablero.DIMCASILLA, HEIGHTCOMPONENT = (Tablero.NUMCASILLAS*Tablero.DIMCASILLA)+40;
    public static int puntosCompletarFilaColumna = 1;
    
    //Método constructor
    public PanelAreaJuego(){
        //Layout del JPanel: Border
        setLayout(new BorderLayout());
        
        //Panel de visualización del tablero de casillas
        tablero = new Tablero();
        
        
        //asociación del gestor de eventos del ratón MouseListener al JPanel
        addMouseListener(this);
        addMouseMotionListener(this);
        //adición en el JFrame del tablero de casillas
        add(tablero,BorderLayout.CENTER);
        setSize(WIDTHCOMPONENT+100,HEIGHTCOMPONENT);
    }
    

    //Gestor del evento cuando el raton es arrastrado
    @Override
    public void mouseDragged(MouseEvent evento) {
        if (DESPLAZAMIENTO) {
            tablero.setCoordX(evento.getX());
            tablero.setCoordY(evento.getY());
            repaint(); 
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent evento) {
    }   
    
    @Override
    public void mouseClicked(MouseEvent evento) {}

    //Gestor del evento cuando el boton del raton es pulsado
    @Override
    public void mousePressed(MouseEvent evento) {  
        //obtención coordenadas cursor del ratón
        int x=evento.getX();                  
        int y=evento.getY(); 
        if ((!DESPLAZAMIENTO)&&(x+DESFASE_X>=COORDENADA_X_INICIAL)
                             &&(x+DESFASE_X<=COORDENADA_X_INICIAL+tablero.getDimensionCasilla())
                             &&(y+DESFASE_Y>=COORDENADA_Y_INICIAL)
                             &&(y+DESFASE_Y<=COORDENADA_Y_INICIAL+tablero.getDimensionCasilla())) {
            DESPLAZAMIENTO=true;
            tablero.setInsercion(false);
            tablero.setCoordX(COORDENADA_X_INICIAL);
            tablero.setCoordY(COORDENADA_Y_INICIAL);                
            repaint(); 
        }
        else {
            tablero.setCoordX(evento.getX());
            tablero.setCoordY(evento.getY()); 
        }
    }
    
    //Gestor del evento cuando el boton del raton es soltado
    @Override
    public void mouseReleased(MouseEvent evento) {
        if (DESPLAZAMIENTO) {
            //obtención coordenadas cursor del ratón
            int x=evento.getX();                  
            int y=evento.getY(); 
            DESPLAZAMIENTO=false;
            
            //      x+-DIMCASILLA/4 < 30*26 + 30
            if (x<tablero.getX(0, tablero.getNumCasillas()-1)+tablero.getDimensionCasilla()) {
                //bucle para identificar casilla del tablero sobre la que se ha soltado
                //el ratón y verificar si está libre o no para insertar o no la imagen
                for (int fila=0; fila <tablero.getNumCasillas(); fila++) {                     
                    for (int columna=0;columna<tablero.getNumCasillas();columna++) {                        
                        if ((x+tablero.getDesfaseX()>=tablero.getX(fila, columna))&&                   
                                (x+tablero.getDesfaseX()<tablero.getX(fila, columna)+tablero.getDimensionCasilla())&&                
                                (y+tablero.getDesfaseY()>=tablero.getY(fila, columna))&&                
                                (y+tablero.getDesfaseY()<tablero.getY(fila, columna)+tablero.getDimensionCasilla())) { 
                            
                            //VERIFICA SI TODAS LAS CASILLAS ESTAN OCUPADAS
                            boolean sePuedeColocar = true;
                            for (int i = -1; i < 2; i++) {
                                for (int j = -1; j < 2; j++) {
                                    //verificar si la casilla está ocupada o no
                                    try{
                                        if (((tablero.getEstadoCasilla(fila+i, columna+j))&&(tablero.getImagen(j+1, i+1)!=null))) { //Si no ocupada O la imagen del 3x3 no esta vacia, se puede colocar
                                            sePuedeColocar = false;
                                        }
                                    }catch(ArrayIndexOutOfBoundsException e){
                                        //Si hay un error de array al colocar la figura en un borde, no hacemos nada y vuelve al centro
                                    }
                                }
                            }
                            if(sePuedeColocar){
                                for (int i = -1; i < 2; i++) {
                                    for (int j = -1; j < 2; j++) {
                                        tablero.cambiarEstadoCasilla(fila+i, columna+j);
                                        tablero.cambiarImagenCasilla(fila+i, columna+j, j+1, i+1);
                                    }
                                }
                                tablero.setInsercion(true);
                                Tablero.seleccionAleatoriaImagen();
                            }
                            
                            //Verificamos si alguna fila o alguna columna esta llena de imagenes
                            int [] posFila = new int [3], posColumna = new int [3];
                            posFila[0]=-1;
                            posColumna[0]=-1;
                            int numeroFilasAVaciar = 0, numeroColumnasAVaciar = 0;
                            for (int i = 2; i < tablero.getNumCasillas()-2; i++) {
                                boolean filaLlena = true;
                                boolean ColumnaLlena = true;
                                for (int j = 2; j < tablero.getNumCasillas()-2; j++) {
                                    if(!tablero.getEstadoCasilla(i, j)){
                                        filaLlena = false;
                                    }
                                    if(!tablero.getEstadoCasilla(j, i)){
                                        ColumnaLlena = false;
                                    }
                                }
                                //Cuando acaba de mirar la fila, si esta llena, la vacia
                                if(filaLlena){
                                    posFila[numeroFilasAVaciar] = i;
                                    numeroFilasAVaciar++;
                                }
                                if(ColumnaLlena){
                                    posColumna[numeroColumnasAVaciar] = i;
                                    numeroColumnasAVaciar++;
                                }
                            }
                            if(posFila[0]!=-1){
                                for (int i = 0; i < posFila.length; i++) {
                                    if(posFila[i]!=0){
                                        tablero.borrarFila(posFila[i]);
                                        VentanaTF.cambiarPuntuacion(VentanaTF.puntos+puntosCompletarFilaColumna);
                                    }
                                }
                            }
                            if(posColumna[0]!=-1){
                                for (int i = 0; i < posColumna.length; i++) {
                                    if(posColumna[i]!=0){
                                        tablero.borrarColumna(posColumna[i]);
                                        VentanaTF.cambiarPuntuacion(VentanaTF.puntos+puntosCompletarFilaColumna);
                                    }
                                }
                            }
                        }                                                      
                    }
                }
            } 
            tablero.setCoordX(COORDENADA_X_INICIAL);
            tablero.setCoordY(COORDENADA_Y_INICIAL);
            repaint();  
        }
    }

    @Override
    public void mouseEntered(MouseEvent evento) {}

    @Override
    public void mouseExited(MouseEvent evento) {}
    
    public static void resetearCoordenadasFiguraCentral(){
        tablero.setCoordX(COORDENADA_X_INICIAL);
        tablero.setCoordY(COORDENADA_Y_INICIAL);
    }
}
