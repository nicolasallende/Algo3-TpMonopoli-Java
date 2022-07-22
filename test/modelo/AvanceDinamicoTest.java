package modelo;

import modelo.Eventos.AvanceDinamico;
import org.junit.Assert;
import org.junit.Test;

public class AvanceDinamicoTest {
	
	//Constantes
	final long dineroInicialJugador = 100000;
	
	public Barrio inicializarBarrioSimple() {
        Barrio barrio = new Barrio("Nombre",5,4000,false,3500,0, 0, 0,0);
        return barrio;
    }

	private void llenarTableroConCasillero(int inicio, int fin,Tablero tablero){
		for (int i = inicio; i < fin; i++) {
			tablero.agregarCasillero(inicializarBarrioSimple());
		}
	}

	private Jugador inicializarJugadorEnTableroConAvanceDinamico(AvanceDinamico avanceDinamico, Tablero tablero){
		tablero.agregarCasillero(avanceDinamico);
		Jugador jugador = new Jugador(dineroInicialJugador, avanceDinamico, "Bravo", tablero);
		tablero.agregarJugador(jugador);
		return jugador;
	}

	@Test
	public void seTiroUn2YNoSeMueveDeLugar() {
		Tablero tablero = new Tablero();
		llenarTableroConCasillero(0,7,tablero);
		AvanceDinamico avanceDinamico = new AvanceDinamico();
		Jugador jugador = inicializarJugadorEnTableroConAvanceDinamico(avanceDinamico,tablero);
		avanceDinamico.moverAlJugador(jugador, 2);
		Assert.assertEquals(avanceDinamico, jugador.casilleroActual());
	}

	@Test
	public void seTiroUn6YSeMueve4Lugares() {
		Tablero tablero = new Tablero();
		llenarTableroConCasillero(0,7,tablero);
		AvanceDinamico avanceDinamico = new AvanceDinamico();
		Jugador jugador = inicializarJugadorEnTableroConAvanceDinamico(avanceDinamico,tablero);

		llenarTableroConCasillero(8,11,tablero);
		Propiedad propiedad12 = inicializarBarrioSimple();
		tablero.agregarCasillero(propiedad12);

		avanceDinamico.moverAlJugador(jugador, 6);
		Assert.assertEquals(propiedad12, jugador.casilleroActual());
	}
	
	@Test
	public void seTiroUn7YPorElCalculoSeMueve5(){
		Tablero tablero = new Tablero();
		llenarTableroConCasillero(0,7,tablero);
		AvanceDinamico avanceDinamico = new AvanceDinamico();
		Jugador jugador = inicializarJugadorEnTableroConAvanceDinamico(avanceDinamico,tablero);

		llenarTableroConCasillero(8,12,tablero);
		Propiedad propiedad13 = inicializarBarrioSimple();
		tablero.agregarCasillero(propiedad13);

		avanceDinamico.moverAlJugador(jugador, 7);
		Assert.assertEquals(propiedad13, jugador.casilleroActual());
	}

	
	@Test
	public void seTiroUn10YPorElCalculoNoSeMueve(){
		Tablero tablero = new Tablero();
		llenarTableroConCasillero(0,7,tablero);
		AvanceDinamico avanceDinamico = new AvanceDinamico();
		Jugador jugador = inicializarJugadorEnTableroConAvanceDinamico(avanceDinamico,tablero);

		avanceDinamico.moverAlJugador(jugador, 10);
		Assert.assertEquals(avanceDinamico, jugador.casilleroActual());
	}
	
	@Test
	public void seTiroUn12YComoElJugadorNoTienePropiedadesSeMueve12(){
		Tablero tablero = new Tablero();
		llenarTableroConCasillero(0,7,tablero);;
		AvanceDinamico avanceDinamico = new AvanceDinamico();
		Jugador jugador = inicializarJugadorEnTableroConAvanceDinamico(avanceDinamico,tablero);

		llenarTableroConCasillero(8,19,tablero);
		Propiedad propiedad20 = inicializarBarrioSimple();
		tablero.agregarCasillero(propiedad20);
		
		avanceDinamico.moverAlJugador(jugador, 12);
		Assert.assertEquals(propiedad20, jugador.casilleroActual());
	}
	
	@Test
	public void seTiroUn11YComoElJugadorTiene1PropiedadSeMueve10(){
		Tablero tablero = new Tablero();
		llenarTableroConCasillero(0,7,tablero);
		AvanceDinamico avanceDinamico = new AvanceDinamico();
		Jugador jugador = inicializarJugadorEnTableroConAvanceDinamico(avanceDinamico,tablero);


		Propiedad propiedad9 = inicializarBarrioSimple();
		tablero.agregarCasillero(propiedad9);
		jugador.agregarPropiedad(propiedad9);

		llenarTableroConCasillero(9,17,tablero);
		Propiedad propiedad18 = inicializarBarrioSimple();
		tablero.agregarCasillero(propiedad18);

		avanceDinamico.moverAlJugador(jugador, 11);
		Assert.assertEquals(propiedad18, jugador.casilleroActual());
	}

}

