package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.ReazioneAvversa;
import it.univr.farmacovigilanza.model.Segnalazione;
import it.univr.farmacovigilanza.model.Terapia;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Francesco
 */
public class SegnalazioneDAOImpl implements SegnalazioneDAO {

    private static final String SEL_REAZIONI_AVVERSE = "SELECT * FROM REAZIONE";
    private static final String INS_SEGNALAZIONE = "INSERT INTO SEGNALAZIONE(IDREAZIONE, DATA_REAZIONE,	DATA_SEGNALAZIONE) VALUES (?, ?, ?)";
    private static final String INS_SEGNALAZIONE_TERAPIA = "INSERT INTO REAZIONE_TERAPIA(IDTERAPIA, IDSEGNALAZIONE) VALUES (?, ?)";
    
    @Override
    public ObservableList<ReazioneAvversa> getReazioniAvverse() {
        ObservableList<ReazioneAvversa> reazioni =FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement =  Connessione.getInstance().prepareStatement(SEL_REAZIONI_AVVERSE);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                reazioni.add(new ReazioneAvversa(rs.getInt("IDREAZIONE"),
                        rs.getString("NOME"),
                        rs.getString("DESCRIZIONE"),
                        rs.getInt("LIVELLO_GRAVITA")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reazioni;
    }

    @Override
    public int salvaSegnalazione(Segnalazione segnalazione, int idPaziente) {
        int idSegnalazione = -1;
        try {
            PreparedStatement preparedStatement = Connessione.getInstance().prepareStatement(INS_SEGNALAZIONE, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, segnalazione.getReazioneAvversa().getId());
            preparedStatement.setDate(2, Date.valueOf(segnalazione.getDataReazione()));
            preparedStatement.setDate(3, Date.valueOf(segnalazione.getDataSegnalazione()));
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            idSegnalazione = rs.getInt(1);
            
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            List<Terapia> terapieAttive = daoFactory.getTerapiaDAO().getTerapie(idPaziente, segnalazione.getDataReazione());
            preparedStatement = Connessione.getInstance().prepareStatement(INS_SEGNALAZIONE_TERAPIA);
            for (Terapia terapia : terapieAttive) {
                 preparedStatement.setInt(1, terapia.getId());
                 preparedStatement.setInt(2, idSegnalazione);
                 preparedStatement.executeUpdate();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TerapiaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idSegnalazione;
    }
    
}
