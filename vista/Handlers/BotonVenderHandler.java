package vista.Handlers;

import controlador.BotonGenericoEventHandler;
import javafx.event.ActionEvent;
import modelo.*;
import modelo.acciones.AccionesDelJugador;
import vista.*;

public class BotonVenderHandler extends BotonGenericoEventHandler{

	private final ContenedorUsuariosPropiedades contenedorUsusariosPropiedades;
	private Juego juegoActual;
	private ContenedorJuego contenedorJuego;
	private ContenedorInferiorJugadores contenedorInfJugadores;
	private MensajeAJugador mensaje;
	private static String mensajePorDefecto = "Se pudo vender exitosamente en ";
	private static String mensajePorDefectoVenta = "\ny se reinician las edificaciones";

	public BotonVenderHandler(Juego juego, ContenedorJuego contenedorJuego) {
		super();
		this.juegoActual = juego;
		this.contenedorJuego = contenedorJuego;
		this.contenedorInfJugadores = this.contenedorJuego.getContenedorInferiorJugadores();
		this.contenedorUsusariosPropiedades = this.contenedorJuego.getContUserPropiedades();
		this.mensaje = new MensajeAJugador();
	}
	
	@Override
	public void handle(ActionEvent event) {
		Tablero tablero = juegoActual.getTablero();
		Jugador jugadorActual = tablero.obtenerJugadorActual();

		String propiedadElegida = OpcionesVender.darOpciones(jugadorActual);
		if(! propiedadElegida.isEmpty()) {

			Propiedad propiedad = (Propiedad) this.juegoActual.getTablero().obtenerCasilleroRelacionado(propiedadElegida);

			jugadorActual.enviarSolicitudVenderAlManejosDeOpciones(propiedadElegida);
			jugadorActual.hacer(AccionesDelJugador.vender);

			String mensaje = mensajePorDefecto + propiedadElegida;
			BoxJugador boxJugador = this.contenedorJuego.getBoxJugador(jugadorActual);
			if ( propiedad instanceof Barrio && boxJugador.tieneRepresentacionesEdificadas(propiedadElegida)){
				boxJugador.eliminarRepresentacionEdificacionesEnBarrio(propiedadElegida);
				mensaje += 	mensajePorDefectoVenta;
			}

			this.mensaje.escribirMensaje(mensaje);
			contenedorJuego.actualizarBotonera();
			this.contenedorInfJugadores.update();
			this.contenedorUsusariosPropiedades.update();
		}
		
	}

}
