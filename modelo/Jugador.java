package modelo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import modelo.acciones.AccionesDelJugador;
import modelo.acciones.ManejadorDeAcciones;
import modelo.construccion.EstadoConstruccion;
import modelo.estados.EnDeuda;
import modelo.estados.EstadoJugador;
import modelo.estados.EstadosPosiblesDelJugador;
import modelo.estados.PostDados;
import modelo.estados.PreDados;
import modelo.estados.Preso;
import modelo.estados.PresoPuedeFianzar;

public class Jugador {
	public long deuda;
	private Casillero posicion;
	private long dinero;
	private String nombre;
	public ArrayList<Propiedad> propiedades;
	private Tablero tablero;
	private EstadoJugador estado;
	public ManejadorDeAcciones manejadorDeAcciones;
	public String nombreCasilleroAnterior;

	public Jugador(long dineroInicial, Casillero casilleroInicial, String nombreJugador,Tablero tablero) {
		this.posicion = casilleroInicial;
		this.dinero = dineroInicial;
		this.propiedades = new ArrayList<Propiedad>();
		this.nombre = nombreJugador;
		this.tablero = tablero;
		this.manejadorDeAcciones = new ManejadorDeAcciones ();
		this.nombreCasilleroAnterior = "";
		this.deuda = 0;
	}

	public List<Barrio> obtenerBarriosEdificados(){
		List<Barrio> BarriosEdificados = new ArrayList<>();
		for (Propiedad propiedad: this.propiedades) {
			if ( propiedad instanceof Barrio && ((Barrio) propiedad).hayEdificacion()){
				BarriosEdificados.add((Barrio) propiedad);
			}
		}
		return BarriosEdificados;
	}

	public void recibirDinero(long dineroaSumar){
		this.dinero += dineroaSumar;
	}
	
	public void pagarDinero(long dineroaSumar){
		this.dinero -= dineroaSumar;
	}

	public void nuevoTurno() {
		if( estado == null) this.estado = new PreDados(this);
		this.estado.nuevoTurno(this);

	}

	public boolean tieneDeuda(){
		return this.deuda < 0;
	}

	public void pagarDeuda(){
		if (this.dinero < 0 ) return;
		long pago;
		if ( Math.abs(this.deuda) > this.dinero )  pago = this.dinero;
		else {
			pago = Math.abs(this.deuda);
		}
		this.dinero -= pago;
		this.deuda += pago;

		//Pagando la deuda al propietario duenio de esa Proppiedad.
		Propiedad propiedad = (Propiedad) this.posicion;
		propiedad.pagarDeuda(pago);

		//Volviendo a la normalidad en caso de que la deuda este saldada
		if ( this.deuda == 0 ) {
			this.cambiarEstado(EstadosPosiblesDelJugador.postDados);
		}
	}

	public String getNombreCasilleroAnterior() {
		return this.nombreCasilleroAnterior;
	}

	public void agregarPropiedad(Propiedad propiedad) {
		this.propiedades.add(propiedad);
	}
	
	public void quitarPropiedad(Propiedad propiedad) {
		this.propiedades.remove(propiedad);
	}
	
	public void cambiarCasillero(Casillero otroCasillero) {
		this.nombreCasilleroAnterior = this.casilleroActual().getNombre();
		this.posicion.desocuparCasillero(this.nombre);
		this.posicion = otroCasillero;
		otroCasillero.jugadorCayo(this.nombre, this);

		//En caso de que entro en numeros rojos.
		if (this.dinero < 0 && this.puedePagarDeuda(this.dinero) ) {
			this.deuda = this.dinero;
			//Se inicializa para que se haga un control de la deuda que tiene que pagar
			this.dinero = 0;
			this.cambiarEstado(EstadosPosiblesDelJugador.EnDeuda);
		}
		//Si no tiene para pagar deuda que lo saque el chequeo de turno
 	}
	
	public boolean noLeQuedaNada() {
		if((this.dinero() <= 0) && (this.cantidadDePropiedadesPoseidas() <= 0)) return true;
		return false;
	}
	
	public boolean puedePagarDeuda(long deuda) {
		long dineroQuePodriaTener = 0;
		for (Propiedad propiedad: this.propiedades) {
			dineroQuePodriaTener += propiedad.precioVenta();
		}
		return Math.abs(deuda) <= dineroQuePodriaTener;
	}

	public int ultimoTiroRealizado() {
		return this.tablero.ultimoTiroRealizado();
	}

	public int cantidadDePropiedadesPoseidas() {
		return this.propiedades.size();//falta sumarle la cantidad de casa/hoteles
	}
	

	public void moverse(int movimiento, Jugador jugador) {
		this.tablero.moverJugadorEnCasillero(movimiento, jugador);
	}

	public void cambiarEstado(EstadosPosiblesDelJugador estado) {
		switch(estado) {
		case preso:
			this.estado= new Preso(this);
			break;
		case presoPuedeFianzar:
			this.estado= new PresoPuedeFianzar(this);
			break;
		case EnDeuda:
			this.estado= new EnDeuda(this);
			break;
		case PreDados:
			this.estado= new PreDados(this);
			break;
		case postDados:
			this.estado= new PostDados(this);
			break;
		default:
			//exception
			break;
		}
	}
	
	public boolean hacer(AccionesDelJugador accion) {
		if(! manejadorDeAcciones.correctamenteInicializado) this.manejadorDeAcciones.init(this, tablero);;
		return this.manejadorDeAcciones.hacer(accion);
	}

	public void comprarPropiedad() {
		Propiedad propiedad = (Propiedad)(this.casilleroActual());
		if ( propiedad.adquirir(this) ){
			this.propiedades.add( (Propiedad)this.posicion);
			manejadorDeAcciones.remover(AccionesDelJugador.edificar);
		}
		
	}
	
	public void venderPropiedad(Propiedad propiedad) {
		propiedad.seVendeLaPropiedad();
		if ( this.tieneDeuda()) this.pagarDeuda();
	}
	
	public Casillero obtenerCasilleroRelacionado(String nombreCasillero) {
		return this.tablero.obtenerCasilleroRelacionado(nombreCasillero);
	}
	
	public Barrio barrioRelacionado(Barrio barrio){
		return this.tablero.buscarBarrioRelacionado(barrio.getNombre());
	}


	public boolean puedeEdificarEnPropiedades(){
		boolean puede = false;
		for (Propiedad propiedad: this.propiedades) {
			if (propiedad instanceof Barrio && !puede) {
				puede = this.puedeEdificar((Barrio) propiedad);
			}
		}
		return puede;
	}


	public boolean esPropiedadSuya(Barrio barrio) {
		return barrio.tieneDuenio() && barrio.nombreDuenio().equals(this.nombre);
	}

	public boolean puedeEdificar(Barrio barrio) {
		if (! this.esPropiedadSuya(barrio) ){
			return false;
		}

		if( barrio.getRelacionado() && this.esPropiedadSuya( this.barrioRelacionado(barrio))) {
			boolean barrioPuedeSolo =  barrio.tieneCapacidad() || (! barrio.tieneCapacidad() && barrio.isConstruccionSimple());
			Jugador jugador = barrio.propietario();
			boolean barrioPuedeConRelacionado = barrio.puedeConstruirHotel(jugador) ;
			return barrioPuedeSolo || barrioPuedeConRelacionado;
		}
		return barrio.tieneCapacidad() && ! barrio.getRelacionado();
	}

	public void edificar(EstadoConstruccion construccionAEdificar, Barrio barrio) {
		//Aclaracion: jugador tiene una validacion el cual es, puedeEdificar. Si no es Verdadero, no cambia de estado y continua como antes.
		if (this.puedeEdificar(barrio) && this.dinero >= barrio.precioConstruccion()){
			((Barrio)this.posicion).construir(construccionAEdificar, this);
			this.dinero -= ((Barrio)this.posicion).precioConstruccion();
		}
	}

	public void intercambiarPropiedades(Propiedad pertenecienteAlJugador, Propiedad pertenecienteAlOtroJugador, Jugador otroJugador) {
		this.quitarPropiedad(pertenecienteAlJugador);
		otroJugador.quitarPropiedad(pertenecienteAlOtroJugador);
		
		this.agregarPropiedad(pertenecienteAlOtroJugador);
		otroJugador.agregarPropiedad(pertenecienteAlJugador);
		
		pertenecienteAlJugador.cambiarDuenio(otroJugador);
		pertenecienteAlOtroJugador.cambiarDuenio(this);

		pertenecienteAlJugador.reiniciarPropiedad();
		pertenecienteAlOtroJugador.reiniciarPropiedad();
	}

	public String toStringPropiedades() {
		StringBuilder representacionPropiedades = new StringBuilder();
		for (int i = 0; i < this.propiedades.size(); i++) {
			representacionPropiedades.append(this.propiedades.get(i).getNombre());
			if ( i < this.propiedades.size() -1 ){
				representacionPropiedades.append(" - ");
			}
		}//Capaz es mejor Poner un barra negra abajo con la propiedades de cada uno que se vayan acumulando. Asi no se deforma. EL Status
		return representacionPropiedades.toString();
	}

	public List<String> getNombreBarriosAEdificar(){
		Hashtable<String, String> nombresProp = new Hashtable<>();
		List<String> nombrePropiedades = new ArrayList<>();

		for (Propiedad propiedad: this.propiedades) {
			if ( propiedad instanceof Barrio && this.puedeEdificar((Barrio) propiedad)) {
				nombresProp.put(propiedad.getNombre(),"-");
			}
		}
		nombrePropiedades.addAll(nombresProp.keySet());
		return nombrePropiedades;
	}

	public void enviarSolicitudDeEdificacionAlManejoDeAcciones(String nombreBarrio) {
		this.manejadorDeAcciones.setElementoAEdificar(nombreBarrio);
	}

	public void enviarSolicitudVenderAlManejosDeOpciones(String nombreBarrio){
		this.manejadorDeAcciones.setElementoAvender(nombreBarrio);
	}
	//Getters &  Setters

	public Casillero casilleroActual() {
		return this.posicion;
	}
	
	public long dinero(){
		return this.dinero;
	}

	public String nombre(){
		return this.nombre;
	}
	
	public ArrayList<Propiedad> getPropiedades() {
		return this.propiedades;
	}
	
	public EstadoJugador getEstado() {
		return this.estado;
	}
	
	public boolean puedeIntercambiar() {
		return this.cantidadDePropiedadesPoseidas() > 0;
	}
	public boolean sePuedeIntecambiarConOtroJugador(){
		if (! this.puedeIntercambiar()) return false;
		boolean puede = false;
		for (Jugador jugador: this.tablero.getJugadores()) {
			if (! jugador.nombre().equals(this.nombre) && jugador.puedeIntercambiar()){
				puede = true;
			}
		}
		return puede;
	}



	public boolean puedeEdificarSinContarLaUtimaPropiedad(){
		ArrayList<Propiedad> propiedadesAux = new ArrayList<>();
		propiedadesAux.addAll(this.propiedades);

		ArrayList<Propiedad> propiedadesSinLaUltima = new ArrayList<>( this.propiedades.subList(0, this.propiedades.size() -1) );

		this.propiedades = propiedadesSinLaUltima;
		boolean puedEdificar = this.puedeEdificarEnPropiedades();
		this.propiedades = propiedadesAux;

		return  puedEdificar;
	}


}


