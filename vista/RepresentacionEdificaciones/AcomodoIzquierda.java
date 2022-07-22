package vista.RepresentacionEdificaciones;

import java.util.List;

public class AcomodoIzquierda extends Acomodo {
    private static double MOVE_CASILLERO = 155.0;
    private static double rotacion = 90.0;
    private static double ACOMODO_EXTRA_Y = 17.0;
    private static double ACOMODO_SEGUNDA_CASA = 50.0;

    private double posX;
    private double posY;

    public AcomodoIzquierda() {
    }

    public void setPosition(List<Double> position){
        this.posX = position.get(0) + MOVE_CASILLERO;
        this.posY = position.get(1) + ACOMODO_EXTRA_Y;
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
        this.posY += ACOMODO_SEGUNDA_CASA;
    }
}
