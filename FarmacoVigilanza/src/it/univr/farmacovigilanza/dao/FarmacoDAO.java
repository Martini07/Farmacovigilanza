package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.Farmaco;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Francesco
 */
public interface FarmacoDAO {
    
    public ObservableList<Farmaco> getFarmaci();
}
