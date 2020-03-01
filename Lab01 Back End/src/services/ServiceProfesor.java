
package services;

import exceptions.*;
import models.Profesor;
import oracle.jdbc.internal.OracleTypes;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class ServiceProfesor extends Service{
    private static final String INSERT = "{call INSERTAR_PROFESOR(?,?,?,?)}";
    private static final String UPDATE = "{call MODIFICAR_PROFESOR(?,?,?,?)}";
    private static final String FIND = "{?=call BUSCAR_PROFESOR(?)}";
    private static final String SELECTALL = "{?=call LISTAR_PROFESOR()}";
    private static final String DELETE = "{call ELIMINAR_PROFESOR(?)}";

    public ServiceProfesor() {
    }

    /**
     * 
     * @param profesor, se inserta en la base de datos
     * @throws GlobalException
     * @throws NoDataException 
     */
    public void insert(Profesor profesor) throws GlobalException, NoDataException {
        try {
            connection();
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        CallableStatement pstmt = null;

        try {
            pstmt = connection.prepareCall(INSERT);
            pstmt.setString(1, profesor.getCedula());
            pstmt.setString(2, profesor.getNombre());
            pstmt.setString(3, profesor.getTelefono());
            pstmt.setString(4, profesor.getEmail());
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

    /**
     * 
     * @param profesor, actualiza todos los datos del profesor en la base de datos
     * @throws GlobalException
     * @throws NoDataException 
     */
    public void update(Profesor profesor) throws GlobalException, NoDataException {
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
            pstmt.setString(1, profesor.getCedula());
            pstmt.setString(2, profesor.getNombre());
            pstmt.setString(3, profesor.getTelefono());
            pstmt.setString(4, profesor.getEmail());
            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new NoDataException("No se realizo la actualización");
            } else {
                System.out.println("\nModificación Satisfactoria!");
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

    /**
     * 
     * @param cedula, cedula del profesor que se necesita encontrar los datos en la base de datos
     * @return Profesor, con todos los datos del profesor con esa cedula
     * @throws GlobalException
     * @throws NoDataException 
     */
    public Profesor find(String cedula) throws GlobalException, NoDataException {

        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Profesor profesor = null;
        CallableStatement pstmt = null;
        try {
            pstmt = connection.prepareCall(FIND);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, cedula);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                profesor = new Profesor(rs.getString("CEDULA"),
                        rs.getString("NOMBRE"),
                        rs.getString("TELEFONO"),
                        rs.getString("EMAIL"));
                coleccion.add(profesor);
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
        return profesor;
    }

    /**
     * 
     * @return ArrayList con todos los profesores en la base de datos 
     * @throws GlobalException
     * @throws NoDataException 
     */
    public ArrayList selectAll() throws GlobalException, NoDataException {
        try {
            connection();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Profesor elProfesor = null;
        CallableStatement pstmt = null;
        try {
            pstmt = connection.prepareCall(SELECTALL);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                elProfesor = new Profesor(rs.getString("CEDULA"),
                        rs.getString("NOMBRE"),
                        rs.getString("TELEFONO"),
                        rs.getString("EMAIL"));
                coleccion.add(elProfesor);
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

    /**
     * 
     * @param cedula, del Profesor que se desea eliminar en la base de datos
     * @throws GlobalException
     * @throws NoDataException 
     */
    public void delete(String cedula) throws GlobalException, NoDataException {
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
            pstmt.setString(1, cedula);

            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new NoDataException("No se realizo el borrado");
            } else {
                System.out.println("\nEliminación Satisfactoria!");
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
