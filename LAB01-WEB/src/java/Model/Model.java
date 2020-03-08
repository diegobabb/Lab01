package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import exceptions.GlobalException;
import exceptions.NoDataException;
import java.util.ArrayList;
import java.util.List;
import models.Curso;
import models.Profesor;
import models.Usuario;
import services.ServiceCurso;
import services.ServiceProfesor;
import services.ServiceUsuario;

/**
 *
 * @author jorac
 */
public class Model {

   ServiceProfesor serviceProfesor;
   ServiceUsuario serviceUsuario;
   ServiceCurso serviceCurso;

    public Model() {
        serviceProfesor = new ServiceProfesor();
        serviceUsuario = new ServiceUsuario();
        serviceCurso = new ServiceCurso();
    }

    public boolean validarUsuario(String cedula, String clave) {
        try {
            Usuario user_tmp = serviceUsuario.find(cedula, clave);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Profesor> listarProfesor() throws GlobalException, NoDataException {

        List<Profesor> lista = new ArrayList<>();

        try {
            lista = serviceProfesor.selectAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return lista;
    }

    public int agregarProfesor(Profesor profe) throws GlobalException, NoDataException {
        serviceProfesor.insert(profe);
        return 1;
    }

    public void eliminarProfesor(String cedula) throws GlobalException, NoDataException {
        serviceProfesor.delete(cedula);
    }

    public void actualizarProfesor(Profesor profesor) throws GlobalException, NoDataException {
        serviceProfesor.update(profesor);
    }
    
    
    public List<Curso> listarCurso() throws GlobalException, NoDataException {

        List<Curso> lista = new ArrayList<>();

        try {
            lista = serviceCurso.selectAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return lista;
    }

    public void agregarCurso(Curso curso) throws GlobalException, NoDataException {
        serviceCurso.insert(curso);
    }

    public void eliminarCurso(String codigo) throws GlobalException, NoDataException {
        serviceCurso.delete(codigo);
    }

    public void actualizarCurso(Curso curso) throws GlobalException, NoDataException {
        serviceCurso.update(curso);
    }
    
    public Curso obtenerCurso(String codigo) throws GlobalException, NoDataException{
        return serviceCurso.find(codigo);
    }

}
