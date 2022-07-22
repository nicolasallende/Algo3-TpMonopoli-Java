package vista.Handlers;

import controlador.BotonGenericoEventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import modelo.Juego;
import modelo.Jugador;
import modelo.Tablero;
import modelo.acciones.AccionesDelJugador;
import vista.BoxJugador;
import vista.ContenedorInferiorJugadores;
import vista.ContenedorJuego;
import vista.MensajeAJugador;
import vista.MensajeDeVictoria;

import javax.management.remote.TargetedNotification;

public class BotonFTurnoHandler extends BotonGenericoEventHandler {

	private final ContenedorInferiorJugadores contenedorInfJugadores;
	private Juego juegoActual;
	private ContenedorJuego contenedorJuego;
	private MensajeAJugador mensaje;
	private Stage stage;
	private MediaPlayer musicaJuego;

	public BotonFTurnoHandler(Juego juego, ContenedorJuego contenedorJuego, Stage stageDeTodo, MediaPlayer musicaJuego) {
		super();
		this.juegoActual = juego;
		this.contenedorJuego = contenedorJuego;
		this.mensaje = new MensajeAJugador();
		this.stage = stageDeTodo;
		this.contenedorInfJugadores = this.contenedorJuego.getContenedorInferiorJugadores();
		this.musicaJuego = musicaJuego;
	}

	public void mostrarMensajeEnCasoDeVictoria(){
		Tablero tablero = this.juegoActual.getTablero();
		if (tablero.partidoTermino()) {
			Jugador ganador = tablero.partidaTieneUnGanador();
			this.musicaJuego.stop();
			MensajeDeVictoria termino = new MensajeDeVictoria(ganador);
			termino.mostrarMensaje(stage);
		}
	}


	@Override
	public void handle(ActionEvent event) {
		Tablero tablero = juegoActual.getTablero();
		Jugador jugadorActual = tablero.obtenerJugadorActual();
		
		if( jugadorActual.hacer(AccionesDelJugador.finalizarTurno)) {
			if(jugadorActual.noLeQuedaNada()) {
				//Sacammos al jugador del modelo del juego. Esto pasa cuando el jugador no tiene dinero ni propiedades para solventar el gasto.
				tablero.quitarJugador(jugadorActual);

				//Sacamos al jugador para que no pueda ser mas representado.
				BoxJugador boxJugador = this.contenedorJuego.getBoxJugador(jugadorActual);
				boxJugador.clean(this.contenedorJuego.getGc());
				this.contenedorJuego.getViewJugadores().remove(boxJugador);
				this.contenedorInfJugadores.reemplazarStatusPorPerder(jugadorActual);

				this.mensaje.escribirMensaje("Perdio el jugador " + jugadorActual.nombre());
			}

			//Actuzalizamos la botoneras y el status
			contenedorJuego.actualizarBotonera();
			this.contenedorInfJugadores.update();

			//Mostramos mensajes en caso de que corresponda
			this.mostrarMensajeEnCasoDeVictoria();
			this.mensaje.escribirMensaje("Es el turno de: "+ tablero.obtenerJugadorActual().nombre());
		}
	}

}
