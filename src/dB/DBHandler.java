/*
 * Instituo FOC
 * Grado superior DAM
 * Proyecto creado para el módulo de programación.
 */
package dB;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.Db4oException;
import com.db4o.query.Query;
import java.io.File;
import javax.swing.JOptionPane;
import model.Coche;
import model.Vehiculo;

/**
 * Maneja las operaciones con la base de datos orientada a objetos (DB4o)
 *
 * @author FHueso
 */
public class DBHandler {

    //La variable de tipo ObjectContainer (interfaz) representa la base de datos
    private ObjectContainer db;

    public DBHandler() {

        crearConexion();
    }

    /**
     * Inserta todos los objetos de la BD en un array
     *
     * @return array tipo Object con todos los objetos de la BD
     */
    public Object[] toArray() {

        Query consulta = db.query(); //Se crea consulta
        consulta.constrain(Vehiculo.class); //Los objetos a consultar serán de tipo Vehiculo.
        consulta.descend("id").orderAscending(); //Objetos ordenados por ID (ascendente).

        ObjectSet<Vehiculo> resultado; //ObjectSet donde se guardarán los resultados de la consulta.

        try {

            //La ejecución de la consulta dará error si estando vacía se ha intentado ordenar con orderAscending
            resultado = consulta.execute();

            Object objetosBD[] = resultado.toArray(); //Se pasa el resultado de la consulta a un array de tipo Object

            cerrarConexion();
            return objetosBD;
        } catch (Exception e) {

            cerrarConexion();
            return new Object[0];
        }
    }

    /**
     * Modifica un objeto Vehiculo en la BD
     *
     * @param vehiculo Objeto Vehiculo a modificar en la BD
     * @return true en el caso de que se haya modificado el objeto Vehiculo con
     * éxito
     */
    public boolean modificar(Vehiculo vehiculo) {

        //Este método parte de la base de que en la base de datos no puede haber
        //varios objetos Vehiculo con el mismo ID.
        //
        ObjectSet<Vehiculo> resultado; //ObjectSet donde se guardarán los resultados de la consulta.

        resultado = db.queryByExample(new Vehiculo(null, null, null, null, vehiculo.getId(), null)); //Consulta simple para buscar vehículo.

        if (resultado.hasNext()) { //Hay coincidencia

            Coche cNuevo = (Coche) resultado.next();
            Coche cAntiguo = (Coche) vehiculo;

            //Actualización de atributos
            cNuevo.setAnnio(cAntiguo.getAnnio());
            cNuevo.setCc(cAntiguo.getCc());
            cNuevo.setColor(cAntiguo.getColor());
            cNuevo.setCombustible(cAntiguo.getCombustible());
            cNuevo.setCv(cAntiguo.getCv());
            cNuevo.setFechaItv(cAntiguo.getFechaItv());
            cNuevo.setFechaSeguro(cAntiguo.getFechaSeguro());
            cNuevo.setMarca(cAntiguo.getMarca());
            cNuevo.setModelo(cAntiguo.getModelo());
            cNuevo.setTxtArea(cAntiguo.getTxtArea());

            //Actualización del vehículo en la BD
            alta(cNuevo);

            return true;
        } else {

            cerrarConexion();
            return false;
        }
    }

    /**
     * Borra un objeto Vehiculo de la BD
     *
     * @param vehiculo Objeto Vehiculo a borrar de la BD
     * @return true en el caso de que se haya podido borrar el objeto
     */
    public boolean borrar(Vehiculo vehiculo) {

        //Este método parte de la base de que en la base de datos no puede haber
        //varios objetos Vehiculo con el mismo ID.
        //
        ObjectSet<Vehiculo> resultado; //ObjectSet donde se guardarán los resultados de la consulta.

        resultado = db.queryByExample(vehiculo); //Consulta simple para buscar vehículo.

        if (resultado.hasNext()) { //Hay coincidencia

            try {

                db.delete(resultado.next());
                cerrarConexion();

                return true;
            } catch (Db4oException e) {

                String mensaje = "Se arrojó el siguiente error borrando el vehículo de la base de datos: "
                        + "\n " + e;
                JOptionPane.showMessageDialog(null, mensaje, "Error en la base de datos", JOptionPane.ERROR_MESSAGE);

                cerrarConexion();
                return false;
            }
        } else { //No hay coincidencia

            cerrarConexion();
            return false;
        }
    }

    /**
     * Inserta un Objeo Vehiculo en la BD
     *
     * @param v Objeto Vehiculo a ingresar
     * @return true si el proceso de alta tuvo éxito
     */
    public boolean alta(Vehiculo v) {

        if (db == null) { //No se pudo abrir la BD

            return false;
        } else { //La BD se abrió correctamente

            try {

                db.store(v);
                cerrarConexion();

                return true;
            } catch (Db4oException e) {

                String mensaje = "Se arrojó el siguiente error agregando el vehículo a la base de datos: "
                        + "\n " + e;
                JOptionPane.showMessageDialog(null, mensaje, "Error en la base de datos", JOptionPane.ERROR_MESSAGE);

                return false;
            }
        }
    }

    /**
     * Busca un vehículo en la BD
     *
     * @param vehiculo Objeto Vehiculo a buscar
     * @return Vehiculo encontrado o null si no ha habido coincidencias
     */
    public Vehiculo buscar(Vehiculo vehiculo) {

        //Este método parte de la base de que en la base de datos no puede haber
        //varios objetos Vehiculo con el mismo ID.
        //
        Query consulta = db.query(); //Se crea consulta
        consulta.constrain(Vehiculo.class); //Los objetos a consultar serán de tipo Vehiculo.
        consulta.descend("id").constrain(vehiculo.getId().toUpperCase()); //Se añade restricción por ID

        ObjectSet<Vehiculo> resultado; //ObjectSet donde se guardarán los resultados de la consulta.

        resultado = consulta.execute(); //Se ejecuta la consulta.

        if (resultado.isEmpty()) { //La consulta está vacía.

            cerrarConexion();
            return null;
        } else { //La consulta contiene registros

            Vehiculo vCoincidente = resultado.next(); //Se guarda el objeto coincidente

            cerrarConexion();
            return vCoincidente;
        }
    }

    private void crearConexion() {

        //Ruta relativa al fichero de la BD        
        final File path = new File("DB");
        final File db4o = new File(File.separator + "vehiculo.yap");

        try {

            if (!path.exists()) { //Si el directorio no existe, se crea
                path.mkdir();
            }

            //El método openFile abre un ObejectContainer en la BD para uso local
            db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), path.getPath() + db4o.getPath());
        } catch (Db4oException e) {

            String mensaje = "Se arrojó el siguiente error abriendo la base de datos: "
                    + "\n " + e;
            JOptionPane.showMessageDialog(null, mensaje, "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cerrarConexion() {

        try {

            if (db != null) {

                db.commit(); //Confirma la transacción actual.
                db.close(); //Cierra conexión con BD.
            }
        } catch (Db4oException e) {

            String mensaje = "Se arrojó el siguiente error cerrando la base de datos: "
                    + "\n " + e;
            JOptionPane.showMessageDialog(null, mensaje, "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }
}
