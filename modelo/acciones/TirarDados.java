package modelo.acciones;

import modelo.Jugador;
import modelo.Tablero;
import modelo.estados.EstadosPosiblesDelJugador;

public class TirarDados extends Accion {

	@Override
	public void hacer(Tablero tablero, Jugador jugador) {
		jugador.cambiarEstado(EstadosPosiblesDelJugador.postDados);
		tablero.tirarDados();
	}

	
}
