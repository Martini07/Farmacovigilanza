/* To change this license header, choose License Headers in Project Properties.
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Francesco
 */
public class PazienteDAOImpl implements PazienteDAO {

    private static final String SEL_PAZIENTE_MEDICO = "SELECT * FROM PAZIENTE WHERE IDMEDICO = ?";
    private static final String SEL_PAZIENTE = "SELECT * FROM PAZIENTE WHERE IDPAZIENTE = ?";
    private static final String SEL_FATTORI_RISCHIO_PAZIENTE = "SELECT F.* FROM RISCHIO_PAZIENTE R, FATTORE F "
            + "WHERE R.IDFATTORE = F.IDFATTORE AND IDPAZIENTE = ?";
    private static final String SEL_FATTORI_RISCHIO = "SELECT * FROM FATTORE";
    private static final String INS_PAZIENTE = "INSERT INTO PAZIENTE(ANNO_NASCITA, PROV_RESIDENZA, PROFESSIONE, IDMEDICO) VALUES(?, ?, ?, ?)";
    private static final String INS_FATT_PAZIENTE = "INSERT INTO RISCHIO_PAZIENTE(IDPAZIENTE, IDFATTORE) VALUES(?, ?)";

    @Override
    public ObservableList<Paziente> getPazienti(int idMedico) {
        ObservableList<Paziente> pazienti = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = Connessione.getInstance().prepareStatement(SEL_PAZIENTE_MEDICO);
            preparedStatement.setInt(1, idMedico);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
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
    public Paziente getPaziente(int idPaziente){
        Paziente paziente = null;
        try {
            PreparedStatement preparedStatement = Connessione.getInstance().prepareStatement(SEL_PAZIENTE);
            preparedStatement.setInt(1, idPaziente);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                paziente = new Paziente(rs.getInt("IDPAZIENTE"),
                        rs.getInt("ANNO_NASCITA"),
                        rs.getString("PROV_RESIDENZA"),
                        rs.getString("PROFESSIONE"),
                        getFattoriRischio(idPaziente));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return paziente;
    }
    @Override
    public List<FattoreRischio> getFattoriRischio(int idPaziente) {
        List<FattoreRischio> fattoriRischio = new ArrayList();
        try {
            PreparedStatement preparedStatement =  Connessione.getInstance().prepareStatement(SEL_FATTORI_RISCHIO_PAZIENTE);
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
            PreparedStatement preparedStatement =  Connessione.getInstance().prepareStatement(SEL_FATTORI_RISCHIO);
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
    public int salvaPaziente(Paziente paziente, int idMedico) {
        int result = -1;
        try {
            PreparedStatement preparedStatement =  Connessione.getInstance().prepareStatement(INS_PAZIENTE, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, paziente.getAnnoNascita());
            preparedStatement.setString(2, paziente.getProvinciaRes());
            preparedStatement.setString(3, paziente.getProfessione());
            preparedStatement.setInt(4, idMedico);
            preparedStatement.executeUpdate();
            
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                int idPaziente = rs.getInt(1);
                result = idPaziente;
                List<FattoreRischio> fattoriRischio = paziente.getFattoriRischio();
                preparedStatement =  Connessione.getInstance().prepareStatement(INS_FATT_PAZIENTE);
                for (FattoreRischio fattoreRischio : fattoriRischio) {
                    preparedStatement.setInt(1, fattoreRischio.getId());
                    preparedStatement.setInt(2, idPaziente);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
