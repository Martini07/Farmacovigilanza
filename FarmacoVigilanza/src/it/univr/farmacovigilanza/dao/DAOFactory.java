package it.univr.farmacovigilanza.dao;

/**
 *
 * @author Francesco
 */
public abstract class DAOFactory {
    
  // List of DAO types supported by the factory
  public static final int MYSQL = 1;

  // There will be a method for each DAO that can be 
  // created. The concrete factories will have to 
  // implement these methods.
  public abstract UserDAO getUserDAO();
  public abstract PazienteDAO getPazienteDAO();
  public abstract SegnalazioneDAO getSegnalazioneDAO();
  public abstract TerapiaDAO getTerapiaDAO();
  public abstract FarmacoDAO getFarmacoDAO();

  public static DAOFactory getDAOFactory(int factory) {
  
    switch (factory) {
      case MYSQL: 
          return new MySqlDAOFactory();
      default: 
          return null;
    }
  }
}
