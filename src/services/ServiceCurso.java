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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Carrera;
import models.Curso;
import oracle.jdbc.internal.OracleTypes;
import static services.Service.connection;

/**
 *
 * @author diego
 */
public class ServiceCurso extends Service {
    private static final String INSERT = "{call INSERTAR_CURSO(?,?,?,?)}";
    private static final String UPDATE = "{call MODIFICAR_CURSO(?,?,?,?)}";
    private static final String FIND = "{?=call BUSCAR_CURSO(?)}";
    private static final String SELECTALL = "{?=call LISTAR_CURSO()}";
    private static final String DELETE = "{call ELIMINAR_CURSO(?)}";
    
    public ServiceCurso() {
    }
    
     public void insert(Curso curso) throws GlobalException, NoDataException {
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
            pstmt.setString(2, curso.getNombre());
            pstmt.setInt(3, curso.getCredito());
            pstmt.setInt(4, curso.getHoras());
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
     
     public void update(Curso curso) throws GlobalException, NoDataException {
        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(UPDATE);
            pstmt.setString(1, curso.getCodigo());
            pstmt.setString(2, curso.getNombre());
            pstmt.setInt(3, curso.getCredito());
            pstmt.setInt(4, curso.getHoras());
            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new NoDataException("No se realizo la actualización");
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
     
     public Curso find(String codigo) throws GlobalException, NoDataException {

        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Curso curso = null;
        CallableStatement pstmt = null;
        try {
            pstmt = connection.prepareCall(FIND);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, codigo);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                curso = new Curso(
                        rs.getString("CODIGO"),
                        rs.getString("NOMBRE"),
                        rs.getInt("CREDITOS"),
                        rs.getInt("HORAS_SEMANALES")
                        );
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
        
        if (curso == null ) {
            throw new NoDataException("No hay datos");
        }
        
        
        return curso;
    }
     
     public List<Curso> selectAll() throws GlobalException, NoDataException {
        try {
            connection();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        List<Curso> coleccion = new ArrayList();
        Curso curso = null;
        CallableStatement pstmt = null;
        try {
            pstmt = connection.prepareCall(SELECTALL);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                 curso = new Curso(
                        rs.getString("CODIGO"),
                        rs.getString("NOMBRE"),
                        rs.getInt("CREDITOS"),
                        rs.getInt("HORAS_SEMANALES")
                        );
                coleccion.add(curso);
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
        if (coleccion == null || coleccion.size() == 0) {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }
     
     public void delete(String codigo) throws GlobalException, NoDataException {
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
            pstmt.setString(1, codigo);

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
}