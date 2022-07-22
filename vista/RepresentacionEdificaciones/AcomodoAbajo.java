package vista.RepresentacionEdificaciones;

import java.util.List;

public class AcomodoAbajo extends Acomodo {
    private static double MOVE_CASILLERO = -108.0;
    private static double rotacion = 0.0;
    private static double ACOMODO_SEGUNDA_CASA = 50.0;

    private double posX;
    private double posY;

    public AcomodoAbajo() {

    }

    public void setPosition(List<Double> position){
        this.posX = position.get(0);
        this.posY = position.get(1) + MOVE_CASILLERO;
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
        this.posX -= ACOMODO_SEGUNDA_CASA;
    }


}
