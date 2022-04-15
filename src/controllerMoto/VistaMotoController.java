/*
 * Instituto FOC
 * Grado superior DAM
 * Proyecto creado para la certificación Cimientos de Java, incluído en el módulo de programación.
 */
package controllerMoto;

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
import model.Moto;
import model.Vehiculo;
import enumerations.crnt;

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
    private Button botonAntMoto, botonBuscarMoto, botonSigMoto, botonBajaMoto, botonModMoto, botonAltaMoto, botonLimpiarMoto;
    @FXML
    private TextField textMatricula, textMarca, textModelo, textColor, textCilindrada, textPotencia, textAnioFabricacion, textF_VtoSeguro, textF_ProxITV;
    @FXML
    private RadioButton radioAMoto, radioA1Moto, radioA2Moto;
    @FXML
    private TextArea areaMoto;

    private String cc = "", cv = "", fechaSeguro = "", fechaItv = "", marca = "", modelo = "", color = "", annio = "", id = "", area = "";
    private crnt carnet;

    public void action(ActionEvent e) {

        if (e.getSource().equals(botonAltaMoto)) {
            altaMoto();
        }

        if (e.getSource().equals(botonBajaMoto)) {
            bajaMoto();
        }

        if (e.getSource().equals(botonLimpiarMoto)) {
            limpiarCampos();
        }

        if (e.getSource().equals(botonBuscarMoto)) {
            buscarMoto();
        }

        if (e.getSource().equals(botonModMoto)) {
            modificarMoto();
        }

        if (e.getSource().equals(botonAntMoto)) {
            anteriorMoto();
        }

        if (e.getSource().equals(botonSigMoto)) {
            siguienteMoto();
        }
    }

    /**
     * Conecta con DBHandler para obtener un vehículo anterior de la BD
     */
    private void anteriorMoto() {

        Object objectList[] = new DBHandler().toArray();

        if (objectList.length > 0) { //Hay objetos en la BD
            //
            //Se van a tener en cuenta dos casos:
            //
            //1. - Se ha introducido una matrícula en la GUI.
            ////Se debe mostrar la matrícula anterior, siempre y cuando haya matrículas (de moto) en la BD
            //
            if (validarDatos()) { //Hay matrícula en la GUI

                Moto moto = new Moto(cc, cv, fechaSeguro, fechaItv, carnet, marca, modelo, color, annio, id, area);

                for (int i = 0; i < objectList.length; i++) { //Se recorren los objetos recuperados en la BD

                    if (objectList[i] instanceof Moto) { //El objeto iterado es de tipo Moto
                        if (((Moto) objectList[i]).getId().equals(moto.getId())) { //El objeto iterado coincide (ID) con el recuperado de la GUI
                            if (i > 0 && (objectList[i - 1] instanceof Moto)) { //Hay objetos Moto anteriores
                                mostrarMoto((Moto) objectList[i - 1]);
                            } else if (i == 0) { //Es el único cohe en la BD
                                mostrarMoto((Moto) objectList[i]);
                            }
                        }
                    }
                }
            } else {
                //
                //2. - No se ha introducido matrícula en la GUI.
                ////Si hay matrículas en la BD, se mostrará la primera (de moto)
                //
                for (Object o : objectList) {

                    if (o instanceof Moto) {
                        mostrarMoto((Moto) o);
                        break;
                    }
                }
            }
        } else { //La BD no tiene objetos

            JOptionPane.showMessageDialog(null, "No hay vehículos para mostrar", "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
        }
    }

    /**
     * Conecta con DBHandler para obtener un vehículo siguiente de la BD
     */
    private void siguienteMoto() {

        Object objectList[] = new DBHandler().toArray();

        if (objectList.length > 0) { //Hay objetos en la BD
            //
            //Se van a tener en cuenta dos casos:
            //
            //1. - Se ha introducido una matrícula en la GUI.
            ////Se debe mostrar la matrícula siguiente, siempre y cuando haya matrículas (de moto) en la BD
            //
            if (validarDatos()) { //Hay matrícula en la GUI

                Moto moto = new Moto(cc, cv, fechaSeguro, fechaItv, carnet, marca, modelo, color, annio, id, area);

                for (int i = 0; i < objectList.length; i++) { //Se recorren los objetos recuperados en la BD

                    if (objectList[i] instanceof Moto) { //El objeto iterado es de tipo Moto
                        if (((Moto) objectList[i]).getId().equals(moto.getId())) { //El objeto iterado coincide (ID) con el recuperado de la GUI
                            if (i < (objectList.length - 1) && (objectList[i + 1] instanceof Moto)) { //Hay objetos Moto posteriores
                                mostrarMoto((Moto) objectList[i + 1]);
                            } else if (i == 0) { //Es el único cohe en la BD
                                mostrarMoto((Moto) objectList[i]);
                            }
                        }
                    }
                }
            } else {
                //
                //2. - No se ha introducido matrícula en la GUI.
                ////Si hay matrículas en la BD, se mostrará la última (de moto)
                //
                if (objectList.length == 1 && (objectList[0] instanceof Moto)) {
                    mostrarMoto((Moto) objectList[0]);
                } else {
                    for (int i = (objectList.length - 1); i >= 0; i--) {

                        if (objectList[i] instanceof Moto) {
                            mostrarMoto((Moto) objectList[i]);
                            break;
                        }
                    }
                }
            }
        } else { //La BD no tiene objetos

            JOptionPane.showMessageDialog(null, "No hay vehículos para mostrar", "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
        }
    }

    /**
     * Conecta con DBHandler para modificar un vehículo en la BD
     */
    private void modificarMoto() {

        if (validarDatos()) {

            Moto moto = new Moto(cc, cv, fechaSeguro, fechaItv, carnet, marca, modelo, color, annio, id, area);

            Vehiculo vehiculo = new DBHandler().buscar(new Vehiculo(null, null, null, null, moto.getId(), null));

            if (vehiculo == null) { //El vehiculo a modificar no existe

                String mensaje = "La matrícula: " + moto.getId() + " no existe.";
                JOptionPane.showMessageDialog(null, mensaje, "Atención", JOptionPane.ERROR_MESSAGE);
            } else { //El vehículo a modificar existe

                String mensaje = "¿Deseas modificar la matrícula: " + moto.getId() + "?";
                int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Atención", JOptionPane.YES_NO_OPTION);

                if (opcion == 0) {

                    if (new DBHandler().modificar(moto)) { //Se modifica el Vehículo en la BD
                        JOptionPane.showMessageDialog(null, "Vehículo modificado", "Modificar", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Matrícula no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Conecta con DBHandler para dar de baja un vehículo en la BD
     */
    private void bajaMoto() {

        if (textMatricula.getText().trim().isEmpty()) { // El campo no puede estar vacío

            JOptionPane.showMessageDialog(null, "Necesito una matrícula para borrar.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            String mensaje = "¿Deseas borrar la matrícula: " + textMatricula.getText() + "?";
            int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Atención", JOptionPane.YES_NO_OPTION);

            if (opcion == 0) {

                Vehiculo vehiculo = new Vehiculo(null, null, null, null, textMatricula.getText().toUpperCase(), null); //Vehículo a borrar
                if (new DBHandler().borrar(vehiculo)) {

                    mensaje = "Matrícula: " + vehiculo.getId() + " borrada.";
                    JOptionPane.showMessageDialog(null, mensaje, "Borrar", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    mensaje = "La matrícula: " + vehiculo.getId() + " no existe.";
                    JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            limpiarCampos();
        }
    }

    /**
     * Conecta con DBHandler para buscar un vehículo
     */
    private void buscarMoto() {

        if (textMatricula.getText().trim().isEmpty()) { //El campo no puede estar vacío

            JOptionPane.showMessageDialog(null, "Necesito una matrícula para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            Vehiculo vehiculo = new Vehiculo(null, null, null, null, textMatricula.getText(), null); //Vehículo a buscar
            Vehiculo vCoincidente = new DBHandler().buscar(vehiculo);

            if (vCoincidente == null || !(vCoincidente instanceof Moto)) { //Búsqueda sin éxito

                String mensaje = "No se ha encontrado la matrícula: " + vehiculo.getId();
                JOptionPane.showMessageDialog(null, mensaje, "Buscar", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } else { //La búsqueda ha tenido éxito
                mostrarMoto((Moto) vCoincidente);
            }
        }
    }

    /**
     * Conecta con DBHandler para dar un vehículo de alta
     */
    private void altaMoto() {

        if (validarDatos()) {

            Moto moto = new Moto(cc, cv, fechaSeguro, fechaItv, carnet, marca, modelo, color, annio, id, area);

            if (new DBHandler().buscar(moto) != null) { //El vehículo ya existe en la BD

                String mensaje = "La matrícula: " + moto.getId() + " ya existe en la base de datos.";
                JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            } else { //Se puede dar de alta

                if (new DBHandler().alta(moto)) {

                    JOptionPane.showMessageDialog(null, "Vehículo dado de alta", "Alta", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "El vehículo no pudo darse de alta", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Matrícula no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Vuelca el valor de los atributos de un objeto Moto a los campos de la GUI
     *
     * @param moto Objeto Moto a mostrar
     */
    private void mostrarMoto(Moto moto) {

        textCilindrada.setText(moto.getCc());
        textPotencia.setText(moto.getCv());
        textF_VtoSeguro.setText(moto.getFechaSeguro());
        textF_ProxITV.setText(moto.getFechaItv());
        textMarca.setText(moto.getMarca());
        textModelo.setText(moto.getModelo());
        textColor.setText(moto.getColor());
        textAnioFabricacion.setText(moto.getAnnio());
        textMatricula.setText(moto.getId());
        areaMoto.setText(moto.getTxtArea());

        if (moto.getCarnet().equals(crnt.A)) {
            radioAMoto.setSelected(true);
        } else if (moto.getCarnet().equals(crnt.A1)) {
            radioA1Moto.setSelected(true);
        } else if (moto.getCarnet().equals(crnt.A2)) {
            radioA2Moto.setSelected(true);
        }
    }

    /**
     * Limpia los campos mostrados en la GUI
     */
    private void limpiarCampos() {

        textCilindrada.setText("");
        textPotencia.setText("");
        textF_VtoSeguro.setText("");
        textF_ProxITV.setText("");
        textMarca.setText("");
        textModelo.setText("");
        textColor.setText("");
        textAnioFabricacion.setText("");
        textMatricula.setText("");
        areaMoto.setText("");

        radioAMoto.setSelected(true);
    }

    /**
     * Recupera datos del formulario solo si se ha rellenado el txtField Matricula
     *
     * @return true si se ha llevado a cabo la recuperación de datos del formulario
     */
    private boolean validarDatos() {

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
            id = textMatricula.getText().toUpperCase();
            area = areaMoto.getText();

            if (radioAMoto.isSelected()) {
                carnet = crnt.A;
            } else if (radioA1Moto.isSelected()) {
                carnet = crnt.A1;
            } else if (radioA2Moto.isSelected()) {
                carnet = crnt.A2;
            }

            return true;
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Al cargar la GUI, se mostrará el primer vehículo en la base de datos (Moto).
        Object object[] = new DBHandler().toArray();

        for (Object o : object) {

            if (o instanceof Moto) {
                mostrarMoto((Moto) o);
                break;
            }
        }
    }
}
