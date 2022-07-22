package modelo;


public abstract class Propiedad extends Casillero {
	protected Jugador propietario;
	
	public Propiedad(){
		super();
		this.propietario = null;
	}

	public String nombreDuenio() {
		return this.propietario.nombre();
	}

	public boolean tieneDuenio(){
		return this.propietario != null;
	}

	public boolean esComprable(Jugador jugador){
		return this.esComprableMonetariamente(jugador) && ! this.tieneDuenio();
	}

	public abstract boolean esComprableMonetariamente(Jugador jugador);

	public abstract void descontarSegunPrecio(Jugador jugador);

	public boolean adquirir(Jugador jugador) {
		if (this.esComprable(jugador)) {
			this.cambiarDuenio(jugador);
			this.descontarSegunPrecio(jugador);
			return true;
		}
		return false;
	}

	public abstract void pagarPaso(Jugador jugador);

	public void jugadorCayo(Jugador jugador) {
		if (this.propietario != null){
			this.pagarPaso(jugador);
		}
	}

	public Jugador propietario(	) {
		return this.propietario;
	}

	public boolean estaAdquirido(){
		return (this.propietario != null);
	}
	
	
	public abstract String getNombre();

	public void cambiarDuenio(Jugador jugador) {
		this.propietario = jugador;
	}
	
	
	public abstract void reiniciarPropiedad();
	
	public abstract void seVendeLaPropiedad();

	public abstract void pagarDeuda(long pago);

	public abstract long precioVenta();
}