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
    private static final String INS_UTENTI= "INSERT INTO UTENTE(IDUTENTE, USERNAME, PASSWORD, TIPOUTENTE, NOME, COGNOME, EMAIL) VALUES(?, ?, ?, ?, ?, ?, ?)";

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

    @Override
    public void creaUtente(int id, String username, String password, Utente.TipoUtente tipoUtente, String nome, String cognome, String email) {
        try {
            PreparedStatement preparedStatement =  Connessione.getInstance().prepareStatement(INS_UTENTI, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4, tipoUtente.ordinal());
            preparedStatement.setString(5, nome);
            preparedStatement.setString(6, cognome);
            preparedStatement.setString(7, email);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
