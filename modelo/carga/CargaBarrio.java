package modelo.carga;

import javax.xml.bind.annotation.XmlRootElement;

import modelo.Jugador;
import modelo.construccion.Edificable;

@XmlRootElement
public class CargaBarrio extends Edificable{
	private String nombre;
    private long precio;
    private long alquiler;
    private long alquiler1Casa; 
    private long alquiler2Casas;
    private long alquilerConHotel;
    private long construirCasa;
    private long construirHotel;
    private boolean relacionado;
    public CargaBarrio() {
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

	public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public long getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(long alquiler) {
        this.alquiler = alquiler;
    }

    public long getAlquiler1Casa() {
        return alquiler1Casa;
    }

    public void setAlquiler1Casa(long alquiler1Casa) {
        this.alquiler1Casa = alquiler1Casa;
    }

    public long getAlquiler2Casas() {
        return alquiler2Casas;
    }

    public void setAlquiler2Casas(long alquiler2Casas) {
        this.alquiler2Casas = alquiler2Casas;
    }

    public long getAlquilerConHotel() {
        return alquilerConHotel;
    }

    public void setAlquilerConHotel(long alquilerConHotel) {
        this.alquilerConHotel = alquilerConHotel;
    }

    public long getConstruirCasa() {
        return construirCasa;
    }

    public void setConstruirCasa(long construirCasa) {
        this.construirCasa = construirCasa;
    }

    public long getConstruirHotel() {
        return construirHotel;
    }

    public void setConstruirHotel(long construirHotel) {
        this.construirHotel = construirHotel;
    }

    public boolean getRelacionado() {
        return relacionado;
    }

    public void setRelacionado(boolean relacionado) {
        this.relacionado = relacionado;
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

