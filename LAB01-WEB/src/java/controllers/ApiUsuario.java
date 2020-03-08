/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Model.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import models.*;

/**
 * REST Web Service
 *
 * @author jorac
 */
@Path("usuario")
public class ApiUsuario {

    @Context
    private HttpServletRequest request;

    /**
     * Creates a new instance of apiProfesor
     */
    public ApiUsuario() {
    }

    /**
     * Retrieves representation of an instance of api.GenericResource
     *
     * @param cedula
     * @param clave
     * @return an instance of java.lang.String
     */
    @GET
    public int autenticar(@QueryParam("cedula") String cedula, @QueryParam("clave") String clave) {
        
        Usuario user = new Usuario(cedula);
        HttpSession session = request.getSession();
        Model mp = new Model();

        if (mp.validarUsuario(cedula, clave)) {
            session.setAttribute("usuario", user);
            return 1;
        }
        
        return 0;
    }
    
    @GET
    @Path("/salir")
    public int salir(){
         HttpSession session = request.getSession();
         session.invalidate();
         return 1;
    }
    

}
