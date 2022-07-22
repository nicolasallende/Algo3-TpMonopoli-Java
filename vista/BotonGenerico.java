package vista;


import controlador.BotonGenericoEventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import vista.Handlers.BotonDadosrHandler;

public class BotonGenerico extends Button {
    private static double ANCHO= 220.0, ALTO= 60.0;
    private Stage stage;

    private BotonGenerico(){
        super();
    }

    public static Button generarbotonInicio(String texto, String tipoLetra, BotonGenericoEventHandler botonEventHandler){
        BotonGenerico botonGenerico = new BotonGenerico();
        botonGenerico.setText(texto);
        botonGenerico.setFont(Font.font(tipoLetra, FontWeight.BOLD, 20));
        botonGenerico.setOnAction(botonEventHandler);
        botonGenerico.setPrefSize(ANCHO, ALTO);
        return botonGenerico;
    }


    public static Button generarBotonJuego(String texto, String tipoLetra, BotonGenericoEventHandler botonEventHandler){
        Button boton = new Button();
        boton.setText(texto);
        boton.setFont(Font.font(tipoLetra));
        boton.setOnAction(botonEventHandler);
        return boton;
    }

}
