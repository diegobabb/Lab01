/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import exceptions.GlobalException;
import exceptions.NoDataException;
import java.util.Observable;
import javax.swing.DefaultComboBoxModel;
import services.ServiceCurso;
import services.ServiceProfesor;

/**
 *
 * @author Diego Babb
 */
public class Model extends Observable {

    TableProfesor tableProfesores;
    TableCursos tableCursos;

    public Model() throws GlobalException, NoDataException {
        this.tableProfesores = new TableProfesor(ServiceProfesor.getInstance().selectAll());
        this.tableCursos = new TableCursos(ServiceCurso.getInstance().selectAll());
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

    public void agregarProfesor(Profesor p) throws GlobalException, NoDataException {
        ServiceProfesor.getInstance().insert(p);
        this.tableProfesores.add(p);
        this.update();
    }

    public void eliminarProfesor(int selectedRowTableProfesores) throws GlobalException, NoDataException {
        ServiceProfesor.getInstance().delete(this.tableProfesores.getCedula(selectedRowTableProfesores));
        this.tableProfesores.remove(selectedRowTableProfesores);
        this.update();
    }

    public void actualizarProfesor(Profesor p) throws GlobalException, NoDataException {
        ServiceProfesor.getInstance().update(p);
        this.tableProfesores.update(p);
        this.update();
    }

    public void agregarCurso(Curso curso) throws GlobalException, NoDataException {
        ServiceCurso.getInstance().insert(curso);
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
        ServiceCurso.getInstance().update(curso);
        this.tableCursos.update(curso);
        this.update();
    }

    public void eliminarCurso(int selectedRowTableCurso) throws GlobalException, NoDataException {
        ServiceCurso.getInstance().delete(this.tableProfesores.getCedula(selectedRowTableCurso));
        this.tableCursos.remove(selectedRowTableCurso);
        this.update();
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

    public int getCursoPosition(String curso) {
        return this.tableCursos.findRow(curso);
    }

    public DefaultComboBoxModel<String> getComboBoxCursos() {
        String[] array = new String[this.tableCursos.getData().size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = this.tableCursos.getCurso(i).getNombre();
        }
        return new DefaultComboBoxModel<String>(array);
    }

    public void refreshTables() throws GlobalException, NoDataException {
        this.tableProfesores = new TableProfesor(ServiceProfesor.getInstance().selectAll());
        this.tableCursos = new TableCursos(ServiceCurso.getInstance().selectAll());
        this.update();
    }
}
