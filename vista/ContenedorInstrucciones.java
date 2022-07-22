package vista;

import controlador.BotonVolverEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ContenedorInstrucciones extends VBox {
    private static double ANCHO= 220.0, ALTO= 60.0;
    private Stage stage;

    public ContenedorInstrucciones(Stage stage, MediaPlayer media){
        this.stage = stage;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(25));

        Label info = new Label();
        info.setText(
                "Instrucciones\n\n"+
                "Jugabilidad\n" +
                "Cuando sea tu turno, tira los dos dados y avanza alrededor del tablero en la dirección de la flecha\n"+
                " La casilla sobre la que caigas determinará lo que debes hacer. Más de un Jugador puede estar a la\n vez sobre la misma casilla.\n\n" +
                "Acciones\n"
                + "Puedes hacer una de las siguientes cosas dependiendo de la casilla sobre la que hayas caído\n" +
                        "   •Comprar Companias u otras Propiedades\n" +
                        "   •Pagar alquileres por haber caído en la Propiedad de otro jugador\n" +
                        "   •Pagar impuestos\n" +
                        "   •Ir a la Cárcel\n" +
                        "   •Cobrar Efectivo por Evento\n"+
                        "   •Si sacas dobles con los dados, mueve tu peón el número de casillas, como siempre, y haz\n      lo que determine la casilla sobre la que has caído. Vuelve a tirar los dados y juega otra vez.\n      Si sacas dobles dos veces seguida se saltea tu turno.\n" +
                        "   •Tener Propiedades te autoriza a cobrar un alquiler a cualquier “inquilino” que caiga en esa\n       casilla\n" +
                        "   •Puedes Edificar los Barrios con Casas y Hoteles segun lo permita"
        );
        info.setFont(Font.font("Forte", FontWeight.BOLD, 18));
        info.setStyle("-fx-background-color: white;");

        Button botonVolver = BotonGenerico.generarbotonInicio("Volver","Forte",new BotonVolverEventHandler(stage, media));
        botonVolver.setMinSize(ANCHO, ALTO);
        botonVolver.setMaxSize(ANCHO, ALTO);

        this.getChildren().addAll(info, botonVolver);
    }
}
