/*
CLASE Tablero de casillas
*/

package TrabajoFinal2024;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class Tablero extends JComponent {
    //DECLARACIÓN DE ATRIBUTOS
    public static int NUMIMAGENESFIGURA;
    public static final int NUMCASILLAS=28;
    public static final int DIMCASILLA=30;
    private static final int NUMCASILLASMARGEN=2;
    private static final int DESFASE_X=0,DESFASE_Y=0;                        //Para coordenadas colocacion de la figura
    private static final int DESFASE_CURSOR_X=-14,DESFASE_CURSOR_Y=-15;
    private static final Image imagenNoOcupada = Toolkit.getDefaultToolkit().getImage("imagenes/LIBRE.jpg");//CASILLA BLANCA
    private static Image imagenes = Toolkit.getDefaultToolkit().getImage("imagenes/CHOCOLATE.jpg");
    
    private static Image [][] imagen3x3 = new Image[3][3];
    
    private static final Casilla [][] casillas=new Casilla[NUMCASILLAS][NUMCASILLAS];
    public static int coordX = (PanelAreaJuego.WIDTHCOMPONENT)-(1*DIMCASILLA), coordY = (PanelAreaJuego.HEIGHTCOMPONENT/2)+(DIMCASILLA/2);    //COLOCACIÓN DE LA IMAGEN (Figura)
    private boolean INSERCION=false;

    //MÉTODO CONSTRUCTOR
    public Tablero() {
        for (int fila=0; fila <NUMCASILLAS; fila++) {
                int x=0;
                int y=fila*DIMCASILLA;
            for (int columna=0;columna<NUMCASILLAS;columna++) {
                if((fila<NUMCASILLASMARGEN || fila >= NUMCASILLAS-NUMCASILLASMARGEN)||(columna<NUMCASILLASMARGEN || columna >= NUMCASILLAS-NUMCASILLASMARGEN)){
                    casillas[fila][columna]=new Casilla(x,y,null,true);
                }else{
                    casillas[fila][columna]=new Casilla(x,y,null,false);
                }
                x=x+DIMCASILLA;
            }
        }
        Random vacio = new Random();
        NUMIMAGENESFIGURA = 0;
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                if (vacio.nextBoolean()){
                    imagen3x3 [k][l] = imagenes;
                }else{
                    imagen3x3 [k][l] = null;
                    NUMIMAGENESFIGURA++;
                }
            }
        }
        if(NUMIMAGENESFIGURA==9){
            seleccionAleatoriaImagen();
        }
    }
    
    //MÉTODO paintComponent asociada al objeto Tablero
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        //visualización de la imagen de todas las casillas del tablero y de la casilla en la que
        //el cursor del ratón está
        for (int fila=NUMCASILLASMARGEN; fila <NUMCASILLAS-NUMCASILLASMARGEN; fila++) {
            for (int columna=NUMCASILLASMARGEN;columna<NUMCASILLAS-NUMCASILLASMARGEN;columna++) {
                if (casillas[fila][columna].estado()) {                         //Si esta ocupada
                    g2.drawImage(casillas[fila][columna].getImagen(), casillas[fila][columna].getX(), casillas[fila][columna].getY(),this);  //Dibujo de la casilla
                }
                else {
                    g2.drawImage(imagenNoOcupada, casillas[fila][columna].getX(), casillas[fila][columna].getY(),this); //Casilla blanca
                }
            }
        }
        //dibujo de las lineas del tablero
        g2.setColor(Color.BLACK);            
        for (int fila=NUMCASILLASMARGEN; fila <NUMCASILLAS-NUMCASILLASMARGEN; fila++) {
            g2.drawLine(DIMCASILLA*NUMCASILLASMARGEN, casillas[fila][NUMCASILLASMARGEN].getY(),NUMCASILLAS*DIMCASILLA-DIMCASILLA*NUMCASILLASMARGEN, casillas[fila][NUMCASILLASMARGEN].getY()); 
        }
        g2.drawLine(DIMCASILLA*NUMCASILLASMARGEN, casillas[NUMCASILLAS-NUMCASILLASMARGEN][NUMCASILLASMARGEN].getY(),
                NUMCASILLAS*DIMCASILLA-DIMCASILLA*NUMCASILLASMARGEN, casillas[NUMCASILLAS-NUMCASILLASMARGEN][NUMCASILLASMARGEN].getY()); //Linea Abajo

        for (int columna=NUMCASILLASMARGEN;columna<NUMCASILLAS-NUMCASILLASMARGEN;columna++) {                             
                g2.drawLine(casillas[NUMCASILLASMARGEN][columna].getX(), DIMCASILLA*NUMCASILLASMARGEN, casillas[NUMCASILLASMARGEN][columna].getX(), NUMCASILLAS*DIMCASILLA-DIMCASILLA*NUMCASILLASMARGEN);
        }           
        g2.drawLine(casillas[NUMCASILLASMARGEN][NUMCASILLAS-NUMCASILLASMARGEN].getX(),DIMCASILLA*NUMCASILLASMARGEN, 
                casillas[NUMCASILLASMARGEN][NUMCASILLAS-NUMCASILLASMARGEN].getX(),NUMCASILLAS*DIMCASILLA-DIMCASILLA*NUMCASILLASMARGEN); //Linea Derecha

        //Visualización imagen a colocar                                        /////////////////////////////////////////////////////////
        int i = -DIMCASILLA*2,j;
        
        for (int k = 0; k < 3; k++) {
            i = i+DIMCASILLA;
            j = -DIMCASILLA*2;
            for (int l = 0; l < 3; l++) {
                if(imagen3x3[k][l]!=null){
                    j = j+DIMCASILLA;
                    g2.drawImage(imagen3x3[k][l],coordX+DESFASE_CURSOR_X+i,coordY+DESFASE_CURSOR_Y+j,this);
                }else{
                    j = j+DIMCASILLA;
                }
            }
        }
        g2.setColor(Color.RED);
        g2.drawRect(coordX+DESFASE_CURSOR_X, coordY+DESFASE_CURSOR_Y, DIMCASILLA, DIMCASILLA);
    }
    
    //Método de acceso a la coordenada X de la casilla correspondiente a la fila y
    //columna dadas por parámetro
    public int getX(int fila,int columna) {
        return casillas[fila][columna].getX();
    }
 
    //Método de acceso a la coordenada Y de la casilla correspondiente a la fila y
    //columna dadas por parámetro    
    public int getY(int fila,int columna) {
        return casillas[fila][columna].getY();
    }
    
    //cambiar el atrubuto imagen
    public void setImagen(Image[][] valor) {
        imagen3x3=valor;
    }
    
    //obtener el atributo imagen
    public Image getImagen(int fila, int columna) {
        return imagen3x3[fila][columna];
    }    
    
    //selección imagen aleatoria
    public static void seleccionAleatoriaImagen() {
        Random vacio = new Random();
        NUMIMAGENESFIGURA = 0;
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                if (vacio.nextBoolean()){
                    imagen3x3 [k][l] = imagenes;
                }else{
                    imagen3x3 [k][l] = null;
                    NUMIMAGENESFIGURA++;
                }
            }
        }
        if(NUMIMAGENESFIGURA==9){
            seleccionAleatoriaImagen();
        }
    }
    
    //Método de acceso al número de filas o columnas del tablero   
    public int getNumCasillas() {
        return NUMCASILLAS;
    }
    //Método que cambia el estado de la casilla correspondiente a la fila y
    //columna dadas por parámetro    
    public void cambiarEstadoCasilla(int fila,int columna) {
        casillas[fila][columna].cambiarEstado();
    }
    
    //Método que cambia la imagen de la casilla indicada
    public void cambiarImagenCasilla(int filaCasilla, int columnaCasilla, int filaImagen, int columnaImagen) {
        if(imagen3x3[filaImagen][columnaImagen]!=null){
            casillas[filaCasilla][columnaCasilla].setImagen(imagen3x3[filaImagen][columnaImagen]);
        }else{
            casillas[filaCasilla][columnaCasilla].cambiarEstado();
            casillas[filaCasilla][columnaCasilla].setImagen(casillas[filaCasilla][columnaCasilla].getImagen());
        }
    }
    
    //Método que devuelve el estado de la casilla correspondiente a la fila y
    //columna dadas por parámetro    
    public boolean getEstadoCasilla(int fila,int columna) {
        return casillas[fila][columna].estado();
    }
    
    //Método que libera a todas las casillas del tablero.
    public void borrar() {
        for (int fila=0; fila <NUMCASILLAS; fila++) { 
            for (int columna=0;columna<NUMCASILLAS;columna++) {
                casillas[fila][columna].setLiberada();  
            }   
        }
    }
    
    //Método que vacía una fila del tablero
    public void borrarFila(int fila){
        for (int i = NUMCASILLASMARGEN; i < NUMCASILLAS-NUMCASILLASMARGEN; i++) {
            casillas[fila][i].setLiberada();  
        }
    }
    
    //Método que vacía una columna del tablero
    public void borrarColumna(int columna){
        for (int i = NUMCASILLASMARGEN; i < NUMCASILLAS-NUMCASILLASMARGEN; i++) {
            casillas[i][columna].setLiberada();  
        }
    }
    
    //Método que rota la figura 90 grados a la izquierda
    public static void rotar90GradosL() {
        int n = imagen3x3.length;
        Image [][] resultado = new Image[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                resultado[i][j] = imagen3x3[2-j][i];
            }
        }
        
        imagen3x3 = resultado;
    }
    
    //Método que rota la figura 90 grados a la derecha
    public static void rotar90GradosR() {
        int n = imagen3x3.length;
        Image [][] resultado = new Image[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                resultado[i][j] = imagen3x3[j][2-i];
            }
        }
        
        imagen3x3 = resultado;
    }
    
    //obtener desfase en accisas
    public int getDesfaseX() {
        return DESFASE_X;
    }
    //obtener desfase en ordenadas
    public int getDesfaseY() {
        return DESFASE_Y;
    }
    //obtener dimensión casillas dek tablero
    public int getDimensionCasilla() {
        return DIMCASILLA;
    }
    //obtener coordenada X
    public int getCoordX() {
        return coordX;
    }
    //modificar coordenada X
    public void setCoordX(int valor) {
        coordX=valor;
    }
    //obtener coordenada Y
    public int getCoordY() {
        return coordY;
    }
    //modificar coordenada Y
    public void setCoordY(int valor) {
        coordY=valor;
    }
    //modificar estado INSERCION
    public void setInsercion(boolean valor) {
        INSERCION=valor;
    }
    
    //Método que cambia la imagen de la figura a colocar
    public static void setImagen(String nombreImagen){
        BufferedImage imagenComprobarExiste;
        nombreImagen = "imagenes/" + nombreImagen + ".jpg";
        try {
            imagenComprobarExiste = ImageIO.read(new File(nombreImagen));
            
            //Si llega a esta parte, existe la imagen
            imagenes = Toolkit.getDefaultToolkit().getImage(nombreImagen);
            GestorEventos.setImagen(nombreImagen);
            seleccionAleatoriaImagen();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(VentanaTF.panelVisualizaciones,
                                                "IMAGEN NO ENCONTRADA, SE UTILIZARÁ LA IMAGEN PREDEFINIDA",
                                                "",
                                                JOptionPane.ERROR_MESSAGE);
            imagenes= Toolkit.getDefaultToolkit().getImage("imagenes/CHOCOLATE.jpg");
            GestorEventos.setImagen("imagenes/CHOCOLATE.jpg");
        }
    }
}
