/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    int carrera;
    List<Grupo> grupos;

    public Curso(int id, String codigo, String nombre, int credito, int horas, int carrera) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.credito = credito;
        this.horas = horas;
        this.carrera = carrera;
        this.grupos = new ArrayList<>();
    }

    public Curso(String codigo, String nombre, int credito, int horas, int carrera) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.credito = credito;
        this.horas = horas;
        this.carrera = carrera;
        this.grupos = new ArrayList<>();
    }

    public Curso() {
        this.codigo = new String();
        this.nombre = new String();
        this.credito = -1;
        this.horas = -1;
        this.grupos = new ArrayList<>();
        this.carrera = -1;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Curso other = (Curso) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
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

    public int getId() {
        return id;
    }

    public int getCarrera() {
        return carrera;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void agregarGrupo(Grupo grupo) {
        this.grupos.add(grupo);
    }

    public void eliminarGrupo(Grupo grupo) {
        this.grupos.remove(grupo);
    }

    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", credito=" + credito + ", horas=" + horas + ", carrera=" + carrera + ", grupos=" + grupos + '}';
    }

}
