package modelo.Eventos;

import modelo.Jugador;

public class MovimientoEnBaseALaPlata extends MovimientoDinamico {

	public int calcularMovimiento(int ultimoTiro, Jugador jugador) {
		return (int)(jugador.dinero() % ultimoTiro);
	}
	
}
