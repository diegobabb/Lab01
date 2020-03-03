/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import logic.Curso;
import logic.Model;
import logic.Profesor;


/**
 * REST Web Service
 *
 * @author jorac
 */
@Path("curso")
public class ApiCurso {

    @Context
    private HttpServletRequest request;
    
    
    /**
     * Creates a new instance of apiProfesor
     */
    public ApiCurso() {
    }

    /**
     * Retrieves representation of an instance of api.GenericResource
     * @return an instance of java.lang.String
     */
  
    @GET
    @Produces({MediaType.APPLICATION_JSON })
    public List<Curso> listar() {
        Model mp = new Model();
        List<Curso> cursos;
        try {
            cursos = mp.listarCurso();
        } catch (Exception ex) {
            return new ArrayList<>();
        } 
        return cursos;
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON })
    public int agregar(Curso c) {
        try {
            Model mp = new Model();
            mp.agregarCurso(c);
            return 1;
        } catch (Exception ex) {
            return 0;
        }
    }
    
    @DELETE
    public int del(@QueryParam("codigo") String codigo) {
        try {
            Model mp = new Model();
            mp.eliminarCurso(codigo);
            return 1;
        } catch (Exception ex) {
            return 0;
        }
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public int update(Curso c) {
        try {
            Model mp = new Model();
            mp.actualizarCurso(c);
            return 1;
        } catch (Exception ex) {
            return 0;
        }
    }
    
}
