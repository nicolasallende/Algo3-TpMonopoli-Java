package vista;

import controlador.BotonAboutEventHandler;
import controlador.BotonInstruccionesEventHandler;
import controlador.BotonJugarEventHandler;
import controlador.BotonSalirEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;

public class ContenedorBienvenida extends VBox {
    private static double ANCHO= 220.0, ALTO= 60.0;
    private Stage stage;


    public ContenedorBienvenida(Stage stageInicio){
        super();
        this.stage = stageInicio;
        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(20);
        this.setPadding(new Insets(25));
        MediaPlayer mediaPlayer = null;
        try {
        String musicFile = "sonidos/intro.wav";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        
        mediaPlayer.play();
        } catch(MediaException error) {
        	MensajeAlertaSonido mensaje = new MensajeAlertaSonido();
        	mensaje.mostrarMensajeDeProblemaConElSonido();
        }
        Image fondo = new Image("file:src/vista/Recursos/otraIntroConFormato.png");
        BackgroundPosition position = new BackgroundPosition(Side.RIGHT, 0,false, Side.TOP, 0, false);
        BackgroundImage imagenDeFondo = new BackgroundImage(fondo, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, position, BackgroundSize.DEFAULT);
        this.setBackground(new Background(imagenDeFondo));

        Label bienvenida = new Label();
        bienvenida.setFont(Font.font("Forte", FontWeight.BOLD, 20));
        bienvenida.setText("Elija una opcion");

        Button botonJugar = BotonGenerico.generarbotonInicio("Jugar","Verdana",new BotonJugarEventHandler(this.stage, mediaPlayer) );
        Button botonInstrucciones = BotonGenerico.generarbotonInicio("Instrucciones", "Forte", new BotonInstruccionesEventHandler(this.stage, mediaPlayer) );
        Button botonAbout = BotonGenerico.generarbotonInicio("Acerca de", "Forte",new BotonAboutEventHandler(this.stage, mediaPlayer) );
        Button botonSalir = BotonGenerico.generarbotonInicio("Salir", "Forte", new BotonSalirEventHandler() );
        this.getChildren().addAll(bienvenida, botonJugar, botonInstrucciones, botonAbout, botonSalir);
        
    }
}


