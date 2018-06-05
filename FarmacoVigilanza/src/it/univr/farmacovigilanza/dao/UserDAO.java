/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.Utente;

/**
 *
 * @author Francesco
 */
public interface UserDAO {
    
    public Utente getUtente(String username, String password);
    
}
