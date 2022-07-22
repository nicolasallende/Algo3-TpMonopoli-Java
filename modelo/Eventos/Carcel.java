package modelo.Eventos;


import modelo.Jugador;
import modelo.estados.EstadosPosiblesDelJugador;

public class Carcel extends Evento {
	private static String nombre = "Carcel";

	public Carcel(){
		super();
    }
	
	public void aplicarEfecto(Jugador jugador) {
		 jugador.cambiarEstado(EstadosPosiblesDelJugador.preso);
	}

	@Override
	public String getNombre() {
		return nombre;
	}


}
