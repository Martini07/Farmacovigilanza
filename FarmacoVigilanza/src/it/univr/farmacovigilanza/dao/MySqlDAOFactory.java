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
    public static final String URL = "jdbc:mysql://sql2.freesqldatabase.com:3306/sql2241488";
    public static final String USERNAME = "sql2241488";
    public static final String PASSWORD = "nH2!bI9!";

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }
    
}
