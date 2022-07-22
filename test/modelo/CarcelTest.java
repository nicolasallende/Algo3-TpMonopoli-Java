package modelo;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import modelo.Eventos.Carcel;
import modelo.acciones.AccionesDelJugador;

public class CarcelTest {

long dineroInicial=100000;
	
	public void inicializacion(Carcel carcel ,Jugador jugador,Tablero tablero,int cantTurnos) throws JAXBException, IOException {
		tablero.agregarJugador(jugador);
		tablero.cargaCasilleros();
		carcel.jugadorCayo(jugador);
		for(int i=0 ;i < cantTurnos ; i++) {
			tablero.nuevoTurno();
		}
	}

	@Test
	public void noPuedeMoverseSiEstaEnLaCarcel() throws JAXBException, IOException {
		Carcel carcel=new Carcel();
		Tablero tablero=new Tablero();		
		Jugador jugador = new Jugador(dineroInicial, (Casillero)carcel,"Julio",tablero);
		inicializacion(carcel,jugador,tablero,1);
		Assert.assertTrue(!jugador.hacer(AccionesDelJugador.tirarDados));
	}

	@Test
	public void luegoDePagarFianzaPuedeMoverse() throws JAXBException, IOException {
		Carcel carcel=new Carcel();
		Tablero tablero=new Tablero();		
		Jugador jugador = new Jugador(dineroInicial, (Casillero)carcel,"Julio",tablero);
		inicializacion(carcel,jugador,tablero,2);
		jugador.hacer(AccionesDelJugador.pagarFianza);
		Assert.assertTrue(jugador.hacer(AccionesDelJugador.tirarDados));
	}
	@Test
	public void puedePagarFianzaSiElTurnoEs2o3() throws JAXBException, IOException {
		Carcel carcel=new Carcel();
		Tablero tablero=new Tablero();		
		Jugador jugador = new Jugador(dineroInicial, (Casillero)carcel,"Julio",tablero);
		inicializacion(carcel,jugador,tablero,2);
		Assert.assertTrue(jugador.hacer(AccionesDelJugador.pagarFianza));
	}

	@Test
	public void siPasan4TurnosPuedeMoverse() throws JAXBException, IOException {
		Carcel carcel=new Carcel();
		Tablero tablero=new Tablero();		
		Jugador jugador = new Jugador(dineroInicial, (Casillero)carcel,"Julio",tablero);
		inicializacion(carcel,jugador,tablero,4);
		Assert.assertTrue(jugador.hacer(AccionesDelJugador.tirarDados));

	}
}
