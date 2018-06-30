package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.Farmaco;
import it.univr.farmacovigilanza.model.FarmacoItem;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Francesco
 */
public interface FarmacoDAO {
    
    public ObservableList<Farmaco> getFarmaci();
    public Farmaco getFarmaco(int idFarmaco);
    public List<FarmacoItem> getFarmaci(int idFarmacologo);
    
}
