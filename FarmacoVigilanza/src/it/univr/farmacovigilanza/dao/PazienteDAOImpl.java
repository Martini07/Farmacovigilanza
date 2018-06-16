/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.FattoreRischio;
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

    private static final String SEL_PAZIENTE_MEDICO = "SELECT * FROM PAZIENTE WHERE IDMEDICO = ?";
    private static final String SEL_FATTORI_RISCHIO_PAZIENTE = "SELECT F.* FROM RISCHIO_PAZIENTE R, FATTORE F "
            + "WHERE R.IDFATTORE = F.IDFATTORE AND IDPAZIENTE = ?";
    private static final String SEL_FATTORI_RISCHIO = "SELECT * FROM FATTORE";
    private static final String INS_PAZIENTE = "INSERT INTO PAZIENTE(ANNO_NASCITA, PROV_RESIDENZA, PROFESSIONE, IDMEDICO) VALUES(?, ?, ?, ?)";

    @Override
    public List<Paziente> getPazienti(int idMedico) {
        List<Paziente> pazienti = new ArrayList();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(SEL_PAZIENTE_MEDICO);
            preparedStatement.setInt(1, idMedico);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int idPaziente = rs.getInt("IDPAZIENTE");
                pazienti.add(new Paziente(rs.getInt("IDPAZIENTE"),
                        rs.getInt("ANNO_NASCITA"),
                        rs.getString("PROV_RESIDENZA"),
                        rs.getString("PROFESSIONE"),
                        getFattoriRischio(idPaziente)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pazienti;
    }
    @Override
    public List<FattoreRischio> getFattoriRischio(int idPaziente) {
        List<FattoreRischio> fattoriRischio = new ArrayList();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(SEL_FATTORI_RISCHIO_PAZIENTE);
            preparedStatement.setInt(1, idPaziente);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                fattoriRischio.add(new FattoreRischio(rs.getInt("IDATTORE"),
                        rs.getString("NOME"),
                        rs.getString("DESCRIZIONE"),
                        rs.getInt("LIVELLO")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fattoriRischio;
    }
    
    @Override
    public List<FattoreRischio> getFattoriRischio() {
        List<FattoreRischio> fattoriRischio = new ArrayList();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(SEL_FATTORI_RISCHIO);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                fattoriRischio.add(new FattoreRischio(rs.getInt("IDATTORE"),
                        rs.getString("NOME"),
                        rs.getString("DESCRIZIONE"),
                        rs.getInt("LIVELLO")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fattoriRischio;
    }

    @Override
    public boolean salvaPaziente(Paziente paziente, int idMedico) {
        boolean result = true;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(INS_PAZIENTE);
            preparedStatement.setInt(1, paziente.getAnnoNascita());
            preparedStatement.setString(2, paziente.getProvinciaRes());
            preparedStatement.setString(3, paziente.getProfessione());
            preparedStatement.setInt(4, idMedico);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            result = false;
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
