package modelo.Eventos;

import modelo.Jugador;

public class Policia extends Evento {
    private Carcel carcel;
    private static String nombre = "Policia";

    public Policia( Carcel prision){
        super();
        carcel = prision;
    }

    public void aplicarEfecto(Jugador jugador) {
        jugador.cambiarCasillero(this.carcel);
    }

    @Override
    public String getNombre() {
        return nombre;
    }

}