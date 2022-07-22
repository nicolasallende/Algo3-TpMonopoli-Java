package modelo.Eventos;

import modelo.Casillero;
import modelo.Jugador;

public class Salida extends Casillero {
    private static String nombre = "Salida";

    public Salida(){
        super();
    }

    @Override
    public void jugadorCayo(Jugador jugador) {
        //En caso de la salida no va a hacer nada.
    }

    @Override
    public String getNombre() {
        return nombre;
    }
}
