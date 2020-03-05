/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import models.Ciclo;

/**
 *
 * @author jorac
 */
public class Grupo {
    Profesor profesor;
    Curso curso;
    int numero;
    Ciclo ciclo;

    public Grupo(Profesor profesor, Curso curso, int numero) {
        this.profesor = profesor;
        this.curso = curso;
        this.numero = numero;
        this.ciclo = new Ciclo();
    }

    public Grupo(Profesor profesor, Curso curso) {
        this.profesor = profesor;
        this.curso = curso;
        this.numero = 0;
        this.ciclo = new Ciclo();
    }
    
    public Grupo() {
        profesor = new Profesor();
        curso = new Curso();
        numero = 0;
        ciclo = new Ciclo();
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    @Override
    public String toString() {
        return "Grupo{" + "profesor=" + profesor + ", curso=" + curso + ", numero=" + numero + ", ciclo=" + ciclo + '}';
    }
    
    
}
