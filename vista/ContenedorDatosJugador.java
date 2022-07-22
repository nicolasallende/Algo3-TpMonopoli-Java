package vista;

import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import modelo.Casillero;
import modelo.Jugador;
import modelo.Propiedad;
import modelo.Tablero;
import modelo.estados.EstadoJugador;

import java.util.ArrayList;
import java.util.Hashtable;


public class ContenedorDatosJugador extends TilePane {
    Stage stageDatos;
    Jugador jugador;

    private static final String CONTENEDOR_DATOS = "contenedor-datos";
    private static final String MENSAJE_JUGADOR_PERDIO = "\n\nEL jugador perdio";

    private String obtenerRepresentacionJugador(){
        if ( this.jugador == null ) return MENSAJE_JUGADOR_PERDIO;
        return "Nombre: "+ this.jugador.nombre()+"\n" + "Posicion: " + this.jugador.casilleroActual().getNombre()+"\n"+ "Dinero: " + this.jugador.dinero()+"\n";
    }


    private Label obtenerStatus(){
        Label status = new Label( this.obtenerRepresentacionJugador() );
        return  status;
    }


    public ContenedorDatosJugador(Stage stage,Jugador jugador) {
        this.stageDatos = stage;
        this.jugador=jugador;

        Label status = this.obtenerStatus();
        this.getStylesheets().add("file:CSS/estilos.css");
        this.getStyleClass().add(CONTENEDOR_DATOS);

        this.getChildren().add(status);
    }

    public void update() {
    	Label statusActualizado = this.obtenerStatus();
    	this.getChildren().set(0, statusActualizado);
    }

    public Jugador getJugador() {
        return this.jugador;
    }

    public void actualizarPorJugadorQuePerdio() {
        this.jugador = null;
    }
}
