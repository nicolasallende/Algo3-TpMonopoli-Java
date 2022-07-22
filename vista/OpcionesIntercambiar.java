package vista;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javafx.scene.control.ChoiceDialog;
import modelo.Jugador;
import modelo.Propiedad;
import modelo.Tablero;

public class OpcionesIntercambiar {
	private static String OPCION_POR_DEFECTO = "";
   
    private static String CONTEXT = "Escogiendo la propiedad:";
     
    private static String TITULO_JUGADORES = "Jugadores con quien intercambiar";
    private static String HEADER_JUGADOR = "Escoja el jugador con quien desea intercambiar: ";
    private static String CONTEXT_JUGADOR = "Escogiendo jugador:";
    
    public static String opcionEscogida(ChoiceDialog<String> dialog ){
        try {
            Optional<String> result = dialog.showAndWait();
            return result.get();
        } catch (NoSuchElementException e ){
            return OPCION_POR_DEFECTO;
        }
    }

    public static String darOpciones(Jugador jugador, String head, String titulo) {
    	ArrayList<Propiedad> propiedades = jugador.getPropiedades();
        ArrayList<String> nombrePropiedades =new ArrayList<String>();
        nombrePropiedades.add(0,OPCION_POR_DEFECTO);
		for(Propiedad p: propiedades) {
			 nombrePropiedades.add(p.getNombre());
        }
        ChoiceDialog<String> dialog = new ChoiceDialog<>(OPCION_POR_DEFECTO, nombrePropiedades);
        dialog.setTitle(titulo);
        dialog.setHeaderText(head);
        dialog.setContentText(CONTEXT);
        String barrioElegido = opcionEscogida(dialog);
        return barrioElegido;
    }
    
    
    public static String darOpcionConQueJugadorIntercambiar(Tablero tablero, Jugador jugador) {
    	List<Jugador> jugadores = tablero.getJugadores();
    	ArrayList<String> nombreJugadores = new ArrayList<String>();
    	nombreJugadores.add(0,OPCION_POR_DEFECTO);
    	for(Jugador j: jugadores) {
    		if(j != jugador && j.puedeIntercambiar()) {
    			nombreJugadores.add(j.nombre());
    		}
    	}
    	 ChoiceDialog<String> dialog = new ChoiceDialog<>(OPCION_POR_DEFECTO, nombreJugadores);
    	 dialog.setTitle(TITULO_JUGADORES);
         dialog.setHeaderText(HEADER_JUGADOR);
         dialog.setContentText(CONTEXT_JUGADOR);
         String jugadorElegido = opcionEscogida(dialog);
         return jugadorElegido;
    }

    
}


