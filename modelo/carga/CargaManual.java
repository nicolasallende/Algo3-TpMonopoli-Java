package modelo.carga;

import java.util.Hashtable;

import modelo.Eventos.AvanceDinamico;
import modelo.Casillero;
import modelo.Eventos.Carcel;
import modelo.Eventos.ImpuestoAlLujo;
import modelo.Eventos.Policia;
import modelo.Eventos.Quini6;
import modelo.Eventos.RetrocesoDinamico;
import modelo.Eventos.Salida;

public class CargaManual {

    public void obteneCasillerosRelACarcel(Hashtable<String,Casillero> casillerosDesordenados){
        Casillero carcel = new Carcel();
        casillerosDesordenados.put(carcel.getNombre(),carcel);
        Casillero policia = new Policia((Carcel) carcel);
        casillerosDesordenados.put(policia.getNombre(), policia);
    }

    public void obtenerCasilleros(Hashtable<String,Casillero> casillerosDesordenados) {
        Casillero salida = new Salida();
        casillerosDesordenados.put(salida.getNombre(), salida);
        this.obteneCasillerosRelACarcel(casillerosDesordenados);
        this.obtenerCasillerosRelaMovDinamico(casillerosDesordenados);
        this.obtenerCasilleroRelaEventos(casillerosDesordenados);
    }

    private void obtenerCasilleroRelaEventos(Hashtable<String,Casillero> casillerosDesordenados) {
        Casillero quini6 = new Quini6();
        casillerosDesordenados.put(quini6.getNombre(), quini6);
        Casillero impuestoAlLujo = new ImpuestoAlLujo();
        casillerosDesordenados.put(impuestoAlLujo.getNombre(), impuestoAlLujo);
    }

    private void obtenerCasillerosRelaMovDinamico(Hashtable<String,Casillero> casillerosDesordenados) {
        Casillero avanceDinamico = new AvanceDinamico();
        casillerosDesordenados.put(avanceDinamico.getNombre(), avanceDinamico);
        Casillero retocesoDinamico = new RetrocesoDinamico();
        casillerosDesordenados.put(retocesoDinamico.getNombre(), retocesoDinamico);
    }

}
