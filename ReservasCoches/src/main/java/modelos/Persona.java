/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Portatil
 */
public abstract class Persona {
    private int id_usuario;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String correo;
    private String contraseña;

    public Persona() {
    }

    public Persona(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Persona(int id_usuario, String nombre, String apellidos, String telefono, String correo, String contraseña) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Persona{" + "id_usuario=" + id_usuario + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", correo=" + correo + ", contrase\u00f1a=" + contraseña + '}';
    } 

    public int compareTo(Persona p){
    
        return this.nombre.compareTo(p.nombre);
    }
}


