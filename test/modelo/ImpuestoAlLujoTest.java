package modelo;


import org.junit.Assert;
import org.junit.Test;

import modelo.Eventos.ImpuestoAlLujo;


public class ImpuestoAlLujoTest {

	@Test
	public void CayoElJugadorYSeReduceEnUn10PorcientoSuDinero() {
		Tablero tablero = new Tablero();
		ImpuestoAlLujo impuestoAlLujo = new ImpuestoAlLujo(); 
		Jugador jugador = new Jugador(100000, impuestoAlLujo , "Bravo", tablero);
		impuestoAlLujo.aplicarEfecto(jugador);
		long tieneQueDar = 90000;
		Assert.assertEquals(tieneQueDar, jugador.dinero());
	
	}

}
