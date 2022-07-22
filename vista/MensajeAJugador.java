package vista;


import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.Jugador;

public class MensajeAJugador {
	private ArrayList<String> casillerosQueMuevenAlJugador;
	private static double TIME_DELAY = 3;

	public MensajeAJugador() {
		casillerosQueMuevenAlJugador = new ArrayList<String>();
		casillerosQueMuevenAlJugador.add("Avance Dinamico");
		casillerosQueMuevenAlJugador.add("Retroceso Dinamico");
		casillerosQueMuevenAlJugador.add("Policia");
	}
	
	public void escribirMensaje(String string) {

         Label secondLabel = new Label(string);
         
         StackPane secondaryLayout = new StackPane();
         secondaryLayout.getChildren().add(secondLabel);

         Scene scene = new Scene(secondaryLayout, 375, 100);

         Stage ventanaNueva = new Stage();

         ventanaNueva.setScene(scene);
         ventanaNueva.setTitle("Ventana Cierra Sola");
         ventanaNueva.show();

		PauseTransition delay = new PauseTransition(Duration.seconds(TIME_DELAY));
		delay.setOnFinished( event -> ventanaNueva.close() );
		delay.play();

	}
	
	public void mensajeDeTirarDados(Jugador jugador, int ultimoTiro) {
		for(String nombre: casillerosQueMuevenAlJugador) {
			if(nombre == jugador.getNombreCasilleroAnterior()) {
				escribirMensaje("Se tiro un: "+ultimoTiro+". Cayo en: "+nombre+" y\n fue movido a: "+jugador.casilleroActual().getNombre());
				return;
			}
		}
		escribirMensaje("Se tiro un: "+ultimoTiro+". Cayo en: "+jugador.casilleroActual().getNombre());
	}
	
}
