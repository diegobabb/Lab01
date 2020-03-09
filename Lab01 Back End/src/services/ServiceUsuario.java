/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import exceptions.GlobalException;
import exceptions.NoDataException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Usuario;
import oracle.jdbc.internal.OracleTypes;
import static services.Service.connection;

/**
 *
 * @author diego
 */
public class ServiceUsuario extends Service {

    private static final String FIND = "{?=call BUSCAR_USUARIO(?,?)}";

    public Usuario find(String usuario, String contrasena) throws GlobalException, NoDataException {
        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        CallableStatement pstmt = null;
        Usuario user = null;
        try {
            pstmt = connection.prepareCall(FIND);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, usuario);
            pstmt.setString(3, contrasena);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            if (rs.next()) {
                user = Usuario.login(
                        rs.getString("CEDULA"));
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
        if (user == null) {
            throw new NoDataException("No se encontro al usuario");
        }
        return user;
    }
}
