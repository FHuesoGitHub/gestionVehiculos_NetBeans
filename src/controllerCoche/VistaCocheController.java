/*
 * Instituto FOC
 * Grado superior DAM
 * Proyecto creado para la certificación Cimientos de Java, incluído en el módulo de programación.
 */
package controllerCoche;

import dB.DBHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import model.Coche;
import model.cmb;

/**
 * FXML Controller class
 *
 * Esta clase maneja los objetos de la vista coche y sus eventos.
 *
 * @author Francisco Hueso
 * @version 02/2022
 */
public class VistaCocheController implements Initializable {

    @FXML
    private Button botonAntCoche, botonBuscarCoche, botonSigCoche, botonBajaCoche, botonModCoche, botonAltaCoche;
    @FXML
    private TextField textMatricula, textMarca, textModelo, textColor, textCilindrada, textPotencia, textAnioFabricacion, textF_VtoSeguro, textF_ProxITV;
    @FXML
    private RadioButton radioDieselCoche, radioGasolinaCoche;
    @FXML
    private TextArea areaCoche;

    private String cc, cv, fechaSeguro, fechaItv, marca, modelo, color, annio, id, area;
    cmb combustible;

    public void action(ActionEvent e) {

        if (e.getSource().equals(botonAltaCoche)) {

            altaCoche();
        }
    }

    /**
     * Conecta con DBHandler para dar un vehículo de alta
     */
    public void altaCoche() {

        /*
        ///////////////////////////////////////////////////////////////
        FALTA COMPROBAR SI LA MaTRÍCULA YA EXISTE
        //////////////////////////////////////////////////////////////
        */
        
        if (validarDatos()) {

            Coche coche = new Coche(cc, cv, fechaSeguro, fechaItv, combustible, marca, modelo, color, annio, id, area);

            if (new DBHandler().alta(coche)) {
                JOptionPane.showMessageDialog(null, "Vehículo dado de alta", "Alta", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "El vehículo no pudo darse de alta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Matrícula no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Recupera datos del formulario solo si se ha rellenado el txtField Matricula
     *
     * @return true si se ha llevado a cabo la recuperación de datos del formulario
     */
    public boolean validarDatos() {

        if (textMatricula.getText().trim().isEmpty()) {
            return false;
        } else {

            cc = textCilindrada.getText();
            cv = textPotencia.getText();
            fechaSeguro = textF_VtoSeguro.getText();
            fechaItv = textF_ProxITV.getText();
            marca = textMarca.getText();
            modelo = textModelo.getText();
            color = textColor.getText();
            annio = textAnioFabricacion.getText();
            id = textMatricula.getText();
            area = areaCoche.getText();

            if (radioDieselCoche.isSelected()) {
                combustible = cmb.DIESEL;
            } else if (radioGasolinaCoche.isSelected()) {
                combustible = cmb.GASOLINA;
            }

            return true;
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
