package modelo.construccion;

import modelo.Barrio;
import modelo.Jugador;
import modelo.Propiedad;

public abstract class  Edificable extends Propiedad {
	private long precioInicial;
	private long precioConstrucion;
	public Construccion construccion;
	private static int maximaCasasParaHotel = 2;

	public Edificable(){
		super();
		precioConstrucion = 0;
		construccion = new ConstruccionSimple();
	}

	public void inicializarPrecioInicial(Barrio barrio){
		if (this.construccion.estado().equals(EstadoConstruccion.SinNada)){
			this.precioInicial = barrio.getAlquiler();
		}
	}

	public void constuir(Barrio barrio) {
		inicializarPrecioInicial(barrio);
		this.construccion = construccion.cambiar(barrio);
		EstadoConstruccion estado = this.construccion.estado();
		this.construccion.modificarPrecios(estado,this);
	}

	public void setPrecioConstrucion(long precioConstrucion) {
		this.precioConstrucion = precioConstrucion;
	}

	public long precioConstruccion(){
		return this.precioConstrucion;
	}

	public abstract boolean esComprableMonetariamente(Jugador jugador);

	public abstract void descontarSegunPrecio(Jugador jugador);

	public boolean tieneCapacidad(Barrio barrio, Jugador jugador){
		return this.construccion.tieneCapacidad(barrio, jugador);
	}

	public boolean isConstruccionSimple(){
		return this.construccion instanceof ConstruccionSimple;
	}

	public boolean estoyEnCondicionesDeConstruir(EstadoConstruccion estadoAContruir,Barrio barrio, Jugador jugador){
		return construccion.estoyEnCondicionesDe(estadoAContruir, barrio, jugador);
	}


	public boolean tieneCapacidadMaximaCasas(){
		return this.construccion.capacidad() == maximaCasasParaHotel;
	}

	@Override
	public void reiniciarPropiedad() {
		if ( this.hayEdificacion() ){
			((Barrio) this).setAlquiler(this.precioInicial);
			this.construccion = new ConstruccionSimple();
		}
	}

	public boolean hayEdificacion(){
		return ! this.construccion.estado().equals(EstadoConstruccion.SinNada);
	}

	public EstadoConstruccion getEstadoConstruccion(){
		return this.construccion.estado();
	}

	public boolean tieneCapacidadParaHotel(Barrio barrioRel){
		return barrioRel.tieneCapacidadMaximaCasas() || barrioRel.construccion.estado().equals(EstadoConstruccion.Hotel);
	}

	@Override
	public abstract void seVendeLaPropiedad();
}
