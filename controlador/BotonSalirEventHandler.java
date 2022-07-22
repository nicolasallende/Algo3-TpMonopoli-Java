package controlador;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class BotonSalirEventHandler extends BotonGenericoEventHandler {
    @Override
    public void handle(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "¿Esta seguro que desea salir?", ButtonType.YES, ButtonType.NO);
        alerta.setTitle("Confirmacion de salida");
        alerta.showAndWait();

        if(alerta.getResult() == ButtonType.YES) {
            System.exit(0);
        }
    }
}
