package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.ReazioneAvversa;
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
public class SegnalazioneDAOImpl extends DAOImpl implements SegnalazioneDAO {

    private static final String SEL_REAZIONI_AVVERSE = "SELECT * FROM REAZIONE";
    
    @Override
    public List<ReazioneAvversa> getReazioniAvverse() {
        List<ReazioneAvversa> reazioni = new ArrayList();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(SEL_REAZIONI_AVVERSE);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
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
    
}
