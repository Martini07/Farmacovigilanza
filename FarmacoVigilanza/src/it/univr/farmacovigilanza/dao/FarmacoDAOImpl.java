package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.Farmaco;
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
public class FarmacoDAOImpl implements FarmacoDAO {

    private static final String SEL_FARMACI = "SELECT * FROM FARMACO";
    							
    @Override
    public List<Farmaco> getFarmaci() {
        List<Farmaco> farmaci = new ArrayList();
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
        
}
