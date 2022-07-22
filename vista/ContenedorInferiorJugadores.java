package vista;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import modelo.Juego;
import modelo.Jugador;
import modelo.Tablero;

import java.util.ArrayList;
import java.util.List;

public class ContenedorInferiorJugadores extends BorderPane {
    Stage stageContInf;
    List<ContenedorDatosJugador> contenedorJugadores;
    List<Jugador> jugadores;
    Label labelTurno;
    private static final String CONTENEDOR_INFERIOR = "contenedor-inferior-jugadores";


    public ContenedorInferiorJugadores(Stage stage, Juego juego){
        super();
        this.stageContInf = stage;
        Tablero tablero = juego.getTablero();
        this.jugadores = tablero.getJugadores();
        this.contenedorJugadores = new ArrayList<>();

        HBox box = new HBox();
        for(Jugador jugador: this.jugadores) {
            ContenedorDatosJugador usuario = new ContenedorDatosJugador(this.stageContInf, jugador);
            //usuario no se actualiza
            this.contenedorJugadores.add(usuario);
            box.getChildren().add(usuario);
        }
        this.getChildren().add(box);
        this.getStylesheets().add("file:CSS/estilos.css");
        this.getStyleClass().add(CONTENEDOR_INFERIOR);

        VBox turno = new VBox();
        labelTurno = new Label("Turno de: ");
        labelTurno.setFont(Font.font("Forte", FontWeight.BOLD, 40));
        turno.setAlignment(Pos.CENTER);
        turno.getChildren().addAll(labelTurno);
    }

    public void update(){
        for (ContenedorDatosJugador contenedorDatosJugador: this.contenedorJugadores) {
            contenedorDatosJugador.update();
        }
    }

    public ContenedorDatosJugador encontraJugadorAsociado(Jugador jugador){
        int indexJugador = 0;
        for (int i = 0; i < contenedorJugadores.size() ; i++) {
            ContenedorDatosJugador contenedorDatosJugador = contenedorJugadores.get(i);
            if ( contenedorDatosJugador.getJugador() != null && contenedorDatosJugador.getJugador().equals(jugador)){
                indexJugador = i;
            }
        }
        return this.contenedorJugadores.get(indexJugador);
    }

    public void reemplazarStatusPorPerder(Jugador jugador){
        ContenedorDatosJugador contenedorDatosJugador = this.encontraJugadorAsociado(jugador);
        contenedorDatosJugador.actualizarPorJugadorQuePerdio();
    }


}
