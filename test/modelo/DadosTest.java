package modelo;


import modelo.Eventos.AvanceDinamico;
import modelo.Eventos.RetrocesoDinamico;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DadosTest {
	public DadosTest(){}



	@Test
	public void seComparanLosNumerosYNoVuelveATirar() {
		Dados dados = new Dados();
		dados.tiraHastaQueSeanIguales();
		Assert.assertTrue(dados.jugadorVuelveATirar());
	}

	@Test
	public void tiroDosVecesYYaNoLeCorrespondeTirarMas() {
		Dados dados = new Dados();
		dados.tiraHastaQueSeanIguales();
		dados.tiraHastaQueSeanIguales();
		Assert.assertFalse(dados.jugadorVuelveATirar());
	}


	//Para este Test se tomo en cuenta que la suma de dados si bien es estatica donde termina el jugador depende del casillero con lo cual, se contemplan los efectos.
//	@Test
//	public void alArrojarLosDadosELJugadorCambiaDePosicionEnFuncionDelaSumaDadosQueLeSalio() throws JAXBException, IOException {
//		Juego juego = new Juego();
//		Tablero tablero = juego.getTablero();
//		Jugador jugador = tablero.obtenerJugadorActual();
//		List<Casillero> casilleros = tablero.getCasilleros();
//
//		AvanceDinamico avance = (AvanceDinamico) tablero.obtenerCasilleroRelacionado("Avance Dinamico");
//		RetrocesoDinamico retroceso = (RetrocesoDinamico) tablero.obtenerCasilleroRelacionado("Retroceso Dinamico");
//		Casillero policia = tablero.obtenerCasilleroRelacionado("Policia");
//		Casillero carcel = tablero.obtenerCasilleroRelacionado("Carcel");
//
//		//Si va a la Policia esto lo manda derecho a la carcel, con lo cual se restan casilleros por eso de lo que pudo moverse.
//		int indexResto = casilleros.indexOf(policia) - casilleros.indexOf(carcel);
//
//		//Empieza en Salida
//		tablero.tirarDados();
//		int ultimoTiro = tablero.ultimoTiroRealizado();
//		if (casilleros.get(ultimoTiro).equals(policia)){
//			ultimoTiro = indexResto;
//		}
//
//		//Contemplamos el caso en el que pase por avance o retroceso.
//		ultimoTiro = ultimoTiro + avance.getMovimiento() + retroceso.getMovimiento();
// 		Assert.assertEquals(casilleros.get(ultimoTiro), jugador.casilleroActual());
//	}
}
