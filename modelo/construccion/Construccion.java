package modelo.construccion;

import modelo.Barrio;
import modelo.Jugador;

public abstract class Construccion {

    public abstract Construccion cambiar(Barrio barrio);

    public int cambiarEstado(EstadoConstruccion[] estados, int indexConstruccion){
        if ( indexConstruccion < estados.length -1 ){
            indexConstruccion += 1;
        }
        return indexConstruccion;
    }

    public abstract EstadoConstruccion estado();

    public abstract boolean tieneCapacidad(Barrio barrio,Jugador jugador);

    public abstract boolean estoyEnCondicionesDe(EstadoConstruccion estadoAContruir, Barrio barrio, Jugador jugador);

    public abstract int capacidad();


    public abstract void modificarPrecios(EstadoConstruccion estadoConstruccion, Edificable edificable);
}

