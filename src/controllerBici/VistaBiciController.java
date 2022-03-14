/*
 * Instituto FOC
 * Grado superior DAM
 * Proyecto creado para la certificación Cimientos de Java, incluído en el módulo de programación.
 */
package controllerBici;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * Esta clase maneja los objetos de la vista bici y sus eventos.
 *
 * @author Francisco Hueso
 * @version 02/2022
 */
public class VistaBiciController implements Initializable {

    @FXML
    private Button botonAntBici, botonBuscarBici, botonSigBici, botonBajaBici, botonRutasBici, botonAltaBici;

    public void action(ActionEvent e) {

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
