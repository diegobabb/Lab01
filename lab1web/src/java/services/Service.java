/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Diego Babb
 */
public class Service {
     protected static Connection connection;

    public Service() {
        connection = null;
    }

    protected static void connection() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "LAB1", "LAB111");
    }

    protected static void disconnect() throws SQLException {
        if (!connection.isClosed()) {
            connection.close();
        }
    }
}
