package vista;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javafx.scene.control.ChoiceDialog;
import modelo.Jugador;
import modelo.Propiedad;

public class OpcionesVender {
	private static String OPCION_POR_DEFECTO = "";
    private static String TITULO = "Propiedades a Elegir";
    private static String HEADER = "Escoga la propiedad que desea vender: ";
    private static String CONTEXT = "Escogiendo la propiedad:";

    public static String opcionEscogida(ChoiceDialog<String> dialog ){
        try {
            Optional<String> result = dialog.showAndWait();
            return result.get();
        } catch (NoSuchElementException e ){
            return OPCION_POR_DEFECTO;
        }
    }

    public static String darOpciones(Jugador jugador) {
    	ArrayList<Propiedad> propiedades = jugador.getPropiedades();
        ArrayList<String> nombrePropiedades =new ArrayList<String>();
        nombrePropiedades.add(0,OPCION_POR_DEFECTO);
		for(Propiedad p: propiedades) {
			 nombrePropiedades.add(p.getNombre());
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>(OPCION_POR_DEFECTO, nombrePropiedades);
        dialog.setTitle(TITULO);
        dialog.setHeaderText(HEADER);
        dialog.setContentText(CONTEXT);
        String barrioElegido = opcionEscogida(dialog);
        return barrioElegido;
    }
}
