package modelo.Eventos;

import modelo.Jugador;

public class RestarLasPropiedades extends MovimientoDinamico {
	
	public int calcularMovimiento(int ultimoTiro, Jugador jugador) {
		return (ultimoTiro - jugador.cantidadDePropiedadesPoseidas());//falta calcular las casas/hoteles
	}

}
