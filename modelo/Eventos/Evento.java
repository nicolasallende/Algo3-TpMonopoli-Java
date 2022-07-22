package modelo.Eventos;


import modelo.Casillero;
import modelo.Jugador;

public abstract class Evento extends Casillero {

	public Evento(){
		super();
	}

	public abstract void aplicarEfecto(Jugador jugador);

	public void jugadorCayo(Jugador jugador){
		this.aplicarEfecto(jugador);
	}

	public abstract String getNombre();
}