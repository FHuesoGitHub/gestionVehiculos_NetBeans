/*
 * Instituto FOC
 * Grado superior DAM
 * Proyecto creado para la certificación Cimientos de Java, incluído en el módulo de programación.
 */
package viewMain;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Francisco Hueso
 * @version 02/2022
 */
public class Gestion_vehiculos extends Application {

    /**
     * Crea un escenario (ventana).
     *
     * @param stage Escenario o ventana.
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {

        //Cargador para la vista principal.
        Parent root = FXMLLoader.load(getClass().getResource("/viewMain/VistaPrincipal.fxml"));        
        Scene scene = new Scene(root);

        stage.setTitle("Gestion de vehículos");
        stage.setScene(scene);
        stage.show();

        //La ventana no se podrá redimensionar.
        stage.setResizable(false);
    }

    /**
     * Lanza la aplicación pasando el control al método start
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
