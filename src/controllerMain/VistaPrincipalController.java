/*
 * Instituto FOC
 * Grado superior DAM
 * Proyecto creado para la certificvación Cimientos de Java, incluído en el módulo de programación.
 */
package controllerMain;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * Esta clase maneja los objetos de la vista principal y sus eventos.
 *
 * @author RECAMBIOS
 */
public class VistaPrincipalController implements Initializable {

    @FXML
    private ImageView imageViewBotonCoche, imageViewBotonMoto, imageViewBotonBici;

    @FXML
    private Button botonCoche, botonMoto, botonBici;

    /**
     * Evento de presionar un botón.
     *
     * @param e Información del objeto quje desencadenó el evento.
     */
    @FXML
    public void mousePressed(MouseEvent e) {

        { // Animación de botones. Modfica levemente las dimensiones para dar sensación de movimiento.
            if (e.getSource().equals(botonCoche)) {

                imageViewBotonCoche.setFitWidth(imageViewBotonCoche.getFitWidth() - 1);
                imageViewBotonCoche.setFitHeight(imageViewBotonCoche.getFitHeight() - 1);
            }

            if (e.getSource().equals(botonMoto)) {

                imageViewBotonMoto.setFitWidth(imageViewBotonMoto.getFitWidth() - 1);
                imageViewBotonMoto.setFitHeight(imageViewBotonMoto.getFitHeight() - 1);
            }

            if (e.getSource().equals(botonBici)) {

                imageViewBotonBici.setFitWidth(imageViewBotonBici.getFitWidth() - 1);
                imageViewBotonBici.setFitHeight(imageViewBotonBici.getFitHeight() - 1);
            }
        }
    }

    /**
     * Evento de soltar un botón.
     *
     * @param e Información del objeto quje desencadenó el evento.
     */
    @FXML
    public void mouseReleased(MouseEvent e) {

        { // Animación de botones. Modfica levemente las dimensiones para dar sensación de movimiento.
            if (e.getSource().equals(botonCoche)) {
                imageViewBotonCoche.setFitWidth(imageViewBotonCoche.getFitWidth() + 1);
                imageViewBotonCoche.setFitHeight(imageViewBotonCoche.getFitHeight() + 1);
            }

            if (e.getSource().equals(botonMoto)) {

                imageViewBotonMoto.setFitWidth(imageViewBotonMoto.getFitWidth() + 1);
                imageViewBotonMoto.setFitHeight(imageViewBotonMoto.getFitHeight() + 1);
            }

            if (e.getSource().equals(botonBici)) {

                imageViewBotonBici.setFitWidth(imageViewBotonBici.getFitWidth() + 1);
                imageViewBotonBici.setFitHeight(imageViewBotonBici.getFitHeight() + 1);
            }
        }
    }

    /**
     * Método que se ejecuta el primero al inicializar la vista.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
