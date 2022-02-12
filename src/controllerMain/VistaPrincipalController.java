/*
 * Instituto FOC
 * Grado superior DAM
 * Proyecto creado para la certificación Cimientos de Java, incluído en el módulo de programación.
 */
package controllerMain;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

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

    @FXML
    private StackPane stackPaneVistaPrincipal;

    private StackPane StackPaneCoche, StackPaneMoto;

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
     * Evento de click de ratón
     *
     * @param e Información del objeto quje desencadenó el evento.
     */
    @FXML
    public void action(ActionEvent e) {

        /*
        Dependiendo del botón pulsado, se muestra la vista correspondiente
        en el stack pane de la vista principal. Estas vistas fueron cargadas previamente,
        en el método initialize.
         */
        if (e.getSource().equals(botonCoche)) {

            StackPaneMoto.setVisible(false);
            //StackPaneBici.setVisible(false);
            StackPaneCoche.setVisible(true);
        }

        if (e.getSource().equals(botonMoto)) {

            StackPaneMoto.setVisible(true);
            //StackPaneBici.setVisible(false);
            StackPaneCoche.setVisible(false);
        }

        if (e.getSource().equals(botonBici)) {

            StackPaneMoto.setVisible(false);
            //StackPaneBici.setVisible(true);
            StackPaneCoche.setVisible(false);
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

        try {

            //Cargador para la vista moto.
            StackPaneCoche = cargarVista("/viewCoche/VistaCoche.fxml");
            //Cargador para la vista moto.
            StackPaneMoto = cargarVista("/viewMoto/VistaMoto.fxml");
            //Añado vistas al stack pane de la vista principal.
            stackPaneVistaPrincipal.getChildren().addAll(StackPaneCoche, StackPaneMoto);
            //Visualizo u oculto cada vista según sea conveniente.
            StackPaneCoche.setVisible(true);
            StackPaneMoto.setVisible(false);
        } catch (IOException ex) {

            Logger.getLogger(VistaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private StackPane cargarVista(String url) throws IOException {

        return (StackPane) FXMLLoader.load(getClass().getResource(url));
    }
}
