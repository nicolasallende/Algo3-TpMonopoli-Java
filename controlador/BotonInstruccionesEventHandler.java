package controlador;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import vista.ContenedorInstrucciones;

public class BotonInstruccionesEventHandler extends BotonGenericoEventHandler {
    private static double ANCHO= 220.0, ALTO= 60.0;
    private Stage stageInstrucciones;
    private Scene escenarioAnt;
    private MediaPlayer musica;

    public BotonInstruccionesEventHandler(Stage stage, MediaPlayer media){
        stageInstrucciones = stage;
        this.musica = media;
    }

    @Override
    public void handle(ActionEvent event) {
        ContenedorInstrucciones contenedorInstrucciones = new ContenedorInstrucciones(this.stageInstrucciones, this.musica);
        Scene escenaInstruciones = new Scene(contenedorInstrucciones, 1100, 800);
        stageInstrucciones.setScene(escenaInstruciones);
        this.musica.stop();
    }
}
