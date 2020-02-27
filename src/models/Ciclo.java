/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

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

    public Ciclo(int id, int numero, int anio, Date fecha_inicio, Date fecha_fin) {
        this.id = id;
        this.numero = numero;
        this.anio = anio;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public Ciclo(int numero, int anio, Date fecha_inicio, Date fecha_fin) {
        this.numero = numero;
        this.anio = anio;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }
    
    public Ciclo() {
        this.id = 0;
        this.numero = 0;
        this.anio = 0;
        this.fecha_inicio = null;
        this.fecha_fin = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    @Override
    public String toString() {
        return "Ciclo{" + "id=" + id + ", numero=" + numero + ", anio=" + anio + ", fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + '}';
    }
    
    
}
