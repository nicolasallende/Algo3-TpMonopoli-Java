package vista.RepresentacionEdificaciones;

import vista.RepresentacionJugador;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public abstract class Acomodo {
    private static String nombrecasilleros[] = {"Salida", "Quini6", "Buenos Aires Sur", "Edesur",
            "Buenos Aires Norte", "Carcel", "Cordoba Sur", "Avance Dinamico", "Subte", "Cordoba Norte",
            "Impuesto Al Lujo", "Santa Fe", "Aysa", "Salta Norte", "Salta Sur", "Policia", "Trenes", "Neuquen", "Retroceso Dinamico", "Tucuman"};

    private static int ABAJO[]      = {0, 6},
                       IZQUIERDA[]  = {6, 11},
                       ARRIBA[]     = {11, 16},
                       DERECHA[]    = {16, 20};



    private static Hashtable <String, Acomodo> posicionamiento;

    static {
        posicionamiento = new Hashtable<>();
//        Posiciones desde Salida hasta Carcel
        for (int i = ABAJO[0]; i < ABAJO[1] ; i++) {
            posicionamiento.put(nombrecasilleros[i], new AcomodoAbajo());
        }

//        Posiciones desde Carcel hasta Impuesto de Lujo
        for (int i = IZQUIERDA[0]; i < IZQUIERDA[1]; i++) {
            posicionamiento.put(nombrecasilleros[i], new AcomodoIzquierda());
        }

//        Posiciones desde Impuesto de Lujo hasta Policia
        for (int i = ARRIBA[0]; i < ARRIBA[1] ; i++) {
            posicionamiento.put(nombrecasilleros[i],new AcomodoArriba());
        }

//        Posiciones desde Policia hasta Salida(no inlusive).
        for (int i = DERECHA[0]; i < DERECHA[1] ; i++) {
            posicionamiento.put(nombrecasilleros[i], new AcomodoDerecha());
        }
    }

    public abstract void setPosition(List<Double> position);
    public abstract double getPosX();
    public abstract double getPosY();
    public abstract double getRotate();


    public static Acomodo instanciarAcomodo(String nombreCasillero, RepresentacionJugador repJugador){
        List<Double> coord = repJugador.getPosicionDeCasilleroEdificado(nombreCasillero);
        Acomodo acomodo = posicionamiento.get(nombreCasillero);
        acomodo.setPosition(coord);
        return acomodo;
    }

    public abstract void sumarExtraAlaCoordenadaRelevante();
}
