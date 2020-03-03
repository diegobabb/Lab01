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
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import models.Model;
import models.Profesor;
import views.View;

/**
 *
 * @author Diego Babb
 */
public class Controller implements ActionListener {

    private Model model;
    private View view;

    public Controller() throws GlobalException, NoDataException {
        this.model = new Model();
        this.view = new View(model);
        view.addListeners(this);
        model.addObserver(view);
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Guardar profesor":
                this.guardarProfesor();
                break;
            case "Eliminar profesor":
                this.eliminarProfesor();
                break;
            case "Guardar cambios":
                this.editarProfesor();
                break;
        }
    }

    private void editarProfesor() {
                try {
            String cedula = this.view.getTextCedula();
            String nombre = this.view.getTextNombre();
            String email = this.view.getTextEmail();
            String telefono = this.view.getTextTelefono();
            if (!cedula.isEmpty() && !nombre.isEmpty() && !email.isEmpty() && !telefono.isEmpty()) {
                String regex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                if (pattern.matcher(email).matches()) {
                    if (cedula.length() == 9) {
                        if (telefono.length() == 8) {
                            this.model.actualizarProfesor(new Profesor(view.getTextCedula(), view.getTextNombre(), view.getTextEmail(), view.getTextTelefono()));
                            this.view.limpiarEspaciosPanelAgregarProfesor();
                            JOptionPane.showMessageDialog(null, "Profesor agregado!!", "information", JOptionPane.OK_OPTION);
                            this.view.volverAPrincipal();
                        } else {
                            JOptionPane.showMessageDialog(null, "Telefono invalido!!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Cedula invalido!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Email invalido!!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Casillas vacias", "Error", JOptionPane.ERROR_MESSAGE);

            }
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo ingresar el nuevo Profesor!!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarProfesor() {
        try {
            this.model.eliminarProfesor(this.view.getSelectedRowTableProfesores());
            JOptionPane.showMessageDialog(null, "Profesor eliminado!!", "information", JOptionPane.OK_OPTION);
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar al Profesor!!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarProfesor() {
        try {
            String cedula = this.view.getTextCedula();
            String nombre = this.view.getTextNombre();
            String email = this.view.getTextEmail();
            String telefono = this.view.getTextTelefono();
            if (!cedula.isEmpty() && !nombre.isEmpty() && !email.isEmpty() && !telefono.isEmpty()) {
                String regex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                if (pattern.matcher(email).matches()) {
                    if (cedula.length() == 9) {
                        if (telefono.length() == 8) {
                            this.model.agregarProfesor(new Profesor(view.getTextCedula(), view.getTextNombre(), view.getTextEmail(), view.getTextTelefono()));
                            this.view.limpiarEspaciosPanelAgregarProfesor();
                            JOptionPane.showMessageDialog(null, "Profesor agregado!!", "information", JOptionPane.OK_OPTION);
                            this.view.volverAPrincipal();
                        } else {
                            JOptionPane.showMessageDialog(null, "Telefono invalido!!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Cedula invalido!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Email invalido!!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Casillas vacias", "Error", JOptionPane.ERROR_MESSAGE);

            }
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo ingresar el nuevo Profesor!!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
