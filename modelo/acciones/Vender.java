package modelo.acciones;

import java.util.ArrayList;

import modelo.Casillero;
import modelo.Jugador;
import modelo.Propiedad;
import modelo.Tablero;
import vista.OpcionesVender;

public class Vender extends Accion {
	private String nombrePropiedad = null;

	public void setBarrioAVender(String barrioAVender) {
		this.nombrePropiedad = barrioAVender;
	}

	@Override
	public void hacer(Tablero tablero, Jugador jugador) {
		Propiedad propiedadElegida = (Propiedad) tablero.obtenerCasilleroRelacionado(this.nombrePropiedad);
		jugador.venderPropiedad(propiedadElegida);
	}
}
