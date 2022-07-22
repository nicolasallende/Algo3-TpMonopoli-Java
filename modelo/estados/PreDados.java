package modelo.estados;


import modelo.Barrio;
import modelo.Casillero;
import modelo.Jugador;
import modelo.Propiedad;
import modelo.acciones.AccionesDelJugador;

public  class PreDados extends EstadoJugador {
	private String accion = "Pre Dados";

	public PreDados(Jugador jugador) {
		super(jugador);
		jugador.manejadorDeAcciones.vaciar(jugador);
		jugador.manejadorDeAcciones.agregarAccion(AccionesDelJugador.tirarDados);

		if (jugador.sePuedeIntecambiarConOtroJugador()){
			jugador.manejadorDeAcciones.agregarAccion(AccionesDelJugador.intercambiar);
		}
		
		Casillero casillero = jugador.casilleroActual();
		if ( casillero instanceof Propiedad && ((Propiedad)casillero).esComprable(jugador) )	jugador.manejadorDeAcciones.agregarAccion(AccionesDelJugador.comprar);
		

		if (jugador.puedeEdificarEnPropiedades()) {
			jugador.manejadorDeAcciones.agregarAccion(AccionesDelJugador.edificar);
		}
		if(jugador.cantidadDePropiedadesPoseidas() > 0) jugador.manejadorDeAcciones.agregarAccion(AccionesDelJugador.vender);

	}

	public String toString(){
		return this.accion;
	}
}
