package vista.Handlers;

import controlador.BotonGenericoEventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modelo.Juego;
import modelo.Jugador;
import modelo.Tablero;
import modelo.acciones.AccionesDelJugador;
import vista.ContenedorInferiorJugadores;
import vista.ContenedorJuego;
import vista.ContenedorUsuariosPropiedades;
import vista.MensajeAJugador;

public class BotonPagarFianzaHandler extends BotonGenericoEventHandler {


	private final ContenedorInferiorJugadores contenedorInfJugadores;
	private final ContenedorUsuariosPropiedades contenedorUsusariosPropiedades;
	private Juego juegoActual;
	private ContenedorJuego contenedorJuego;
	private MensajeAJugador mensaje;
	
	public BotonPagarFianzaHandler(Juego juego, ContenedorJuego contenedorJuego) {
		super();
		this.juegoActual = juego;
		this.contenedorJuego = contenedorJuego;
		this.mensaje = new MensajeAJugador();
		this.contenedorInfJugadores = this.contenedorJuego.getContenedorInferiorJugadores();
		this.contenedorUsusariosPropiedades = this.contenedorJuego.getContUserPropiedades();
	}
	@Override
	public void handle(ActionEvent event) {
		Tablero tablero = juegoActual.getTablero();
		Jugador jugadorActual = tablero.obtenerJugadorActual();
		if(jugadorActual.hacer(AccionesDelJugador.pagarFianza)){
			String nombreJugador = jugadorActual.nombre();
			mensaje.escribirMensaje(nombreJugador+", ya es libre de moverse");
		}
		
		contenedorJuego.actualizarBotonera();
		this.contenedorInfJugadores.update();
		this.contenedorUsusariosPropiedades.update();
	}

}
