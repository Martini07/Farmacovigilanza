/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.Farmacologo;
import it.univr.farmacovigilanza.model.Medico;
import it.univr.farmacovigilanza.model.Utente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francesco
 */
public class UserDAOImpl implements UserDAO {

    private static final String SEL_UTENTI= "SELECT * FROM UTENTE WHERE USERNAME = ? AND PASSWORD = ?";

    @Override
    public Utente getUtente(String username, String password) {
        try {
            PreparedStatement preparedStatement =  Connessione.getInstance().prepareStatement(SEL_UTENTI);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                if (Utente.TipoUtente.MEDICO.ordinal() == rs.getInt("tipoUtente")){
                    return new Medico(rs.getInt("idutente"), rs.getString("username"), rs.getString("nome"), rs.getString("cognome"), rs.getString("email"));
                } else { //Farmacologo
                    return new Farmacologo(rs.getInt("idutente"), rs.getString("username"), rs.getString("nome"), rs.getString("cognome"), rs.getString("email"));
                }
            }
        } catch (SQLException ex) {
             Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
