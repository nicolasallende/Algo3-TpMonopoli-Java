package modelo.acciones;

import modelo.*;

public class Comprar extends Accion{

	@Override
	public void hacer(Tablero tablero, Jugador jugador) {
		jugador.comprarPropiedad();
	}

}
