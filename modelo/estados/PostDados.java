package modelo.estados;

import modelo.Jugador;
import modelo.acciones.AccionesDelJugador;

public class PostDados extends EstadoJugador {
	public PostDados(Jugador jugador) {
		super(jugador);
		jugador.manejadorDeAcciones.vaciar(jugador);
		jugador.manejadorDeAcciones.agregarAccion(AccionesDelJugador.finalizarTurno);
	}

	public void nuevoTurno(Jugador jugador) {
		jugador.cambiarEstado(EstadosPosiblesDelJugador.PreDados);
	}
}
