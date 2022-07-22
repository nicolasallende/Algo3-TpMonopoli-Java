package modelo.acciones;

import java.util.ArrayList;
import java.util.List;

import modelo.Barrio;
import modelo.Jugador;
import modelo.Propiedad;
import modelo.Tablero;

public class ManejadorDeAcciones {
	
	public boolean correctamenteInicializado = false;
	
	Jugador jugador;
	Tablero tablero;
	
	TirarDados tirarDados = new TirarDados();
	PagarFianza pagarFianza = new PagarFianza();
	Vender vender = new Vender();
	Comprar comprar = new Comprar();
	FinalizarTurno finalizarTurno =new FinalizarTurno();
	Intercambiar intercambiar = new Intercambiar();
	Edificar edificar = new Edificar();

	private ArrayList<AccionesDelJugador> acciones=new ArrayList<AccionesDelJugador>();
	
	public ManejadorDeAcciones(Jugador j,Tablero t) {
		this.jugador = j;
		this.tablero = t;
	}
	
	public ManejadorDeAcciones() {
		// para poder inicializar jugador
	}

	public void setElementoAEdificar(String nombreBarrio){
		this.edificar.setBarrioAEdificar(nombreBarrio);
	}

	public void init(Jugador j,Tablero t) {
		this.jugador = j;
		this.tablero = t;
		correctamenteInicializado = true;
	}
	
	public void agregarAccion(AccionesDelJugador accion) {
		this.acciones.add(accion);
	}

	public boolean hacer(AccionesDelJugador accion) {
		switch(accion) {
		case comprar:
			if(acciones.contains(accion)) {
				this.comprar.hacer(tablero, jugador);
				this.acciones.remove(AccionesDelJugador.comprar);
				if (jugador.sePuedeIntecambiarConOtroJugador()){
					jugador.manejadorDeAcciones.agregarAccion(AccionesDelJugador.intercambiar);
				}
				if (jugador.puedeEdificarSinContarLaUtimaPropiedad()){
					this.acciones.add(AccionesDelJugador.edificar);
				}
				return true;
			}
			break;
		case edificar:
			if(acciones.contains(accion)) {
				this.edificar.hacer(tablero, jugador);
				if (! jugador.puedeEdificarEnPropiedades()){
					this.acciones.remove(AccionesDelJugador.edificar);
				}
				return true;
			}
			break;
		case finalizarTurno:
			if(acciones.contains(accion)) {
				this.finalizarTurno.hacer(tablero, jugador);
				return true;
			}
			break;
		case intercambiar:
			if(acciones.contains(accion)) {
				this.intercambiar.hacer(tablero, jugador);
				if (! jugador.puedeEdificarEnPropiedades()){
					this.acciones.remove(AccionesDelJugador.edificar);
				}
				return true;
			}
			break;
		case pagarFianza:
			if(acciones.contains(accion)) {
				this.pagarFianza.hacer(tablero, jugador);
				return true;
			}
			break;
		case tirarDados:
			if(acciones.contains(accion)) {
				this.tirarDados.hacer(tablero, jugador);
				return true;
			}
			break;
		case vender:
			if(acciones.contains(accion)) {
				this.vender.hacer(tablero, jugador);
				if ( jugador.getPropiedades().isEmpty() ){
					this.acciones.remove(AccionesDelJugador.vender);
				}
				if ( jugador.getNombreBarriosAEdificar().isEmpty()){
					this.acciones.remove(AccionesDelJugador.edificar);
				}
				return true;
			}
			break;
		default:
			return false;
		}
		return false;
	}
	
	public void vaciar(Jugador jugador) {
		this.acciones = new ArrayList<AccionesDelJugador>();
	}

	public void remover(AccionesDelJugador accion) {
		this.acciones.remove(accion);
		
	}

	public boolean contiene(AccionesDelJugador accion) {
		this.acciones.contains(accion);
		return false;
	}
	public ArrayList<AccionesDelJugador> getAcciones(){
		return this.acciones;
	}

	public void setElementoAvender(String elementoAvender) {
		this.vender.setBarrioAVender(elementoAvender);
	}
}
