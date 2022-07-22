package modelo.carga;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import modelo.Barrio;
import modelo.Casillero;
import modelo.Compania;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;


public class CargaPorTipo {


    private Barrio inicializarBarrio(CargaBarrio cargaBarrio){
        Barrio barrio = new Barrio(
            cargaBarrio.getNombre(), cargaBarrio.getPrecio(), cargaBarrio.getConstruirCasa(), cargaBarrio.getRelacionado(),
            cargaBarrio.getAlquiler1Casa(),cargaBarrio.getAlquiler2Casas(), cargaBarrio.getConstruirHotel(), cargaBarrio.getAlquilerConHotel(),
            cargaBarrio.getAlquiler()
            );
        return barrio;
    }

    public void getCasillerosBarrios(Hashtable<String, Casillero> casillerosDesordenados, int cantidad) throws JAXBException, IOException {
        try {
            JAXBContext context = JAXBContext.newInstance(CargaBarrio.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            String fileName = "src/casillerosXML/barrio";
            for (int i = 0; i < cantidad; i++) {
                String fileNameComplete = fileName + Integer.toString(i) + ".xml";
                CargaBarrio cargaBarrio = (CargaBarrio) unmarshaller.unmarshal(new File(fileNameComplete));
                Barrio barrio = this.inicializarBarrio(cargaBarrio);
                casillerosDesordenados.put(barrio.getNombre(), barrio);
            }
        } catch (JAXBException e){
            System.out.println("Se rompio todo en Barrio");
            e.printStackTrace();
        }
    }


    private Compania inicializarCompania(CargaCompania cargaCompania){
        Compania compania = new Compania(
                cargaCompania.getNombre(), cargaCompania.getPrecio(),
                cargaCompania.getMultiplicador(), cargaCompania.getMultiplicador2(), cargaCompania.getCompaniaRelacionada());
        return compania;
    }

    public void getCasillerosCompanias(Hashtable<String, Casillero>  casillerosDesordenados, int cantidad) throws JAXBException, IOException  {
        try {
            JAXBContext context = JAXBContext.newInstance(CargaCompania.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            String fileName = "src/casillerosXML/compania";
            for (int i = 0; i < cantidad; i++) {
                String fileNameComplete = fileName + Integer.toString(i) + ".xml";
                CargaCompania cargaCompania = (CargaCompania) unmarshaller.unmarshal(new File(fileNameComplete));
                Compania compania = this.inicializarCompania(cargaCompania);
                casillerosDesordenados.put(compania.getNombre(), compania);
            }
        } catch (JAXBException e){
            System.out.println("Se rompio todo en Compania");
            e.printStackTrace();
        }
    }
}
