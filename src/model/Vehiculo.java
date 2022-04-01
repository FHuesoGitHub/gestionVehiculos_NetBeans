/*
 * Instituo FOC
 * Grado superior DAM
 * Proyecto creado para la certificvación Cimientos de Java, incluído en el módulo de programación.
 */
package model;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 * Modela los vehículos. Generalización.
 *
 * @author Francisco Hueso
 * @version 02/2022
 */
public class Vehiculo {

    protected String marca, modelo, color, annio, id;

    protected ArrayList<Vehiculo> vehiculoList = new ArrayList<Vehiculo>();

    public Vehiculo() {
    }

    public Vehiculo(String marca, String modelo, String color, String annio, String id) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.annio = annio;
        this.id = id;
    }
    
    Métodos anterior y siguiente. Devolverán la posición anterior y siguiente del arrayList, al objeto recibido por parámetros.

    /**
     * Busca el objeto Vehiculo en la colección y lo sustituye
     *
     * @param v Objeto Vehiculo a sustituir
     * @return true si se ha insertado o false en caso contrario
     */
    public boolean modificar(Vehiculo v) {

        boolean exito = false;

        //Recupero el campo a buscar
        String codigo = v.getId();

        //Creo un iterador para recorrer la colección de datos
        Iterator it = vehiculoList.iterator();

        //Recorro la colección para encontrar ocurrencias
        while (it.hasNext()) { //Mientras haya elementos en la colección

            //Recupero el siguiente elemento de la colección
            Vehiculo elemento = (Vehiculo) it.next();

            if (elemento.getId().equals(codigo)) { //Si hay ocurrencia:

                //Recupero el índice del objeto encontrado
                int indice = vehiculoList.indexOf(elemento);

                //Sustituyo el índice encontrado por el recibido por parámetros
                vehiculoList.set(indice, v);

                exito = true;

                break;
            }
        }

        return exito;
    }

    /**
     * Inserta un objeto Producto en el la colección si este no existe.
     *
     * @param v Objeto Producto a insertar en la colección
     * @return true si se ha insertado o false en caso contrario
     */
    public boolean insertar(Vehiculo v) {

        if (buscar(v) == null) { //El Vehiculo no se encuentra en la colección

            vehiculoList.add(v);
            JOptionPane.showMessageDialog(null, "Vehiculo insertado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else { //El Vehiculo ya se encuentra en la colección

            JOptionPane.showMessageDialog(null, "Vehiculo ya existente.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Elimina un objeto Vehiculo de la colección
     *
     * @param v Objeto Vehiculo a borrar de la colección
     */
    public void borrar(Vehiculo v) {

        vehiculoList.remove(v);
    }

    /**
     * Busca un objeto Vehiculo en la colección
     *
     * @param v Objeto Vehiculo a buscar en la colección
     *
     * @return Objeto Vehiculo encontrado o null si no hay ocurrencias.
     */
    public Vehiculo buscar(Vehiculo v) {

        Vehiculo vehiculo = null;

        //Recupero el campo a buscar
        String id = v.getId();

        //Creo un iterador para recorrer la colección de datos
        Iterator it = vehiculoList.iterator();

        //Recorro la colección para encontrar ocurrencias
        while (it.hasNext()) { //Mientras haya elementos en la colección

            //Recupero el siguiente elemento de la colección
            Vehiculo elemento = (Vehiculo) it.next();

            if (elemento.getId().equals(id)) { //Si hay ocurrencia, guardo el objeto encontrado

                vehiculo = elemento;
                break;
            }
        }

        return vehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAnnio() {
        return annio;
    }

    public void setAnnio(String annio) {
        this.annio = annio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Vehiculo> getVehiculoList() {
        return vehiculoList;
    }

    public void setVehiculoList(ArrayList<Vehiculo> vehiculoList) {
        this.vehiculoList = vehiculoList;
    }
}
