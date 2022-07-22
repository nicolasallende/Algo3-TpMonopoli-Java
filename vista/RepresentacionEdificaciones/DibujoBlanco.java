package vista.RepresentacionEdificaciones;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import vista.RepresentacionJugador;

public class DibujoBlanco extends DibujoEdificacion {
	private RepresentacionJugador repJugador;
    private static Double TAMCASA = 50.0;
    private double cordenadaX;
    private double cordenadaY;
    private Acomodo acomodoCasa;

    public void sumarcomodoPosCorrespondientePorSegundaCasa(){
        this.acomodoCasa.sumarExtraAlaCoordenadaRelevante();
    }


    public DibujoBlanco(String nombreCasillero, RepresentacionJugador repJugador){
        this.repJugador = repJugador;
        this.acomodoCasa = Acomodo.instanciarAcomodo(nombreCasillero, this.repJugador);

    }

    public HBox dibujar() {
    	 HBox hBox= new HBox();

         hBox.setTranslateX(cordenadaX);
         hBox.setTranslateY(cordenadaY);
         hBox.setStyle("-fx-background-color: #ffffff;");

         hBox.setRotate(acomodoCasa.getRotate());

        return hBox;
    }

}
