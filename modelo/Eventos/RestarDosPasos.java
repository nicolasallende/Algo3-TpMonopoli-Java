package modelo.Eventos;

import modelo.Jugador;

public class RestarDosPasos extends MovimientoDinamico {

	private int restarDosPasos = 2;
	
	public int calcularMovimiento(int ultimoTiro, Jugador jugador) {
		return (ultimoTiro - restarDosPasos);
	}
}
