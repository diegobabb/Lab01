package controllers;

import exceptions.GlobalException;
import exceptions.NoDataException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import models.Curso;
import models.Model;
import models.Profesor;
import views.*;

/**
 *
 * @author Diego Babb
 */
public class Controller implements ActionListener {

    private Model model;
    private View view;
    private Login login;

    public Controller() throws GlobalException, NoDataException {
        this.model = new Model();
        this.login = new Login(model);
        this.login.setVisible(true);
        this.login.addListeners(this);
    }

    private void initView() throws GlobalException, NoDataException {
        this.view = new View(model);
        view.addListeners(this);
        model.addObserver(view);
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Ingresar":
                this.login();
                break;
            case "Guardar profesor":
                this.guardarProfesor();
                break;
            case "Guardar curso":
                this.guardarCurso();
                break;
            case "Eliminar profesor":
                this.eliminarProfesor();
                break;
            case "Eliminar curso":
                this.eliminarCurso();
                break;
            case "Guardar cambios":
                this.editarProfesor();
                break;
            case "Guardar cambio":
                this.editarCurso();
                break;
            case "logout":
                this.logout();
                break;
        }
    }

    private void editarProfesor() {
        try {
            String cedula = this.view.getTextCedula();
            String nombre = this.view.getTextNombre();
            String email = this.view.getTextEmail();
            String telefono = this.view.getTextTelefono();
            String curso = model.getCurso(this.view.getSelectedIndexComboBoxCurso()).getCodigo();
            if (!cedula.isEmpty() && !nombre.isEmpty() && !email.isEmpty() && !telefono.isEmpty()) {
                String regex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                if (pattern.matcher(email).matches()) {
                    if (cedula.length() == 9) {
                        if (telefono.length() == 8) {
                            this.model.actualizarProfesor(new Profesor(cedula, nombre, telefono, email, curso));
                            this.view.limpiarEspaciosPanelAgregarProfesor();
                            JOptionPane.showMessageDialog(null, "Profesor actualizado!!", "information", JOptionPane.OK_OPTION);
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
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el Profesor!!", "Error", JOptionPane.ERROR_MESSAGE);
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
            String curso = model.getCurso(this.view.getSelectedIndexComboBoxCurso()).getCodigo();
            System.out.println(curso);
            if (!cedula.isEmpty() && !nombre.isEmpty() && !email.isEmpty() && !telefono.isEmpty()) {
                String regex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                if (pattern.matcher(email).matches()) {
                    if (cedula.length() == 9) {
                        if (telefono.length() == 8) {
                            this.model.agregarProfesor(new Profesor(cedula, nombre, telefono, email, curso));
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

    private void guardarCurso() {
        String codigo = this.view.getTextCodigo();
        String nombre = this.view.getTextNombreCurso();
        String creditos = this.view.getTextCreditos();
        String horas = this.view.getTextHorasSemanales();
        int carrera = this.view.getSelectedIndexComboBoxCarrera();
        if (!codigo.isEmpty() && !nombre.isEmpty() && !creditos.isEmpty() && !horas.isEmpty()) {
            try {
                this.model.agregarCurso(new Curso(codigo, nombre,
                        Integer.parseInt(creditos),
                        Integer.parseInt(horas), carrera)
                );
                this.view.limpiarEspaciosPanelAgregarCurso();
                JOptionPane.showMessageDialog(null, "Curso agregado!!", "information", JOptionPane.OK_OPTION);
                this.view.volverAPrincipal();
            } catch (GlobalException | NoDataException ex) {
                JOptionPane.showMessageDialog(null, "No se pudo ingresar el nuevo Curso!!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "La cantidad de creditos y horas se debe de dar en numeros", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Casillas vacias", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void editarCurso() {
        String codigo = this.view.getTextCodigo();
        String nombre = this.view.getTextNombreCurso();
        String creditos = this.view.getTextCreditos();
        String horas = this.view.getTextHorasSemanales();
        int carrera = this.view.getSelectedIndexComboBoxCarrera();
        if (!codigo.isEmpty() && !nombre.isEmpty() && !creditos.isEmpty() && !horas.isEmpty()) {
            try {
                this.model.actualizarCurso(new Curso(codigo, nombre,
                        Integer.parseInt(creditos),
                        Integer.parseInt(horas), carrera)
                );
                this.view.limpiarEspaciosPanelAgregarCurso();
                JOptionPane.showMessageDialog(null, "Curso actualizado!!", "information", JOptionPane.OK_OPTION);
                this.view.volverAPrincipal();
            } catch (GlobalException | NoDataException ex) {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar el Curso!!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "La cantidad de creditos y horas se debe de dar en numeros", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Casillas vacias", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void eliminarCurso() {
        try {
            this.model.eliminarCurso(this.view.getSelectedRowTableCurso());
            JOptionPane.showMessageDialog(null, "Curso eliminado!!", "information", JOptionPane.OK_OPTION);
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar al Profesor!!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void login() {
        String usuario = this.login.getTextNombreUsuario().replaceAll("\\s", "");
        String contrasena = this.login.getTextContrasena().replaceAll("\\s", "");
        if (!usuario.isEmpty() && !contrasena.isEmpty()) {
            try {
                this.model.login(usuario, contrasena);
                this.login.setVisible(false);
                this.initView();
                this.login = null;
            } catch (NoDataException | GlobalException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Casillas vacias", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void logout() {
        this.model.logout();
        this.login = new Login(model);
        this.login.addListeners(this);
        this.view.setVisible(false);
        this.view = null;
        this.login.setVisible(true);
    }
}
