/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author diego
 */
public class TableCursos extends AbstractTableModel {

    private ArrayList<Curso> data;

    public TableCursos(ArrayList<Curso> data) {
        this.data = data;
    }

    public TableCursos() {
        this.data = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return this.data.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return this.data.get(rowIndex).getCodigo();
            case 1:
                return this.data.get(rowIndex).getNombre();
            case 2:
                return this.data.get(rowIndex).getCredito();
            case 3:
                return this.data.get(rowIndex).getHoras();
            case 4:
                return this.getCarreraNombre(this.data.get(rowIndex).getCarrera());
        }
        return null;
    }

    public Curso getCurso(int i) {
        return this.data.get(i);
    }

    private String getCarreraNombre(int i) {
        switch (i) {
            case 1:
                return "Ingenieria en Sistemas";
            case 2:
                return "Administracion de Empresas";
            case 3:
                return "Matematicas";
            case 4:
                return "Educacion";
        }
        return "Sin definir";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Codigo";
            case 1:
                return "Nombre";
            case 2:
                return "Creditos";
            case 3:
                return "Horas";
            case 4:
                return "Carrera";
        }
        return "???";
    }

    void add(Curso curso) {
        this.data.add(curso);
    }

    void update(Curso curso) {
        this.data.remove(curso);
        this.data.add(curso);
    }

    void remove(int selectedRowTableCurso) {
        this.data.remove(selectedRowTableCurso);
    }

}
