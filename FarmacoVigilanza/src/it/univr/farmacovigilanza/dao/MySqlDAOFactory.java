/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univr.farmacovigilanza.dao;

/**
 *
 * @author Francesco
 */
public class MySqlDAOFactory extends DAOFactory {
    
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://db4free.net:3306/farmacovigilanza";
    public static final String USERNAME = "farmacovigilanza";
    public static final String PASSWORD = "farmacovigilanza";

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }
    
    @Override
    public PazienteDAO getPazienteDAO() {
        return new PazienteDAOImpl();
    }
    
    
}
