package modelo;

import java.io.IOException;
import java.util.Hashtable;
import javax.xml.bind.JAXBException;


import modelo.Casillero;


import org.junit.Test;

import modelo.carga.CargaPorTipo;

public class CargaPorTipoTest {
	@Test
	public void getCasillerosBarriosTest() throws JAXBException, IOException{
		Hashtable<String, Casillero> casillerosDesordenados =new Hashtable<String, Casillero>();
		int cantidad=4;
		CargaPorTipo ct=new CargaPorTipo();
		ct.getCasillerosBarrios(casillerosDesordenados,cantidad);
		Casillero testSubject=casillerosDesordenados.get("Buenos Aires Sur");
	}
}
