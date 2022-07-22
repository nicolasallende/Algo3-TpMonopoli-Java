package modelo.acciones;

import modelo.Jugador;
import modelo.Tablero;

public abstract class Accion {
	
	/*
	tirarDados , pagarFianza , vender , comprar , finalizarTurno,
	intercambiar , edificar
	*/
	public abstract void hacer(Tablero tablero , Jugador jugador) ;
			
	
}
