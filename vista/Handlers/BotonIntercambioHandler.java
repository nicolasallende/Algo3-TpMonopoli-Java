package vista.Handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controlador.BotonGenericoEventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import modelo.Barrio;
import modelo.Juego;
import modelo.Jugador;
import modelo.Propiedad;
import modelo.Tablero;
import modelo.acciones.AccionesDelJugador;
import vista.BoxJugador;
import vista.ContenedorJuego;
import vista.ContenedorUsuariosPropiedades;
import vista.OpcionesIntercambiar;

public class BotonIntercambioHandler extends BotonGenericoEventHandler {
	
	private static String TITULO_PROPIEDADES_PROPIAS = "Propiedades propias a elegir";
	private static String HEADER_PROPIEDADES_PROPIAS = "Escoja la propiedad que desea dar: ";
	private static String TITULO_PROPIEDADES_OTRO = "Propiedades del otro a elegir";
	private static String HEADER_PROPIEDADES_OTRO = "Escoja la propiedad que desea obtener: ";
	 
	private Juego juegoActual;
	private ContenedorJuego contenedorJuego;
	private final ContenedorUsuariosPropiedades contenedorUsusariosPropiedades;
	
	public BotonIntercambioHandler(Juego juego, ContenedorJuego contenedorJuego) {
		super();
		this.juegoActual = juego;
		this.contenedorJuego = contenedorJuego;
		this.contenedorUsusariosPropiedades = this.contenedorJuego.getContUserPropiedades();
	}
	
	@Override
	public void handle(ActionEvent event) {
		Tablero tablero = juegoActual.getTablero();
		Jugador jugadorActual = tablero.obtenerJugadorActual();
		
		//se elige el jugador con quie intercambiar
		Jugador jugadorConQuienCambiar = jugadorDeseado(tablero, jugadorActual);
		if(jugadorConQuienCambiar != null) {

			//se elige la propiedad priopia para dar
			Propiedad propiedadDelJugador = propiedadDeseada(jugadorActual, HEADER_PROPIEDADES_PROPIAS, TITULO_PROPIEDADES_PROPIAS );
			if(propiedadDelJugador != null) {
				
				//se elige la propiedad del otro
				Propiedad propiedadDelOtro = propiedadDeseada(jugadorConQuienCambiar, HEADER_PROPIEDADES_OTRO, TITULO_PROPIEDADES_OTRO);
				if(propiedadDelOtro != null) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirmar intercambio");
					alert.setHeaderText("Accepta este intercambio jugador "+ jugadorConQuienCambiar.nombre());

					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK){
						jugadorActual.intercambiarPropiedades(propiedadDelJugador, propiedadDelOtro, jugadorConQuienCambiar);
						jugadorActual.hacer(AccionesDelJugador.intercambiar);
						borrarCasas(jugadorActual, propiedadDelJugador.getNombre());
						borrarCasas(jugadorConQuienCambiar, propiedadDelOtro.getNombre());
					}
				}
				
			}
		}
		
		contenedorJuego.actualizarBotonera();
		this.contenedorUsusariosPropiedades.update();
	
		
	}
	
	
	private void borrarCasas(Jugador jugadorActual, String propiedadElegida) {
		Propiedad propiedad = (Propiedad) this.juegoActual.getTablero().obtenerCasilleroRelacionado(propiedadElegida);
		BoxJugador boxJugador = this.contenedorJuego.getBoxJugador(jugadorActual);
		if ( propiedad instanceof Barrio && boxJugador.tieneRepresentacionesEdificadas(propiedadElegida)){
			boxJugador.eliminarRepresentacionEdificacionesEnBarrio(propiedadElegida);
	}
}
	
	
	private Jugador jugadorDeseado(Tablero tablero, Jugador jugador) {
		List<Jugador> jugadores = tablero.getJugadores();
		Jugador jugadorElegido = null;
		String jElegido = OpcionesIntercambiar.darOpcionConQueJugadorIntercambiar(tablero, jugador);
		
		for(Jugador j: jugadores) {
			if(j.nombre().equals(jElegido)) {
				jugadorElegido = j;
				break;
			}
		}
		return jugadorElegido;
	}

	
	private Propiedad propiedadDeseada(Jugador jugador, String head, String titulo) {
		ArrayList<Propiedad> propiedades = jugador.getPropiedades();
		Propiedad propiedadElegida = null;
		String pElegido = OpcionesIntercambiar.darOpciones(jugador, head, titulo);
		//Se usa un string porque el ususario selecciona de strings
		for(Propiedad p: propiedades) {
			if(p.getNombre().equals(pElegido)) {
				propiedadElegida = p;
				break;
			}
		}
		return propiedadElegida;
	}
}
