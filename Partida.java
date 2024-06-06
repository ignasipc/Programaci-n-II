/**
 * CLASE Partida
 */

package TrabajoFinal2024;

public class Partida {
    //DECLARACIÓN ATRIBUTOS
    
    //String jugador -> 32 caracteres * 2 bytes = 64 bytes
    //String fecha   -> 14 caracteres * 2 bytes = 28 bytes
    //int tiempo     -> 4 bytes
    //int girarF     -> 4 bytes
    //int nuevaF     -> 4 bytes
    //int puntosIni  -> 4 bytes
    //Int puntos     -> 4 bytes
    private static final int DIMENSION = 112;
    
    //Declaración atributos campos de un objeto Partida
    private String nombreJugador, fecha;
    private int tiempo, girarF, nuevaF, puntosIniciales, puntos;
    
    //MÉTODO CONSTRUCTOR VACÍO
    public Partida() {}
    
    //MÉTODO CONSTRUCTOR
    public Partida(String nombreJugador, String fecha, int tiempo, int girarF, int nuevaF, int puntosIniciales, int puntos) {     
        this.nombreJugador = nombreJugador;
        this.fecha = fecha;
        this.tiempo = tiempo;
        this.girarF = girarF;
        this.nuevaF = nuevaF;
        this.puntosIniciales = puntosIniciales;
        this.puntos = puntos;
    }
    
    //MÉTODOS FUNCIONALES

    //MÉTODOS GETs y SETs
    public String getNombreJugador(){
        return nombreJugador;
    }     
    public void setNombreJugador(String dato){      
        nombreJugador = dato;
    }
    
    public void setFecha(String str){   
        fecha = str; 
    }      
    public String getFecha(){        
        return fecha;
    }
    
    public void setTiempo(int t){   
        tiempo = t; 
    }      
    public int getTiempo(){        
        return tiempo;
    }
    
    public void setGirarF(int g){   
        girarF = g; 
    }      
    public int getGirarF(){        
        return girarF;
    }
    
    public void setNuevaF(int n){   
        nuevaF = n; 
    }      
    public int getNuevaF(){        
        return nuevaF;
    }
    
    public void setPuntosIniciales(int pI){   
        puntosIniciales = pI; 
    }      
    public int getPuntosIniciales(){        
        return puntosIniciales;
    }
    
    public void setPuntos(int p){   
        puntos = p; 
    }      
    public int getPuntos(){        
        return puntos;
    }

    //Método get que retorna el nombre sin espacios vacíos
    public String getNombreReal(){
        char arrayNombre [] = nombreJugador.toCharArray();
        String nombreReal = "";
        
        for (int i = 0; i < arrayNombre.length; i++) {
            if (arrayNombre[i]!=' '){
                nombreReal = nombreReal + arrayNombre[i];
            }
        }
        
        return nombreReal;
    }
    
    //método que convierte a String un objeto Partida
    @Override   
    public String toString() {       
        return " " + nombreJugador + 
               " | " + fecha +
               " |         " + tiempo +
               "        |        " + girarF +
               "       |       " + nuevaF +
               "        |         " + puntosIniciales +
               "          |        " + puntos +  "\n";
    }
    
    //método que devuelve la dimensión en bytes de un objeto Partida  
    public static int getDimension() {
        return DIMENSION;
    }
}