package modelo.acciones;

import modelo.Barrio;
import modelo.Jugador;
import modelo.Tablero;


public class Edificar extends Accion {
	private String nombreBarrio = null;

	@Override
	public void hacer(Tablero tablero, Jugador jugador) {
		Barrio barrio = (Barrio)tablero.obtenerCasilleroRelacionado(this.nombreBarrio);
		barrio.construir();
		jugador.pagarDinero(barrio.precioConstruccion());
	}

	public void setBarrioAEdificar(String nombreBarrio) {
		this.nombreBarrio = nombreBarrio;
	}
}
