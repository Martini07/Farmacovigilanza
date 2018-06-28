package it.univr.farmacovigilanza.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import it.univr.farmacovigilanza.model.Terapia;
import java.sql.ResultSet;

/**
 *
 * @author Francesco
 */
public class TerapiaDAOImpl implements TerapiaDAO {
    
    private static final String INS_TERAPIA = "INSERT INTO TERAPIA(IDFARMACO, DATA_INIZIO, DATA_FINE, DOSE, FREQUENZA, IDPAZIENTE) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SEL_TERAPIA_MEDICO = "";

    @Override
    public List<Terapia> getTerapie(int idMedico) {
         List<Terapia> fattoriRischio = new ArrayList();
         return fattoriRischio;
    }

    @Override
    public int salvaTerapia(Terapia terapia) {
        int result = -1;
        try {
            PreparedStatement preparedStatement =  Connessione.getInstance().prepareStatement(INS_TERAPIA, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, terapia.getFarmaco().getId());
            preparedStatement.setDate(2, Date.valueOf(terapia.getDataInizio()));
            preparedStatement.setDate(3, Date.valueOf(terapia.getDataFine()));
            preparedStatement.setInt(4, terapia.getDose());
            preparedStatement.setInt(5, terapia.getFrequenzaGiornaliera());
            preparedStatement.setInt(6, terapia.getPaziente().getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            result = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(TerapiaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}

