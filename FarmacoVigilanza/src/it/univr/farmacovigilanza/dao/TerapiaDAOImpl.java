package it.univr.farmacovigilanza.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import it.univr.farmacovigilanza.model.Terapia;

/**
 *
 * @author Francesco
 */
public class TerapiaDAOImpl extends DAOImpl implements TerapiaDAO {
    
    private static final String INS_TERAPIA = "INSERT INTO PAZIENTE(ANNO_NASCITA, PROV_RESIDENZA, PROFESSIONE, IDMEDICO) VALUES(?, ?, ?, ?)";
    private static final String SEL_TERAPIA_MEDICO = "INSERT INTO TERAPIA(IDFARMACO, DATA_INIZIO, DATA_FINE, DOSE, FREQUENZA, IDPAZIENTE) VALUES(?, ?, ?, ?, ?, ?)";

    @Override
    public List<Terapia> getTerapie(int idMedico) {
         List<Terapia> fattoriRischio = new ArrayList();
         return fattoriRischio;
    }

    @Override
    public boolean salvaTerapia(Terapia terapia) {
        boolean result = true;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(INS_TERAPIA);
            preparedStatement.setInt(1, terapia.getFarmaco().getId());
            preparedStatement.setDate(2, Date.valueOf(terapia.getDataInizio()));
            preparedStatement.setDate(3, Date.valueOf(terapia.getDataFine()));
            preparedStatement.setInt(4, terapia.getDose());
            preparedStatement.setInt(5, terapia.getFrequenzaGiornaliera());
            preparedStatement.setInt(6, terapia.getPaziente().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            result = false;
            Logger.getLogger(TerapiaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}

