package modelo.estados;

import java.util.Hashtable;

import modelo.Jugador;
import modelo.acciones.AccionesDelJugador;
import modelo.estados.EstadosPosiblesDelJugador;

public  class PresoPuedeFianzar extends EstadoJugador{
	private Hashtable <Jugador,Double> presos;
	private final long fianza=45000;
	private final int sentenciaTurnos = 3;
	public PresoPuedeFianzar(Jugador jugador){
		super(jugador);
		jugador.manejadorDeAcciones.vaciar(jugador);
		jugador.manejadorDeAcciones.agregarAccion(AccionesDelJugador.pagarFianza);
		jugador.manejadorDeAcciones.agregarAccion(AccionesDelJugador.finalizarTurno);
		presos = new Hashtable<>();
	}
	@Override
	public void nuevoTurno(Jugador jugador) {
		if(jugador.dinero()<fianza) jugador.manejadorDeAcciones.remover(AccionesDelJugador.pagarFianza);
		else if(!jugador.manejadorDeAcciones.contiene(AccionesDelJugador.pagarFianza))jugador.manejadorDeAcciones.agregarAccion(AccionesDelJugador.finalizarTurno);
		presos.putIfAbsent(jugador, (double) 2);
		presos.replace(jugador, presos.get(jugador)+1);
		if(presos.get(jugador)>sentenciaTurnos) {
			jugador.cambiarEstado(EstadosPosiblesDelJugador.PreDados);
			presos.put(jugador, (double) 1);
		}
	}
}
