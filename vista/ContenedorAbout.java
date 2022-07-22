package vista;

import controlador.BotonVolverEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ContenedorAbout extends VBox {
    private static double ANCHO= 220.0, ALTO= 60.0;
    private Stage stage;

    public ContenedorAbout(Stage stage, MediaPlayer media){
        this.stage = stage;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(25));

        Label info = new Label();
        info.setText(
            "Desarrollado por alumnos de Algoritmos y Programacion 3 de la FIUBA\n"+
                    " Integrantes:\n     •Nicolas Allende\n     •Alexander Joaquin Cardozo\n     •Fernando Tejello\n     •Juan Cruz Opizzi"
        );
        info.setFont(Font.font("Forte", FontWeight.BOLD, 18));
        info.setStyle("-fx-background-color: white;");

        Button botonVolver = BotonGenerico.generarbotonInicio("Volver","Forte",new BotonVolverEventHandler(stage, media));
        botonVolver.setMinSize(ANCHO, ALTO);
        botonVolver.setMaxSize(ANCHO, ALTO);

        this.getChildren().addAll(info, botonVolver);
    }
}
