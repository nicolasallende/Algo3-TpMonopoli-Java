package modelo.construccion;

import modelo.Barrio;
import modelo.Jugador;

public class ConstruccionSimple extends Construccion {
    private static EstadoConstruccion estados[] = {EstadoConstruccion.SinNada, EstadoConstruccion.Casa};
    private int indexConstruccion;

    public ConstruccionSimple(){
        this.indexConstruccion = 0;
    }


    public Construccion cambiar(Barrio barrio) {
        Construccion construccion = this;
        if ( this.isLimite() && this.esConstruccionDoble(barrio)){
            construccion = new ConstruccionCompleja(this.indexConstruccion);
        }
        this.indexConstruccion = construccion.cambiarEstado(estados, this.indexConstruccion);
        return construccion;
    }

    public EstadoConstruccion estado(){
        return estados[this.indexConstruccion];
    }


    public boolean isLimite(){
        return this.indexConstruccion == estados.length - 1;
    }

    @Override
    public boolean tieneCapacidad(Barrio barrio, Jugador jugador) {
        return ! this.isLimite() || (this.isLimite() && barrio.getRelacionado());
    }


    public boolean esConstruccionDoble(Barrio barrio){
        return barrio.getRelacionado();
    }


    public int capacidad(){
        return this.indexConstruccion;
    }


    @Override
    public boolean estoyEnCondicionesDe(EstadoConstruccion estadoAContruir, Barrio barrio, Jugador jugador)  {
        return estadoAContruir.equals(EstadoConstruccion.Casa) && (estados[indexConstruccion]).equals(EstadoConstruccion.SinNada) ||
                (estadoAContruir.equals(EstadoConstruccion.Casa) && barrio.getRelacionado());
    }

    @Override
    public void modificarPrecios(EstadoConstruccion estado, Edificable edificable) {
        switch(estado) {
            case Casa:
                ((Barrio) edificable).setAlquiler(((Barrio) edificable).getAlquiler1Casa());
                edificable.setPrecioConstrucion(((Barrio) edificable).getConstruirCasa());
                break;
            default:
                break;
        }
    }

}
