package modelo.Eventos;

import modelo.Jugador;

public class ImpuestoAlLujo extends Evento {
	private static String nombre = "Impuesto Al Lujo";
	private static double porcentajeADescontar = 0.10;

	 public ImpuestoAlLujo() {
		 super();
	 }
	
	@Override
	public String getNombre() {
		return nombre;
	}
	 
	public void calcularDineroConImpuesto(Jugador jugador, long dineroJugador) {
		long dineroARestar = (long) (dineroJugador*porcentajeADescontar);
		jugador.pagarDinero(dineroARestar);
	}
	
	
	public void aplicarEfecto(Jugador jugador) {
		long dineroJugador = jugador.dinero();
		calcularDineroConImpuesto(jugador, dineroJugador);
	}

}
