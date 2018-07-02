package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.Farmaco;
import it.univr.farmacovigilanza.model.Farmaco.Stato;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Francesco
 */
public class FarmacoDAOImpl implements FarmacoDAO {

    private static final String SEL_FARMACI = "SELECT * FROM FARMACO WHERE STATO IS NULL OR STATO <> ?";
    private static final String SEL_FARMACO = "SELECT * FROM FARMACO WHERE IDFARMACO = ?";
    private static final String SEL_FARMACI_FARMACOLOGO = "SELECT * FROM FARMACO WHERE IDFARMACOLOGO = ?";
    private static final String UPD_FARMACO = "UPDATE FARMACO SET STATO = ? WHERE IDFARMACO = ?";
    
    @Override
    public ObservableList<Farmaco> getFarmaci() {
        ObservableList<Farmaco> farmaci = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = Connessione.getInstance().prepareStatement(SEL_FARMACI);
            preparedStatement.setInt(1, Stato.RITIRA.ordinal());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                farmaci.add(new Farmaco(rs.getInt("IDFARMACO"),
                        rs.getString("NOME"),
                        rs.getString("DESCRIZIONE"),
                        rs.getString("DITTA_PRODUTTRICE"),
                        rs.getString("COD_MINISTERIALE"),
                        rs.getString("PRINCIPIO_ATTIVO"),
                        rs.getInt("QUANTITA"),
                        rs.getString("UNITA_MISURA"),
                        Farmaco.Stato.values()[rs.getInt("STATO")]));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmacoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return farmaci;
    }
    
    @Override
    public ObservableList<Farmaco> getFarmaci(int idFarmacologo) {
        ObservableList<Farmaco> farmaci = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = Connessione.getInstance().prepareStatement(SEL_FARMACI_FARMACOLOGO);
            preparedStatement.setInt(1, idFarmacologo);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                farmaci.add(new Farmaco(rs.getInt("IDFARMACO"),
                        rs.getString("NOME"),
                        rs.getString("DESCRIZIONE"),
                        rs.getString("DITTA_PRODUTTRICE"),
                        rs.getString("COD_MINISTERIALE"),
                        rs.getString("PRINCIPIO_ATTIVO"),
                        rs.getInt("QUANTITA"),
                        rs.getString("UNITA_MISURA"),
                        Farmaco.Stato.values()[rs.getInt("STATO")]));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmacoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return farmaci;
    }
    
    @Override
    public Farmaco getFarmaco(int idFarmaco) {
        Farmaco farmaco = null;
        try {
            PreparedStatement preparedStatement = Connessione.getInstance().prepareStatement(SEL_FARMACO);
            preparedStatement.setInt(1, idFarmaco);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                farmaco = new Farmaco(rs.getInt("IDFARMACO"),
                        rs.getString("NOME"),
                        rs.getString("DESCRIZIONE"),
                        rs.getString("DITTA_PRODUTTRICE"),
                        rs.getString("COD_MINISTERIALE"),
                        rs.getString("PRINCIPIO_ATTIVO"),
                        rs.getInt("QUANTITA"),
                        rs.getString("UNITA_MISURA"),
                        Farmaco.Stato.values()[rs.getInt("STATO")]);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmacoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return farmaco;
    }

    @Override
    public void aggiornaFarmaco(int idFarmaco, Stato stato) {
        try {
            PreparedStatement preparedStatement = Connessione.getInstance().prepareStatement(UPD_FARMACO);
            preparedStatement.setInt(1, stato.ordinal());
            preparedStatement.setInt(2, idFarmaco);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FarmacoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
