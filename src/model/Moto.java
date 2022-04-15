/*
 * Instituo FOC
 * Grado superior DAM
 * Proyecto creado para la certificvación Cimientos de Java, incluído en el módulo de programación.
 */
package model;

import enumerations.crnt;

/**
 * Modela los objetos Moto. Especificación de Vehículo.
 *
 * @author Francisco Hueso
 * @version 02/2022
 */
public class Moto extends Vehiculo {

    private String cc, cv, fechaSeguro, fechaItv;
    private crnt carnet;

    public Moto() {
    }

    public Moto(String cc, String cv, String fechaSeguro, String fechaItv, crnt carnet, String marca, String modelo, String color, String annio, String id, String txtArea) {
        super(marca, modelo, color, annio, id, txtArea);
        this.cc = cc;
        this.cv = cv;
        this.fechaSeguro = fechaSeguro;
        this.fechaItv = fechaItv;
        this.carnet = carnet;
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

    public crnt getCarnet() {
        return carnet;
    }

    public void setCarnet(crnt carnet) {
        this.carnet = carnet;
    }
}
