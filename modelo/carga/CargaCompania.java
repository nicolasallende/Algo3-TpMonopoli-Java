package modelo.carga;

import javax.xml.bind.annotation.XmlRootElement;

import modelo.Jugador;
import modelo.Propiedad;

@XmlRootElement
public class CargaCompania extends Propiedad {
	
	private String nombre;
	private long precio;
	private long multiplicador;
	private long multiplicador2;
	private String companiaRelacionada;
	
	public CargaCompania() {
		super();
	}

	@Override
	public boolean esComprableMonetariamente(Jugador jugador) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void descontarSegunPrecio(Jugador jugador) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pagarPaso(Jugador jugador) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reiniciarPropiedad() {
		// TODO Auto-generated method stub
		
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

	@Override
	public void seVendeLaPropiedad() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pagarDeuda(long pago) {

	}

	@Override
	public long precioVenta() {
		return 0;
	}


}
