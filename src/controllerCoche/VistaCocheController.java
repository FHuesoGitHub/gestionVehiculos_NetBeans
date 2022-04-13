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
import enumerations.cmb;
import model.Moto;
import model.Vehiculo;

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
    private Button botonAntCoche, botonBuscarCoche, botonSigCoche, botonBajaCoche, botonModCoche, botonAltaCoche, botonLimpiarCoche;
    @FXML
    private TextField textMatricula, textMarca, textModelo, textColor, textCilindrada, textPotencia, textAnioFabricacion, textF_VtoSeguro, textF_ProxITV;
    @FXML
    private RadioButton radioDieselCoche, radioGasolinaCoche;
    @FXML
    private TextArea areaCoche;

    private String cc = "", cv = "", fechaSeguro = "", fechaItv = "", marca = "", modelo = "", color = "", annio = "", id = "", area = "";
    cmb combustible;

    public void action(ActionEvent e) {

        if (e.getSource().equals(botonAltaCoche)) {

            altaCoche();
        }

        if (e.getSource().equals(botonBajaCoche)) {

            bajaCoche();
        }

        if (e.getSource().equals(botonLimpiarCoche)) {
            limpiarCampos();
        }

        if (e.getSource().equals(botonBuscarCoche)) {
            buscarCoche();
        }

        if (e.getSource().equals(botonModCoche)) {
            modificarCoche();
        }

        if (e.getSource().equals(botonAntCoche)) {
            anteriorCoche();
        }

        if (e.getSource().equals(botonSigCoche)) {
            siguienteCoche();
        }
    }

    /**
     * Conecta con DBHandler para obtener un vehículo anterior de la BD
     */
    private void anteriorCoche() {

        Object objectList[] = new DBHandler().toArray();

        if (objectList.length > 0) { //Hay objetos en la BD
            //
            //Se van a tener en cuenta dos casos:
            //
            //1. - Se ha introducido una matrícula en la GUI.
            ////Se debe mostrar la matrícula anterior, siempre y cuando haya matrículas (de coche) en la BD
            //
            if (validarDatos()) { //Hay matrícula en la GUI

                Coche coche = new Coche(cc, cv, fechaSeguro, fechaItv, combustible, marca, modelo, color, annio, id, area);

                for (int i = 0; i < objectList.length; i++) { //Se recorren los objetos recuperados en la BD

                    if (objectList[i] instanceof Coche) { //El objeto iterado es de tipo Coche
                        if (((Coche) objectList[i]).getId().equals(coche.getId())) { //El objeto iterado coincide (ID) con el recuperado de la GUI
                            if (i > 0) { //Hay objetos Coche anteriores
                                mostrarCoche((Coche) objectList[i - 1]);
                            } else if (i == 0) { //Es el único cohe en la BD
                                mostrarCoche((Coche) objectList[i]);
                            }
                        }
                    }
                }
            } else {
                //
                //2. - No se ha introducido matrícula en la GUI.
                ////Si hay matrículas en la BD, se mostrará la primera (de coche)
                //
                for (Object o : objectList) {

                    if (o instanceof Coche) {
                        mostrarCoche((Coche) o);
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
    private void siguienteCoche() {
        Object objectList[] = new DBHandler().toArray();

        if (objectList.length > 0) { //Hay objetos en la BD
            //
            //Se van a tener en cuenta dos casos:
            //
            //1. - Se ha introducido una matrícula en la GUI.
            ////Se debe mostrar la matrícula siguiente, siempre y cuando haya matrículas (de coche) en la BD
            //
            if (validarDatos()) { //Hay matrícula en la GUI

                Coche coche = new Coche(cc, cv, fechaSeguro, fechaItv, combustible, marca, modelo, color, annio, id, area);

                for (int i = 0; i < objectList.length; i++) { //Se recorren los objetos recuperados en la BD

                    if (objectList[i] instanceof Coche) { //El objeto iterado es de tipo Coche
                        if (((Coche) objectList[i]).getId().equals(coche.getId())) { //El objeto iterado coincide (ID) con el recuperado de la GUI
                            if (i < (objectList.length - 1)) { //Hay objetos Coche posteriores
                                mostrarCoche((Coche) objectList[i + 1]);
                            } else if (i == 0) { //Es el único cohe en la BD
                                mostrarCoche((Coche) objectList[i]);
                            }
                        }
                    }
                }
            } else {
                //
                //2. - No se ha introducido matrícula en la GUI.
                ////Si hay matrículas en la BD, se mostrará la última (de coche)
                //
                if (objectList.length == 1) {
                    mostrarCoche((Coche) objectList[0]);
                } else {
                    for (int i = (objectList.length - 1); i > 0; i--) {

                        if (objectList[i] instanceof Coche) {
                            mostrarCoche((Coche) objectList[i]);
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
    private void modificarCoche() {

        if (validarDatos()) {

            Coche coche = new Coche(cc, cv, fechaSeguro, fechaItv, combustible, marca, modelo, color, annio, id, area);

            Vehiculo vehiculo = new DBHandler().buscar(new Vehiculo(null, null, null, null, coche.getId(), null));

            if (vehiculo == null) { //El vehiculo a modificar no existe

                String mensaje = "La matrícula: " + coche.getId() + " no existe.";
                JOptionPane.showMessageDialog(null, mensaje, "Atención", JOptionPane.ERROR_MESSAGE);
            } else { //El vehículo a modificar existe

                String mensaje = "¿Deseas modificar la matrícula: " + coche.getId() + "?";
                int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Atención", JOptionPane.YES_NO_OPTION);

                if (opcion == 0) {

                    if (new DBHandler().modificar(coche)) { //Se modifica el Vehículo en la BD
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
    private void bajaCoche() {

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
    private void buscarCoche() {

        if (textMatricula.getText().trim().isEmpty()) { //El campo no puede estar vacío

            JOptionPane.showMessageDialog(null, "Necesito una matrícula para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            Vehiculo vehiculo = new Vehiculo(null, null, null, null, textMatricula.getText(), null); //Vehículo a buscar
            Vehiculo vCoincidente = new DBHandler().buscar(vehiculo);

            if (vCoincidente == null) { //Búsqueda sin éxito

                String mensaje = "No se ha encontrado la matrícula: " + vehiculo.getId();
                JOptionPane.showMessageDialog(null, mensaje, "Buscar", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } else { //La búsqueda ha tenido éxito
                mostrarCoche((Coche) vCoincidente);
            }
        }
    }

    /**
     * Conecta con DBHandler para dar un vehículo de alta
     */
    private void altaCoche() {

        if (validarDatos()) {

            Coche coche = new Coche(cc, cv, fechaSeguro, fechaItv, combustible, marca, modelo, color, annio, id, area);

            if (new DBHandler().buscar(coche) != null) { //El vehículo ya existe en la BD

                String mensaje = "La matrícula: " + coche.getId() + " ya existe en la base de datos.";
                JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            } else { //Se puede dar de alta

                if (new DBHandler().alta(coche)) {

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
     * Vuelca el valor de los atributos de un objeto Coche a los campos de la
     * GUI
     *
     * @param coche Objeto Coche a mostrar
     */
    private void mostrarCoche(Coche coche) {

        textCilindrada.setText(coche.getCc());
        textPotencia.setText(coche.getCv());
        textF_VtoSeguro.setText(coche.getFechaSeguro());
        textF_ProxITV.setText(coche.getFechaItv());
        textMarca.setText(coche.getMarca());
        textModelo.setText(coche.getModelo());
        textColor.setText(coche.getColor());
        textAnioFabricacion.setText(coche.getAnnio());
        textMatricula.setText(coche.getId());
        areaCoche.setText(coche.getTxtArea());

        if (coche.getCombustible().equals(cmb.DIESEL)) {
            radioDieselCoche.setSelected(true);
        } else if (coche.getCombustible().equals(cmb.GASOLINA)) {
            radioGasolinaCoche.setSelected(true);
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
        areaCoche.setText("");

        radioDieselCoche.setSelected(true);
    }

    /**
     * Recupera datos del formulario solo si se ha rellenado el txtField
     * Matricula
     *
     * @return true si se ha llevado a cabo la recuperación de datos del
     * formulario
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

        //
        //Introduce matrículas de prueba en la BD
        /*for (int i = 0; i < 100; i++) {

            Coche c = new Coche(String.valueOf(i), String.valueOf(i), String.valueOf(i),
                    String.valueOf(i), cmb.DIESEL, String.valueOf(i), String.valueOf(i),
                    String.valueOf(i), String.valueOf(i), String.valueOf(i), String.valueOf(i));

            new DBHandler().alta(c);
        }*/
        //
        //
        //Al cargar la GUI, se mostrará el primer vehículo en la base de datos (Coche).
        Object object[] = new DBHandler().toArray();

        for (Object o : object) {

            if (o instanceof Coche) {
                mostrarCoche((Coche) o);
                break;
            }
        }
    }

}
