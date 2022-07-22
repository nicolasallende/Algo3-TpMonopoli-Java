package modelo;

import org.junit.Assert;
import org.junit.Test;

public class CompaniaTest {
	final long plataInicial=100000;
	
	//Test Generales
	@Test
	public void LaCompaniaCuandoSeCreaNoTieneDuenio() {
		Compania compania = new Compania();
		Assert.assertFalse(compania.estaAdquirido());
	}
	
	@Test
    public void companiaConPropietarioEstaAdquirido(){
        Tablero tablero = new Tablero();
        Compania compania = new Compania();
        Jugador jugador = new Jugador(plataInicial, compania,"Roy",tablero);

        compania.adquirir(jugador);
        Assert.assertTrue(compania.estaAdquirido());
    }
	
	 @Test
	    public void veoElNombreDelPropietarioDelCompania(){
	        Tablero tablero = new Tablero();
	        Compania compania = new Compania();
	        Jugador jugador = new Jugador(plataInicial, compania,"Roy",tablero);

	        compania.adquirir(jugador);
	        Assert.assertEquals(jugador.nombre(), compania.propietario.nombre());
	    }
	 
	 @Test
	    public void alComprarCompaniaSeDescuentaAljugadorEltotalDelPrecio(){
	        Tablero tablero = new Tablero();
	        Compania Edesur = new Compania("Edesur", 35000, 500, 1000, "Aysa");
	        Jugador jugador = new Jugador(plataInicial, Edesur, "Pablo", tablero);
	        jugador.comprarPropiedad();
	        Assert.assertEquals(65000,jugador.dinero());
	    }
	 //Test edesur y aysa
	 @Test
	 public void unJugadorCaeEnEdesurQueEsPropiedadDeOtroJugadorYPaga500x5(){
		 Tablero tablero = new Tablero();
		 Compania Edesur = new Compania("Edesur", 35000, 500, 1000, "Aysa");
		 Compania Aysa = new Compania("Aysa", 30000, 300, 500, "Edesur");
		 Jugador duenioDeEdesur = new Jugador(plataInicial, Edesur, "Pablo", tablero);
		 Jugador jugadorQuePaga = new Jugador(plataInicial, Edesur, "Joaquin", tablero);
		 tablero.agregarCasillero(Edesur);
		 tablero.agregarCasillero(Aysa);
		 tablero.agregarJugador(jugadorQuePaga);
		 tablero.agregarJugador(duenioDeEdesur);
		 Edesur.adquirir(duenioDeEdesur);
		 long precioARestar = Edesur.calcularPrecioARestar(5, jugadorQuePaga);
		 jugadorQuePaga.pagarDinero(precioARestar);
		 Assert.assertEquals(97500,jugadorQuePaga.dinero());
	 }
	 
	 @Test
	 public void unJugadorPoseeEdesurYAysaYOtroJugadorCaeEnEdesurYPaga1000x5(){
		 Tablero tablero = new Tablero();
		 Compania Edesur = new Compania("Edesur", 35000, 500, 1000, "Aysa");
		 Compania Aysa = new Compania("Aysa", 30000, 300, 500, "Edesur");
		 Jugador duenioDeEdesurYAysa = new Jugador(plataInicial, Edesur, "Pablo", tablero);
		 Jugador jugadorQuePaga = new Jugador(plataInicial, Edesur, "Joaquin", tablero);
		 tablero.agregarCasillero(Edesur);
		 tablero.agregarCasillero(Aysa);
		 tablero.agregarJugador(jugadorQuePaga);
		 tablero.agregarJugador(duenioDeEdesurYAysa);
		 Edesur.adquirir(duenioDeEdesurYAysa);
		 Aysa.adquirir(duenioDeEdesurYAysa);
		 long precioARestar = Edesur.calcularPrecioARestar(5, jugadorQuePaga);
		 jugadorQuePaga.pagarDinero(precioARestar);
		 Assert.assertEquals(95000,jugadorQuePaga.dinero());
	 }
	 
	 
	 @Test
	 public void unJugadorCaeEnAysaQueEsPropiedadDeOtroJugadorYPaga300x10(){
		 Tablero tablero = new Tablero();
		 Compania Edesur = new Compania("Edesur", 35000, 500, 1000, "Aysa");
		 Compania Aysa = new Compania("Aysa", 30000, 300, 500, "Edesur");
		 Jugador duenioDeAysa = new Jugador(plataInicial, Edesur, "Pablo", tablero);
		 Jugador jugadorQuePaga = new Jugador(plataInicial, Edesur, "Joaquin", tablero);
		 tablero.agregarCasillero(Edesur);
		 tablero.agregarCasillero(Aysa);
		 tablero.agregarJugador(jugadorQuePaga);
		 tablero.agregarJugador(duenioDeAysa);
		 Aysa.adquirir(duenioDeAysa);
		 long precioARestar = Aysa.calcularPrecioARestar(10, jugadorQuePaga);
		 jugadorQuePaga.pagarDinero(precioARestar);
		 Assert.assertEquals(97000,jugadorQuePaga.dinero());
	 }

	 @Test
	 public void EdesurYAysaSonPropiedadDeUnjugadorYOtroCaeEnAysaYPaga500x10(){
		 Tablero tablero = new Tablero();
		 Compania Edesur = new Compania("Edesur", 35000, 500, 1000, "Aysa");
		 Compania Aysa = new Compania("Aysa", 30000, 300, 500, "Edesur");
		 Jugador duenioDeAysaYEdesur = new Jugador(plataInicial, Edesur, "Pablo", tablero);
		 Jugador jugadorQuePaga = new Jugador(plataInicial, Edesur, "Joaquin", tablero);
		 tablero.agregarCasillero(Edesur);
		 tablero.agregarCasillero(Aysa);
		 tablero.agregarJugador(jugadorQuePaga);
		 tablero.agregarJugador(duenioDeAysaYEdesur);
		 Aysa.adquirir(duenioDeAysaYEdesur);
		 Edesur.adquirir(duenioDeAysaYEdesur);
		 long precioARestar = Aysa.calcularPrecioARestar(10, jugadorQuePaga);
		 jugadorQuePaga.pagarDinero(precioARestar);
		 Assert.assertEquals(95000,jugadorQuePaga.dinero());
	 }
	 
	 @Test
	 public void seVendeLaPropiedadYElJugadorRecibeEl15PorciontodelPrecioDeCompraOriginal() {
		 Tablero tablero = new Tablero();
		 Compania Edesur = new Compania("Edesur", 50000, 500, 1000, "Aysa");
		 Compania Aysa = new Compania("Aysa", 30000, 300, 500, "Edesur");
		 Jugador duenioDeEdesur = new Jugador(plataInicial, Edesur, "Pablo", tablero);
		 tablero.agregarCasillero(Edesur);
		 tablero.agregarCasillero(Aysa);
		 tablero.agregarJugador(duenioDeEdesur);
		 Assert.assertEquals(100000,duenioDeEdesur.dinero());
		 duenioDeEdesur.comprarPropiedad();
		 Assert.assertEquals(50000,duenioDeEdesur.dinero());
		 duenioDeEdesur.venderPropiedad(Edesur);
		 Assert.assertEquals(92500, duenioDeEdesur.dinero());
	 
	 }
	 
	 //tests de Trenes y subte
	 @Test
	 public void TrenesYSubteSonPropiedadDeUnJugadorYOtroCae(){
		 final int  valorDados = 10;
		 Tablero tablero = new Tablero();
		 Compania Trenes = new Compania("Trenes", 38000, 450, 800, "Subtes");
		 Compania Subtes = new Compania("Subtes", 40000, 600, 1100, "Trenes");
		 Jugador duenio = new Jugador(plataInicial, Trenes, "a", tablero);
		 Jugador jugadorQuePaga = new Jugador(plataInicial, Trenes, "b", tablero);
		 tablero.agregarCasillero(Trenes);
		 tablero.agregarCasillero(Subtes);
		 tablero.agregarJugador(jugadorQuePaga);
		 tablero.agregarJugador(duenio);
		 Subtes.adquirir(duenio);
		 Trenes.adquirir(duenio);
		 long precioARestar = Trenes.calcularPrecioARestar(valorDados, jugadorQuePaga);
		 jugadorQuePaga.pagarDinero(precioARestar);
		 Assert.assertEquals(plataInicial-valorDados*800,jugadorQuePaga.dinero());
	 }
	 @Test
	 public void TrenesEsPropiedadDeUnJugadorYOtroCae(){
		 final int  valorDados = 10;
		 Tablero tablero = new Tablero();
		 Compania Trenes = new Compania("Trenes", 38000, 450, 800, "Subtes");
		 Compania Subtes = new Compania("Subtes", 40000, 600, 1100, "Trenes");
		 Jugador duenio = new Jugador(plataInicial, Trenes, "a", tablero);
		 Jugador jugadorQuePaga = new Jugador(plataInicial, Trenes, "b", tablero);
		 tablero.agregarCasillero(Subtes);
		 tablero.agregarCasillero(Trenes);
		 tablero.agregarJugador(jugadorQuePaga);
		 tablero.agregarJugador(duenio);
		 Trenes.adquirir(duenio);
		 long precioARestar = Trenes.calcularPrecioARestar(valorDados, jugadorQuePaga);
		 jugadorQuePaga.pagarDinero(precioARestar);
		 Assert.assertEquals(plataInicial-valorDados*450,jugadorQuePaga.dinero());
	 }
	 
	 
}
