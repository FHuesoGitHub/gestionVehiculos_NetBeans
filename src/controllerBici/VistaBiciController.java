/*
 * Instituto FOC
 * Grado superior DAM
 * Proyecto creado para la certificación Cimientos de Java, incluído en el módulo de programación.
 */
package controllerBici;

import dB.DBHandler;
import enumerations.tmn;
import enumerations.tp;
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
import model.Bici;
import model.Vehiculo;

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
    private Button botonAntBici, botonBuscarBici, botonSigBici, botonBajaBici, botonModBici, botonAltaBici, botonLimpiarBici;
    @FXML
    private TextField textMarca, textModelo, textColor, textAnioCompra, textId;
    @FXML
    private TextArea areaBici;
    @FXML
    private RadioButton radioAdultoBici, radioCadeteBici, radioNinioBici, radioMontaniaBici, radioCarrtereaBici, radioPaseoBici;

    private String marca = "", modelo = "", color = "", annio = "", id = "", area = "";
    private tmn tamanio;
    private tp tipo;

    public void action(ActionEvent e) {

        if (e.getSource().equals(botonAltaBici)) {
            altaBici();
        }

        if (e.getSource().equals(botonBajaBici)) {
            bajaBici();
        }

        if (e.getSource().equals(botonLimpiarBici)) {
            limpiarCampos();
        }

        if (e.getSource().equals(botonBuscarBici)) {
            buscarBici();
        }

        if (e.getSource().equals(botonModBici)) {
            modificarBici();
        }

        if (e.getSource().equals(botonAntBici)) {
            anteriorBici();
        }

        if (e.getSource().equals(botonSigBici)) {
            siguienteBici();
        }
    }

    /**
     * Conecta con DBHandler para obtener un vehículo anterior de la BD
     */
    private void anteriorBici() {

        Object objectList[] = new DBHandler().toArray();

        if (objectList.length > 0) { //Hay objetos en la BD
            //
            //Se van a tener en cuenta dos casos:
            //
            //1. - Se ha introducido un ID en la GUI.
            ////Se debe mostrar el ID anterior, siempre y cuando haya IDs (de bici) en la BD
            //
            if (validarDatos()) { //Hay ID en la GUI

                Bici bici = new Bici(tamanio, tipo, marca, modelo, color, annio, id, area);

                for (int i = 0; i < objectList.length; i++) { //Se recorren los objetos recuperados en la BD

                    if (objectList[i] instanceof Bici) { //El objeto iterado es de tipo Bici
                        if (((Bici) objectList[i]).getId().equals(bici.getId())) { //El objeto iterado coincide (ID) con el recuperado de la GUI
                            if (i > 0 && (objectList[i - 1] instanceof Bici)) { //Hay objetos Bici anteriores
                                mostrarBici((Bici) objectList[i - 1]);
                            } else if (i == 0) { //Es el único cohe en la BD
                                mostrarBici((Bici) objectList[i]);
                            }
                        }
                    }
                }
            } else {
                //
                //2. - No se ha introducido ID en la GUI.
                ////Si hay IDs en la BD, se mostrará el primero (de bici)
                //
                for (Object o : objectList) {

                    if (o instanceof Bici) {
                        mostrarBici((Bici) o);
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
    private void siguienteBici() {

        Object objectList[] = new DBHandler().toArray();

        if (objectList.length > 0) { //Hay objetos en la BD
            //
            //Se van a tener en cuenta dos casos:
            //
            //1. - Se ha introducido un ID en la GUI.
            ////Se debe mostrar el ID siguiente, siempre y cuando haya IDs (de bici) en la BD
            //
            if (validarDatos()) { //Hay ID en la GUI

                Bici bici = new Bici(tamanio, tipo, marca, modelo, color, annio, id, area);

                for (int i = 0; i < objectList.length; i++) { //Se recorren los objetos recuperados en la BD

                    if (objectList[i] instanceof Bici) { //El objeto iterado es de tipo Bici
                        if (((Bici) objectList[i]).getId().equals(bici.getId())) { //El objeto iterado coincide (ID) con el recuperado de la GUI
                            if (i < (objectList.length - 1) && (objectList[i + 1] instanceof Bici)) { //Hay objetos Bici posteriores
                                mostrarBici((Bici) objectList[i + 1]);
                            } else if (i == 0) { //Es el único cohe en la BD
                                mostrarBici((Bici) objectList[i]);
                            }
                        }
                    }
                }
            } else {
                //
                //2. - No se ha introducido ID en la GUI.
                ////Si hay IDs en la BD, se mostrará la última (de bici)
                //
                if (objectList.length == 1 && (objectList[0] instanceof Bici)) {
                    mostrarBici((Bici) objectList[0]);
                } else {
                    for (int i = (objectList.length - 1); i >= 0; i--) {

                        if (objectList[i] instanceof Bici) {
                            mostrarBici((Bici) objectList[i]);
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
    private void modificarBici() {

        if (validarDatos()) {

            Bici bici = new Bici(tamanio, tipo, marca, modelo, color, annio, id, area);

            Vehiculo vehiculo = new DBHandler().buscar(new Vehiculo(null, null, null, null, bici.getId(), null));

            if (vehiculo == null) { //El vehiculo a modificar no existe

                String mensaje = "El ID: " + bici.getId() + " no existe.";
                JOptionPane.showMessageDialog(null, mensaje, "Atención", JOptionPane.ERROR_MESSAGE);
            } else { //El vehículo a modificar existe

                String mensaje = "¿Deseas modificar el ID: " + bici.getId() + "?";
                int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Atención", JOptionPane.YES_NO_OPTION);

                if (opcion == 0) {

                    if (new DBHandler().modificar(bici)) { //Se modifica el Vehículo en la BD
                        JOptionPane.showMessageDialog(null, "Vehículo modificado", "Modificar", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "ID no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Conecta con DBHandler para dar de baja un vehículo en la BD
     */
    private void bajaBici() {

        if (textId.getText().trim().isEmpty()) { // El campo no puede estar vacío

            JOptionPane.showMessageDialog(null, "Necesito un ID para borrar.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            String mensaje = "¿Deseas borrar el ID: " + textId.getText() + "?";
            int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Atención", JOptionPane.YES_NO_OPTION);

            if (opcion == 0) {

                Vehiculo vehiculo = new Vehiculo(null, null, null, null, textId.getText().toUpperCase(), null); //Vehículo a borrar
                if (new DBHandler().borrar(vehiculo)) {

                    mensaje = "ID: " + vehiculo.getId() + " borrado.";
                    JOptionPane.showMessageDialog(null, mensaje, "Borrar", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    mensaje = "LEL ID: " + vehiculo.getId() + " no existe.";
                    JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            limpiarCampos();
        }
    }

    /**
     * Conecta con DBHandler para buscar un vehículo
     */
    private void buscarBici() {

        if (textId.getText().trim().isEmpty()) { //El campo no puede estar vacío

            JOptionPane.showMessageDialog(null, "Necesito un ID para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            Vehiculo vehiculo = new Vehiculo(null, null, null, null, textId.getText(), null); //Vehículo a buscar
            Vehiculo vCoincidente = new DBHandler().buscar(vehiculo);

            if (vCoincidente == null || !(vCoincidente instanceof Bici)) { //Búsqueda sin éxito

                String mensaje = "No se ha encontrado el ID: " + vehiculo.getId();
                JOptionPane.showMessageDialog(null, mensaje, "Buscar", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } else { //La búsqueda ha tenido éxito
                mostrarBici((Bici) vCoincidente);
            }
        }
    }

    /**
     * Conecta con DBHandler para dar un vehículo de alta
     */
    private void altaBici() {

        if (validarDatos()) {

            Bici bici = new Bici(tamanio, tipo, marca, modelo, color, annio, id, area);

            if (new DBHandler().buscar(bici) != null) { //El vehículo ya existe en la BD

                String mensaje = "El ID: " + bici.getId() + " ya existe en la base de datos.";
                JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            } else { //Se puede dar de alta

                if (new DBHandler().alta(bici)) {

                    JOptionPane.showMessageDialog(null, "Vehículo dado de alta", "Alta", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "El vehículo no pudo darse de alta", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "ID no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Vuelca el valor de los atributos de un objeto Bici a los campos de la GUI
     *
     * @param bici Objeto Bici a mostrar
     */
    private void mostrarBici(Bici bici) {

        textMarca.setText(bici.getMarca());
        textModelo.setText(bici.getModelo());
        textColor.setText(bici.getColor());
        textId.setText(bici.getId());
        areaBici.setText(bici.getTxtArea());
        textAnioCompra.setText(bici.getAnnio());

        if (bici.getTamaño().equals(tmn.ADULTO)) {
            radioAdultoBici.setSelected(true);
        } else if (bici.getTamaño().equals(tmn.CADETE)) {
            radioCadeteBici.setSelected(true);
        } else if (bici.getTamaño().equals(tmn.NINIO)) {
            radioNinioBici.setSelected(true);
        }

        if (bici.getTipo().equals(tp.CARRETERA)) {
            radioCarrtereaBici.setSelected(true);
        } else if (bici.getTipo().equals(tp.MONTANIA)) {
            radioMontaniaBici.setSelected(true);
        } else if (bici.getTipo().equals(tp.PASEO)) {
            radioPaseoBici.setSelected(true);
        }
    }

    /**
     * Limpia los campos mostrados en la GUI
     */
    private void limpiarCampos() {

        textMarca.setText("");
        textModelo.setText("");
        textColor.setText("");
        textAnioCompra.setText("");
        textId.setText("");
        areaBici.setText("");

        radioAdultoBici.setSelected(true);
        radioMontaniaBici.setSelected(true);
    }

    /**
     * Recupera datos del formulario solo si se ha rellenado el txtField Matricula
     *
     * @return true si se ha llevado a cabo la recuperación de datos del formulario
     */
    private boolean validarDatos() {

        if (textId.getText().trim().isEmpty()) {
            return false;
        } else {

            marca = textMarca.getText();
            modelo = textModelo.getText();
            color = textColor.getText();
            annio = textAnioCompra.getText();
            id = textId.getText().toUpperCase();
            area = areaBici.getText();

            if (radioAdultoBici.isSelected()) {
                tamanio = tmn.ADULTO;
            } else if (radioCadeteBici.isSelected()) {
                tamanio = tmn.CADETE;
            } else if (radioNinioBici.isSelected()) {
                tamanio = tmn.NINIO;
            }

            if (radioCarrtereaBici.isSelected()) {
                tipo = tp.CARRETERA;
            } else if (radioMontaniaBici.isSelected()) {
                tipo = tp.MONTANIA;
            } else if (radioPaseoBici.isSelected()) {
                tipo = tp.PASEO;
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
        //Al cargar la GUI, se mostrará el primer vehículo en la base de datos (Bici).
        Object object[] = new DBHandler().toArray();

        for (Object o : object) {

            if (o instanceof Bici) {
                mostrarBici((Bici) o);
                break;
            }
        }
    }
}
