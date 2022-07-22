package modelo;

import modelo.acciones.AccionesDelJugador;
import modelo.construccion.EstadoConstruccion;
import modelo.estados.EstadosPosiblesDelJugador;

import org.junit.Assert;
import org.junit.Test;

import modelo.Eventos.Quini6;
import modelo.Eventos.Salida;
import modelo.Eventos.Carcel;


public class JugadorTest {
	//Constantes
	final long dineroInicialJugador = 100000;
    final long precioCordobaNorte = 20000;
    final long precioCordobaSur = 18000;
    final long precioConstruirCasaCordobaNorte = 2500;
    final long precioConstruirCasaCordobaSur = 2000;
    final long precioBuenosAiresNorte = 25000;
    final long precioBuenosAiresSur = 20000;
    final long precioConstruirCasaBuenosAiresNorte = 5500;
    final long precioConstruirCasaBuenosAiresSur = 5000;
    final long precionAlqBNorte = 2500;
    final long precionAlqBSur = 2000;
    final long precioAlqCNorte = 1300;
    final long precioAlqCSur= 1000;

    final long precioSantaFe =15000,
            precioConstruirCasaSantaFe =4000,
            precioAlquiler1casaSantaFe=3500,
            precioAlquilerSantaFe = 1500;

    @Test
    public void seMueveAOtroCasilleroYseActualizaElCasilleroDondeEstaPosicionado(){
        Tablero tablero = new Tablero();
        Salida salida = new Salida();
        Jugador jugador = new Jugador(0,salida ,"jugador1",tablero);
        Quini6 quini6 = new Quini6();
        Assert.assertEquals(salida, jugador.casilleroActual());
        jugador.cambiarCasillero(quini6);
        Assert.assertEquals(quini6, jugador.casilleroActual());
    }

    @Test
    public void seIntercambianPropiedades() {
    	 Tablero tablero = new Tablero();
         Barrio barrioNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4500, 0, 0,precionAlqBNorte);
         Barrio barrioSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 0, 0,precionAlqBSur);
         tablero.agregarCasillero(barrioNorte);
         tablero.agregarCasillero(barrioSur);
         
         Jugador jugador1 = new Jugador(0, barrioNorte, "Pablo", tablero);
         Jugador jugador2 = new Jugador(0, barrioSur, "Juan", tablero);
         
         jugador1.comprarPropiedad();
         jugador2.comprarPropiedad();
         
         jugador1.intercambiarPropiedades(barrioNorte, barrioSur, jugador2);
         Assert.assertEquals(barrioNorte.propietario(), jugador2);
         Assert.assertEquals(barrioSur.propietario(), jugador1);
    }
    
    @Test
    public void seIntercambianPropiedadesYCuandoOtroJugadorCaeLaPlataVaAlNuevoDuenio() {
    	Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4500, 0, 0,precionAlqBNorte);
        Barrio barrioSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 0, 0,precionAlqBSur);
        barrioNorte.setAlquiler(10000);
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);
        
        Jugador jugador1 = new Jugador(0, barrioNorte, "Pablo", tablero);
        Jugador jugador2 = new Jugador(0, barrioSur, "Juan", tablero);
        Jugador elQueVaAPagar = new Jugador(100000, barrioNorte, "Bam", tablero);
        tablero.agregarJugador(jugador1);
        tablero.agregarJugador(jugador2);
        tablero.agregarJugador(elQueVaAPagar);
        
        jugador1.comprarPropiedad();//compra barrio Norte
        jugador2.comprarPropiedad();//compra barrio sur
        jugador1.intercambiarPropiedades(barrioNorte, barrioSur, jugador2);

        barrioNorte.pagarPaso(elQueVaAPagar);
        Assert.assertEquals(jugador1, barrioSur.propietario());
        Assert.assertEquals(jugador2, barrioNorte.propietario());
        Assert.assertEquals(90000, elQueVaAPagar.dinero());
        Assert.assertEquals(10000, jugador2.dinero());
        Assert.assertEquals(0, jugador1.dinero());
    }


    @Test
    public void seIntercambianPropiedadesYSeCobraElPrecioSinCasasConstruidas(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4500, 0, 0,precionAlqBNorte);
        Barrio barrioSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 0, 0,precionAlqBSur);
        Barrio barrioX = new Barrio("Barrio X",0,0, false,0,0,0,0,0);
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);
        tablero.agregarCasillero(barrioX);

        Jugador jugador1 = new Jugador(100000, barrioSur, "Pablo", tablero);
        Jugador jugador2 = new Jugador(0, barrioX, "Juan", tablero);
        Jugador elQueVaAPagar = new Jugador(100000, barrioNorte, "Bam", tablero);
        tablero.agregarJugador(jugador1);
        tablero.agregarJugador(jugador2);
        tablero.agregarJugador(elQueVaAPagar);


        jugador1.comprarPropiedad();
        jugador1.cambiarCasillero(barrioNorte);
        jugador1.comprarPropiedad();
        jugador1.edificar(EstadoConstruccion.Casa,(Barrio) jugador1.casilleroActual());//construye una casa en barrio norte
        barrioNorte.jugadorCayo(elQueVaAPagar);
        Assert.assertEquals(96500, elQueVaAPagar.dinero());//con esto controlo que si se creo la casa en barrio norte

        jugador2.comprarPropiedad();
        jugador1.intercambiarPropiedades(barrioNorte, barrioX, jugador2);
        barrioNorte.jugadorCayo(elQueVaAPagar);

        Assert.assertEquals(jugador1, barrioX.propietario);
        Assert.assertEquals(jugador2, barrioNorte.propietario);
        Assert.assertEquals(94000, elQueVaAPagar.dinero());
        Assert.assertEquals(2500, jugador2.dinero());
        Assert.assertEquals(53000, jugador1.dinero());
    }

    @Test
    public void jugadoCaeEnPropiedadDeUnJugadorPresoPierdeDineroIgual(){
        Tablero tablero = new Tablero();
        Carcel carcel=new Carcel();
        Salida salida = new Salida();

        Barrio barrioNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4500, 0, 0,precionAlqBNorte);
        Barrio barrioSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 0, 0,precionAlqBSur);
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);

        Jugador jugadorPreso = new Jugador(100000, barrioNorte, "Pablo", tablero);
        Jugador jugadorQuePaga = new Jugador(100000, salida, "Juan", tablero);

        jugadorPreso.comprarPropiedad();//compra barrio Norte
        jugadorPreso.cambiarCasillero(barrioSur);
        jugadorPreso.comprarPropiedad();//compra barrio Sur

        jugadorPreso.edificar(EstadoConstruccion.Casa,(Barrio) jugadorPreso.casilleroActual());
        jugadorPreso.edificar(EstadoConstruccion.Casa,(Barrio) jugadorPreso.casilleroActual());

        carcel.jugadorCayo(jugadorPreso);
        barrioSur.jugadorCayo(jugadorQuePaga);

        Assert.assertEquals( 96500,jugadorQuePaga.dinero());
    }

    @Test
    public void jugadorCobraAlquilerAunqueEstePreso(){
        Tablero tablero = new Tablero();
        Carcel carcel=new Carcel();
        Salida salida = new Salida();

        Barrio barrioNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4000, 0, 0,precionAlqBNorte);
        Barrio barrioSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 0, 0,precionAlqBSur);
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);

        Jugador jugadorPreso = new Jugador(100000, barrioNorte, "Pablo", tablero);
        Jugador jugadorQuePaga = new Jugador(100000, salida, "Juan", tablero);

        jugadorPreso.comprarPropiedad();//compra barrio Norte
        jugadorPreso.cambiarCasillero(barrioSur);
        jugadorPreso.comprarPropiedad();//compra barrio Sur

        jugadorPreso.edificar(EstadoConstruccion.Casa,(Barrio) jugadorPreso.casilleroActual());
        jugadorPreso.edificar(EstadoConstruccion.Casa,(Barrio) jugadorPreso.casilleroActual());

        carcel.jugadorCayo(jugadorPreso);
        barrioSur.jugadorCayo(jugadorQuePaga);

        Assert.assertEquals( 48500 ,jugadorPreso.dinero());
    }


    @Test
    public void jugadorGanaDineroSiOtroJugadorCaeEnSuPropiedadCon1Casa(){
        Tablero tablero = new Tablero();
        Salida salida = new Salida();

        Barrio barrioNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4500, 0, 0,precionAlqBNorte);
        Barrio barrioSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 0, 0,precionAlqBSur);
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);

        Jugador jugadorPropietario = new Jugador(100000, barrioNorte, "Pablo", tablero);
        Jugador jugadorQuePaga = new Jugador(100000, salida, "Juan", tablero);

        jugadorPropietario.comprarPropiedad();//compra barrio Norte
        jugadorPropietario.cambiarCasillero(barrioSur);
        jugadorPropietario.comprarPropiedad();//compra barrio Sur

        jugadorPropietario.edificar(EstadoConstruccion.Casa,(Barrio) jugadorPropietario.casilleroActual());

        barrioSur.jugadorCayo(jugadorQuePaga);

        Assert.assertEquals(53000, jugadorPropietario.dinero());
    }

    @Test
    public void jugadorGanaDineroSiOtroJugadorCaeEnSuPropiedadCon2Casas(){
        Tablero tablero = new Tablero();
        Salida salida = new Salida();

        Barrio barrioNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4500, 0, 0,precionAlqBNorte);
        Barrio barrioSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 0, 0,precionAlqBSur);
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);

        Jugador jugadorPropietario = new Jugador(100000, barrioNorte, "Pablo", tablero);
        Jugador jugadorQuePaga = new Jugador(100000, salida, "Juan", tablero);

        jugadorPropietario.comprarPropiedad();//compra barrio Norte
        jugadorPropietario.cambiarCasillero(barrioSur);
        jugadorPropietario.comprarPropiedad();//compra barrio Sur

        jugadorPropietario.edificar(EstadoConstruccion.Casa,(Barrio) jugadorPropietario.casilleroActual());
        jugadorPropietario.edificar(EstadoConstruccion.Casa,(Barrio) jugadorPropietario.casilleroActual());

        barrioSur.jugadorCayo(jugadorQuePaga);

        Assert.assertEquals(48500, jugadorPropietario.dinero());
    }

    @Test
    public void jugadorGanaDineroSiOtroJugadorCaeEnSuPropiedadConHotel(){
        Tablero tablero = new Tablero();
        Salida salida = new Salida();

        Barrio barrioNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4500, 9000, 6000,precionAlqBNorte);
        Barrio barrioSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 0, 0,precionAlqBSur);
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);

        Jugador jugadorPropietario = new Jugador(100000, barrioNorte, "Pablo", tablero);
        Jugador jugadorQuePaga = new Jugador(100000, salida, "Juan", tablero);

        jugadorPropietario.comprarPropiedad();//compra barrio Norte
        jugadorPropietario.cambiarCasillero(barrioSur);
        jugadorPropietario.comprarPropiedad();//compra barrio Sur

        jugadorPropietario.edificar(EstadoConstruccion.Casa,(Barrio) jugadorPropietario.casilleroActual());
        jugadorPropietario.edificar(EstadoConstruccion.Casa,(Barrio) jugadorPropietario.casilleroActual());
        jugadorPropietario.cambiarCasillero(barrioNorte);
        jugadorPropietario.edificar(EstadoConstruccion.Casa,(Barrio) jugadorPropietario.casilleroActual());
        jugadorPropietario.edificar(EstadoConstruccion.Casa,(Barrio) jugadorPropietario.casilleroActual());
        jugadorPropietario.edificar(EstadoConstruccion.Hotel,(Barrio) jugadorPropietario.casilleroActual());

        barrioNorte.jugadorCayo(jugadorQuePaga);

        Assert.assertEquals(31000, jugadorPropietario.dinero());
    }


    @Test
    public void jugadorNoPuedeComprarPropiedadConDuenio(){
        Tablero tablero = new Tablero();

        Barrio barrioSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 0, 0,precionAlqBSur);
        tablero.agregarCasillero(barrioSur);

        Jugador jugadorConBarrio = new Jugador(100000, barrioSur, "Pablo", tablero);
        Jugador jugadorQueNoPuedeComprar = new Jugador(100000,barrioSur, "Martin", tablero );
        long dineroInicial = jugadorQueNoPuedeComprar.dinero();

        jugadorConBarrio.comprarPropiedad();
        jugadorQueNoPuedeComprar.comprarPropiedad();

        Assert.assertEquals(dineroInicial, jugadorQueNoPuedeComprar.dinero() );
    }

    @Test
    public void jugadorQueAcabaDeComprarUnBarrioNoPuedeEdificarEnElMismoTurno(){
        Tablero tablero = new Tablero();
        Barrio barrio = new Barrio("Barrio Unico",8000,1000,false,3000,3500, 0, 0,0);
        tablero.agregarCasillero(barrio);

        Jugador jugador = new Jugador(10000, barrio, "Pablo", tablero);
        jugador.cambiarEstado(EstadosPosiblesDelJugador.PreDados);
        jugador.comprarPropiedad();
        long dineroInicial = jugador.dinero();

        Assert.assertTrue(!jugador.hacer(AccionesDelJugador.edificar));
       	Assert.assertEquals(dineroInicial, jugador.dinero() );
    }

    @Test
    public void jugadorNoPuedeEdificarUnBarrioQueAdmiteSoloUnaConstruccion(){
        Tablero tablero = new Tablero();
        Barrio barrio = new Barrio("Barrio Unico",3000,1000,false,3000,3500, 0, 0,0);
        tablero.agregarCasillero(barrio);

        Jugador jugador = new Jugador(10000, barrio, "Pablo", tablero);
        jugador.comprarPropiedad();
        jugador.edificar(EstadoConstruccion.Casa,(Barrio) jugador.casilleroActual());
        long dineroRestante = jugador.dinero();
        jugador.edificar(EstadoConstruccion.Casa,(Barrio) jugador.casilleroActual());

        Assert.assertEquals(dineroRestante, jugador.dinero());
    }


    @Test
    public void jugadorQueTieneBuenosAiresCompletoY2CasasEnUnay0EnlaOtraNoPuedeConstruirHotel(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4500, 0, 0,precionAlqBNorte);
        Barrio barrioSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 0, 0,precionAlqBSur);
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);

        Jugador jugador1 = new Jugador(100000, barrioNorte, "Pablo", tablero);
        jugador1.comprarPropiedad();
        jugador1.cambiarCasillero(barrioSur);
        jugador1.comprarPropiedad();
        jugador1.edificar(EstadoConstruccion.Casa,(Barrio)jugador1.casilleroActual());
        jugador1.edificar(EstadoConstruccion.Casa,(Barrio)jugador1.casilleroActual());

        Assert.assertFalse(jugador1.puedeEdificar(barrioSur));
        Assert.assertFalse(barrioSur.puedeConstruirHotel(jugador1));
    }

    @Test
    public void alComprarCordobaCompletoYConstruir2CasasAmbasEstoyEnCondicionesDeConstruirHotelesEnLasDosBarrios(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Buenos Aires Norte",precioBuenosAiresNorte,precioConstruirCasaBuenosAiresNorte,true,3500,4500, 0, 0,precionAlqBNorte);
        Barrio barrioSur = new Barrio("Buenos Aires Sur",precioBuenosAiresSur,precioConstruirCasaBuenosAiresSur,true,3000,3500, 0, 0,precionAlqBSur);

        //Se agrega a Tablero los dos Barrios
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);

        //inicializa y Compra  Cordoba
        Jugador jugador = new Jugador(100000, barrioNorte, "Pablo", tablero);
        jugador.comprarPropiedad();
        jugador.cambiarCasillero(barrioSur);
        jugador.comprarPropiedad();
        jugador.edificar(EstadoConstruccion.Casa,barrioSur);
        jugador.edificar(EstadoConstruccion.Casa,barrioSur);
        jugador.cambiarCasillero(barrioNorte);
        jugador.edificar(EstadoConstruccion.Casa,barrioNorte);
        jugador.edificar(EstadoConstruccion.Casa,barrioNorte);

        Assert.assertTrue( jugador.puedeEdificar(barrioNorte));
        Assert.assertTrue( jugador.puedeEdificar(barrioSur));
        Assert.assertTrue( barrioNorte.puedeConstruirHotel(jugador));
        Assert.assertTrue( barrioSur.puedeConstruirHotel(jugador));
    }
    @Test
    public void alComprarCordobaCompletoYConstruir2CasasAmbasConstruyoUnHotelEnCadaUnaYNoPuedoConstruirMas(){
        Tablero tablero = new Tablero();
        Barrio barrioNorte = new Barrio("Cordoba Norte", precioCordobaNorte, 2500, true, 1800, 2900, 3500, 0, precioAlqCNorte);
        Barrio barrioSur = new Barrio("Cordoba Sur", precioCordobaSur, 2000, true, 1500, 2500, 3000, 0,precioAlqCSur);

        //Se agrega a Tablero los dos Barrios
        tablero.agregarCasillero(barrioNorte);
        tablero.agregarCasillero(barrioSur);

        //inicializa y Compra  Cordoba
        Jugador jugador = new Jugador(100000, barrioNorte, "Pablo", tablero);
        jugador.comprarPropiedad();

        //Me muevo a Cordoba Sur, compro y edifico
        jugador.cambiarCasillero(barrioSur);
        jugador.comprarPropiedad();

        jugador.edificar(EstadoConstruccion.Casa,barrioSur);
        jugador.edificar(EstadoConstruccion.Casa,barrioSur);

        //Me muevo a Cordoba Norte y empiezo a edificar
        jugador.cambiarCasillero(barrioNorte);
        jugador.edificar(EstadoConstruccion.Casa,barrioNorte);
        jugador.edificar(EstadoConstruccion.Casa,barrioNorte);
        jugador.edificar(EstadoConstruccion.Hotel,barrioNorte);

        //Me muevo de nuevoedificarHotel
        jugador.cambiarCasillero(barrioSur);
        jugador.edificar(EstadoConstruccion.Hotel,barrioSur);

        Assert.assertFalse(barrioNorte.tieneCapacidad());
        Assert.assertFalse(barrioSur.tieneCapacidad());
    }

    @Test
    public void jugadorNoPuedeConstruirHotelEnBarrioUnico(){
        Tablero tablero = new Tablero();
        Barrio barrio = new Barrio("Santa Fe",precioSantaFe,precioConstruirCasaSantaFe,false,precioAlquiler1casaSantaFe,0, 0, 0, precioAlquilerSantaFe);
        Jugador jugador = new Jugador(dineroInicialJugador, barrio, "Pablo", tablero);
        tablero.agregarCasillero(barrio);
        jugador.comprarPropiedad();
        jugador.edificar(EstadoConstruccion.Casa,barrio);

        //Si no puede construir un Hotel el dinero tiene que ser el mismo antes que despues
        long dineroAntes = jugador.dinero();
        jugador.edificar(EstadoConstruccion.Hotel, barrio);
        Assert.assertEquals(dineroAntes, jugador.dinero());
    }


}