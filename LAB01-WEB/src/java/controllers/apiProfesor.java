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
import models.*;
import Model.Model;


/**
 * REST Web Service
 *
 * @author jorac
 */
@Path("profesor")
public class apiProfesor {

    @Context
    private HttpServletRequest request;
    
    
    /**
     * Creates a new instance of apiProfesor
     */
    public apiProfesor() {
    }

    /**
     * Retrieves representation of an instance of api.GenericResource
     * @return an instance of java.lang.String
     */
  
    @GET
    @Produces({MediaType.APPLICATION_JSON })
    public List<Profesor> listar() {
        Model mp = new Model();
        List<Profesor> profes;
        try {
            profes = mp.listarProfesor();
        } catch (Exception ex) {
            return new ArrayList<>();
        } 
        return profes;
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON })
    public int agregar(Profesor p) {
        try {
            Model mp = new Model();
            return mp.agregarProfesor(p);
        } catch (Exception ex) {
            return -1;
        }
    }
    
    @DELETE
    public int del(@QueryParam("cedula") String cedula) {
        try {
            Model mp = new Model();
            mp.eliminarProfesor(cedula);
            return 1;
        } catch (Exception ex) {
            return 0;
        }
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public int update(Profesor p) {
        try {
            Model mp = new Model();
            mp.actualizarProfesor(p);
            return 1;
        } catch (Exception ex) {
            return 0;
        }
    }
    
}
