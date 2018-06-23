/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univr.farmacovigilanza.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francesco
 */
public abstract class Connessione {

    private static Connection instance = null;

    public static Connection getInstance() {
        if (instance == null) {
            instance = getConnection(); 
        }
        return instance;
    }

    private static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(MySqlDAOFactory.DRIVER);
            connection = DriverManager.getConnection(MySqlDAOFactory.URL, MySqlDAOFactory.USERNAME, MySqlDAOFactory.PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

}
