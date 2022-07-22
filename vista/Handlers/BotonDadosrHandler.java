package vista.Handlers;

import controlador.BotonGenericoEventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import modelo.Juego;
import modelo.Jugador;
import modelo.Tablero;
import modelo.acciones.AccionesDelJugador;
import vista.*;

import java.util.List;

public class BotonDadosrHandler extends BotonGenericoEventHandler {
	GraphicsContext gc;

	private Juego juegoActual;
	private ContenedorJuego contenedorJuego;
	private ContenedorInferiorJugadores contenedorInfJugadores;
	private MensajeAJugador mensaje;
	
	public BotonDadosrHandler(GraphicsContext gc, Juego juego, ContenedorJuego contenedorJuego) {
		super();
		this.juegoActual = juego;
		this.contenedorJuego = contenedorJuego;
		this.gc = gc;
		this.contenedorInfJugadores = this.contenedorJuego.getContenedorInferiorJugadores();
		this.mensaje = new MensajeAJugador();
	}

	@Override
	public void handle(ActionEvent event) {
		Jugador jugadorActual = this.juegoActual.getTablero().obtenerJugadorActual();
		
		BoxJugador boxJugador = this.contenedorJuego.getBoxJugador(jugadorActual);
		boxJugador.clean(this.gc);
		if( jugadorActual.hacer(AccionesDelJugador.tirarDados)) {
			String posibleMsgDobleTiro = this.juegoActual.getTablero().vuelveATirar();
			if (! posibleMsgDobleTiro.isEmpty()) this.mensaje.escribirMensaje(posibleMsgDobleTiro);

			int ultimoTiro = this.juegoActual.getTablero().ultimoTiroRealizado();
			String nombreCasilleroAMoverse = jugadorActual.casilleroActual().getNombre();

			boxJugador.getRepjugador().move(nombreCasilleroAMoverse);
			mensaje.mensajeDeTirarDados(jugadorActual, ultimoTiro);
			
			contenedorJuego.actualizarBotonera();
		}
		boxJugador.update(this.gc);
		this.contenedorInfJugadores.update();

	}


}
