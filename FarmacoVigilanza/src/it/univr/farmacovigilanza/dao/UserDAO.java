package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.Utente;
import it.univr.farmacovigilanza.model.Utente.TipoUtente;

/**
 *
 * @author Francesco
 */
public interface UserDAO {
    
    public Utente getUtente(String username, String password);
    public void creaUtente(int id, String username, String password, TipoUtente tipoUtente, String nome, String cognome, String email);

}
