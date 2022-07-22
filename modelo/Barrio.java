package modelo;

import modelo.construccion.Edificable;
import modelo.construccion.EstadoConstruccion;


public class Barrio extends Edificable {

    private static final double PORCENTAJE_DE_VENTA = 0.85;
    private String nombre;
    private long precio;
    private long alquiler;
    private long alquiler1Casa;
    private long alquiler2Casas;
    private long alquilerConHotel;
    private long construirCasa;
    private long construirHotel;
    private boolean tieneRelacionado;
    public Barrio() {
    	super();
    }
    public Barrio(String nombreBarrio, long precio, long construirCasa, boolean relacionado, long alquiler1casa, long alquiler2casas, 
    long construirHotel, long alquilerHotel, long precioAlquiler) {
        super();
        this.nombre=nombreBarrio;
        this.precio=precio;
        this.construirCasa=construirCasa;
        this.tieneRelacionado=relacionado;
        this.alquiler1Casa=alquiler1casa;
        this.alquiler2Casas=alquiler2casas;
        this.construirHotel=construirHotel;
        this.alquilerConHotel=alquilerHotel;
        this.alquiler = precioAlquiler;
    }

    public boolean esComprableMonetariamente(Jugador jugador){
        return jugador.dinero() >= this.precio;
    }

    public void descontarSegunPrecio(Jugador jugador){
        jugador.pagarDinero(this.precio);
    }


    public long entregarLoQuePuedePagar(Jugador jugador){
        long loQuePuedePagar;
        if ( jugador.dinero() >= this.alquiler){
            loQuePuedePagar = this.alquiler;
        } else {
            loQuePuedePagar = jugador.dinero();
        }
        return loQuePuedePagar;
    }


    @Override
    public void pagarPaso(Jugador jugador) {
        long loQuePuedePagar = this.entregarLoQuePuedePagar(jugador);
        jugador.pagarDinero(this.alquiler);
        propietario.recibirDinero(loQuePuedePagar);
    }

    public boolean puedeConstruirHotel(Jugador jugador) {
        Barrio barrioRel = jugador.barrioRelacionado(this);
        return jugador.esPropiedadSuya(barrioRel) && this.tieneCapacidadMaximaCasas() && this.tieneCapacidadParaHotel(barrioRel);
    }

    public void construir(){
        super.constuir(this);
    }//construccion Lineal

    public void construir(EstadoConstruccion construccionAEdificar, Jugador jugador) {
        if ( this.estoyEnCondicionesDeConstruir(construccionAEdificar, this, jugador)){
            this.construir();
        }
    }//construccion Especifica

    public boolean tieneCapacidad(){
        return this.tieneCapacidad(this, this.propietario);
    }
    
    public String getNombre() {//9 llamados
        return this.nombre;
    }

    public long getPrecio() {//1 llamado
        return precio;
    }

    public void setPrecio(long precio) {//1 llamado
        this.precio = precio;
    }

    public long getAlquiler(){
        return this.alquiler;
    }

    public void setAlquiler(long alquiler) {//3 llamados
        this.alquiler = alquiler;
    }

    public long getAlquiler1Casa() {//1 llamado
        return alquiler1Casa;
    }

    public long getAlquiler2Casas() {//1 llamado
        return alquiler2Casas;
    }

    public long getAlquilerConHotel() {//1 llamado
        return alquilerConHotel;
    }

    public long getConstruirCasa() {//2 llamados
        return construirCasa;
    }

    public long getConstruirHotel() {//1 llamado
        return construirHotel;
    }

    public boolean getRelacionado() {//3 llamados
        return tieneRelacionado;
    }
    
    @Override
    public  void seVendeLaPropiedad() {
		long precioDeVenta = this.precioVenta();
		propietario.recibirDinero(precioDeVenta);
		propietario.quitarPropiedad(this);
		this.reiniciarPropiedad();
		propietario = null;
	}

    @Override
    public void pagarDeuda(long pago) {
        propietario.recibirDinero(pago);
    }

    @Override
    public long precioVenta() {
        return (long)(this.precio * PORCENTAJE_DE_VENTA);
    }

}