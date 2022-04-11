/*
 * Instituo FOC
 * Grado superior DAM
 * Proyecto creado para la certificvación Cimientos de Java, incluído en el módulo de programación.
 */
package model;

import enumerations.tmn;
import enumerations.tp;

/**
 * Modela los objetos Bici. Especificación de Vehículo.
 *
 * @author Francisco Hueso
 * @version 02/2022
 */
public class Bici extends Vehiculo {

    private tmn tamaño;
    private tp tipo;

    public Bici() {
    }

    public Bici(tmn tamaño, tp tipo, String marca, String modelo, String color, String annio, String id, String txtArea) {
        super(marca, modelo, color, annio, id, txtArea);
        this.tamaño = tamaño;
        this.tipo = tipo;
    }

    public tmn getTamaño() {
        return tamaño;
    }

    public void setTamaño(tmn tamaño) {
        this.tamaño = tamaño;
    }

    public tp getTipo() {
        return tipo;
    }

    public void setTipo(tp tipo) {
        this.tipo = tipo;
    }
}
