/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author diego
 */
public class Usuario {

    static private Usuario instance = null;

    private String cedula;

    public Usuario(String cedula) {
        this.cedula = cedula;
    }

    static public Usuario login(String cedula) {
        if (instance != null) {
            return Usuario.instance;
        } else {
            Usuario.instance = new Usuario(cedula);
            return Usuario.instance;
        }
    }

    static public void logout() {
        if (instance != null) {
            Usuario.instance = null;
        }
    }

    public String getCedula() {
        return cedula;
    }
}
