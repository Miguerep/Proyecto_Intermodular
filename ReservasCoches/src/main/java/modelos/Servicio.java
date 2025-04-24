/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Portatil
 */

public class Servicio {
    private int id;
    private String tipo;
    private int duracion;
    private String precio;

    public Servicio(int id, String tipo, int duracion, String precio) {
        this.id = id;
        this.tipo = tipo;
        this.duracion = duracion;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getPrecio() {
        return precio;
    }
}
