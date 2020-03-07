/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import exceptions.GlobalException;
import exceptions.NoDataException;
import java.util.Observable;
import javax.swing.table.TableModel;
import services.ServiceCurso;
import services.ServiceProfesor;
import services.ServiceUsuario;

/**
 *
 * @author Diego Babb
 */
public class Model extends Observable {

    TableProfesor tableProfesores;
    TableCursos tableCursos;

    public Model() throws GlobalException, NoDataException {
        ServiceProfesor SP = new ServiceProfesor();
        ServiceCurso SC = new ServiceCurso();
        this.tableProfesores = new TableProfesor(SP.selectAll());
        this.tableCursos = new TableCursos(SC.selectAll());
    }

    public void update() {
        this.setChanged();
        this.notifyObservers(null);
    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        this.update();
    }

    public TableModel getTable(Object o) {
        if (o instanceof Profesor) {
            return this.tableProfesores;
        }
        return this.tableCursos;
    }

    public void agregarProfesor(Profesor p) throws GlobalException, NoDataException {
        ServiceProfesor SP = new ServiceProfesor();
        SP.insert(p);
        this.tableProfesores.add(p);
        this.update();
    }

    public void eliminarProfesor(int selectedRowTableProfesores) throws GlobalException, NoDataException {
        ServiceProfesor SP = new ServiceProfesor();
        SP.delete(this.tableProfesores.getCedula(selectedRowTableProfesores));
        this.tableProfesores.remove(selectedRowTableProfesores);
        this.update();
    }

    public void actualizarProfesor(Profesor p) throws GlobalException, NoDataException {
        ServiceProfesor SP = new ServiceProfesor();
        SP.update(p);
        this.tableProfesores.update(p);
        this.update();
    }

    public void agregarCurso(Curso curso) throws GlobalException, NoDataException {
        ServiceCurso SP = new ServiceCurso();
        SP.insert(curso);
        this.tableCursos.add(curso);
        this.update();
    }

    public Curso getCurso(int row) {
        return this.tableCursos.getCurso(row);
    }

    public Profesor getProfesor(int row) {
        return this.tableProfesores.getProfesor(row);
    }

    public void actualizarCurso(Curso curso) throws GlobalException, NoDataException {
        ServiceCurso SC = new ServiceCurso();
        SC.update(curso);
        this.tableCursos.update(curso);
        this.update();
    }

    public void eliminarCurso(int selectedRowTableCurso) throws GlobalException, NoDataException {
        ServiceCurso SC = new ServiceCurso();
        SC.delete(this.tableProfesores.getCedula(selectedRowTableCurso));
        this.tableCursos.remove(selectedRowTableCurso);
        this.update();
    }

    public void login(String usuario, String contrasena) throws GlobalException, NoDataException {
        ServiceUsuario SU = new ServiceUsuario();
        SU.find(usuario, contrasena);
    }

    public void logout() {
        Usuario.logout();
    }

    public TableProfesor getTableProfesores() {
        return this.tableProfesores;
    }

    public TableCursos getTableCursos() {
        return tableCursos;
    }
}
