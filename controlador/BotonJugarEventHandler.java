package controlador;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import vista.ContenedorInstrucciones;
import vista.ContenedorJuego;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

public class BotonJugarEventHandler extends BotonGenericoEventHandler {
    private Stage stageJuego;
    private MediaPlayer musicaIntro;
    private static String musicFileJuego = "sonidos/juego.wav";
    private MediaPlayer musicaJuego;

    public BotonJugarEventHandler(Stage stage, MediaPlayer media){
        stageJuego = stage;
        this.musicaIntro = media;
    }

    @Override
    public void handle(ActionEvent event) {
    	try {
    		this.musicaIntro.stop();
    	} catch (NullPointerException e) {	
    		}
    	try {
            Media sound = new Media(new File(musicFileJuego).toURI().toString());
            this.musicaJuego = new MediaPlayer(sound);

            ContenedorJuego contenedorJuego  = new ContenedorJuego(this.stageJuego, this.musicaJuego);
            Scene escenaJuego = new Scene(contenedorJuego, 1500, 950);
            escenaJuego.getStylesheets().add("file:CSS/estilos.css");

            this.stageJuego.setScene(escenaJuego);

        } catch (JAXBException e) {
            e.printStackTrace();
        	
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MediaException error) {
        	
        }
        
    }
}
