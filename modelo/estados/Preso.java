package modelo.estados;


import modelo.Jugador;
import modelo.acciones.AccionesDelJugador;

public  class Preso extends EstadoJugador{
	public Preso(Jugador jugador) {
		super(jugador);
		jugador.manejadorDeAcciones.vaciar(jugador);
		jugador.manejadorDeAcciones.agregarAccion(AccionesDelJugador.finalizarTurno);
		//puede intercambiar?
	}
	
	public void nuevoTurno(Jugador jugador) {
		jugador.cambiarEstado(EstadosPosiblesDelJugador.presoPuedeFianzar);
	}
}
