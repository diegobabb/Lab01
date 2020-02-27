/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author jorac
 */
public class Carrera {
    
    private int id;
    private String codigo;
    private String nombre;
    
    public Carrera(int id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Carrera(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
    
    
    public Carrera() {
        this.id = 0;
        this.codigo = new String();
        this.nombre = new String();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    } 

    @Override
    public String toString() {
        return "Carrera{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + '}';
    }
    
    
    
}
