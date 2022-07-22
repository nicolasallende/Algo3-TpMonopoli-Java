package modelo;

import modelo.construccion.EstadoConstruccion;
import org.junit.Assert;
import org.junit.Test;

import modelo.Eventos.Salida;

import java.util.Arrays;
import java.util.List;

public class BarrioTest {
	//Constantes
	final long dineroInicialJugador = 100000,
	//Santa Fe
	precioSantaFe =15000,
	precioConstruirCasaSantaFe =4000,
	precioAlquiler1casaSantaFe=3500,
	//Buenos Aires
	//("Buenos Aires Norte",precioBuenosAiresNorte,5500,true,3500,4000,0, 0),
	precioBuenosAiresNorte = 25000,
	precioBuenosAiresSur = 20000,
	precioConstruirCasaBuenosAiresNorte = 5500,
	precioConstruirCasaBuenosAiresSur = 5000,
	precioAlquiler1casaBuenosAiresNorte = 3500,
	precioAlquiler2casaBuenosAiresNorte = 4000,
	precioAlquilerHotelBuenosAiresNorte = 4000,
	precioConstruirHotelBuenosAiresNorte = 4000,
	//Cordoba
	precioCordobaNorte = 20000,
	precioCordobaSur = 18000,
	precioConstruirCasaCordobaNorte = 2500,
	precioConstruirCasaCordobaSur = 2000, 
	//Salta
	precioSaltaNorte = 23000,
	precioSaltaSur = precioSaltaNorte,
	//Neuquen
	precioNeuquen=17000,
	//Tucuman
	precioTucuman = 25000,
	precioAlquilerSantaFe = 1500,
    precionAlqBNorte = 2500,
    precionAlqBSur = 2000,
    precioAlqSNorte = 2000,
    precioAlqSSur = 2000,
    precioAlqCNorte = 1300,
    precioAlqCSur= 1000;

	public Barrio inicializarBarrioSimple(String nombreBarrio, long precio) {
        Barrio barrio = new Barrio(nombreBarrio,precio,precioConstruirCasaSantaFe,false,precioAlquiler1casaSantaFe,0, 0, 0,0);
        return barrio;
    }
    @Test
    public void barrioSinDuenioEstaLibre(){
        Barrio barrio = new Barrio("Santa Fe",precioSantaFe,precioConstruirCasaSantaFe,false,precioAlquiler1casaSantaFe,0, 0, 0, precioAlquilerSantaFe);
        Assert.assertFalse(barrio.estaAdquirido());
    }

    @Test
    public void barrioConPropietarioEstaOcupado(){
        Tablero tablero = new Tablero();
        Barrio barrio = new Barrio("Santa Fe",precioSantaFe,precioConstruirCasaSantaFe,false,precioAlquiler1casaSantaFe,0, 0, 0, precioAlquilerSantaFe);
        Jugador jugador = new Jugador(dineroInicialJugador, barrio,"Don Pepe",tablero);

        barrio.adquirir(jugador);
        Assert.assertTrue(barrio.estaAdquirido());
    }

    @Test
    public void veoElNombreDelPropietarioDelBarrio(){
        Tablero tablero = new Tablero();
        Barrio barrio = new Barrio("Santa Fe",precioSantaFe,precioConstruirCasaSantaFe,false,precioAlquiler1casaSantaFe,0, 0, 0,precioAlquilerSantaFe);
        Jugador jugador = new Jugador(dineroInicialJugador, barrio,"Don Pepe",tablero);

        barrio.adquirir(jugador);
        Assert.assertEquals(jugador.nombre(), barrio.propietario.nombre());
    }

    @Test
    public void alComprarSantaFeSinPropietarioSeDescuentaAljugadorEltotalDelPrecioDelBarrio(){
        Tablero tablero = new Tablero();
        Barrio barrio = new Barrio("Santa Fe",precioSantaFe,precioConstruirCasaSantaFe,false,precioAlquiler1casaSantaFe,0, 0, 0,precioSantaFe);
        Jugador jugador = new Jugador(dineroInicialJugador, barrio, "Pablo", tablero);
        jugador.comprarPropiedad();
        Assert.assertEquals(dineroInicialJugador-precioSantaFe,jugador.dinero());
    }
    @Test
    public void construirUnaCasaEnSantaFeVale4000(){//todo
        Tablero tablero = new Tablero();
        Barrio barrio = new Barrio("Santa Fe",precioSantaFe,precioConstruirCasaSantaFe,false,precioAlquiler1casaSantaFe,0, 0, 0, precioAlquilerSantaFe);
        Jugador jugador = new Jugador(dineroInicialJugador, barrio, "Pablo", tablero);

        jugador.comprarPropiedad();
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        Assert.assertEquals(dineroInicialJugador-precioSantaFe-precioConstruirCasaSantaFe,jugador.dinero());
    }

    @Test
    public void alComprarBuenosAiresSinPropietarioSeDescuentaAljugadorEltotalDelPrecioDelBarrio(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = inicializarBarrioSimple("Buenos Aires Norte",precioBuenosAiresNorte);
        Barrio barrioSur = inicializarBarrioSimple("Buenos Aires Sur",precioBuenosAiresSur);

        //inicializa y Compra  Buenos aires
        Jugador jugador = new Jugador(dineroInicialJugador, barrioNorte, "Pablo", tablero);
        jugador.comprarPropiedad();
        jugador.cambiarCasillero(barrioSur);
        jugador.comprarPropiedad();

        Assert.assertEquals(dineroInicialJugador-precioBuenosAiresNorte-precioBuenosAiresSur,jugador.dinero());
    }


    @Test
    public void alComprarCordobaSinPropietarioSeDescuentaAljugadorEltotalDelPrecioDelBarrio(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = inicializarBarrioSimple("Cordoba Norte",precioCordobaNorte);
        Barrio barrioSur = inicializarBarrioSimple("Cordoba Sur",precioCordobaSur);

        Jugador jugador = new Jugador(dineroInicialJugador, barrioNorte, "Pablo", tablero);
        jugador.comprarPropiedad();
        jugador.cambiarCasillero(barrioSur);
        jugador.comprarPropiedad();

        Assert.assertEquals(dineroInicialJugador-precioCordobaNorte-precioCordobaSur,jugador.dinero());
    }

    @Test
    public void alComprarSaltaSinPropietarioSeDescuentaAljugadorEltotalDelPrecioDelBarrio(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = inicializarBarrioSimple("Salta Norte",precioSaltaNorte);
        Barrio barrioSur = inicializarBarrioSimple("Salta Sur",precioSaltaSur);

        Jugador jugador = new Jugador(dineroInicialJugador, barrioNorte, "Pablo", tablero);
        jugador.comprarPropiedad();
        jugador.cambiarCasillero(barrioSur);
        jugador.comprarPropiedad();

        Assert.assertEquals(dineroInicialJugador-precioSaltaNorte-precioSaltaSur,jugador.dinero());
    }

    @Test
    public void alComprarNeuquenSinPropietarioSeDescuentaAljugadorEltotalDelPrecioDelBarrio(){
        Tablero tablero = new Tablero();
        Barrio barrio = inicializarBarrioSimple("Neuquen",precioNeuquen);

        Jugador jugador = new Jugador(dineroInicialJugador, barrio, "Pablo", tablero);
        jugador.comprarPropiedad();
        Assert.assertEquals(dineroInicialJugador-precioNeuquen,jugador.dinero());
    }

    @Test
    public void alComprarTucumanSinPropietarioSeDescuentaAljugadorElPrecioDelBarrio(){
        Tablero tablero = new Tablero();
        Barrio barrio = inicializarBarrioSimple("Tucuman",precioTucuman);

        Jugador jugador = new Jugador(dineroInicialJugador, barrio, "Pablo", tablero);
        jugador.comprarPropiedad();
        Assert.assertEquals(dineroInicialJugador-precioTucuman,jugador.dinero());
    }




    @Test
    public void alComprarBuenosAiresCompletoyEdificarUnaCasaSeDecrementa5000ElDineroDelJugador(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4500, 0, 0,precionAlqBNorte);
        Barrio barrioSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 0, 0,precionAlqBSur);

        //Se agrega a Tablero los dos Barrios
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);

        //inicializa y Compra  Buenos aires
        Jugador jugador = new Jugador(dineroInicialJugador, barrioNorte, "Pablo", tablero);
        jugador.comprarPropiedad();
        jugador.cambiarCasillero(barrioSur);
        jugador.comprarPropiedad();

        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        Assert.assertEquals(dineroInicialJugador-precioBuenosAiresNorte-precioBuenosAiresSur-precioConstruirCasaBuenosAiresSur,jugador.dinero());
    }

    @Test
    public void alCaerUnJugadorEnBuenosAiresSurYEstePertencerAunJugadorConElBarrioCompletoYConUnaCasaEnCadaUnoSeResta3000(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4500, 0, 0,precionAlqBNorte);
        Barrio barrioSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 0, 0,precionAlqBSur);

        //Se agrega a Tablero los dos Barrios
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);
        Casillero salida = new Salida();
        Jugador jugador = new Jugador(dineroInicialJugador, barrioNorte, "Pablo", tablero);
        Jugador jugadorConMalaSuerte = new Jugador(5000,salida,"Martin", tablero);

        //inicializa y Compra  Buenos aires
        jugador.comprarPropiedad();
        jugador.cambiarCasillero(barrioSur);
        jugador.comprarPropiedad();

        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.cambiarCasillero(barrioNorte);
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());

        barrioSur.jugadorCayo(jugadorConMalaSuerte);
        Assert.assertEquals(2000,jugadorConMalaSuerte.dinero());
    }

    @Test
    public void alCaerUnJugadorEnBuenosAiresNorteYEstePertencenAUnJugadorConElBarrioCompletoYConUnaCasaEnCadaUnoSeResta3500(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4500, 0, 0,precionAlqBNorte);
        Barrio barrioSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 0, 0,precionAlqBSur);

        //Se agrega a Tablero los dos Barrios
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);
        Casillero salida = new Salida();
        Jugador jugador = new Jugador(dineroInicialJugador, barrioNorte, "Pablo", tablero);
        Jugador jugadorConMalaSuerte = new Jugador(5000,salida,"Martin", tablero);

        //inicializa y Compra  Buenos aires
        jugador.comprarPropiedad();
        jugador.cambiarCasillero(barrioSur);
        jugador.comprarPropiedad();

        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.cambiarCasillero(barrioNorte);
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());

        barrioNorte.jugadorCayo(jugadorConMalaSuerte);
        Assert.assertEquals(1500,jugadorConMalaSuerte.dinero());
    }


    @Test
    public void alCaerUnjugadorEnBuenosAiresSurCon2CasasYCon1CasaEnElNorteProvocaQueElJugadorPierda3500(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4500, 0, 0,precionAlqBNorte);
        Barrio barrioSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 0, 0,precionAlqBSur);

        //Se agrega a Tablero los dos Barrios
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);
        Casillero salida = new Salida();
        Jugador jugador = new Jugador(dineroInicialJugador, barrioNorte, "Pablo", tablero);
        Jugador jugadorConMalaSuerte = new Jugador(5000,salida,"Martin", tablero);

        //inicializa y Compra  Buenos aires
        jugador.comprarPropiedad();
        jugador.cambiarCasillero(barrioSur);
        jugador.comprarPropiedad();

        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.cambiarCasillero(barrioNorte);
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());

        barrioSur.jugadorCayo(jugadorConMalaSuerte);
        Assert.assertEquals(1500,jugadorConMalaSuerte.dinero());
    }


    @Test
    public void jugadorPoseeAmbosBuenosAiresPeroNoPoseeCasasNoDecrementaSuDineroAlCrearUnHotel(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4500, 0, 0,precionAlqBNorte);
        Barrio barrioSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 0, 0,precionAlqBSur);

        //Se agrega a Tablero los dos Barrios
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);

        //inicializa y Compra  Buenos aires
        Jugador jugador = new Jugador(dineroInicialJugador, barrioNorte, "Pablo", tablero);
        jugador.comprarPropiedad();
        jugador.cambiarCasillero(barrioSur);
        jugador.comprarPropiedad();
        jugador.edificar(EstadoConstruccion.Hotel,(Barrio)jugador.casilleroActual());

        Assert.assertEquals(55000, jugador.dinero());
    }

    @Test
    public void jugadorPoseeAmbasBuenosAiresPuedeYCreaUnHotelSuDineroDecrementaElPrecioDelHotel(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4000, 0, 0,precionAlqBNorte);
        Barrio barrioSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 8000, 0,precionAlqBSur);

        //Se agrega a Tablero los dos Barrios
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);
        Jugador jugador = new Jugador(dineroInicialJugador, barrioNorte, "Pablo", tablero);

        //inicializa y Compra  Buenos aires
        jugador.comprarPropiedad();
        jugador.cambiarCasillero(barrioSur);
        jugador.comprarPropiedad();
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.cambiarCasillero(barrioNorte);
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.cambiarCasillero(barrioSur);
        jugador.edificar(EstadoConstruccion.Hotel,(Barrio)jugador.casilleroActual());

        Assert.assertEquals(26000, jugador.dinero());
    }

    @Test
    public void jugadorCaeEnBuenosAiresSurConHotelYsuDineroSeDecrementaPorElAlquilerDeEste(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4000, 0, 0,precionAlqBNorte);
        Barrio barrioSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 0, 5000,precionAlqBSur);


        //Se agrega a Tablero los dos Barrios
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);
        Casillero salida = new Salida();
        Jugador jugador = new Jugador(dineroInicialJugador, barrioNorte, "Pablo", tablero);
        Jugador jugadorConMalaSuerte = new Jugador(5000,salida,"Martin", tablero);

        //inicializa y Compra  Buenos aires
        jugador.comprarPropiedad();
        jugador.cambiarCasillero(barrioSur);
        jugador.comprarPropiedad();

        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.cambiarCasillero(barrioNorte);
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.cambiarCasillero(barrioSur);
        jugador.edificar(EstadoConstruccion.Hotel,(Barrio)jugador.casilleroActual());

        barrioSur.jugadorCayo(jugadorConMalaSuerte);

        Assert.assertEquals(0, jugadorConMalaSuerte.dinero());
    }

    @Test
    public void jugadorPoseeAmbasSaltasPeroNoPoseeCasasNoDecrementaSuDineroAlcrearUnHotel(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4000, 0, 0,precionAlqBNorte);
        Barrio barrioSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 0, 0,precionAlqBSur);

        //Se agrega a Tablero los dos Barrios
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);

        //inicializa y Compra  Salta
        Jugador jugador = new Jugador(dineroInicialJugador, barrioNorte, "Pablo", tablero);
        jugador.comprarPropiedad();
        jugador.cambiarCasillero(barrioSur);
        jugador.comprarPropiedad();
        jugador.edificar(EstadoConstruccion.Hotel,(Barrio)jugador.casilleroActual());

        Assert.assertEquals(55000, jugador.dinero());
    }

    @Test
    public void jugadorPuedeYCreaUnHotelEnSaltaYSuDineroDecrementaElCostoDelHotel(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Salta Norte", precioSaltaNorte, 4500, true, 3250, 3850, 7500, 5500, precioAlqSNorte);
        Barrio barrioSur = new Barrio("Salta Sur", precioSaltaSur, 4500, true, 3250, 3850, 7500, 5500, precioAlqSSur);

        //Se agrega a Tablero los dos Barrios
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);

        //inicializa y Compra  Salta
        Jugador jugador = new Jugador(100000, barrioNorte, "Pablo", tablero);
        jugador.comprarPropiedad();

        jugador.cambiarCasillero(barrioSur);
        jugador.comprarPropiedad();
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.cambiarCasillero(barrioNorte);
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());


        jugador.edificar(EstadoConstruccion.Hotel,(Barrio)jugador.casilleroActual());

        Assert.assertEquals(28500, jugador.dinero());
    }

    @Test
    public void jugadorCaeEnSaltaConHotelSuDineroSeDecrementaElAlquilerDelHotel(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Salta Norte", precioSaltaNorte, 4500, true, 3250, 3850, 7500, 5500, precioAlqSNorte);
        Barrio barrioSur = new Barrio("Salta Sur", precioSaltaSur, 4500, true, 3250, 3850, 7500, 5500, precioAlqSSur);

        //Se agrega a Tablero los dos Barrios
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);
        Casillero salida = new Salida();
        Jugador jugador = new Jugador(dineroInicialJugador, barrioNorte, "Pablo", tablero);
        Jugador jugadorConMalaSuerte = new Jugador(5500,salida,"Martin", tablero);

        //inicializa y Compra  Salta
        jugador.comprarPropiedad();
        jugador.cambiarCasillero(barrioSur);
        jugador.comprarPropiedad();
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.cambiarCasillero(barrioNorte);
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.edificar(EstadoConstruccion.Hotel,(Barrio)jugador.casilleroActual());

        barrioNorte.jugadorCayo(jugadorConMalaSuerte);

        Assert.assertEquals(0, jugadorConMalaSuerte.dinero());
    }

    @Test
    public void jugadorPoseeAmbosCordobasPeroNoPoseeCasasNoDecrementoSuDineroAlCrearHotel(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Cordoba Norte", precioCordobaNorte, 2500, true, 1800, 2900, 3500, 0, precioAlqCNorte);
        Barrio barrioSur = new Barrio("Cordoba Sur", precioCordobaSur, 2000, true, 1500, 2500, 3000, 0,precioAlqCSur);

        //Se agrega a Tablero los dos Barrios
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);

        //inicializa y Compra  Cordoba
        Jugador jugador = new Jugador(dineroInicialJugador, barrioNorte, "Pablo", tablero);
        jugador.comprarPropiedad();
        jugador.cambiarCasillero(barrioSur);
        jugador.comprarPropiedad();
        jugador.edificar(EstadoConstruccion.Hotel,(Barrio)jugador.casilleroActual());

        Assert.assertEquals(62000, jugador.dinero());
    }

    @Test
    public void jugadorConstruyeUnHotelEnCordobaYSuDineroSeDecrementaElPrecioDelHotel(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Cordoba Norte", precioCordobaNorte, precioConstruirCasaCordobaNorte, true, 1800, 2900, 3500, 0,precioAlqCNorte);
        Barrio barrioSur = new Barrio("Cordoba Sur", precioCordobaSur, precioConstruirCasaCordobaSur, true, 1500, 2500, 3000, 0, precioAlqCSur);

        //Se agrega a Tablero los dos Barrios
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);

        //inicializa y Compra  Cordoba
        Jugador jugador = new Jugador(dineroInicialJugador, barrioNorte, "Pablo", tablero);
        jugador.comprarPropiedad();
        jugador.cambiarCasillero(barrioSur);
        jugador.comprarPropiedad();
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.cambiarCasillero(barrioNorte);
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.edificar(EstadoConstruccion.Hotel,(Barrio)jugador.casilleroActual());

        Assert.assertEquals(49500, jugador.dinero());
    }

    @Test
    public void jugadorCaeEnUnHotelDeCordobaYPagaElAlquilerDeEste(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Cordoba Norte", precioCordobaNorte, precioConstruirCasaCordobaNorte, true, 1800, 2900, 3500, 3500, precioAlqCNorte);
        Barrio barrioSur = new Barrio("Cordoba Sur", precioCordobaSur, precioConstruirCasaCordobaSur, true, 1500, 2500, 3000, 3000, precioAlqCSur);

        //Se agrega a Tablero los dos Barrios
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);
        Casillero salida = new Salida();
        Jugador jugador = new Jugador(dineroInicialJugador, barrioNorte, "Pablo", tablero);
        Jugador jugadorConMalaSuerte = new Jugador(3500,salida,"Martin", tablero);

        //inicializa y Compra  Cordoba
        jugador.comprarPropiedad();
        jugador.cambiarCasillero(barrioSur);
        jugador.comprarPropiedad();
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.cambiarCasillero(barrioNorte);
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.edificar(EstadoConstruccion.Casa,(Barrio)jugador.casilleroActual());
        jugador.edificar(EstadoConstruccion.Hotel,(Barrio)jugador.casilleroActual());

        barrioNorte.jugadorCayo(jugadorConMalaSuerte);

        Assert.assertEquals(0, jugadorConMalaSuerte.dinero());
    }


    //Test que sirven para el select de la interfaz grafica
    @Test
    public void alComprarBuenosAiresApareceComoPosibleParaEdificar(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = inicializarBarrioSimple("Buenos Aires Norte",precioBuenosAiresNorte);
        Barrio barrioSur = inicializarBarrioSimple("Buenos Aires Sur",precioBuenosAiresSur);

        //inicializa y Compra  Buenos aires
        Jugador jugador = new Jugador(dineroInicialJugador, barrioNorte, "Pablo", tablero);
        jugador.comprarPropiedad();
        jugador.cambiarCasillero(barrioSur);
        jugador.comprarPropiedad();

        List<String> posiblesAEdificar = jugador.getNombreBarriosAEdificar();
        List<String> posiblesAEdificarEsperados = Arrays.asList("Buenos Aires Sur", "Buenos Aires Norte");


        Assert.assertTrue(posiblesAEdificar.containsAll(posiblesAEdificarEsperados));
        Assert.assertTrue(posiblesAEdificarEsperados.containsAll(posiblesAEdificar));
    }

    @Test
    public void alComprarCordobaSurSoloNoEstoyEnCondicionesDeEdificar(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Cordoba Norte", precioCordobaNorte, precioConstruirCasaCordobaNorte, true, 1800, 2900, 3500, 3500, precioAlqCNorte);
        Barrio barrioSur = new Barrio("Cordoba Sur", precioCordobaSur, precioConstruirCasaCordobaSur, true, 1500, 2500, 3000, 3000, precioAlqCSur);
        tablero.agregarCasillero(barrioSur);
        tablero.agregarCasillero(barrioNorte);

        //inicializa y Compra  Buenos aires
        Jugador jugador = new Jugador(dineroInicialJugador, barrioSur, "Pablo", tablero);
        tablero.agregarJugador(jugador);
        jugador.comprarPropiedad();

        Assert.assertFalse(jugador.puedeEdificar(barrioSur));
    }

    @Test
    public void alComprarCordobaCompletoEstaEnEnCondicionesDeEdificar(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Cordoba Norte", precioCordobaNorte, precioConstruirCasaCordobaNorte, true, 1800, 2900, 3500, 3500, precioAlqCNorte);
        Barrio barrioSur = new Barrio("Cordoba Sur", precioCordobaSur, precioConstruirCasaCordobaSur, true, 1500, 2500, 3000, 3000, precioAlqCSur);
        tablero.agregarCasillero(barrioSur);
        tablero.agregarCasillero(barrioNorte);

        //inicializa y Compra  Buenos aires
        Jugador jugador = new Jugador(dineroInicialJugador, barrioSur, "Pablo", tablero);
        tablero.agregarJugador(jugador);
        jugador.comprarPropiedad();
        jugador.cambiarCasillero(barrioNorte);
        jugador.comprarPropiedad();

        Assert.assertTrue(jugador.puedeEdificar(barrioNorte));
    }

    @Test
    public void alComprarUnBarrioUnicoPuedeEdificar(){
        Tablero tablero = new Tablero();
        Barrio barrio = new Barrio("Santa Fe",precioSantaFe,precioConstruirCasaSantaFe,false,precioAlquiler1casaSantaFe,0, 0, 0, precioAlquilerSantaFe);
        Jugador jugador = new Jugador(dineroInicialJugador, barrio, "Pablo", tablero);
        tablero.agregarCasillero(barrio);
        jugador.comprarPropiedad();

        Assert.assertTrue( jugador.puedeEdificar(barrio));
    }

    @Test
    public void alComprarUnBarrioUnicoYLLevarloAlMaximoDeEdificacionNoSePuedeEdificarMas(){
        Tablero tablero = new Tablero();
        Barrio barrio = new Barrio("Santa Fe",precioSantaFe,precioConstruirCasaSantaFe,false,precioAlquiler1casaSantaFe,0, 0, 0, precioAlquilerSantaFe);
        Jugador jugador = new Jugador(dineroInicialJugador, barrio, "Pablo", tablero);
        tablero.agregarCasillero(barrio);
        jugador.comprarPropiedad();
        jugador.edificar(EstadoConstruccion.Casa,(Barrio) jugador.casilleroActual());

        Assert.assertFalse( jugador.puedeEdificar(barrio));
    }

    @Test
    public void alEdificarEnSantaFeSinEdificacionesELEstadoDeConstruccionPasaASerCASA(){
        Tablero tablero = new Tablero();
        Barrio barrio = new Barrio("Santa Fe",precioSantaFe,precioConstruirCasaSantaFe,false,precioAlquiler1casaSantaFe,0, 0, 0, precioAlquilerSantaFe);
        Jugador jugador = new Jugador(dineroInicialJugador, barrio, "Pablo", tablero);
        tablero.agregarCasillero(barrio);
        jugador.comprarPropiedad();

        jugador.edificar(EstadoConstruccion.Casa,(Barrio) jugador.casilleroActual());

        Assert.assertTrue(barrio.getEstadoConstruccion().equals(EstadoConstruccion.Casa));
    }
}