/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author jorac
 */
public class Carrera {
    
    private int id;
    private String codigo;
    private String nombre;
    
    public Carrera() {
        this.id = 1;
        this.codigo = "EIF";
        this.nombre = "Ingenieria en Sistemas";
    }
    
    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Carrera{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + '}';
    }
    
}
