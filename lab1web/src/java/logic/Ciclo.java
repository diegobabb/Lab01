/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.sql.Date;

/**
 *
 * @author jorac
 */
public class Ciclo {
    
    private int id;
    private int numero;
    private int anio;
    private Date fecha_inicio;
    private Date fecha_fin;
    
    public Ciclo() {
        this.id = 1;
        this.numero = 1;
        this.anio = 2020;
        this.fecha_inicio = new Date(20,02,10);
        this.fecha_fin = new Date(20,06,10);
    }

    public int getId() {
        return id;
    }

    public int getNumero() {
        return numero;
    }

    public int getAnio() {
        return anio;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    @Override
    public String toString() {
        return "Ciclo{" + "id=" + id + ", numero=" + numero + ", anio=" + anio + ", fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + '}';
    }
    
    
}
