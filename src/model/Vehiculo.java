/*
 * Instituo FOC
 * Grado superior DAM
 * Proyecto creado para la certificvación Cimientos de Java, incluído en el módulo de programación.
 */
package model;

/**
 * Modela los objetos Vehiculo. Generalización.
 *
 * @author Francisco Hueso
 * @version 02/2022
 */
public class Vehiculo {

    protected String marca, modelo, color, annio, id, txtArea;

    public Vehiculo() {
    }

    public Vehiculo(String marca, String modelo, String color, String annio, String id, String txtArea) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.annio = annio;
        this.id = id;
        this.txtArea = txtArea;
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

    public String getTxtArea() {
        return txtArea;
    }

    public void setTxtArea(String txtArea) {
        this.txtArea = txtArea;
    }
}
