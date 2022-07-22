package modelo;


import modelo.Eventos.Salida;
import modelo.acciones.AccionesDelJugador;

import modelo.estados.EnDeuda;
import modelo.estados.EstadoJugador;
import org.junit.Assert;
import org.junit.Test;
import modelo.Barrio;
import modelo.Eventos.AvanceDinamico;
import modelo.Eventos.Carcel;
import modelo.Eventos.ImpuestoAlLujo;
import modelo.Eventos.Policia;
import modelo.Eventos.Quini6;
import modelo.Eventos.RetrocesoDinamico;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class PartidaTest {
	
		//Constantes
		final long dineroInicialJugador = 100000;
		//Santa Fe
		final long precioSantaFe =15000;
		final long precioConstruirCasaSantaFe =4000;
		final long precioAlquiler1casaSantaFe=3500;
		//Buenos Aires
		//("Buenos Aires Norte",precioBuenosAiresNorte,5500,true,3500,4000,0, 0);
		final long precioBuenosAiresNorte = 25000;
		final long precioBuenosAiresSur = 20000;
		final long precioConstruirCasaBuenosAiresNorte = 5500;
		final long precioConstruirCasaBuenosAiresSur = 5000;
		final long precioAlquiler1casaBuenosAiresNorte = 3500;
		final long precioAlquiler2casaBuenosAiresNorte = 4000;
		final long precioAlquilerHotelBuenosAiresNorte = 4000;
		final long precioConstruirHotelBuenosAiresNorte = 4000;
		//Cordoba
		final long precioCordobaNorte = 20000;
		final long precioCordobaSur = 18000;
		final long precioConstruirCasaCordobaNorte = 2500;
		final long precioConstruirCasaCordobaSur = 2000; 
		//Salta
		final long precioSaltaNorte = 23000;
		final long precioSaltaSur = precioSaltaNorte;
		//Neuquen
		final long precioNeuquen = 17000;
		final long precioAlgNeuquen = 1800;
		final long precioAlq1CasaNeuquen = 3800;
		final long precioConstruir1CasaNeuquen = 4800;
		//Tucuman
		final long precioTucuman = 25000;
		final long precioAlqTucuman = 2500;
		final long precioAlq1EdificioTucuman = 4500;
		final long precioConstruirCasaTucuman = 7000;
		//Alquileres
		final long precioAlquilerSantaFe = 1500;
	    final long precionAlqBNorte = 2500;
	    final long precionAlqBSur = 2000;
	    final long precioAlqSNorte = 2000;
	    final long precioAlqSSur = 2000;
	    final long precioAlqCNorte = 1300;
	    final long precioAlqCSur= 1000;

	    
	//no puedo referenciar a los casilleros si uso esta funcion    
	public void cargarTablero(Tablero tablero) {
		Salida salida   = new Salida();
		ImpuestoAlLujo impuestoAlLujo = new ImpuestoAlLujo(); 
		Casillero quini6 = new Quini6();
		Carcel carcel = new Carcel();
		AvanceDinamico avance = new AvanceDinamico();
		RetrocesoDinamico retroceso = new RetrocesoDinamico();
		Policia policia = new Policia(carcel);
		Compania edesur = new Compania("Edesur", 35000, 500, 1000, "Aysa");
		Compania aysa = new Compania("Aysa", 30000, 300, 500, "Edesur");
		Compania tren = new Compania("Trenes", 38000, 450, 800, "Subtes");
		Compania subte = new Compania("Subtes", 40000, 600, 1100, "Trenes");
		 
		Barrio buenosAiresNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4500, 9000, 6000,precionAlqBNorte);
	    Barrio buenosAiresSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 8000, 5000,precionAlqBSur);
		Barrio cordobaNorte = new Barrio("Cordoba Norte", precioCordobaNorte, precioConstruirCasaCordobaNorte, true, 1800, 2900, 3500, 3500, precioAlqCNorte);
        Barrio cordobaSur = new Barrio("Cordoba Sur", precioCordobaSur, precioConstruirCasaCordobaSur, true, 1500, 2500, 3000, 3000, precioAlqCSur);
        Barrio saltaNorte = new Barrio("Salta Norte", precioSaltaNorte, 4500, true, 3250, 3850, 7500, 5500, precioAlqSNorte);
        Barrio saltaSur = new Barrio("Salta Sur", precioSaltaSur, 4500, true, 3250, 3850, 7500, 5500, precioAlqSSur);
        Barrio santaFe = new Barrio("Santa Fe",precioSantaFe,precioConstruirCasaSantaFe,false,precioAlquiler1casaSantaFe,0, 0, 0, precioAlquilerSantaFe);
        Barrio neuquen = new Barrio("Neuquen",precioNeuquen, precioConstruir1CasaNeuquen, false, precioAlq1CasaNeuquen, 0, 0, 0, precioAlgNeuquen);
        Barrio tucuman = new Barrio("Tucuman",precioTucuman, precioConstruirCasaTucuman, false, precioAlq1EdificioTucuman, 0, 0 , 0, precioAlqTucuman);

        tablero.agregarCasillero(salida);
        tablero.agregarCasillero(quini6);
        tablero.agregarCasillero(buenosAiresSur);
        tablero.agregarCasillero(edesur);
        tablero.agregarCasillero(buenosAiresNorte);
        tablero.agregarCasillero(carcel);
        tablero.agregarCasillero(cordobaSur);
        tablero.agregarCasillero(avance);
        tablero.agregarCasillero(subte);
        tablero.agregarCasillero(cordobaNorte);
        tablero.agregarCasillero(impuestoAlLujo);
        tablero.agregarCasillero(santaFe);
        tablero.agregarCasillero(aysa);
        tablero.agregarCasillero(saltaNorte);
        tablero.agregarCasillero(saltaSur);
        tablero.agregarCasillero(policia);
        tablero.agregarCasillero(tren);
        tablero.agregarCasillero(neuquen);
        tablero.agregarCasillero(retroceso);
        tablero.agregarCasillero(tucuman);
        
		Jugador jugadorGomez = new Jugador(dineroInicialJugador, salida, "Gomez", tablero);
		Jugador jugadorPablo = new Jugador(dineroInicialJugador, salida, "Pablo", tablero);
		Jugador jugadorJorge = new Jugador(dineroInicialJugador, salida, "Jorge", tablero);
		agregarJugadoresAlTablero(tablero, jugadorPablo, jugadorJorge, jugadorGomez);
		
	}
	    
	    
	public Barrio inicializarBarrioSimple(String nombreBarrio, long precio) {
		Barrio barrio = new Barrio(nombreBarrio,precio,precioConstruirCasaSantaFe,false,precioAlquiler1casaSantaFe,0, 0, 0,0);
		return barrio;
	}
	
	private void agregarJugadoresAlTablero(Tablero tablero, Jugador jugador1, Jugador jugador2, Jugador jugador3) {
		tablero.agregarJugador(jugador1);
		tablero.agregarJugador(jugador2);
		tablero.agregarJugador(jugador3);
	}
	
	@Test
	public void partida2JugadoresNoTienenDineroEntoncesLaPartidaYaTermino() {
		Tablero tablero = new Tablero();
		Barrio barrio = new Barrio();
		Jugador jugadorPerdedor = new Jugador(0, barrio, "Fede", tablero);
		Jugador jugadorOtroPerdedor = new Jugador(0, barrio, "Gaston", tablero);
		Jugador jugadorGanador = new Jugador(1000, barrio, "Batman", tablero);
		agregarJugadoresAlTablero(tablero, jugadorGanador, jugadorOtroPerdedor, jugadorPerdedor);
		Assert.assertTrue(tablero.partidoTermino());
	}
	
	
	@Test
	public void partida2JugadoresEmpiezanSinDineroEntoncesGanaElTercerJugador() {
		Tablero tablero = new Tablero();
		Barrio barrio = new Barrio();
		Jugador jugadorQueVaAPerder = new Jugador(0, barrio, "Gomez", tablero);
		Jugador jugadorOtroQueVaAPerder = new Jugador(0, barrio, "Pablo", tablero);
		Jugador jugadorQueVaAGanar = new Jugador(1000, barrio, "Jorge", tablero);
		agregarJugadoresAlTablero(tablero, jugadorOtroQueVaAPerder, jugadorQueVaAPerder, jugadorQueVaAGanar);
		Assert.assertEquals(jugadorQueVaAGanar, tablero.partidaTieneUnGanador());
	}
	
	@Test
	public void partidaTerminaCuando2JugadoresPierdenTodoSuDinero() {
		Tablero tablero = new Tablero();
		Barrio barrio = new Barrio();
		Jugador jugadorGomez = new Jugador(10000, barrio, "Gomez", tablero);
		Jugador jugadorPablo = new Jugador(10000, barrio, "Pablo", tablero);
		Jugador jugadorJorge = new Jugador(10000, barrio, "Jorge", tablero);
		agregarJugadoresAlTablero(tablero, jugadorPablo, jugadorJorge, jugadorGomez);
		
		jugadorGomez.pagarDinero(5000);
		jugadorPablo.pagarDinero(5000);
		
		Assert.assertFalse(tablero.partidoTermino()); //me aseguro que una partida no termine 
													  //hay mas de un jugador con dinero
		
		jugadorGomez.pagarDinero(5000);
		Assert.assertFalse(tablero.partidoTermino());
		jugadorPablo.pagarDinero(5000);
		
		Assert.assertTrue(tablero.partidoTermino());
		Assert.assertEquals(jugadorJorge, tablero.partidaTieneUnGanador());
	}
	
	@Test
	public void quedan2JugadoresElPrimeroCaeEnlaPropiedadDelOtroPeroNoLeAlcanzaParaPagarElAlquilerElSegundoJugadorGana() {
		Tablero tablero   = new Tablero();
		Salida salida     = new Salida();
		Barrio tucuman = new Barrio("Tucuman",precioTucuman, precioConstruirCasaTucuman, false, precioAlq1EdificioTucuman, 0, 0 , 0, precioAlqTucuman);

		tablero.agregarCasillero(salida);
		tablero.agregarCasillero(tucuman);
		
		Jugador jugadorGomez = new Jugador(dineroInicialJugador, salida, "Gomez", tablero);
		Jugador jugadorPablo = new Jugador(dineroInicialJugador, salida, "Pablo", tablero);
		Jugador jugadorJorge = new Jugador(dineroInicialJugador, salida, "Jorge", tablero);
		agregarJugadoresAlTablero(tablero, jugadorPablo, jugadorJorge, jugadorGomez);
		
		jugadorGomez.pagarDinero(100000); //no me interesa este jugador asi que lo elimino del juego
		jugadorJorge.pagarDinero(99000); //lo dejo con menos de la cantidad necesaria para pagar el alquiler
		
		tablero.nuevoTurno();
		Jugador jugador = tablero.obtenerJugadorActual();
		
		tablero.moverjugador(1);
		Assert.assertEquals(tucuman, jugador.casilleroActual());
		Assert.assertEquals(jugadorPablo, jugador);
		tablero.finalizarTurno();
		
		tablero.nuevoTurno();
		tablero.finalizarTurno();
		
		tablero.nuevoTurno();
		tablero.finalizarTurno();
		
		tablero.nuevoTurno();
		jugador = tablero.obtenerJugadorActual();
		Assert.assertEquals(jugadorPablo, jugador);
		jugador.manejadorDeAcciones.agregarAccion(AccionesDelJugador.comprar); 
		Assert.assertTrue(jugador.hacer(AccionesDelJugador.comprar));
		tablero.finalizarTurno();
		
		tablero.nuevoTurno();
		jugador = tablero.obtenerJugadorActual();
		Assert.assertEquals(jugadorJorge, jugador); //es el jugador que va a pagar el alquiler
		tablero.moverjugador(1);
		
		Assert.assertTrue(tablero.partidoTermino());
		Assert.assertEquals(jugadorPablo, tablero.partidaTieneUnGanador());
	}
	
	
	@Test
	public void quedan2JugadoresElPrimeroCaeEnUnaDeLosBarriosDelOtroPeroNoLeAlcanzaParaPagarElAlquilerElSegundoJugadorGana() {
		Tablero tablero   = new Tablero();
		
		Salida salida     = new Salida();
		Barrio buenosAiresNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4500, 9000, 6000,precionAlqBNorte);
	    Barrio buenosAiresSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 8000, 5000,precionAlqBSur);
		
		tablero.agregarCasillero(salida);
		tablero.agregarCasillero(buenosAiresSur);
		tablero.agregarCasillero(buenosAiresNorte);
		
		Jugador jugadorGomez = new Jugador(dineroInicialJugador, salida, "Gomez", tablero);
		Jugador jugadorPablo = new Jugador(dineroInicialJugador, salida, "Pablo", tablero);
		Jugador jugadorJorge = new Jugador(dineroInicialJugador, salida, "Jorge", tablero);
		agregarJugadoresAlTablero(tablero, jugadorPablo, jugadorJorge, jugadorGomez);
		
		jugadorGomez.pagarDinero(100000); //no me interesa este jugador asi que lo elimino del juego
		jugadorJorge.pagarDinero(99000); //lo dejo con menos de la cantidad necesaria para pagar el alquiler
	
		tablero.nuevoTurno();
		Jugador jugador = tablero.obtenerJugadorActual();
		tablero.moverjugador(1);
		Assert.assertEquals(buenosAiresSur, jugador.casilleroActual());
		Assert.assertEquals(jugadorPablo, jugador);
		tablero.finalizarTurno();
		
		tablero.nuevoTurno();
		tablero.finalizarTurno();
		
		tablero.nuevoTurno();
		tablero.finalizarTurno();
		
		tablero.nuevoTurno();
		jugador = tablero.obtenerJugadorActual();
		Assert.assertEquals(jugadorPablo, jugador);
		jugador.manejadorDeAcciones.agregarAccion(AccionesDelJugador.comprar); //compra buenos aires sur 
		Assert.assertTrue(jugador.hacer(AccionesDelJugador.comprar));
		tablero.finalizarTurno();
		
		tablero.nuevoTurno();
		tablero.finalizarTurno();
		
		tablero.nuevoTurno();
		tablero.finalizarTurno();
		
		tablero.nuevoTurno();
		jugador = tablero.obtenerJugadorActual();
		tablero.moverjugador(1);
		Assert.assertEquals(buenosAiresNorte, jugador.casilleroActual());
		Assert.assertEquals(jugadorPablo, jugador);
		tablero.finalizarTurno();
		
		tablero.nuevoTurno();
		tablero.finalizarTurno();
		
		tablero.nuevoTurno();
		tablero.finalizarTurno();
		
		tablero.nuevoTurno();
		jugador = tablero.obtenerJugadorActual();
		Assert.assertEquals(jugadorPablo, jugador);
		jugador.manejadorDeAcciones.agregarAccion(AccionesDelJugador.comprar); //compra buenos aires Norte
		Assert.assertTrue(jugador.hacer(AccionesDelJugador.comprar));
		tablero.finalizarTurno();
		
		tablero.nuevoTurno();
		jugador = tablero.obtenerJugadorActual();
		Assert.assertEquals(jugadorJorge, jugador); //es el jugador que va a pagar el alquiler
		tablero.moverjugador(2);
		Assert.assertEquals(buenosAiresNorte, jugador.casilleroActual());
		
		
		Assert.assertTrue(tablero.partidoTermino());
		Assert.assertEquals(jugadorPablo, tablero.partidaTieneUnGanador());
	}
	
	@Test
	public void quedan2jugadoresElPrimeroUsaTodoSuDineroParaSalirDeLaCarcelPorEndeGanaElOtro() {
		Tablero tablero = new Tablero();
		Salida salida   = new Salida();
		Carcel carcel = new Carcel();
		
		tablero.agregarCasillero(salida);
		tablero.agregarCasillero(carcel);
		
		Jugador jugadorGomez = new Jugador(dineroInicialJugador, salida, "Gomez", tablero);
		Jugador jugadorPablo = new Jugador(dineroInicialJugador, salida, "Pablo", tablero);
		Jugador jugadorJorge = new Jugador(dineroInicialJugador, salida, "Jorge", tablero);
		agregarJugadoresAlTablero(tablero, jugadorPablo, jugadorJorge, jugadorGomez);
		
		jugadorGomez.pagarDinero(100000); //no me interesa este jugador asi que lo elimino del juego
		jugadorPablo.pagarDinero(55000); //lo dejo con la cantidad justa para pagar la fianza
		
		tablero.nuevoTurno();
		Jugador jugador = tablero.obtenerJugadorActual();
		tablero.moverjugador(1);
		Assert.assertEquals(carcel, jugador.casilleroActual());
		Assert.assertEquals(jugadorPablo, jugador);
		tablero.finalizarTurno();
		
		tablero.nuevoTurno();
		tablero.finalizarTurno();
		
		tablero.nuevoTurno();
		tablero.finalizarTurno();
		
		tablero.nuevoTurno();
		jugador = tablero.obtenerJugadorActual();
		Assert.assertEquals(jugadorPablo, jugador);
		jugador.hacer(AccionesDelJugador.pagarFianza);
		tablero.finalizarTurno();
		
		Assert.assertTrue(tablero.partidoTermino());
		Assert.assertEquals(jugadorJorge, tablero.partidaTieneUnGanador());
	}
	
	@Test
	public void jugadorCaeEnCarcelYPasado1TurnoSaleConFianza() {
		Tablero tablero = new Tablero();
		Salida salida   = new Salida();
		Carcel carcel = new Carcel();
		
		tablero.agregarCasillero(salida);
		tablero.agregarCasillero(carcel);
		
		Jugador jugadorGomez = new Jugador(dineroInicialJugador, salida, "Gomez", tablero);
		Jugador jugadorPablo = new Jugador(dineroInicialJugador, salida, "Pablo", tablero);
		Jugador jugadorJorge = new Jugador(dineroInicialJugador, salida, "Jorge", tablero);
		agregarJugadoresAlTablero(tablero, jugadorPablo, jugadorJorge, jugadorGomez);
		
		//1 RONDA DE TURNOS
		tablero.nuevoTurno(); 
		Jugador jugador = tablero.obtenerJugadorActual();
		tablero.moverjugador(1);
		//
		Assert.assertEquals(carcel, jugador.casilleroActual()); //cayo en carcel
		Assert.assertFalse(jugador.hacer(AccionesDelJugador.pagarFianza)); //no puede pagar fianza cuando cae en la carcel
		//
		tablero.finalizarTurno();
		
		tablero.nuevoTurno(); //salteo el turno del segundo jugador		
		tablero.finalizarTurno();
		
		tablero.nuevoTurno(); 
		tablero.finalizarTurno(); //salteo el turno del tercer jugador
		
		tablero.nuevoTurno();
		Assert.assertTrue(jugador.hacer(AccionesDelJugador.pagarFianza));
	}
	
	@Test
	public void jugadorCaeEnLaPoliciaYTerminaEnLaCarcel() {
		Tablero tablero = new Tablero();
		Salida salida   = new Salida();
		Carcel carcel = new Carcel();
		Policia policia = new Policia(carcel);
		
		tablero.agregarCasillero(salida);
		tablero.agregarCasillero(carcel);
		tablero.agregarCasillero(policia);
		
		Jugador jugadorGomez = new Jugador(dineroInicialJugador, salida, "Gomez", tablero);
		Jugador jugadorPablo = new Jugador(dineroInicialJugador, salida, "Pablo", tablero);
		Jugador jugadorJorge = new Jugador(dineroInicialJugador, salida, "Jorge", tablero);
		agregarJugadoresAlTablero(tablero, jugadorPablo, jugadorJorge, jugadorGomez);
		
		tablero.nuevoTurno(); 
		Jugador jugador = tablero.obtenerJugadorActual();
		tablero.moverjugador(2); //cae en policia
		
		Assert.assertEquals(carcel, jugador.casilleroActual()); //cayo en carcel
	}
	
	@Test
	public void jugadorCaeEnUnBarrioNoPuedeComprarloEnElTurnoQueCae() {
		Tablero tablero = new Tablero();
		Salida salida   = new Salida();
		Barrio santaFe = new Barrio("Santa Fe",precioSantaFe,precioConstruirCasaSantaFe,false,precioAlquiler1casaSantaFe,0, 0, 0, precioAlquilerSantaFe);
		
		tablero.agregarCasillero(salida);
		tablero.agregarCasillero(santaFe);
		
		Jugador jugadorGomez = new Jugador(dineroInicialJugador, salida, "Gomez", tablero);
		Jugador jugadorPablo = new Jugador(dineroInicialJugador, salida, "Pablo", tablero);
		Jugador jugadorJorge = new Jugador(dineroInicialJugador, salida, "Jorge", tablero);
		agregarJugadoresAlTablero(tablero, jugadorPablo, jugadorJorge, jugadorGomez);
		
		tablero.nuevoTurno(); 
		Jugador jugador = tablero.obtenerJugadorActual();
		tablero.moverjugador(1); //cae en Santa Fe
		
		Assert.assertEquals(santaFe, jugador.casilleroActual());
		Assert.assertFalse(jugador.hacer(AccionesDelJugador.comprar));
	}
	
	@Test
	public void jugadorCaeEnQuinielaYAumentaSuDinero() {
		Tablero tablero   = new Tablero();
		Salida salida     = new Salida();
		Casillero quini6 = new Quini6();
		
		tablero.agregarCasillero(salida);
		tablero.agregarCasillero(quini6);
		
		Jugador jugadorGomez = new Jugador(dineroInicialJugador, salida, "Gomez", tablero);
		Jugador jugadorPablo = new Jugador(dineroInicialJugador, salida, "Pablo", tablero);
		Jugador jugadorJorge = new Jugador(dineroInicialJugador, salida, "Jorge", tablero);
		agregarJugadoresAlTablero(tablero, jugadorPablo, jugadorJorge, jugadorGomez);
		
		tablero.nuevoTurno(); 
		Jugador jugador = tablero.obtenerJugadorActual();
		tablero.moverjugador(1); 
		
		Assert.assertEquals(quini6, jugador.casilleroActual());
		Assert.assertEquals(150000, jugador.dinero());
		
	}
	
	@Test
	public void jugadorCompraTucumanPasadoUnTurnoQueCayoAhi() {
		Tablero tablero   = new Tablero();
		Salida salida     = new Salida();
		Barrio tucuman = new Barrio("Tucuman",precioTucuman, precioConstruirCasaTucuman, false, precioAlq1EdificioTucuman, 0, 0 , 0, precioAlqTucuman);

		tablero.agregarCasillero(salida);
		tablero.agregarCasillero(tucuman);
		
		Jugador jugadorGomez = new Jugador(dineroInicialJugador, salida, "Gomez", tablero);
		Jugador jugadorPablo = new Jugador(dineroInicialJugador, salida, "Pablo", tablero);
		Jugador jugadorJorge = new Jugador(dineroInicialJugador, salida, "Jorge", tablero);
		agregarJugadoresAlTablero(tablero, jugadorPablo, jugadorJorge, jugadorGomez);
		
		tablero.nuevoTurno(); 
		Jugador jugador = tablero.obtenerJugadorActual();
		tablero.moverjugador(1); //cae en Tucuman
		
		Assert.assertEquals(tucuman, jugador.casilleroActual());
		Assert.assertFalse(jugador.hacer(AccionesDelJugador.comprar));
		Assert.assertEquals(jugadorPablo, jugador);
		
		tablero.finalizarTurno();
		tablero.nuevoTurno(); //salteo el turno del segundo jugador		
		jugador = tablero.obtenerJugadorActual();
		tablero.finalizarTurno();
		
		tablero.nuevoTurno(); //salteo el turno del tercer jugador
		jugador = tablero.obtenerJugadorActual();
		tablero.finalizarTurno();
		
		tablero.nuevoTurno(); 		
		
		jugador = tablero.obtenerJugadorActual();
		Assert.assertEquals(jugadorPablo, jugador);
		jugador.manejadorDeAcciones.agregarAccion(AccionesDelJugador.comprar); //hacia falta ponerlo
		
		Assert.assertTrue(jugador.hacer(AccionesDelJugador.comprar)); 
		Assert.assertTrue(tucuman.estaAdquirido());
	}

	@Test
	public void jugadorTienePropiedadesPeroNoTieneDineroYPasaPorUnBarrioSinDuenioPuedePagar() throws JAXBException, IOException {
		Tablero tablero = new Tablero();
		tablero.cargaCasilleros();
		List<Casillero> casilleros = tablero.getCasilleros();
		Casillero salida = casilleros.get(0); //Obtengo la salida.

		Barrio buenosAiresNorte = (Barrio) tablero.obtenerCasilleroRelacionado("Buenos Aires Norte");
		Barrio buenosAiresSur = (Barrio) tablero.obtenerCasilleroRelacionado("Buenos Aires Sur");
		Barrio cordobaNorte = (Barrio) tablero.obtenerCasilleroRelacionado("Cordoba Norte");

		Jugador jugador = new Jugador(45000,buenosAiresNorte , "Batman", tablero);
		jugador.comprarPropiedad();
		jugador.cambiarCasillero(buenosAiresSur);
		jugador.comprarPropiedad();
		jugador.cambiarCasillero(salida);

		Jugador jugador2 = new Jugador(60000,cordobaNorte , "Robin", tablero);
		jugador2.comprarPropiedad();


		tablero.agregarJugador(jugador);
		tablero.nuevoTurno();
		jugador.cambiarCasillero(cordobaNorte);
		Assert.assertTrue(jugador.puedePagarDeuda(jugador.dinero()));
	}


	@Test
	public void jugadorTienePropiedadesPeroNoTieneDineroYPasaPorUnBarrioConDuenioPuedePagarYEntraEnDeuda() throws JAXBException, IOException {
		Tablero tablero = new Tablero();
		tablero.cargaCasilleros();
		List<Casillero> casilleros = tablero.getCasilleros();
		Casillero salida = casilleros.get(0); //Obtengo la salida.

		Barrio buenosAiresNorte = (Barrio) tablero.obtenerCasilleroRelacionado("Buenos Aires Norte");
		Barrio buenosAiresSur = (Barrio) tablero.obtenerCasilleroRelacionado("Buenos Aires Sur");
		Barrio cordobaNorte = (Barrio) tablero.obtenerCasilleroRelacionado("Cordoba Norte");

		Jugador jugador = new Jugador(45000,buenosAiresNorte , "Batman", tablero);
		jugador.comprarPropiedad();
		jugador.cambiarCasillero(buenosAiresSur);
		jugador.comprarPropiedad();
		jugador.cambiarCasillero(salida);
		Jugador jugador2 = new Jugador(20000,cordobaNorte , "Robin", tablero);
		jugador2.comprarPropiedad();

		tablero.agregarJugador(jugador);
		tablero.nuevoTurno();
		jugador.cambiarCasillero(cordobaNorte);

		//En venderPropiedad si el jugador tiene deuda se encarga de pagarla.
		jugador.venderPropiedad(buenosAiresNorte);
		Assert.assertFalse(jugador.tieneDeuda());
	}
	
	@Test
	public void jugadorTienePropiedadesPeroNoTieneDineroYPasaPorUnBarrioConDuenioVendeUnaPropiedadParaPagarOtroJugadorLaCompraYOtroCaeEnLaPropiedad() throws JAXBException, IOException {
		Tablero tablero = new Tablero();
		tablero.cargaCasilleros();
		List<Casillero> casilleros = tablero.getCasilleros();
		Casillero salida = casilleros.get(0); //Obtengo la salida.

		Barrio buenosAiresNorte = (Barrio) tablero.obtenerCasilleroRelacionado("Buenos Aires Norte");
		Barrio buenosAiresSur = (Barrio) tablero.obtenerCasilleroRelacionado("Buenos Aires Sur");
		Barrio cordobaNorte = (Barrio) tablero.obtenerCasilleroRelacionado("Cordoba Norte");

		Jugador jugador = new Jugador(45000,buenosAiresNorte , "Batman", tablero);
		jugador.comprarPropiedad();
		jugador.cambiarCasillero(buenosAiresSur);
		jugador.comprarPropiedad();
		jugador.cambiarCasillero(salida);

		Jugador jugador2 = new Jugador(60000,cordobaNorte , "Robin", tablero);
		jugador2.comprarPropiedad();//ahora su dinero es 40000
		
		Jugador jugador3 = new Jugador(100000, salida,"Joker", tablero);
		
		tablero.agregarJugador(jugador);
		tablero.agregarJugador(jugador3);
		tablero.agregarJugador(jugador2);
		
		tablero.nuevoTurno();
		jugador.cambiarCasillero(cordobaNorte);

		//En venderPropiedad si el jugador tiene deuda se encarga de pagarla.
		jugador.venderPropiedad(buenosAiresNorte);//le paga a jugador2
		
		tablero.nuevoTurno();
		jugador3.cambiarCasillero(buenosAiresNorte);
		jugador3.comprarPropiedad();
		Assert.assertEquals(75000, jugador3.dinero());
		
		tablero.nuevoTurno();
		jugador2.cambiarCasillero(buenosAiresNorte);//le paga al jugador3 que es el nuevo propietario de buenosAiresSur
		Assert.assertEquals(77500, jugador3.dinero());
		
	}
}

