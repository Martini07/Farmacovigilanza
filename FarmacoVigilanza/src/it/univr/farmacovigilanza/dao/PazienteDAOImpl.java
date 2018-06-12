/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.Paziente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francesco
 */
public class PazienteDAOImpl extends DAOImpl implements PazienteDAO {

    private static final String SEL_PAZIENTE_NOME_COGNOME = "SELECT * FROM PAZIENTE WHERE NOME = ? AND COGNOME = ?";
    private static final String INS_PAZIENTE = "INSERT INTO PAZIENTE(NOME, COGNOME, ANNO_NASCITA, PROV_RESIDENZA, PROFESSIONE) VALUES(?, ?, ?, ?, ?)";

    @Override
    public List<Paziente> getPazienti(String nome, String cognome) {
        List<Paziente> pazienti = new ArrayList();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(SEL_PAZIENTE_NOME_COGNOME);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, cognome);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                pazienti.add(new Paziente(rs.getInt("IDPAZIENTE"),
                        rs.getString("NOME"),
                        rs.getString("COGNOME"),
                        rs.getInt("ANNO_NASCITA"),
                        rs.getString("PROV_RESIDENZA"),
                        rs.getString("PROFESSIONE"),
                        null));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pazienti;
    }

    @Override
    public boolean salvaPaziente(Paziente paziente) {
        boolean result = true;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(INS_PAZIENTE);
            preparedStatement.setString(1, paziente.getNome());
            preparedStatement.setString(2, paziente.getCognome());
            preparedStatement.setInt(3, paziente.getAnnoNascita());
            preparedStatement.setString(4, paziente.getProvinciaRes());
            preparedStatement.setString(5, paziente.getProfessione());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            result = false;
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
