package modelo;

import org.junit.Test;

import modelo.Eventos.RetrocesoDinamico;

import org.junit.Assert;



public class RetrocesoDinamicoTest {
	private Barrio barrio() {//crea un barrio "placeholder" 
	    	return new Barrio(null, 0, 0, false, 0, 0, 0, 0,0);
	}
	 
    private void llenarTableroConCasillero(int inicio, int fin,Tablero tablero){
        for (int i = inicio; i > fin; i--) {
            tablero.agregarCasillero(barrio());
        }
    }
   
    private Jugador inicializarJugadorEnTableroConRetrocesoDinamico(RetrocesoDinamico retrocesoDinamico, Tablero tablero){
        tablero.agregarCasillero(retrocesoDinamico);
        Jugador jugador = new Jugador(100000, retrocesoDinamico, "Alfa", tablero);
        tablero.agregarJugador(jugador);
        return jugador;
    }

    @Test
    public void siElRestoDa0NoMeMuevo(){
        Tablero tablero = new Tablero();
        llenarTableroConCasillero(0,-7,tablero);

        RetrocesoDinamico retrocesoDinamico = new RetrocesoDinamico();
        Jugador jugador = inicializarJugadorEnTableroConRetrocesoDinamico(retrocesoDinamico,tablero);
        retrocesoDinamico.moverAlJugador(jugador, 8);

        Assert.assertEquals(retrocesoDinamico, jugador.casilleroActual());
    }

    @Test
    public void seTiroUn7YPorElCalculoRetrocede5(){
        Tablero tablero = new Tablero();

        Propiedad propiedad6 =barrio();
        tablero.agregarCasillero(propiedad6);

        llenarTableroConCasillero(-6,-10,tablero);

        RetrocesoDinamico retrocesoDinamico = new RetrocesoDinamico();
        Jugador jugador = inicializarJugadorEnTableroConRetrocesoDinamico(retrocesoDinamico,tablero);
        tablero.agregarCasillero(retrocesoDinamico);

        retrocesoDinamico.moverAlJugador(jugador, 7);
        Assert.assertEquals(propiedad6, jugador.casilleroActual());
    }

    @Test
    public void siTiro12Retrocedo10Casilleros(){
        Tablero tablero = new Tablero();

        Propiedad propiedad1 = barrio();
        tablero.agregarCasillero(propiedad1);


        llenarTableroConCasillero(-1,-10,tablero);

        RetrocesoDinamico retrocesoDinamico = new RetrocesoDinamico();
        Jugador jugador = inicializarJugadorEnTableroConRetrocesoDinamico(retrocesoDinamico,tablero);
        tablero.agregarCasillero(retrocesoDinamico);

        retrocesoDinamico.moverAlJugador(jugador, 12);

        Assert.assertEquals(propiedad1, jugador.casilleroActual());
    }

    @Test
    public void siTiro3YNoTengoPropiedadesMeMuevo3Espacios(){
        Tablero tablero = new Tablero();

        Propiedad propiedad1 =barrio();
        tablero.agregarCasillero(propiedad1);

        llenarTableroConCasillero(-1,-3,tablero);
        RetrocesoDinamico retrocesoDinamico = new RetrocesoDinamico();
        Jugador jugador = inicializarJugadorEnTableroConRetrocesoDinamico(retrocesoDinamico,tablero);
        tablero.agregarCasillero(retrocesoDinamico);

        retrocesoDinamico.moverAlJugador(jugador, 3);

        Assert.assertEquals(propiedad1, jugador.casilleroActual());
    }

    @Test
    public void siTiro3YTengo1PropiedadRetrocedo1Espacio(){
        Tablero tablero = new Tablero();

        Propiedad propiedad1 = barrio();
        tablero.agregarCasillero(propiedad1);

        llenarTableroConCasillero(-1,-2,tablero);

        RetrocesoDinamico retrocesoDinamico = new RetrocesoDinamico();
        Jugador jugador = inicializarJugadorEnTableroConRetrocesoDinamico(retrocesoDinamico,tablero);
        tablero.agregarCasillero(retrocesoDinamico);

        Propiedad propiedadJugador = barrio();
        jugador.agregarPropiedad(propiedadJugador);

        retrocesoDinamico.moverAlJugador(jugador, 3);

        Assert.assertEquals(propiedad1, jugador.casilleroActual());
    }
}