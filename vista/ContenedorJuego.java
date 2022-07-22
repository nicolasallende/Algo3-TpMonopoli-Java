package vista;

import controlador.BotonSalirEventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modelo.Juego;
import modelo.Jugador;
import modelo.Tablero;
import modelo.acciones.AccionesDelJugador;
import vista.Handlers.BotonComprarPropiedadHandler;
import vista.Handlers.BotonDadosrHandler;
import vista.Handlers.BotonEdificarHandler;
import vista.Handlers.BotonFTurnoHandler;
import vista.Handlers.BotonIntercambioHandler;
import vista.Handlers.BotonPagarFianzaHandler;
import vista.Handlers.BotonVenderHandler;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ContenedorJuego extends BorderPane {
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    private final MediaPlayer musicaJuego;

    Stage stage;
    Juego juego;
    Tablero tablero;
    Button botonDados , botonFTurno , botonIntercambio , botonComprarPropiedad ,
    	botonPagarFianza , botonMover , botonSalir , botonEdificar , botonVender ;
    
    List<BoxJugador> viewJugadores;
    private static Color colores[] = {Color.GREEN, Color.RED, Color.BLUE};
    private static double translateX = 220;
    private GraphicsContext gc;
    private ContenedorInferiorJugadores contenedorInferiorJugadores;
    private ContenedorUsuariosPropiedades contenedorUsuariosPropiedades ;
    private static String LETRA = "Forte";

    public ContenedorJuego(Stage stage, MediaPlayer musicaJuego) throws JAXBException, IOException {
        musicaJuego.play();
        this.musicaJuego = musicaJuego;

        this.juego = new Juego();
        this.viewJugadores = new ArrayList<>();
        this.stage = stage;
        this.setCentro();
        this.setBotonera();
        this.tablero = juego.getTablero();
        iniciarlizarRepresentacionJugadores();
        this.actualizarBotonera();

    }

    public GraphicsContext getGc() {
        return this.gc;
    }

    private void setBotonera() {//set usado en el constructor

    	botonDados = BotonGenerico.generarBotonJuego("Tirar Dados",LETRA, new BotonDadosrHandler(this.gc,juego, this));
    	botonFTurno = BotonGenerico.generarBotonJuego("Finalizar Turno",LETRA, new BotonFTurnoHandler(juego, this, stage, this.musicaJuego));
    	botonIntercambio = BotonGenerico.generarBotonJuego("Intercambiar Propiedades",LETRA, new BotonIntercambioHandler(juego, this));
    	botonComprarPropiedad =  BotonGenerico.generarBotonJuego("Comprar Propiedad",LETRA, new BotonComprarPropiedadHandler(juego, this));
    	botonPagarFianza =  BotonGenerico.generarBotonJuego("Pagar Fianza",LETRA, new BotonPagarFianzaHandler(juego, this));
        botonSalir = BotonGenerico.generarBotonJuego("Salir", LETRA, new BotonSalirEventHandler() );
        botonEdificar = BotonGenerico.generarBotonJuego("Edificar", LETRA, new BotonEdificarHandler(juego, this));
        botonVender = BotonGenerico.generarBotonJuego("Vender Propiedades",LETRA, new BotonVenderHandler(juego, this));
        
        VBox contenedorVertical = new VBox(botonDados, botonFTurno, botonIntercambio, botonComprarPropiedad, botonEdificar,botonVender,
        		botonPagarFianza, botonSalir);
        
        contenedorVertical.setSpacing(10);
        contenedorVertical.setPadding(new Insets(15));
        this.setLeft(contenedorVertical);
        contenedorVertical.setTranslateX(-translateX);
    }
    
    private void setCentro() {//set usado en el constructor

        Image imagen = new Image("file:src/vista/Recursos/AlgoPolyTablero.jpg");
        BackgroundImage imagenDeFondo = new BackgroundImage(imagen, BackgroundRepeat.NO_REPEAT  ,BackgroundRepeat.NO_REPEAT , BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(imagenDeFondo);
        this.setBackground(background);

        this.contenedorInferiorJugadores = new ContenedorInferiorJugadores(this.stage,this.juego);
        this.getChildren().add(this.contenedorInferiorJugadores);

        this.contenedorUsuariosPropiedades = new ContenedorUsuariosPropiedades(this.stage, this.juego);
        this.setBottom(this.contenedorUsuariosPropiedades);

        Group canvasContainer = new Group();
        this.agregarRepJugador(canvasContainer, imagen);
        this.getChildren().add(canvasContainer);
        this.setTranslateX(translateX);

    }
    
    private void iniciarlizarRepresentacionJugadores(){
        for (BoxJugador view: viewJugadores) {
            view.draw(this.gc);
        }
    }

    public void actualizarBotonera() {
    	
    	  this.botonDados.setDisable(true);
    	  this.botonFTurno.setDisable(true);
    	  this.botonIntercambio.setDisable(true);
    	  this.botonComprarPropiedad.setDisable(true);
    	  this.botonPagarFianza.setDisable(true);
    	  this.botonEdificar.setDisable(true);
    	  this.botonVender.setDisable(true);

    	for(AccionesDelJugador a: tablero.obtenerJugadorActual().manejadorDeAcciones.getAcciones()) {
            System.out.println("Accion Posible: " + GREEN + a + RESET);
            switch(a) {
			case comprar:
				botonComprarPropiedad.setDisable(false);
				break;
			case edificar:
                botonEdificar.setDisable(false);
				break;
			case finalizarTurno:
				botonFTurno.setDisable(false);
				break;
			case intercambiar:
				botonIntercambio.setDisable(false);
				break;
			case pagarFianza:
				botonPagarFianza.setDisable(false);
				break;
			case tirarDados:
				botonDados.setDisable(false);
				break;
			case vender:
				this.botonVender.setDisable(false);
				break;
			default:
				break;
    		
    		}
    	}
    }

    public BoxJugador getBoxJugador(Jugador jugadorABuscar){
        BoxJugador boxJugadorBuscado = null;
        for (BoxJugador boxJugador: this.viewJugadores){
            Jugador jugadorAsociado = boxJugador.getRepjugador().getJugadorAsociado();
            if( jugadorABuscar.nombre().equals( jugadorAsociado.nombre())){
                boxJugadorBuscado = boxJugador;
                break;
            }
        }
        return boxJugadorBuscado;
    }


    private void agregarRepJugador(Group canvasContainer,Image imagen){
        List<Jugador> jugadores = this.juego.getJugadores();

        Canvas canvas = new Canvas(imagen.getWidth(), imagen.getHeight());
        this.gc = canvas.getGraphicsContext2D();
        canvasContainer.getChildren().add(canvas);

        for (int i = 0; i < this.juego.getCantJugadores(); i++) {
            RepresentacionJugador repJugador = new RepresentacionJugador(colores[i], i, jugadores.get(i));
            BoxJugador boxJugador = new BoxJugador(repJugador, this, imagen);
            this.viewJugadores.add(boxJugador);
        }
    }
    //Getters & Setters
    public List<BoxJugador> getViewJugadores(){
        return this.viewJugadores;
    }


    public ContenedorInferiorJugadores getContenedorInferiorJugadores() {
        return this.contenedorInferiorJugadores;
    }
    
    public ContenedorUsuariosPropiedades getContUserPropiedades() {
        return this.contenedorUsuariosPropiedades ;
    }

    
}
