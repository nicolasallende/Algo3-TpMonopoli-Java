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

public class BotonComprarPropiedadHandler extends BotonGenericoEventHandler {

	private final Juego juegoActual;
	private final ContenedorInferiorJugadores contenedorInfJugadores;
	private final ContenedorUsuariosPropiedades contenedorUsusariosPropiedades;
	private ContenedorJuego contenedorJuego;
	
	public BotonComprarPropiedadHandler(Juego juego, ContenedorJuego contenedorJuego) {
		super();
		this.juegoActual = juego;
		this.contenedorJuego = contenedorJuego;
		this.contenedorInfJugadores = this.contenedorJuego.getContenedorInferiorJugadores();
		this.contenedorUsusariosPropiedades = this.contenedorJuego.getContUserPropiedades();
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		Tablero tablero = this.juegoActual.getTablero();
		Jugador jugadorActual = tablero.obtenerJugadorActual();
		if( jugadorActual.hacer(AccionesDelJugador.comprar)) {
			contenedorJuego.actualizarBotonera();
		}
		this.contenedorInfJugadores.update();
		this.contenedorUsusariosPropiedades.update();
	}
}

