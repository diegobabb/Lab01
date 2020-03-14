/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import exceptions.GlobalException;
import exceptions.NoDataException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JOptionPane;
import models.ModelLogin;
import views.Login;

/**
 *
 * @author diego
 */
public class ControllerLogin extends Observable implements ActionListener {

    private Login view;
    private ModelLogin model;

    public ControllerLogin(App a) {
        this.model = new ModelLogin();
        this.view = new Login(model);
        this.view.setVisible(true);
        this.view.addListeners(this);
        this.model.addObserver(view);
        this.addObserver(a);
    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Ingresar":
                this.login();
                break;
        }
    }

    private void login() {
        String usuario = this.view.getTextNombreUsuario().replaceAll("\\s", "");
        String contrasena = this.view.getTextContrasena().replaceAll("\\s", "");
        if (!usuario.isEmpty() && !contrasena.isEmpty()) {
            try {
                this.model.login(usuario, contrasena);
                this.view.setVisible(false);
                this.update();
            } catch (NoDataException | GlobalException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Casillas vacias", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void update() {
        this.setChanged();
        this.notifyObservers(null);
    }

}
