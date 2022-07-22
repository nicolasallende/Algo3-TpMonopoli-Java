package modelo;

import java.util.Hashtable;

public abstract class Casillero {
	public Hashtable<String,String> personasAdentro;

	public Casillero() {
		personasAdentro = new Hashtable<String, String>();
	}


	public abstract void jugadorCayo(Jugador jugador);


	public void jugadorCayo(String nombreJugador, Jugador jugador) {
		jugadorCayo(jugador);
		this.personasAdentro.put(nombreJugador, "-");
	}

	public abstract String getNombre();

	public boolean containNameCasillero(String nameOtroCasillero){
		String nombreCasillero = getNombre();
		return nombreCasillero.contains(nameOtroCasillero);
	}

	public void desocuparCasillero(String nombre){
		this.personasAdentro.remove(nombre);
	}

}
