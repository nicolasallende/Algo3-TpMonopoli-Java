package vista;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import modelo.Jugador;

public class FooterUsuarioPropiedades extends VBox {

    private final Jugador jugador;

    private Label getStatusPropiedades(){
        Label status = new Label();
        status.setText("  " + this.jugador.nombre() + " Propiedades: " + this.jugador.toStringPropiedades());
        status.setFont(Font.font("courier new", FontWeight.SEMI_BOLD, 14));
        status.setTextFill(Color.BLACK);
        return status;



    }

    public FooterUsuarioPropiedades(Stage stage, Jugador jugador){
        this.jugador = jugador;
        this.setMinWidth(2000);
        this.setTranslateX(-225);
        this.setSpacing(10);
        this.setPadding(new Insets(5));
        this.setStyle("-fx-background-color: royalblue; -fx-padding: 10px;");
        this.getChildren().add(this.getStatusPropiedades());
    }

    public void update(){
        this.getChildren().set(0, this.getStatusPropiedades());
    }

}
