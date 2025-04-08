/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Portatil
 */
public class Pago {
    
    private int id_pago;
    private int id_reserva;
    private float total;
    private String metodo_pago;

    public Pago() {
    }

    public Pago(int id_pago, int id_reserva, float total) {
        this.id_pago = id_pago;
        this.id_reserva = id_reserva;
        this.total = total;
    }
    
    

    public Pago(int id_pago, int id_reserva, float total, String metodo_pago) {
        this.id_pago = id_pago;
        this.id_reserva = id_reserva;
        this.total = total;
        
        this.metodo_pago = metodo_pago;
    }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    @Override
    public String toString() {
        return "Pago{" + "id_pago=" + id_pago + ", id_reserva=" + id_reserva + ", total=" + total + ", metodo_pago=" + metodo_pago + '}';
    }
    
    

    public void procesarPago() {
        // lógica para procesar el pago
    }

    public void reembolsarPago() {
        // lógica para reembolsar
    }
}

    

