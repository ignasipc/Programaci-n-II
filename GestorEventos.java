/**
 * CLASE ActionListener GestorEventos que gestiona todos los eventos del JFrame
 */

package TrabajoFinal2024;

import java.awt.CardLayout;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class GestorEventos implements ActionListener{
    //Atributos
    private  final String titulo1 = "                                                           HISTORIAL\n\n"
                                  + " JUGADOR              |     FECHA      |  TIEMPO DE JUEGO  |  GIRAR FIGURA  |  NUEVA FIGURA  |  PUNTOS INICIALES  |  PUNTOS TOTALES\n";
    private  final String titulo2 = "                                                       HISTORIAL SELECTIVO\n\n"
                                  + " JUGADOR              |     FECHA      |  TIEMPO DE JUEGO  |  GIRAR FIGURA  |  NUEVA FIGURA  |  PUNTOS INICIALES  |  PUNTOS TOTALES\n";
    private static final String [] opcionesConfig = {"CONFIGURACIÓN ESPECÍFICA DEL JUEGO",
                                            "MODIFICAR EL TIEMPO DE DURACIÓN DE LA PARTIDA","NADA"};
    
    private static int puntosIniciales = 0, puntosRotarForma = 1, puntosNuevaForma = 5, nuevoTiempo = 30;
    private static String imagen = "imagenes/CHOCOLATE.jpg";
    
    private static CardLayout local;
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        local = (CardLayout) (VentanaTF.panelVisualizaciones.getLayout());
        
        switch(evento.getActionCommand()){
            case "NUEVA PARTIDA" -> {
                if(!VentanaTF.juegoActivado){
                    iniciarPartida();
                }else{
                    emergenteDebesTerminarPartida();
                }
            }
            case "CONFIGURACIÓN" -> {
                if(!VentanaTF.juegoActivado){
                    emergenteConfiguracion();
                }else{
                    emergenteDebesTerminarPartida();
                }
            }
            case "HISTORIAL" -> {
                if(!VentanaTF.juegoActivado){
                    limpiarHistorial(titulo1);
                    local.show(VentanaTF.panelVisualizaciones, "Panel Historial");
                    lecturaDatosJugadores();
                }else{
                    emergenteDebesTerminarPartida();
                }
            }
            case "HISTORIAL SELECTIVO" -> {
                if(!VentanaTF.juegoActivado){
                    limpiarHistorial(titulo2);
                    local.show(VentanaTF.panelVisualizaciones, "Panel Historial");
                    lecturaDatosSelectivosJugador();
                }else{
                    emergenteDebesTerminarPartida();
                }
            }
            case "INFORMACIÓN" -> {
                if(!VentanaTF.juegoActivado){
                    local.show(VentanaTF.panelVisualizaciones, "Panel Informacion");
                }else{
                    emergenteDebesTerminarPartida();
                }
            }
            case "VOLVER AL MENÚ PRINCIPAL" -> {
                local.show(VentanaTF.panelVisualizaciones, "Panel Inicio");
            }
            case "SALIR" -> {
                System.exit(0);
            }
            
            case "ROTAR IZQUIERDA" -> {
                rotarIzquierda();
            }
            case "ROTAR DERECHA" -> {
                rotarDerecha();
            }
            case "NUEVA FORMA" -> {
                nuevaForma();
            }
            
            case "CRONOMETRO" -> {
                //VERIFICACIÓN SI EL VALOR ACTUAL DE LA JProgressBar barraTemporal
                //HA LLEGADO AL VALOR MÁXIMO ESTIPULADO
                if(PanelBarraTemporalPartida.barraTemporal.getValorBarraTemporal()<PanelBarraTemporalPartida.barraTemporal.getValorMaximo()){
                    //ASIGNAR A JProgressBar barraTemporal SU VALOR INCREMENTADO
                    //EN UNA UNIDAD
                    PanelBarraTemporalPartida.barraTemporal.setValorBarraTemporal(PanelBarraTemporalPartida.barraTemporal.getValorBarraTemporal()+1);
                }else{
                    //DETENER EL TEMPORIZADOR cronometro
                    PanelBarraTemporalPartida.cronometro.stop();
                    emergenteHaTerminadoPartida();
                    partidaFinalizada();
                }
            }
        }
    }
    
    //Método para decirle al usuario que termine la partida
    private void emergenteDebesTerminarPartida() {
        JOptionPane.showMessageDialog(VentanaTF.panelVisualizaciones,"ANTES DEBES TERMINAR LA PARTIDA EN CURSO",
                                                        "",
                                                        JOptionPane.WARNING_MESSAGE);
    }
    
    //Método para indicarle al usuario que ha finalizado la partida y los puntos que ha obtenido
    private void emergenteHaTerminadoPartida(){
        JOptionPane.showMessageDialog(VentanaTF.panelVisualizaciones,"¡SE HA TERMINADO EL TIEMPO!\nHAS OBTENIDO "+VentanaTF.puntos+" PUNTOS",
                                                        "",
                                                        JOptionPane.INFORMATION_MESSAGE);
    }
    
    //Método que gestiona la configuración de la partida
    private void emergenteConfiguracion(){
        local.show(VentanaTF.panelVisualizaciones, "Panel Inicio");
        int respuesta=JOptionPane.showOptionDialog(VentanaTF.panelVisualizaciones, //contenedor padre
                                                    "¿ QUÉ DESEAS REALIZAR ?", //texto visualizado 
                                                    "",//título ventana
                                                    JOptionPane.DEFAULT_OPTION,//tipo de ventana
                                                    JOptionPane.QUESTION_MESSAGE,//tipo de mensaje
                                                                                 // - ERROR_MESSAGE
                                                                                 // - INFORMATION_MESSAGE
                                                                                 // - WARNING_MESSAGE
                                                                                 // - QUESTION_MESSAGE (por defecto)
                                                                                 // - PLAIN_MESSAGE
                                                    null, //icono de la ventana
                                                    opcionesConfig, //array de opciones
                                                    opcionesConfig[2]);//botón por defecto
        switch (respuesta){
            case 0 -> {
                ventanaEmergenteDatosConfig();
                emergenteConfiguracion();
            }
            case 1 -> {
                ventanaEmergenteNuevoTiempo();
                emergenteConfiguracion();
            }
        }
    }
    
    //Método para preguntarle al usuario su nombre de usuario para la partida
    private void ventanaEmergenteDatosNuevaPartida() {
        String[] literalesIntroduccion = {"NOMBRE DE JUGADOR: "};
        literalesIntroduccion = new LTGraficos(TrabajoFinal2024.ventana,literalesIntroduccion).getDatosTexto();
        if (literalesIntroduccion != null) {
            VentanaTF.cambiarUsuario(literalesIntroduccion[0]);
            VentanaTF.cambiarPuntuacion(0);
        }else{
            JOptionPane.showMessageDialog(VentanaTF.panelVisualizaciones,
                                                "PORFAVOR INTRODUCE TODOS LOS DATOS",
                                                "",
                                                JOptionPane.ERROR_MESSAGE);
            ventanaEmergenteDatosNuevaPartida(); 
        }
    }
    
    //Método que pregunta al usuario los campos que quiere modificar de la partida (Todas menos el tiempo de partida)
    private void ventanaEmergenteDatosConfig() {
        String[] literalesIntroduccion = {"PUNTUACIÓN CASILLAS FORMAS ELIMINADAS DEL TABLERO ["+PanelAreaJuego.puntosCompletarFilaColumna+" puntos]: ",
                    "PUNTUACIÓN ROTAR FORMA [-"+puntosRotarForma+" puntos]: ",
                    "PUNTUACIÓN NUEVA FORMA [-"+puntosNuevaForma+" puntos]: ",
                    "PUNTUACIÓN INICIAL ["+puntosIniciales+" puntos]: ",
                    "IMAGEN CASILLAS FORMAS ["+imagen+"]: "};
        literalesIntroduccion = new LTGraficos(TrabajoFinal2024.ventana,literalesIntroduccion).getDatosTexto();
        if (literalesIntroduccion != null) {
            try{
                if(!literalesIntroduccion[0].equals("")){
                    if(Integer.parseInt(literalesIntroduccion[0])>0){
                        PanelAreaJuego.puntosCompletarFilaColumna = Integer.parseInt(literalesIntroduccion[0]);
                    }
                }
                if(!literalesIntroduccion[1].equals("")){
                    if(Integer.parseInt(literalesIntroduccion[1])>=0){
                        puntosRotarForma = Integer.parseInt(literalesIntroduccion[1]);
                    }
                }
                if(!literalesIntroduccion[2].equals("")){
                    if(Integer.parseInt(literalesIntroduccion[2])>=0){
                        puntosNuevaForma = Integer.parseInt(literalesIntroduccion[2]);
                    }
                }
                if(!literalesIntroduccion[3].equals("")){
                    if(Integer.parseInt(literalesIntroduccion[3])>=0){
                        puntosIniciales = Integer.parseInt(literalesIntroduccion[3]);
                    }
                }
                if(!literalesIntroduccion[4].equals("")){
                    Tablero.setImagen(literalesIntroduccion[4]);
                    PanelAreaJuego.tablero = new Tablero();
                }
            }catch(NumberFormatException error){
                JOptionPane.showMessageDialog(VentanaTF.panelVisualizaciones,
                                                "UNO DE LOS CAMPOS ERA UNA LETRA EN VEZ DE UN NÚMERO",
                                                "",
                                                JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    //Método que modifica el tiempo de partida según lo que quiera el usuario
    private void ventanaEmergenteNuevoTiempo(){
        String nuevoTiempoString;
        
        nuevoTiempoString = JOptionPane.showInputDialog(VentanaTF.panelVisualizaciones, 
                                                        "INDICA EL NUEVO TIEMPO:\n(ACTUAL: "+PanelBarraTemporalPartida.getTiempoActual()+"s)", 
                                                        "", 
                                                        JOptionPane.QUESTION_MESSAGE);
        if (nuevoTiempoString!=null){
            try{
                nuevoTiempo = Integer.parseInt(nuevoTiempoString);
                if(nuevoTiempo > 0){
                    PanelBarraTemporalPartida.setTiempo(nuevoTiempo);
                }else{
                    JOptionPane.showMessageDialog(VentanaTF.panelVisualizaciones,
                                                    "DEBES INSERTAR UN NÚMERO MAYOR A 0",
                                                    "",
                                                    JOptionPane.ERROR_MESSAGE);
                    ventanaEmergenteNuevoTiempo();
                }
            }catch(NumberFormatException error){
                JOptionPane.showMessageDialog(VentanaTF.panelVisualizaciones,
                                                "DEBES INSERTAR UN NÚMERO",
                                                "",
                                                JOptionPane.ERROR_MESSAGE);
                ventanaEmergenteNuevoTiempo();
            }
        }
    }
    
    //Método que inicializa la partida, pidiendo el nombre de usuario y iniciando las puntuaciones
    private void iniciarPartida(){
        ventanaEmergenteDatosNuevaPartida();
        VentanaTF.juegoActivado = !VentanaTF.juegoActivado;
        VentanaTF.cambiarPuntuacion(puntosIniciales);
        local.show(VentanaTF.panelVisualizaciones, "Panel Partida");
        PanelBarraTemporalPartida.barraTemporal.setValorBarraTemporal(0);
        PanelBarraTemporalPartida.cronometro.start();
    }
    
    //Método para finalizar la partida, cambiando el estado de JuegoActivo y escribe los datos en el fichero
    private void partidaFinalizada(){
        VentanaTF.juegoActivado = !VentanaTF.juegoActivado;
        escribirDatosJugador();
        VentanaTF.puntos = 0;
        PanelAreaJuego.tablero = new Tablero();
        PanelAreaJuego.resetearCoordenadasFiguraCentral();
        local.show(VentanaTF.panelVisualizaciones, "Panel Inicio");
    }
    
    //Método para rotar la figura a posicionar
    private void rotarIzquierda(){
        if((VentanaTF.puntos>0)&&(VentanaTF.puntos-puntosRotarForma>=0)){
            Tablero.rotar90GradosL();
            VentanaTF.cambiarPuntuacion(VentanaTF.puntos - puntosRotarForma);
        }else{
            JOptionPane.showMessageDialog(VentanaTF.panelVisualizaciones,"NO TIENES SUFICIENTES PUNTOS\nPARA CANJEAR",
                                                        "",
                                                        JOptionPane.ERROR_MESSAGE);
        }
        PanelBotonesPartida.partida.repaint();
    }
    
    //Método para rotar la figura a posicionar
    private void rotarDerecha(){
        if((VentanaTF.puntos>0)&&(VentanaTF.puntos-puntosRotarForma>=0)){
            Tablero.rotar90GradosR();
            VentanaTF.cambiarPuntuacion(VentanaTF.puntos - puntosRotarForma);
        }else{
            JOptionPane.showMessageDialog(VentanaTF.panelVisualizaciones,"NO TIENES SUFICIENTES PUNTOS\nPARA CANJEAR",
                                                        "",
                                                        JOptionPane.ERROR_MESSAGE);
        }
        PanelBotonesPartida.partida.repaint();
    }
    
    //Método generar una nueva figura a posicionar
    private void nuevaForma(){
        if((VentanaTF.puntos>0)&&(VentanaTF.puntos-puntosNuevaForma>=0)){
            Tablero.seleccionAleatoriaImagen();
            VentanaTF.cambiarPuntuacion(VentanaTF.puntos - puntosNuevaForma);
        }else{
            JOptionPane.showMessageDialog(VentanaTF.panelVisualizaciones,"NO TIENES SUFICIENTES PUNTOS\nPARA CANJEAR",
                                                        "",
                                                        JOptionPane.ERROR_MESSAGE);
        }
        PanelBotonesPartida.partida.repaint();
    }
    
    //Método para guardar en un fichero los datos del jugador
    private void escribirDatosJugador() {
        PartidaObjetosFicheroLecturaEscritura fichero;
        Partida partida;

        try {
            fichero = new PartidaObjetosFicheroLecturaEscritura();
            try {
                DateTimeFormatter fechaInicio = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
                partida = new Partida(VentanaTF.usuario, fechaInicio.format(LocalDateTime.now()), 
                        nuevoTiempo, puntosRotarForma, puntosNuevaForma, puntosIniciales, VentanaTF.puntos);
                fichero.escritura(partida);
            } catch (IOException error) {
                //SE HA TERMINADO EL FICHERO
            } finally {
                try {
                    fichero.cierre();
                } catch (IOException error) {
                    System.out.println("ENLACE FICHERO YA CERRADO");
                }
            }
        } catch (IOException error) {
            System.out.println("ERROR: " + error.toString());
        }
    }
    
    //Método para leer todos los objetos Partida del fichero
    private void lecturaDatosJugadores() {
        //DECLARACIONES
        //declaración objeto FicheroPartidaInOut
        PartidaObjetosFicheroLecturaEscritura fichero;
        //declaración objeto Partida
        Partida partida;
        
        boolean hayDatos = false;

        try {
            //delaración objeto FicheroPartidaInOut para posibilitar la escritura
            //de un objeto Partida en el fichero
            fichero = new PartidaObjetosFicheroLecturaEscritura();

            try {
                //lectura del primer objeto Partida desde el fichero
                partida = fichero.lectura();
                //bucle de lectura y visualización
                while (partida != null) {
                    hayDatos = true;
                    PanelHistorial.areaVisualizacionHistorial.append(partida.toString());
                    //lectura siguiente objeto Partida desde el fichero  
                    partida = fichero.lectura();
                }
            } catch (IOException error) {
                //SE HA TERMINADO EL FICHERO
            } finally {
                fichero.cierre();
            }
        } catch (IOException error) {
            System.out.println("ERROR: " + error.toString());
        }
        //Si el fichero está vacío enseñamos un mensaje de que está vacío
        if(!hayDatos){
            JOptionPane.showMessageDialog(VentanaTF.panelVisualizaciones,"TODAVÍA NO HAY DATOS ALMACENADOS",
                                                                    "",
                                                                    JOptionPane.ERROR_MESSAGE);
            local.show(VentanaTF.panelVisualizaciones, "Panel Inicio");
        }
    }
    
    //Método para leer todos los objetos Partida del fichero y mostrar los que coincidan con el nombre dado
    private void lecturaDatosSelectivosJugador() {
        //DECLARACIONES
        //declaración objeto FicheroPartidaInOut
        PartidaObjetosFicheroLecturaEscritura fichero;

        Partida partida;

        String Usuario;
        Usuario = JOptionPane.showInputDialog("HISTORIAL DE JUGADOR\n"
                + "INTRODUCE EL NOMBRE DE USUARIO");
        
        boolean encontrado = false;
        
        //ACCIONES
        if (Usuario != null) {
            try {
                //declaración objeto FicheroElementoInOut para posibilitar la
                //lectura desde el fichero
                fichero = new PartidaObjetosFicheroLecturaEscritura();
                try {
                    partida = fichero.lectura();
                    //bucle lectura
                    while (partida != null) {
                        String jugadorLeido = partida.getNombreReal();

                        if (Usuario.equals(jugadorLeido)) {
                            PanelHistorial.areaVisualizacionHistorial.append(partida.toString());
                            encontrado = true;
                        }
                        //lectura elemento
                        partida = fichero.lectura();
                    }
                        
                    //Si no se ha encontrado el nombre
                    if(!encontrado){
                        JOptionPane.showMessageDialog(VentanaTF.panelVisualizaciones,"NO SE HA ENCONTRADO EL JUGADOR: \""+Usuario+"\"",
                                                                    "",
                                                                    JOptionPane.ERROR_MESSAGE);
                        local.show(VentanaTF.panelVisualizaciones, "Panel Inicio");
                    }
                } catch (IOException error) {
                    //SE HA TERMINADO EL FICHERO
                } finally {
                    try {
                        //cierre del enlace del fichero
                        fichero.cierre();
                    } catch (IOException error) {
                        System.out.println("ENLACE FICHERO YA CERRADO");
                    }
                }
            } catch (FileNotFoundException error) {
                System.out.println("ERROR: " + error.toString());
            }
        }else{
            local.show(VentanaTF.panelVisualizaciones, "Panel Inicio");
        }
    }
    
    //Método para vaciar el panelHistorial
    private void limpiarHistorial(String titulo){
        PanelHistorial.areaVisualizacionHistorial.setText(titulo);
    }
    
    //Método para cambiar el nombre de la imagen de la figura
    public static void setImagen(String nuevoNombre){
        imagen = nuevoNombre;
    }
}