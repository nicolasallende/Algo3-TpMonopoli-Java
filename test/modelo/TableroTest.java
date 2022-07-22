package modelo;

import modelo.Eventos.Salida;
import org.junit.Assert;
import org.junit.Test;

import modelo.Tablero;

public class TableroTest {

	private static long precio =0,
						construirCasa = 0,
						alquilar1Casa = 0,
						alquilar2Casas = 0,
						construirHotel = 0,
						alquilarHotel = 0,
					    precioAlquiler = 0;
	private static  boolean relacionado = false;

	private Barrio inicializarBarrio(String nombre){
		return  new Barrio(nombre, precio, construirCasa, relacionado, alquilar1Casa, alquilar2Casas, construirHotel, alquilarHotel, precioAlquiler);
	}

	private Jugador inicializarJugadorEnTablero(Casillero posicionJugador, Tablero tablero, String nombreJugador){
		tablero.agregarCasillero(posicionJugador);
		Jugador jugador = new Jugador(100000, posicionJugador, nombreJugador, tablero);
		tablero.agregarJugador(jugador);
		return jugador;
	}

	
	private void llenarTableroConCasillero(int inicio, int fin,Tablero tablero){
		for (int i = inicio; i < fin; i++) {
			Barrio barrio = inicializarBarrio("barrio"+i);
			tablero.agregarCasillero(barrio);
		}
	}
	
    @Test
    public void seObtieneLaPosicionDelJugadorPasandoleElCasilleroDondeSeEncuentra() {
    	Tablero tablero = new Tablero();
    	Casillero posicion = inicializarBarrio("barrio19");
    	llenarTableroConCasillero(0, 19, tablero);
    	tablero.agregarCasillero(posicion);
    	Assert.assertEquals(19, tablero.posicionJugadorEnTablero(posicion));
    }
    

    @Test
    public void elJugadorEstabaEnElCasillero4YSeMueveAl10() {
    	Tablero tablero = new Tablero();
    	llenarTableroConCasillero(1, 4, tablero);
    	Casillero posicion4 = inicializarBarrio("barrio4");
    	Jugador jugador = inicializarJugadorEnTablero(posicion4, tablero, "Pablo");
    	Assert.assertEquals(posicion4, jugador.casilleroActual());
    	llenarTableroConCasillero(5, 10, tablero);
    	Casillero posicion10 =  inicializarBarrio("barrio10");
    	tablero.agregarCasillero(posicion10);
    	tablero.moverJugadorEnCasillero(6, jugador);
    	Assert.assertEquals(posicion10, jugador.casilleroActual());
    	
    }
    
    @Test
    public void elJugadorEstabaEnElCasillero19YSeMueveAl1() {
    	Tablero tablero = new Tablero();
    	Casillero posicion1 =  inicializarBarrio("barrio1");
    	tablero.agregarCasillero(posicion1);
    	llenarTableroConCasillero(2, 19, tablero);
    	Casillero posicion19 =  inicializarBarrio("barrio19");
    	Jugador jugador =  inicializarJugadorEnTablero(posicion19, tablero, "Pablo");
    	Casillero posicion20 =  inicializarBarrio("barrio20");
    	tablero.agregarCasillero(posicion20);
    	tablero.moverJugadorEnCasillero(2, jugador);
    	Assert.assertEquals(posicion1, jugador.casilleroActual());
    }


    @Test
	public void luegoDeUnTurnoEnteroLeTocaDeNuevoAlPrimerJugador(){
		Tablero tablero = new Tablero();
		String nombre1erJugador = "Pablo";
		Jugador jugador1 = inicializarJugadorEnTablero(new Salida(), tablero, nombre1erJugador);
		Jugador jugador2 = inicializarJugadorEnTablero(new Salida(), tablero, "Martin");
		Jugador jugador3 = inicializarJugadorEnTablero(new Salida(), tablero, "Dario");

		//Le toca al primer jugador
		tablero.nuevoTurno();
		tablero.finalizarTurno();

		//Le toca al segundo jugador
		tablero.nuevoTurno();
		tablero.finalizarTurno();

		//Le toca al tercer Jugador
		tablero.nuevoTurno();
		tablero.finalizarTurno();

		//Le toca al primer Jugador de nuevo
		tablero.nuevoTurno();
		Jugador jugadorActual = tablero.obtenerJugadorActual();
		Assert.assertEquals(nombre1erJugador, jugadorActual.nombre());
	}

}
