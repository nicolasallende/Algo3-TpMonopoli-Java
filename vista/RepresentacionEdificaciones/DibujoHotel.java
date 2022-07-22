package vista.RepresentacionEdificaciones;

import javafx.scene.layout.HBox;
import vista.RepresentacionJugador;

import java.util.List;

public class DibujoHotel extends DibujoEdificacion {
    private RepresentacionJugador repJugador;

    private double cordenadaX;
    private double cordenadaY;
    private static String filePath = "file:src/vista/Recursos/hotel_monopoly.jpg";
    private static double ANCHO = 60;
    private static double ALTO= 80;

    private Acomodo acomodoHotel;

    public DibujoHotel(String nombreCasillero, RepresentacionJugador repJugador){
        this.repJugador = repJugador;

        this.acomodoHotel = Acomodo.instanciarAcomodo(nombreCasillero, this.repJugador);
        this.cordenadaX = acomodoHotel.getPosX();
        this.cordenadaY = acomodoHotel.getPosY();
    }

    public HBox dibujar(){
        //Aca vos deberias saber en funciones de las coordenadas que te dan si los tenes que rotar o no.
        return super.crearFigura(this.acomodoHotel, filePath, ALTO, ANCHO, this.cordenadaX, this.cordenadaY);
    }
}
