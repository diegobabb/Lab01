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
public class TableProfesor extends AbstractTableModel {

    private ArrayList<Profesor> data;

    public TableProfesor(ArrayList<Profesor> data) {
        this.data = data;
    }

    public TableProfesor() {
        this.data = new ArrayList<>();
    }
    
    public void add(Profesor p){
        this.data.add(p);
    }

    @Override
    public int getRowCount() {
        return this.data.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return this.data.get(rowIndex).getCedula();
            case 1:
                return this.data.get(rowIndex).getNombre();
            case 2:
                return this.data.get(rowIndex).getTelefono();
            case 3:
                return this.data.get(rowIndex).getEmail();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Cedula";
            case 1:
                return "Nombre";
            case 2:
                return "Telefono";
            case 3:
                return "Email";
        }
        return "???";
    }

    String getCedula(int selectedRowTableProfesores) {
       return this.data.get(selectedRowTableProfesores).getCedula();
    }

    void remove(int selectedRowTableProfesores) {
        this.data.remove(selectedRowTableProfesores);
    }

    void update(Profesor p) {
        this.data.remove(p);
        this.data.add(p);
    }

    Profesor getProfesor(int row) {
        return this.data.get(row);
    }

}
