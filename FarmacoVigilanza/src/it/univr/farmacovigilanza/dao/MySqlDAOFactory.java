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
    
    @Override
    public SegnalazioneDAO getSegnalazioneDAO() {
        return new SegnalazioneDAOImpl();
    }

    @Override
    public TerapiaDAO getTerapiaDAO() {
        return new TerapiaDAOImpl();
    }

    @Override
    public FarmacoDAO getFarmacoDAO() {
        return new FarmacoDAOImpl();
    }
}
