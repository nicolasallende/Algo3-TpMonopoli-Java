package modelo;

import modelo.Eventos.Salida;
import modelo.carga.CargaTablero;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class Juego {
    private Tablero tablero;
    private CargaTablero carga;
    private static int CANT_JUGADORES = 3;
    //Cada jugador comenzara con la misma cantidad de dinero equivalente a $100000
    private final long dineroInicial = 100000;

    public void agregarjugadores() {
        this.tablero.agregarJugador(new Jugador(dineroInicial, new Salida(),"Verde", tablero));
        this.tablero.agregarJugador(new Jugador(dineroInicial, new Salida(),"Rojo", tablero));
        this.tablero.agregarJugador(new Jugador(dineroInicial, new Salida(),"Azul", tablero));
    }

    public Juego() throws JAXBException, IOException {
        tablero = new Tablero();
        tablero.cargaCasilleros();
        agregarjugadores();
        tablero.nuevoTurno();
    }

    public boolean juegoTerminado(){
        return tablero.cantidadJugadores() == 1;
    }

    public List<Jugador> getJugadores(){
        return this.tablero.getJugadores();
    }

    public int getCantJugadores(){
        return tablero.cantidadJugadores();
    }

    public Tablero getTablero(){
        return this.tablero;
    }

    public boolean jugadorVuelveATirar(){
        return this.tablero.getDados().jugadorVuelveATirar();
    }

}
