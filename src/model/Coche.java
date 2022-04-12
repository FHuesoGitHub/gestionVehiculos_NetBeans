/*
 * Instituo FOC
 * Grado superior DAM
 * Proyecto creado para la certificvación Cimientos de Java, incluído en el módulo de programación.
 */
package model;

import enumerations.cmb;

/**
 * Modela los objetos Coche. Especificación de Vehículo.
 *
 * @author Francisco Hueso
 * @version 02/2022
 */
public class Coche extends Vehiculo {

    private String cc, cv, fechaSeguro, fechaItv;
    private cmb combustible;

    public Coche() {        
    }

    public Coche(String cc, String cv, String fechaSeguro, String fechaItv, cmb combustible, String marca, String modelo, String color, String annio, String id, String txtArea) {
        super(marca, modelo, color, annio, id, txtArea);
        this.cc = cc;
        this.cv = cv;
        this.fechaSeguro = fechaSeguro;
        this.fechaItv = fechaItv;
        this.combustible = combustible;
    }   

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getFechaSeguro() {
        return fechaSeguro;
    }

    public void setFechaSeguro(String fechaSeguro) {
        this.fechaSeguro = fechaSeguro;
    }

    public String getFechaItv() {
        return fechaItv;
    }

    public void setFechaItv(String fechaItv) {
        this.fechaItv = fechaItv;
    }

    public cmb getCombustible() {
        return combustible;
    }

    public void setCombustible(cmb combustible) {
        this.combustible = combustible;
    }
}
