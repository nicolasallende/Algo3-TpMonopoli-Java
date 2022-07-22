package modelo.estados;

import modelo.Jugador;
import modelo.acciones.AccionesDelJugador;

public class EnDeuda extends EstadoJugador{
	public EnDeuda(Jugador jugador) {
		super(jugador);
		jugador.manejadorDeAcciones.vaciar(jugador);
		jugador.manejadorDeAcciones.agregarAccion(AccionesDelJugador.vender);	
		//jugador.manejadorDeAcciones.agregarAccion();
	}
	
}
