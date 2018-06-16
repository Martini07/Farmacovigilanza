package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.Utente;

/**
 *
 * @author Francesco
 */
public interface UserDAO {
    
    public Utente getUtente(String username, String password);
    
}
