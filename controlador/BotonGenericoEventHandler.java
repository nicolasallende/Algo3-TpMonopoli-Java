package controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public abstract class BotonGenericoEventHandler implements EventHandler<ActionEvent> {

     public  abstract void handle(ActionEvent event);
}
