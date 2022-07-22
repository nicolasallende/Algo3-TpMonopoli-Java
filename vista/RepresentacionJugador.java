package vista;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import modelo.Jugador;
import vista.RepresentacionEdificaciones.DibujoEdificacion;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class RepresentacionJugador {

    private final Color color;
    private static int RATIO = 20;
    private static String nombrecasilleros[] = {"Salida", "Quini6", "Buenos Aires Sur", "Edesur",
            "Buenos Aires Norte", "Carcel", "Cordoba Sur", "Avance Dinamico", "Subte", "Cordoba Norte",
            "Impuesto Al Lujo", "Santa Fe", "Aysa", "Salta Norte", "Salta Sur", "Policia", "Trenes", "Neuquen", "Retroceso Dinamico", "Tucuman"};

    private List<Double> posicion;
    public static Hashtable<String, List<Double>> posicionesCasilleros;
    private int numJugador;
    private Jugador jugadorAsociado;
    private static int INICIAL = 0;


    //Movimiento de Izq a Derecha. Se toma como Referencia, el comienzo en salida
    private static double movimientoEnX[] = {1180.0, 975.0, 775.0, 575.0, 375.0, 150.0};

    private static double movimientoEnY[] = {600.0, 450.0, 325.0, 220.0, 125.0, 10.0};

    static {
        posicionesCasilleros = new Hashtable<>();
//        Posiciones desde Salida hasta Carcel
        for (int i = 0; i < movimientoEnX.length; i++) {
            posicionesCasilleros.put(nombrecasilleros[i], Arrays.asList(movimientoEnX[i],movimientoEnY[0]));
        }

//        Posiciones desde Carcel hasta Impuesto de Lujo
        for (int i = 0; i < movimientoEnY.length -1; i++) {
            posicionesCasilleros.put(nombrecasilleros[6 + i], Arrays.asList(movimientoEnX[5], movimientoEnY[i+1]));
        }

//        Posiciones desde Impuesto de Lujo hasta Policia
        for (int i = 0; i < movimientoEnX.length - 1 ; i++) {
            posicionesCasilleros.put(nombrecasilleros[11 + i], Arrays.asList(movimientoEnX[4-i], movimientoEnY[5]));
        }

//        Posiciones desde Policia hasta Salida(no inlusive).
        for (int i = 0; i < movimientoEnY.length - 2 ; i++) {
            posicionesCasilleros.put(nombrecasilleros[16+i], Arrays.asList(movimientoEnX[0], movimientoEnY[4-i]));
        }
    }

    public RepresentacionJugador(Color color, int numJugador, Jugador jugador){
        this.jugadorAsociado = jugador;
        this.color = color;
        this.posicion = posicionesCasilleros.get(nombrecasilleros[INICIAL]);
        this.numJugador = numJugador;
    }

    public void move(String nombreCasillero) {
        this.posicion = posicionesCasilleros.get(nombreCasillero);
    }

    public List<Double> getPosicionDeCasilleroEdificado(String nombreCasillero){
        return posicionesCasilleros.get(nombreCasillero);
    }

    public double getPositionInX(){
        return this.posicion.get(0);
    }

    public double getPositionInY(){
        return this.posicion.get(1);
    }

    public int radio(){
        return RATIO;
    }

    public Color getColor(){
        return this.color;
    }

    public int getNumJugador(){
       return this.numJugador;
    }

    public Jugador getJugadorAsociado() {
        return this.jugadorAsociado;
    }
}
