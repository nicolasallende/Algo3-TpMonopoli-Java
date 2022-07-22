package modelo;

import org.junit.Assert;
import org.junit.Test;

import modelo.Eventos.Carcel;
import modelo.Eventos.Policia;


public class PoliciaTest {

    @Test
    public void caerEnLaPoliciaTeLlevaALaCarcel(){
        Tablero tablero = new Tablero();
        Carcel carcel = new Carcel();
        Policia policia = new Policia(carcel);
        Jugador jugador = new Jugador(10000, (Casillero)policia,"Gaston",tablero);
        policia.jugadorCayo(jugador);
        Assert.assertEquals(carcel , jugador.casilleroActual());
    }
    /*
    @Test
    public void caerEnPoliciaTeCambiaElEstadoAPreso() {
    	Tablero tablero = new Tablero();
        Carcel carcel = new Carcel();
        Policia policia = new Policia(carcel);
        Jugador jugador = new Jugador(10000, (Casillero)policia,"Gaston",tablero);
        policia.jugadorCayo(jugador);
        System.out.println(jugador.estado.toString());
    }
*/
}