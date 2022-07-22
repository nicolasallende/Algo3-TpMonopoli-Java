package vista;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Juego;
import modelo.Jugador;
import modelo.Tablero;

import java.util.ArrayList;
import java.util.List;

public class ContenedorUsuariosPropiedades extends VBox {
    private final Stage stage;
    private final List<Jugador> jugadores;
    private List<FooterUsuarioPropiedades> propiedadesPorUsuario;

    public ContenedorUsuariosPropiedades(Stage stage, Juego juego){
        this.propiedadesPorUsuario = new ArrayList<>();
        this.stage = stage;
        Tablero tablero = juego.getTablero();
        this.jugadores = tablero.getJugadores();

        for(Jugador jugador: this.jugadores) {
            FooterUsuarioPropiedades usuarioPropiedades = new FooterUsuarioPropiedades(this.stage, jugador);

            this.propiedadesPorUsuario.add(usuarioPropiedades);
            this.getChildren().add(usuarioPropiedades);
        }

        //Habria que poner algunos estilos para que se vea mejor.
    }

    public void update(){
        for (FooterUsuarioPropiedades footerUsuarioPropiedades: this.propiedadesPorUsuario) {
            footerUsuarioPropiedades.update();
        }
    }
}
