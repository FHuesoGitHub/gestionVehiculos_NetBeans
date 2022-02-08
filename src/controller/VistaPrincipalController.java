/*
 * Instituo FOC
 * Grado superior DAM
 * Proyecto creado para la certificvación Cimientos de Java, incluído en el módulo de programación.
 */
package controller;

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
 * @author RECAMBIOS
 */
public class VistaPrincipalController implements Initializable {

    @FXML
    private ImageView imageViewBotonCoche;

    @FXML
    private Button botonCoche;

    @FXML
    public void mousePressed(MouseEvent e) {

        if (e.getSource().equals(botonCoche)) {

            imageViewBotonCoche.setFitWidth(imageViewBotonCoche.getFitWidth() - 1);
            imageViewBotonCoche.setFitHeight(imageViewBotonCoche.getFitHeight() - 1);
        }
    }

    @FXML
    public void mouseReleased(MouseEvent e) {

        if (e.getSource().equals(botonCoche)) {
            imageViewBotonCoche.setFitWidth(imageViewBotonCoche.getFitWidth() + 1);
            imageViewBotonCoche.setFitHeight(imageViewBotonCoche.getFitHeight() + 1);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
