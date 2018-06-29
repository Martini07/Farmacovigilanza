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
import java.time.LocalDate;

/**
 *
 * @author Francesco
 */
public class TerapiaDAOImpl implements TerapiaDAO {

    private static final String INS_TERAPIA = "INSERT INTO TERAPIA(IDFARMACO, DATA_INIZIO, DATA_FINE, DOSE, FREQUENZA, IDPAZIENTE) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SEL_TERAPIA_PAZIENTE = "SELECT * FROM TERAPIA WHERE IDPAZIENTE = ? AND DATA_INIZIO <= ? AND ? <= DATA_FINE";

    @Override
    public List<Terapia> getTerapie(int idPaziente, LocalDate data) {
        List<Terapia> terapie = new ArrayList();
        try {
            PreparedStatement preparedStatement = Connessione.getInstance().prepareStatement(SEL_TERAPIA_PAZIENTE);
            preparedStatement.setInt(1, idPaziente);
            preparedStatement.setDate(2, Date.valueOf(data));
            preparedStatement.setDate(3, Date.valueOf(data));
            ResultSet rs = preparedStatement.executeQuery();
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            FarmacoDAO farmacoDAO = daoFactory.getFarmacoDAO();
            while (rs.next()) {
                terapie.add(new Terapia(rs.getInt("IDTERAPIA"),
                        farmacoDAO.getFarmaco(rs.getInt("IDFARMACO")),
                        rs.getDate("DATA_INIZIO").toLocalDate(),
                        rs.getDate("DATA_FINE").toLocalDate(),
                        rs.getInt("DOSE"),
                        rs.getInt("FREQUENZA"),
                        null)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return terapie;
    }

    @Override
    public int salvaTerapia(Terapia terapia) {
        int result = -1;
        try {
            PreparedStatement preparedStatement = Connessione.getInstance().prepareStatement(INS_TERAPIA, PreparedStatement.RETURN_GENERATED_KEYS);
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
