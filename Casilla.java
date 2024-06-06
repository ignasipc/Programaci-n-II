/*
CLASE Casilla
*/

package TrabajoFinal2024;

import java.awt.Image;

class Casilla {
    //DECLARACIÓN DE ATRIBUTOS
    private boolean ocupada;
    private int x,y;
    private Image imagen;
    
    //MÉTODOS CONSTRUCTORES
    public Casilla() {
        ocupada=false;
    }
    
    public Casilla(int x,int y,Image ima, boolean CasillaBorde) {
        ocupada=CasillaBorde;
        this.x=x;
        this.y=y;
        imagen=ima;
    }
    
    //MÉTODO QUE LIBERA UNA CASILLA
    public void setLiberada() {
        ocupada=false;
    }
    
    //MÉTODO QUE DEVUELVE EL ESTADO DE UNA CASILLA
    public boolean estado() {
        return ocupada;
    }
    
    //MÉTODO QUE CAMBIA EL ESTADO A OCUPADA DE UNA CASILLA 
    public void setOcupada() {
        ocupada=true;
    }
    
    //MÉTODO QUE CAMCIA EL ESTADO DE UNA CASILLA
    public void cambiarEstado() {
        ocupada=!ocupada;
    }
    
    //MÉTODO DE MODIFICA LA COORDENADA X DE UNA CASILLA
    public void setX(int x) {
        this.x=x;
    }
    
    //MÉTODO DE MODIFICA LA COORDENADA Y DE UNA CASILLA
    public void setY(int y) {
        this.y=y;
    }  
    
    //MÉTODO DE DA ACCESO A LA COORDENADA X DE UNA CASILLA
    public int getX() {
        return x;
    }
    
    //MÉTODO DE DA ACCESO A LA COORDENADA Y DE UNA CASILLA
    public int getY() {
        return y;
    }
    
    //MÉTODO QUE MODIFICA EL ATRIBUTO imagen DE UNA CASILLA
    public void setImagen(Image valor) {
        imagen=valor;
    }
    
    //MÉTODO QUE DA ACCESO AL ATRIBUTO imagen DE UNA CASILLA
    public Image getImagen() {
        return imagen;
    }
}
