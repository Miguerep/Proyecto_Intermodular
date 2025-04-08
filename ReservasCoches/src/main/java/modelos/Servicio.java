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
    
    private int id_servicio;

    enum servicios 
    {LIMPIEZA_COMPLETA, TRATAMIENTO_CERAMICO, LIMPIEZA_INTERIOR, TRATAMIENTO_CUERO} 
    private float duracion;
    private float precio;

    public Servicio() {
    }

    public Servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public Servicio(int id_servicio, float duracion, float precio) {
        this.id_servicio = id_servicio;
        this.duracion = duracion;
        this.precio = precio;
    }
    
    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Servicio{" + "id_servicio=" + id_servicio + ", duracion=" + duracion + ", precio=" + precio + '}';
    }
    
    
}
