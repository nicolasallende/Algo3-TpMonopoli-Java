package modelo.acciones;

import modelo.Jugador;
import modelo.Tablero;

public class FinalizarTurno extends Accion{

	@Override
	public void hacer(Tablero tablero, Jugador jugador) {
		tablero.finalizarTurno();
		System.out.println("Jugador que el toca es " + tablero.obtenerJugadorActual().nombre());
	}

}
