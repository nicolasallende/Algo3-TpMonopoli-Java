package modelo;

import java.io.IOException;
import java.util.*;

import modelo.carga.CargaTablero;
import modelo.estados.EstadosPosiblesDelJugador;
import vista.MensajeAJugador;

import javax.xml.bind.JAXBException;

/*
 * Se encarga de coordinar los turnos ,
 * contiene los casilleros
 * 
 */
public class Tablero {
	private ArrayList<Jugador> jugadores;
	private int indexJugadores;
	private List<Casillero> casilleros;
	private static int cantCasilleros = 20;
	private Jugador jugadorActual ;
	private Dados dados;
	private int valorDelUltimoTiro= 0 ;
	private boolean hayUnGanador = false;
	private static String MENSAJE_OTRO_TIRO = "Saco dobles vuelve a tirar";
	private static String SIN_MENSAJE = "";
	private static int cantJugadoresConDinero = 0;     //para los test de ganar partida
	

 	public Tablero() {
		this.casilleros = new ArrayList<Casillero>(cantCasilleros);
		this.jugadores = new ArrayList<Jugador>();
		this.dados = new Dados();
		this.indexJugadores = 0;
	}

	public void nuevoTurno() {
		this.dados.inicializarDados();
		jugadorActual = this.jugadores.get(this.indexJugadores);
		jugadorActual.nuevoTurno();
	
	}

	//Metodo solo para testing
	public List<Casillero> getCasilleros() {
		return this.casilleros;
	}

	public Barrio buscarBarrioRelacionado(String nombreCasillero){
		String tags[] = nombreCasillero.split(" ");
		String nombreAbuscar = tags[0];
		for (int i = 1; i < tags.length - 1; i++) {
			nombreAbuscar +=  " " + tags[i];
		}
		Casillero casilleroRelacionado = null;
		for (Casillero casillero: casilleros){
			if (casillero.containNameCasillero(nombreAbuscar) && ! casillero.getNombre().equals(nombreCasillero)){
				casilleroRelacionado = casillero;
				break;
			}
		}
		return (Barrio)casilleroRelacionado;
	}
	
	public Casillero obtenerCasilleroRelacionado(String nombreCasillero) {
		Casillero casilleroBuscado = null;
		for (Casillero casillero: casilleros) {
			if( casillero.getNombre().equals(nombreCasillero)) {
				 casilleroBuscado = casillero;
				 break;
			}
		}
		return casilleroBuscado;
	}

	public void cargaCasilleros() throws JAXBException, IOException {
		CargaTablero carga = new CargaTablero();
		this.casilleros = carga.obtenertablero();
	}

	public void tirarDados() {
		valorDelUltimoTiro = this.dados.tirarDados();
		this.moverJugadorEnCasillero(valorDelUltimoTiro, jugadorActual);
	}

	public void cambiarDeJugador(){
		if ( this.indexJugadores < this.jugadores.size() - 1 ){
			this.indexJugadores += 1;
		} else {
			this.indexJugadores = 0;
		}
	}

	public void finalizarTurno() {
		this.cambiarDeJugador();
		this.nuevoTurno();
	}
	
	//Metodo hecho solo para el test de partida 
	public void moverjugador(int numero) {
		this.moverJugadorEnCasillero(numero, jugadorActual);
	}
	
	public boolean partidoTermino() {
		if(this.partidaTieneUnGanador() != null) {
			this.hayUnGanador = true;
		}
		return this.hayUnGanador;
	}
	

	public Jugador partidaTieneUnGanador() {
		int j = 0;
		for (int i = 0; i < this.jugadores.size(); i++) {
			if(jugadores.get(i).dinero() > 0) {
				cantJugadoresConDinero ++;
				j = i;
			}
		}
		if (cantJugadoresConDinero == 1) {
			cantJugadoresConDinero = 0;
			return jugadores.get(j);
		}
		else {
			cantJugadoresConDinero = 0;
			return null;
		}
		
	}

	public void moverJugadorEnCasillero(int cantPosicionesAMover, Jugador jugador){
		int posicion = this.posicionJugadorEnTablero(jugador.casilleroActual());
		if ( (posicion + cantPosicionesAMover)  < cantCasilleros  ){
			int nuevaPosicion = posicion + cantPosicionesAMover;
			jugador.cambiarCasillero(casilleros.get(nuevaPosicion));
		} else {
			int posicionSobrepasada = (posicion + cantPosicionesAMover);
			int posiscionDesdeSalida =  posicionSobrepasada - cantCasilleros;
			jugador.cambiarCasillero(casilleros.get(posiscionDesdeSalida));
		}
	}

	public int posicionJugadorEnTablero(Casillero casillero){
		Casillero otroCasillero = this.casilleros.get(0);
		int posicionCasillero = 0;
		while(! casillero.getNombre().equals(otroCasillero.getNombre())){
			posicionCasillero += 1;
			otroCasillero = this.casilleros.get(posicionCasillero);
		}
		return posicionCasillero;
	}

	
	public String vuelveATirar() {
		if(dados.jugadorVuelveATirar()) {
			jugadorActual.cambiarEstado(EstadosPosiblesDelJugador.PreDados);
			return MENSAJE_OTRO_TIRO;
		}
		return SIN_MENSAJE;
		
	}
	//Getters &  Setters
	public Dados getDados() {
		return this.dados;
	}
	
	public int cantidadJugadores(){
		return this.jugadores.size();
	}
	
	public List<Jugador> getJugadores(){
 		return this.jugadores;
	}
	
	public Jugador obtenerJugadorActual() {
		return this.jugadorActual;
	}

 	public int ultimoTiroRealizado() {
		return valorDelUltimoTiro;
	}
 	//Metodos agregacion
	public void agregarJugador(Jugador jugador) {
		this.jugadores.add(jugador);
	}
	
	public void agregarCasillero(Casillero casillero) {
		this.casilleros.add(casillero);
	}
	
	public void quitarJugador(Jugador jugador) {
		this.jugadores.remove(jugador);
	}
}
	

