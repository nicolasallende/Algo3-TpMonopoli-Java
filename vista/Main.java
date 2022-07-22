package vista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Monopoly Argentina");

        ContenedorBienvenida contenedorBienvenida = new ContenedorBienvenida(stage);
        Scene escenaBienvenida = new Scene(contenedorBienvenida, 1100, 800);

        stage.setScene(escenaBienvenida);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
