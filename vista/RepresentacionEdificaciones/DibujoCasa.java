package vista.RepresentacionEdificaciones;

import javafx.scene.layout.HBox;
import vista.RepresentacionJugador;

import java.util.List;

public class DibujoCasa extends DibujoEdificacion {
    private RepresentacionJugador repJugador;
    private static Double TAMCASA = 50.0;
    private double cordenadaX;
    private double cordenadaY;
    private static String filePath = "file:src/vista/Recursos/house_monopoly.jpg";
    private int casasAdibujar;
    private Acomodo acomodoCasa;
    private static int segunda_Casa = 2;

    public void sumarcomodoPosCorrespondientePorSegundaCasa(){
        this.acomodoCasa.sumarExtraAlaCoordenadaRelevante();
    }


    public DibujoCasa(int numCasa, String nombreCasillero, RepresentacionJugador repJugador){
        this.repJugador = repJugador;
        this.casasAdibujar = 0;
        this.acomodoCasa = Acomodo.instanciarAcomodo(nombreCasillero, this.repJugador);

        if (numCasa == segunda_Casa) sumarcomodoPosCorrespondientePorSegundaCasa();
        this.cordenadaX = acomodoCasa.getPosX();
        this.cordenadaY = acomodoCasa.getPosY();
    }

    public HBox dibujar() {
        HBox hBox = super.crearFigura(this.acomodoCasa, filePath, TAMCASA, TAMCASA, this.cordenadaX, this.cordenadaY);
        return hBox;
    }

}
