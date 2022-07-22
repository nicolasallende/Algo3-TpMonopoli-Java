package modelo.construccion;

import modelo.Barrio;
import modelo.Jugador;

public class ConstruccionCompleja extends Construccion {
    private static EstadoConstruccion estados[] = { EstadoConstruccion.SinNada, EstadoConstruccion.Casa, EstadoConstruccion.DosCasas, EstadoConstruccion.Hotel};
    private int indexConstruccion;

    public ConstruccionCompleja(int index){
        this.indexConstruccion = index + 1;
    }

    @Override
    public Construccion cambiar(Barrio barrio) {
        this.indexConstruccion = super.cambiarEstado(estados, this.indexConstruccion);
        return this;
    }

    public EstadoConstruccion estado(){
        return estados[indexConstruccion];
    }

    public int capacidad(){
        return this.indexConstruccion;
    }


    @Override
    public boolean tieneCapacidad(Barrio barrio, Jugador jugador) {
        return this.indexConstruccion < estados.length - 1 && barrio.puedeConstruirHotel(jugador);
    }


    @Override
    public boolean estoyEnCondicionesDe(EstadoConstruccion estadoAContruir, Barrio barrio, Jugador jugador) {
        return estadoAContruir.equals(EstadoConstruccion.Hotel) && barrio.puedeConstruirHotel(jugador);
    }

    @Override
    public void modificarPrecios(EstadoConstruccion estado, Edificable edificable) {
        switch(estado) {
            case DosCasas:
                ((Barrio)edificable).setAlquiler(((Barrio)edificable).getAlquiler2Casas());
                edificable.setPrecioConstrucion( ((Barrio)edificable).getConstruirCasa());
                break;
            case Hotel:
                ((Barrio)edificable).setAlquiler(((Barrio)edificable).getAlquilerConHotel());
                edificable.setPrecioConstrucion( ((Barrio)edificable).getConstruirHotel());
                break;
            default:
                break;
        }
    }
}
