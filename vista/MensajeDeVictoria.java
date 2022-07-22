package vista;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import modelo.Jugador;

public class MensajeDeVictoria extends VBox {
	
	public MensajeDeVictoria(Jugador jugador) {
		 super();
	        this.setAlignment(Pos.CENTER_LEFT);
	        this.setPadding(new Insets(290));
	        MediaPlayer mediaPlayer = null;
	        try {
				String musicFile = "sonidos/ganaste.wav";
				Media sound = new Media(new File(musicFile).toURI().toString());
				mediaPlayer = new MediaPlayer(sound);
				mediaPlayer.play();
	        } catch(MediaException error) {
	        }
	        Image fondo = new Image("file:src/vista/Recursos/ganar.png");
	        BackgroundPosition position = new BackgroundPosition(Side.RIGHT, 0,false, Side.TOP, 0, false);
	        BackgroundImage imagenDeFondo = new BackgroundImage(fondo, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, position, BackgroundSize.DEFAULT);
	        this.setBackground(new Background(imagenDeFondo));
	        
	        Label bienvenida = new Label();
	        bienvenida.setFont(Font.font("Forte", FontWeight.BOLD, 20));
	        bienvenida.setText("Felicidades jugador " + jugador.nombre());
	        
	        this.getChildren().add(bienvenida);
	}
	
	public void mostrarMensaje( Stage stage) {
		Scene escenaFinal = new Scene(this, 1100, 800);

        stage.setScene(escenaFinal);
        stage.show();
	}
}
