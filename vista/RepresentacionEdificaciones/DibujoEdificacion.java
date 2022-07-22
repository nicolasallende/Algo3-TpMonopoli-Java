package vista.RepresentacionEdificaciones;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import modelo.construccion.EstadoConstruccion;
import vista.RepresentacionJugador;

public abstract class DibujoEdificacion {
    private static int valoresposibles[] = {1,2};

    public static DibujoEdificacion instanciarDibujoARealizar(EstadoConstruccion estado, String nombreCasillero, RepresentacionJugador repJugador){
        DibujoEdificacion dibujoEdificacion = null;
        switch(estado) {
            case Casa:
                dibujoEdificacion = new DibujoCasa(valoresposibles[0], nombreCasillero, repJugador);
                break;
            case DosCasas:
                dibujoEdificacion = new DibujoCasa(valoresposibles[1], nombreCasillero, repJugador);
                break;
            case Hotel:
                dibujoEdificacion = new DibujoHotel(nombreCasillero, repJugador);
                break;
            case SinNada:
            	dibujoEdificacion = new DibujoBlanco(nombreCasillero, repJugador);
            	break;
            default:
            	break;
            
        }
        return dibujoEdificacion;
    }

    public abstract HBox dibujar();

    public HBox crearFigura(Acomodo acomodo, String filePath,double ancho, double alto, double coordX, double coordY){
        HBox boxImageEdif= new HBox();

        Image imageEdif = new Image(filePath);
        ImageView imageViewEdif= new ImageView(imageEdif);
        imageViewEdif.setFitHeight(ancho);
        imageViewEdif.setFitWidth(alto);

        boxImageEdif.setTranslateX(coordX);
        boxImageEdif.setTranslateY(coordY);
        boxImageEdif.getChildren().add(imageViewEdif);

        boxImageEdif.setRotate(acomodo.getRotate());
        return boxImageEdif;
    }
}
