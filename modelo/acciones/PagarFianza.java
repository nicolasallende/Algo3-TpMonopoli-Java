package modelo.acciones;

import modelo.Jugador;
import modelo.Tablero;
import modelo.estados.EstadosPosiblesDelJugador;

public class PagarFianza extends Accion {
	final long fianza = 45000 ;
	@Override
	public void hacer(Tablero tablero, Jugador jugador) {
		jugador.pagarDinero(fianza);
		jugador.cambiarEstado(EstadosPosiblesDelJugador.PreDados);
	}

}
