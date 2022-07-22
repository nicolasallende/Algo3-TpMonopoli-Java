package modelo.carga;

import javax.xml.bind.JAXBException;

import modelo.Casillero;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class CargaTablero {
    private CargaManual cargaManual;
    private CargaPorTipo cargaPorTipo;
    private static int cantidadCompanias = 4;
    private static int cantidadBarrios = 9;
    private static String ordenCasilleros[] = {"Salida", "Quini6", "Buenos Aires Sur", "Edesur",
            "Buenos Aires Norte", "Carcel", "Cordoba Sur", "Avance Dinamico", "Subte", "Cordoba Norte",
            "Impuesto Al Lujo", "Santa Fe", "Aysa", "Salta Norte", "Salta Sur", "Policia", "Trenes", "Neuquen", "Retroceso Dinamico", "Tucuman"};

    public CargaTablero(){
        cargaManual = new CargaManual();
        cargaPorTipo = new CargaPorTipo();
    }

    public Hashtable<String, Casillero> obtenerCasillerosDesordenados() throws JAXBException, IOException {
        Hashtable<String, Casillero>  casillerosDesordenados = new Hashtable<>();
        this.cargaManual.obtenerCasilleros(casillerosDesordenados);
        this.cargaPorTipo.getCasillerosBarrios(casillerosDesordenados,cantidadBarrios);
        this.cargaPorTipo.getCasillerosCompanias(casillerosDesordenados,cantidadCompanias);
        return casillerosDesordenados;
    }

    public List<Casillero> obtenerCasillerosOrdenados(Hashtable<String, Casillero> casillerosDesordenados){
        List<Casillero> tableroOrdenado= new ArrayList<>();
        for (String nombreCasilleros: ordenCasilleros) {
            Casillero casillero = casillerosDesordenados.get(nombreCasilleros);
            tableroOrdenado.add(casillero);
        }
        return tableroOrdenado;
    }

    public List<Casillero> obtenertablero() throws JAXBException, IOException {
        Hashtable<String, Casillero> casillerosDesordenados = this.obtenerCasillerosDesordenados();
        return this.obtenerCasillerosOrdenados(casillerosDesordenados);
    }


}
