/*
 * Instituto FOC
 * Grado superior DAM
 * Proyecto creado para la certificación Cimientos de Java, incluído en el módulo de programación.
 */
package controllerMoto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * Esta clase maneja los objetos de la vista moto y sus eventos.
 *
 * @author Francisco Hueso
 * @version 02/2022
 */
public class VistaMotoController implements Initializable {

    @FXML
    private Button botonAntMoto, botonBuscarMoto, botonSigMoto, botonBajaMoto, botonModMoto, botonAltaMoto;
    
    public void action(ActionEvent e){
        
        /*
        if(e.getSource().equals(xxx)){
            
        }*/
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
