package modelo.Eventos;

import modelo.Jugador;

public abstract class MovimientoDinamico {

	public abstract int calcularMovimiento(int ultimoTiro, Jugador jugador);
	
}
