/**
 * CLASE PartidaObjetosFicheroLecturaEscritura
 */

package TrabajoFinal2024;

import java.io.*;

public class PartidaObjetosFicheroLecturaEscritura {
    //DECLARACIÓN ATRIBUTOS
    private RandomAccessFile fichero = null;
    private static final int DIMENSIONNOMBRE = 20;
   
    //MÉTODOS
    
    //MÉTODO CONSTRUCTOR
    public PartidaObjetosFicheroLecturaEscritura() throws FileNotFoundException {
            fichero = new RandomAccessFile("partidasTetrisUIB.dat","rw");
    }
    
    //MÉTODOS FUNCIONALES
    
    //Método lectura objeto Partida desde el fichero
    public Partida lectura() throws IOException{
        //DECLARACIONES
        Partida partida = new Partida();
        
        //ACCIONES
        try {
            partida.setNombreJugador(lecturaString(DIMENSIONNOMBRE));
            partida.setFecha(lecturaString(14));
            partida.setTiempo(fichero.readInt());
            partida.setGirarF(fichero.readInt());
            partida.setNuevaF(fichero.readInt());
            partida.setPuntosIniciales(fichero.readInt());
            partida.setPuntos(fichero.readInt());
        }catch (EOFException error) {
            return null;
        }
        return partida;
    }
    
    
    //Método lectura objeto Partida desde el fichero dado el número del elemento de fichero Partida
    public Partida lectura(int posicion) throws IOException {        
        //DECLARACIONES
        Partida partida = new Partida();
        
        //ACCIONES
        try {
            //verificación si la posición del elemento a leer existe en el fichero
            if ((posicion>0) && (posicion<=(fichero.length()/Partida.getDimension()))) {
                //posicionamiento puntero del fichero en el elemento de fichero Partida dado
                fichero.seek(((posicion-1)*Partida.getDimension()));
                
                partida.setNombreJugador(lecturaString(DIMENSIONNOMBRE));
                partida.setFecha(lecturaString(14));
                partida.setTiempo(fichero.readInt());
                partida.setGirarF(fichero.readInt());
                partida.setNuevaF(fichero.readInt());
                partida.setPuntosIniciales(fichero.readInt());
                partida.setPuntos(fichero.readInt());
            }
            else {
                //invocación excepcion no predefinida entradaIncorrecta
                throw new EntradaIncorrecta("POSICIÓN NO EXISTENTE");
            }
        }catch (EntradaIncorrecta error) {
            System.out.println(error.toString());
            return null;
        }
        return partida;
    }
    
    //Método lectura de un String desde el fichero
    private String lecturaString(int dimension) throws IOException {
        //DECLARACIONES
        String campo="";
        
        //ACCIONES
        //bucle de lectura y concatenación de los caracteres desde el fichero
        for (int i=0;i<dimension;i++) {
            //concatenación en el String campo del caracter leido desde el fichero
            campo=campo+fichero.readChar();
        }  
        //devolución del String resultante
        return campo;
    }
    
    //Método escritura de un objeto Partida en el fichero
    public void escritura(Partida partida) throws IOException {
        //posicionamiento del puntero al final del fichero de objetos Partida
        fichero.seek(fichero.length());
        
        escrituraString(partida.getNombreJugador(), DIMENSIONNOMBRE);
        escrituraString(partida.getFecha(), 14);    
        
        fichero.writeInt(partida.getTiempo());
        fichero.writeInt(partida.getGirarF());
        fichero.writeInt(partida.getNuevaF());
        fichero.writeInt(partida.getPuntosIniciales());
        fichero.writeInt(partida.getPuntos());
    }
    
    //Método reescritura en el fichero de un objeto Partida dado el número que ocupa en el fichero
    public void escritura(Partida partida,int posicion) throws IOException {
        try {
            //verificación si la posición del elemento a leer existe en el fichero
            if ((posicion>0) &&(posicion<=(fichero.length()/Partida.getDimension()))) {
                //posicionamiento del puntero en el elemento de fichero objeto Partida
                //correspondiente al número dado por parámetro
                fichero.seek((posicion-1)*Partida.getDimension());
                
                escrituraString(partida.getNombreJugador(), DIMENSIONNOMBRE);
                escrituraString(partida.getFecha(), 14);
                
                fichero.writeInt(partida.getTiempo());
                fichero.writeInt(partida.getGirarF());
                fichero.writeInt(partida.getNuevaF());
                fichero.writeInt(partida.getPuntosIniciales());
                fichero.writeInt(partida.getPuntos());
            }
            else {
                //invocación excepcion no predefinida entradaIncorrecta
                throw new EntradaIncorrecta("NO ES POSIBLE ESCRIBIR EL ELEMENTO DADO");                
            }
        }catch (EntradaIncorrecta error) {
            System.out.println(error.toString());
        }
    }

    //Método escritura de un String en el fichero a través  de la escritura de los 
    //caracteres que lo conforman en función del número de éstos dado por parámetro
    private void escrituraString(String campo, int dimension) throws IOException {
        //bucle de escritura en el fichero, caracter a caracter, del String
        //y en función de la dimensión dada
        for (int i=0;((i<dimension) && (i<campo.length())); i++) {
            //escritura en el fichero del caracter i-ésimo del String
            fichero.writeChar(campo.charAt(i));
        }
        //verificar si la dimensión del String dado es menor que la dimensión
        //del atributo que debe representar del objeto Partida
        if (campo.length()<dimension) {
            //al ser la dimnensión del String menor que la dimensión del atributo
            //que representa del objeto Partida se escribirán caracteres
            //espacio hasta llegar a la dimensión del atributo
            for (int i=0; i<(dimension-campo.length()); i++) {
                //escritura en el fichero del caracter espacio
                fichero.writeChar(' ');
            }
        }            
    }
    
    //Cierre del enlace con el fichero
    public void cierre() throws IOException {
        try { 
            fichero.close();     
        } catch (IOException error) {
            System.out.println("ENLACE FICHERO YA CERRADO");
        } 
    }
}