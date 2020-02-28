/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import exceptions.GlobalException;
import exceptions.NoDataException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Curso;
import models.Grupo;
import models.Profesor;
import oracle.jdbc.internal.OracleTypes;
import static services.Service.connection;

/**
 *
 * @author jorac
 */
public class ServiceGrupo extends Service{
    private static final String INSERT = "{call CREAR_GRUPO(?, ?)}";
    private static final String DELETE = "{call ELIMINAR_GRUPO(?, ?)}";
    private static final String FIND = "{?=call BUSCAR_GRUPO(?, ?)}";
    
    
    public void insert(Curso curso, Profesor profesor) throws GlobalException, NoDataException {
        try {
            connection();
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        CallableStatement pstmt = null;

        try {
            pstmt = connection.prepareCall(INSERT);
            pstmt.setString(1, curso.getCodigo());
            pstmt.setString(2, profesor.getCedula());
            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la insercion");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Llave duplicada");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }
    
    
     public void delete(Curso curso, Profesor profesor) throws GlobalException, NoDataException {
        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(DELETE);
            pstmt.setString(1, curso.getCodigo());
            pstmt.setString(2, profesor.getCedula());
            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new NoDataException("No se realizo el borrado");
            } 
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }
     
     
     public Grupo find(Curso curso, Profesor profesor) throws GlobalException, NoDataException {
         
         ServiceCurso s_curso = new ServiceCurso();
         ServiceProfesor s_profe = new ServiceProfesor();
        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        CallableStatement pstmt = null;
        Grupo grupo = null;
        try {
            pstmt = connection.prepareCall(FIND);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, curso.getCodigo());
            pstmt.setString(3, profesor.getCedula());
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            if (rs.next()) {
                grupo = new Grupo(
                        s_profe.find(rs.getString("PROFESOR")),
                        s_curso.find(rs.getString("CURSO")),
                        rs.getInt("NUMERO"));
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (grupo == null) {
            throw new NoDataException("No hay datos");
        }
        return grupo;
    }
     
}
