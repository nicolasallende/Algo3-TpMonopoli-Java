package vista;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MensajeAlertaSonido {
	  
	public void mostrarMensajeDeProblemaConElSonido() {
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Warning alert");
	 
	        alert.setHeaderText(null);
	        alert.setContentText("Hay problemas para reproducir el sonido, se proseguira al juego sin musica");
	 
	        alert.showAndWait();
	    }
}
