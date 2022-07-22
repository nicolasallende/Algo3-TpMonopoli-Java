package vista.Handlers;

import controlador.BotonGenericoEventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import modelo.Barrio;
import modelo.Juego;
import modelo.Jugador;
import modelo.Tablero;
import modelo.acciones.AccionesDelJugador;
import vista.*;

import java.util.Hashtable;
import java.util.List;


public class BotonEdificarHandler extends BotonGenericoEventHandler {
	private Juego juegoActual;
	private ContenedorJuego contenedorJuego;
	private ContenedorInferiorJugadores contenedorInfJugadores;
	private MensajeAJugador mensaje;
	private static String mensajePorDefecto = "Se pudo construir exitosamente en ";

	public BotonEdificarHandler(Juego juego, ContenedorJuego contenedorJuego) {
		super();
		this.juegoActual = juego;
		this.contenedorJuego = contenedorJuego;
		this.contenedorInfJugadores = this.contenedorJuego.getContenedorInferiorJugadores();
		this.mensaje = new MensajeAJugador();
	}

	public void handle(ActionEvent event) {
		Tablero tablero = juegoActual.getTablero();
		Jugador jugadorActual = tablero.obtenerJugadorActual();

		String barrioElegido = OpcionesAEdificar.darOpciones(jugadorActual);
		if(! barrioElegido.isEmpty()) {

			Barrio barrioFisico = (Barrio) this.juegoActual.getTablero().obtenerCasilleroRelacionado(barrioElegido);
			jugadorActual.enviarSolicitudDeEdificacionAlManejoDeAcciones(barrioElegido);
			jugadorActual.hacer(AccionesDelJugador.edificar);

			//Anidiamos la nueva edificacion al tablero y eliminamos las anteriores segun el estado de la construccion
			BoxJugador boxJugador = this.contenedorJuego.getBoxJugador(jugadorActual);
			RepresentacionJugador repJugador = boxJugador.getRepjugador();
			boxJugador.agregarRepresentacionEdificacion(barrioFisico.getEstadoConstruccion(), barrioElegido, repJugador) ;


			this.mensaje.escribirMensaje(mensajePorDefecto + barrioElegido);
			contenedorJuego.actualizarBotonera();
			this.contenedorInfJugadores.update();
		}
	}
}
