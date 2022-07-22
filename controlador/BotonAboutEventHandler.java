package controlador;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import vista.ContenedorAbout;

public class BotonAboutEventHandler  extends BotonGenericoEventHandler {
    private Stage stageAcercaDe;
    private MediaPlayer musica;

    public BotonAboutEventHandler(Stage stage, MediaPlayer media){
        stageAcercaDe = stage;
        musica = media;
    }

    @Override
    public void handle(ActionEvent event) {
        ContenedorAbout contenedorAbout= new ContenedorAbout(this.stageAcercaDe, this.musica);
        Scene escenaInstruciones = new Scene(contenedorAbout, 1100, 800);
        stageAcercaDe.setScene(escenaInstruciones);
        this.musica.stop();
    }
}
