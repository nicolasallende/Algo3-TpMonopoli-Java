package vista;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import modelo.Barrio;
import modelo.Jugador;
import modelo.construccion.EstadoConstruccion;
import vista.RepresentacionEdificaciones.DibujoEdificacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BoxJugador {

    private final RepresentacionJugador repjugador;
    private Image image;
    private ContenedorJuego contenedorJuego;
    private HashMap<String, List<HBox> > representacionEdificaciones;

    public BoxJugador(RepresentacionJugador repjugador, ContenedorJuego contenedorJuego, Image image){
        this.repjugador = repjugador;
        this.image = image;
        this.contenedorJuego = contenedorJuego;
        this.representacionEdificaciones = new HashMap<>();
    }

    public void draw(GraphicsContext gc) {
        this.drawShapes(gc, this.repjugador.getColor());
    }

    private void drawShapes(GraphicsContext gc, Color color) {
        gc.setFill(color);
        double posX = this.repjugador.getPositionInX()+(-30*(this.repjugador.getNumJugador()) );
        double posY = this.repjugador.getPositionInY()+(30*(this.repjugador.getNumJugador()+1));
        gc.fillOval(posX, posY , this.repjugador.radio(), this.repjugador.radio());
    }


    private void redibujarLoEstatico(GraphicsContext gc){
        List<BoxJugador> viewJugadores = this.contenedorJuego.getViewJugadores();
        for (BoxJugador boxJugador: viewJugadores) {
            if (! this.repjugador.equals(boxJugador.getRepjugador())) {
                boxJugador.drawShapes(gc, boxJugador.getRepjugador().getColor());
            }
        }
    }

    public void clean(GraphicsContext gc) {
        gc.drawImage(this.image,0, 0);
        this.redibujarLoEstatico(gc);
    }

    public void inicializarEnCasoDeQueLoRequiera(String nombreCasillero){
        if (! this.representacionEdificaciones.containsKey(nombreCasillero)) {
            this.representacionEdificaciones.put(nombreCasillero, new ArrayList<>());
        }
    }


    private void removerImagenesDeCasaDelTablero(List<HBox> hBoxes) {
        for (HBox boxImageHouse: hBoxes) {
            contenedorJuego.getChildren().remove(boxImageHouse);
        }
    }
    public void eliminarRepresentacionEdificacionesEnBarrio(String nombreBarrio){
        for (HBox boxRepEdif: this.representacionEdificaciones.get(nombreBarrio)) {
            this.contenedorJuego.getChildren().remove(boxRepEdif);
        }
        this.representacionEdificaciones.remove(nombreBarrio);
    }

    public void agregarRepresentacionEdificacion(EstadoConstruccion estadoConstruccion, String nombreCasillero, RepresentacionJugador repJugador){
        inicializarEnCasoDeQueLoRequiera(nombreCasillero);

        //Obtenemos la representacion de la Imagen
        DibujoEdificacion dibujoEdificacion = DibujoEdificacion.instanciarDibujoARealizar(estadoConstruccion, nombreCasillero, repJugador);
        HBox boxImagenEdif = dibujoEdificacion.dibujar();

        if (! estadoConstruccion.equals(EstadoConstruccion.Hotel) ){
            List<HBox> listaImagenes = new ArrayList<>();
            listaImagenes.addAll(this.representacionEdificaciones.get(nombreCasillero));

            //Agregamos las nueva en la lista y la agregamos.
            listaImagenes.add(boxImagenEdif);
            this.representacionEdificaciones.put(nombreCasillero, listaImagenes);
        } else {
            this.removerImagenesDeCasaDelTablero(this.representacionEdificaciones.get(nombreCasillero));
            this.representacionEdificaciones.put(nombreCasillero, Arrays.asList(boxImagenEdif));
        }

        this.contenedorJuego.getChildren().add(boxImagenEdif);
    }

    public HashMap<String, List<HBox>> getRepresentacionEdificaciones() {
        return this.representacionEdificaciones;
    }

    public void update(GraphicsContext gc) {
        this.draw(gc);
    }



    public RepresentacionJugador getRepjugador() {
        return this.repjugador;
    }

    public boolean tieneRepresentacionesEdificadas(String propiedadElegida) {
        return this.representacionEdificaciones.containsKey(propiedadElegida);
    }
}
