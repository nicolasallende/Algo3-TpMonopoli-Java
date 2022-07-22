package vista.RepresentacionEdificaciones;

import java.util.List;

public class AcomodoDerecha extends Acomodo {

    private static double MOVE_CASILLERO = -175.0;
    private static double rotacion = 270.0;

    private static double MOVE_EXTRA = 85.0;
    private double posX;
    private double posY;

    public AcomodoDerecha() {
    }

    public void setPosition(List<Double> position){
        this.posX = position.get(0) + MOVE_CASILLERO;
        this.posY = position.get(1) + MOVE_EXTRA;
    }

    public double getPosX() {
        return this.posX;
    }

    public double getPosY() {
        return this.posY;
    }

    @Override
    public double getRotate() {
        return rotacion;
    }

    @Override
    public void sumarExtraAlaCoordenadaRelevante() {
        //En este caso como es Neuque y Tucuman. No puede tenes 2 casas.
    }
}
