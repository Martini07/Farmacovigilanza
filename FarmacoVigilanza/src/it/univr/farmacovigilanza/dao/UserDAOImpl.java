/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.Utente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francesco
 */
public class UserDAOImpl implements UserDAO {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(MySqlDAOFactory.DRIVER);
            connection = DriverManager.getConnection(MySqlDAOFactory.URL, MySqlDAOFactory.USERNAME, MySqlDAOFactory.PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    @Override
    public Utente getUtente(String username, String password) {
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM UTENTE WHERE username='" + username + "'");
            if (rs.next()) {
                Utente u = new Utente(rs.getInt("idutente"), rs.getString("username"));
                return u;
                //if (utente.pwd == password)
                //    return utente;
                //else 
                //    return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

}
