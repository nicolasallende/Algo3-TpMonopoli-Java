package modelo;

public class Compania extends Propiedad {
	private static final double PORCENTAJE_DE_VENTA = 0.85;
	private String nombre;
	private long precio;
	private long multiplicador;
	private long multiplicador2;
	private String companiaRelacionada;
	
	public Compania() {//no se deberia usar
		super();
	}
	public Compania(String nombreCompania, long precioCompania, long multiplicadorCompania, long otroMultiplicador, String otraCompania) {
		super();
		this.nombre=nombreCompania;
		this.precio=precioCompania;
		this.multiplicador=multiplicadorCompania;
		this.multiplicador2=otroMultiplicador;
		this.companiaRelacionada=otraCompania;
	}

	public boolean esComprableMonetariamente(Jugador jugador){
		return jugador.dinero() >= this.precio;
	}

	public void descontarSegunPrecio(Jugador jugador){
		jugador.pagarDinero(this.precio);
	}


	@Override
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombreCompania) {
		this.nombre = nombreCompania;
	}
	
	public long getPrecio() {
		return this.precio;
	}
	
	public void setPrecio(long precioCompania) {
		this.precio = precioCompania;
	}

	public long getMultiplicador() {
		return this.multiplicador;
	}

	public void setMultiplicador(long multiplicadorCompania) {
		this.multiplicador = multiplicadorCompania;
	}

	public long getMultiplicador2() {
		return this.multiplicador2;
	}

	public void setMultiplicador2(long multiplicadorCompania) {
		this.multiplicador2 = multiplicadorCompania;
	}

	public String getCompaniaRelacionada() {
		return this.companiaRelacionada;
	}

	public void setCompaniaRelacionada(String otraCompania) {
		this.companiaRelacionada = otraCompania;
	}


	public long entregarLoQuePuedePagar(Jugador jugador, long loqueTendriaQuePagar){
		long loQuePuedePagar;
		if ( jugador.dinero() >= loqueTendriaQuePagar){
			loQuePuedePagar = loqueTendriaQuePagar;
		} else {
			loQuePuedePagar = jugador.dinero();
		}
		return loQuePuedePagar;
	}

	@Override
	public void pagarPaso(Jugador jugador) {
		int ultimoTiro = jugador.ultimoTiroRealizado();
		long loQueDeberiaPagar = calcularPrecioARestar(ultimoTiro, jugador);
		long loQuePuedePagar = this.entregarLoQuePuedePagar(jugador, loQueDeberiaPagar);
		jugador.pagarDinero(loQueDeberiaPagar);
		propietario.recibirDinero(loQuePuedePagar);
	}

	public long calcularPrecioARestar(int ultimoTiro, Jugador jugador) {
		Jugador propietarioDeCompaniaRelacionada = obtenerPropietarioDeCompaniaRelacionada(jugador);

		long precioAPagar;
		if(propietarioDeCompaniaRelacionada != this.propietario) precioAPagar = this.multiplicador * ultimoTiro;
		else precioAPagar = this.multiplicador2 * ultimoTiro;
		return precioAPagar;
	}

	private Jugador obtenerPropietarioDeCompaniaRelacionada(Jugador jugador) {
		Compania otraCompania = (Compania) jugador.obtenerCasilleroRelacionado(companiaRelacionada);
		return otraCompania.propietario;
	}

	@Override
	public void reiniciarPropiedad() {		
		//En caso de compania no hace nada ya, a lo ya que el beneficio extra se obtenia cuando tenia la relacionada
	}
	
	@Override
	public  void seVendeLaPropiedad() {
		long precioDeVenta = this.precioVenta();
		propietario.recibirDinero(precioDeVenta );
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

