/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorac
 */
public class Curso {
    int id;
    String codigo;
    String nombre;
    int credito;
    int horas;
    Carrera carrera;
    List<Grupo> grupos;

    public Curso(int id, String codigo, String nombre, int credito, int horas) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.credito = credito;
        this.horas = horas;
        this.carrera = new Carrera();
        this.grupos = new ArrayList<>();
    }
    

    public Curso(String codigo, String nombre, int credito, int horas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.credito = credito;
        this.horas = horas;
        this.carrera = new Carrera();
        this.grupos = new ArrayList<>();
    }

    public Curso(){
        this.codigo = new String();
        this.nombre = new String();
        this.credito = 0;
        this.horas = 0;
        this.grupos = new ArrayList<>();
        this.carrera = new Carrera();
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

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
    
    public void agregarGrupo(Grupo grupo){
        this.grupos.add(grupo);
    }
    
    public void eliminarGrupo(Grupo grupo){
        this.grupos.remove(grupo);
    }

    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", credito=" + credito + ", horas=" + horas + ", carrera=" + carrera + ", grupos=" + grupos + '}';
    }
    
}
