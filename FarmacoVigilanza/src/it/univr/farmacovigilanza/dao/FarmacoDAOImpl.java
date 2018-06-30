package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.Farmaco;
import it.univr.farmacovigilanza.model.FarmacoItem;
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
public class FarmacoDAOImpl implements FarmacoDAO {

    private static final String SEL_FARMACI = "SELECT * FROM FARMACO";
    private static final String SEL_FARMACO = "SELECT * FROM FARMACO WHERE IDFARMACO = ?";
    private static final String SEL_CATALOGO = "SELECT * FROM CATALOGO C, FARMACO F WHERE C.IDFARMACO = F.IDFARMACO AND C.IDFARMACOLOGO = ?";
    
    @Override
    public ObservableList<Farmaco> getFarmaci() {
        ObservableList<Farmaco> farmaci = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = Connessione.getInstance().prepareStatement(SEL_FARMACI);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                farmaci.add(new Farmaco(rs.getInt("IDFARMACO"),
                        rs.getString("NOME"),
                        rs.getString("DESCRIZIONE"),
                        rs.getString("DITTA_PRODUTTRICE"),
                        rs.getString("COD_MINISTERIALE"),
                        rs.getString("PRINCIPIO_ATTIVO"),
                        rs.getInt("QUANTITA"),
                        rs.getString("UNITA_MISURA")));
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
                        rs.getString("UNITA_MISURA"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmacoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return farmaco;
    }
    
    @Override
    public List<FarmacoItem> getFarmaci(int idFarmacologo) {
        List<FarmacoItem> farmaci = new ArrayList();
        try {
            PreparedStatement preparedStatement =  Connessione.getInstance().prepareStatement(SEL_CATALOGO);
            preparedStatement.setInt(1, idFarmacologo);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                farmaci.add(new FarmacoItem(rs.getInt("IDFARMACO"),
                        rs.getString("NOME"),
                        rs.getString("DESCRIZIONE"),
                        rs.getString("DITTA_PRODUTTRICE"),
                        rs.getString("COD_MINISTERIALE"),
                        rs.getString("PRINCIPIO_ATTIVO"),
                        rs.getInt("QUANTITA"),
                        rs.getString("UNITA_MISURA"),
                        rs.getString("BARCODE"),
                        rs.getDate("DATA_SCADENZA").toLocalDate(),
                        rs.getDate("DATA_PRODUZIONE").toLocalDate(),
                        null));
            }			
        } catch (SQLException ex) {
            Logger.getLogger(SegnalazioneDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return farmaci;
    }
        
}
