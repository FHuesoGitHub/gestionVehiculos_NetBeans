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

                String mensaje = "Se arrojó el siguiente error agregando el Artículo a la base de datos: "
                        + "\n " + e;
                JOptionPane.showMessageDialog(null, mensaje, "Error en la base de datos", JOptionPane.ERROR_MESSAGE);

                return false;
            }
        }
    }

    /**
     * BORRAR   RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
     * RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
     */
    private String VehiculotoString() {

        ObjectSet<Vehiculo> resultado;
        resultado = db.queryByExample(new Vehiculo(null, null, null, null, "a", null));

        Coche c = (Coche) resultado.next();

        String coche = "Anio: " + c.getAnnio() + " Cilindrada: " + c.getCc() + " Color: " + c.getColor()
                + " Cavallos: " + c.getCv() + " Fecha ITV: " + c.getFechaItv() + " Fecha seguro: " + c.getFechaSeguro()
                + " Matricula: " + c.getId() + " Modelo: " + c.getModelo() + " Area" + " Combustible: " + c.getCombustible();

        return coche;
    }

    private void crearConexion() {

        //Ruta relativa al fichero de la BD
        final String uriBD = "src" + File.separator + "dB" + File.separator + "vehiculo.yap";

        try {

            //El método openFile abre un ObejectContainer en la BD para uso local
            db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), uriBD);
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

/*
public boolean insertar(Articulo a) {

        if (db == null) { //No se pudo abrir la BD

            return false;
        } else { //La BD se abrió correctamente

            try {

                db.store(a);
                cerrarConexion();

                return true;
            } catch (Db4oException e) {

                String mensaje = "Se arrojó el siguiente error agregando el Artículo a la base de datos: "
                        + "\n " + e;
                JOptionPane.showMessageDialog(null, mensaje, "Error en la base de datos", JOptionPane.ERROR_MESSAGE);

                return false;
            }

        }
    }

    
    public boolean modificar(Articulo articulo) {

        //Este método parte de la base de que cada artículo tiene un código único.        
        //
        String codigo = articulo.getCodigo(); //Código del artículo a buscar

        ObjectSet<Articulo> resultado; //ObjectSet donde se guardarán los resultados de la consulta.

        resultado = db.queryByExample(new Articulo(codigo, null, null, 0)); //Consulta simple para buscar artículo.

        if (resultado.hasNext()) { //Hay coincidencia

            Articulo nuevoArticulo = resultado.next();

            //Actualización de atributos
            nuevoArticulo.setNombre(articulo.getNombre());
            nuevoArticulo.setDescripcion(articulo.getDescripcion());
            nuevoArticulo.setCantidad(articulo.getCantidad());
            //Actualización del artículo en la BD
            insertar(nuevoArticulo);

            return true;
        } else {

            cerrarConexion();
            return false;
        }
    }

    
    public String mostrar() {

        String listado = "";

        if (db == null) { //No se pudo abrir la BD

            return null;
        } else { //La BD se abrió correctamente

            Query consulta = db.query(); //Se crea consulta
            consulta.constrain(Articulo

.class
); //Los objetos a consultar serán de tipo Articulo.

            ObjectSet<Articulo> resultado; //ObjectSet donde se guardarán los resultados de la consulta.

            resultado = consulta.execute(); //Se ejecuta la consulta.

            if (resultado.isEmpty()) { //La BD está vacía.

                cerrarConexion();
                return null;
            } else { //La BD contiene registros

                //Se recorre el ObjectSet y se extraen los datos de cada objeto hacie el String
                while (resultado.hasNext()) {

                    Articulo a = resultado.next();

                    listado += "Código: " + a.getCodigo() + "\n"
                            + "Nombre: " + a.getNombre() + "\n"
                            + "Cantidad: " + String.valueOf(a.getCantidad()) + "\n"
                            + "Descripción: " + a.getDescripcion() + "\n\n";
                }

                cerrarConexion();
                return listado;
            }

        }
    }

    
    public Articulo buscar(String codigo) {

        //Este método parte de la base de que en la base de datos no puede haber
        // varios objetos con el mismo código.
        //
        Query consulta = db.query(); //Se crea consulta
        consulta.constrain(Articulo

.class
); //Los objetos a consultar serán de tipo Articulo.
        consulta.descend("codigo").constrain(codigo); //Se añade restricción por código

        ObjectSet<Articulo> resultado; //ObjectSet donde se guardarán los resultados de la consulta.

        resultado = consulta.execute(); //Se ejecuta la consulta.

        if (resultado.isEmpty()) { //La consulta está vacía.

            cerrarConexion();
            return null;
        } else { //La consulta contiene registros

            Articulo articulo = resultado.next(); //Se guarda el objeto coincidente

            cerrarConexion();
            return articulo;
        }
    }

   
    public boolean borrar(String codigo) {

        //Este método parte de la base de que cada artículo tiene un código único.        
        //
        ObjectSet<Articulo> resultado; //ObjectSet donde se guardarán los resultados de la consulta.

        resultado = db.queryByExample(new Articulo(codigo, null, null, 0)); //Consulta simple para buscar artículo.

        if (resultado.hasNext()) { //Hay coincidencia

            try {

                db.delete(resultado.next());
                cerrarConexion();

                return true;
            } catch (Db4oException e) {

                String mensaje = "Se arrojó el siguiente error borrando el Artículo de la base de datos: "
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

    
    private void crearConexion() {

        //Ruta relativa al fichero de la BD
        final String uriBD = "src" + File.separator + "datos" + File.separator + "tarea8.yap";

        try {

            
            //El método openFile abre un ObejctContainer en la BD para uso local
             
            db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), uriBD);
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
 */
