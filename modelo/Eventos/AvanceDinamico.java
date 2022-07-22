package modelo.Eventos;

import java.util.HashMap;

import modelo.*;

public class AvanceDinamico extends Evento {
	private HashMap< Integer , MovimientoDinamico> dicAvanceDinamico;
	private static String nombre = "Avance Dinamico";
	private int movimiento = 0;

	public AvanceDinamico() {
		super();
		dicAvanceDinamico = new HashMap < Integer, MovimientoDinamico>();
		
		RestarDosPasos restarDosPasos = new RestarDosPasos();
		for (int i = 2 ; i <= 6 ; i++) {
			dicAvanceDinamico.put(i, restarDosPasos);
		}
		
		MovimientoEnBaseALaPlata dividirPorLaPlata = new MovimientoEnBaseALaPlata();
		for (int i = 7 ; i <= 10 ; i++) {
			dicAvanceDinamico.put(i, dividirPorLaPlata);
		}
		RestarLasPropiedades restarLasPropiedades = new RestarLasPropiedades();
		for (int i = 11 ; i <= 12  ; i++) {
			dicAvanceDinamico.put(i, restarLasPropiedades);
		}
	}
	
	public int calcularNuevoMovimiento(Jugador jugador, int ultimoTiro) {
		return 	(dicAvanceDinamico.get(ultimoTiro)).calcularMovimiento(ultimoTiro, jugador);
		
	}
		
	public void moverAlJugador(Jugador jugador, int ultimoTiro) {
		this.movimiento = calcularNuevoMovimiento(jugador, ultimoTiro);
		if(this.movimiento > 0) jugador.moverse(this.movimiento, jugador);
	}
	
	@Override
	public void aplicarEfecto(Jugador jugador) {
		int ultimoTiro = jugador.ultimoTiroRealizado();
		moverAlJugador( jugador, ultimoTiro);
	}

	@Override
	public String getNombre() {
		return nombre;
	}


	public int getMovimiento(){
		return this.movimiento;
	}
}
	