package controlador;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import vista.ContenedorBienvenida;

public class BotonVolverEventHandler extends BotonGenericoEventHandler {
    private Stage stageVolver;
    private MediaPlayer musica;

    public BotonVolverEventHandler(Stage stage, MediaPlayer media) {
        stageVolver = stage;
        this.musica = media;
    }

    @Override
    public void handle(ActionEvent event) {
        ContenedorBienvenida contenedorBienvenida = new ContenedorBienvenida(this.stageVolver);
        Scene escenaBienvenida = new Scene(contenedorBienvenida, this.stageVolver.getScene().getWidth(), this.stageVolver.getScene().getHeight());
        this.stageVolver.setScene(escenaBienvenida);
        this.musica.setOnRepeat( () -> {} );
    }
}
