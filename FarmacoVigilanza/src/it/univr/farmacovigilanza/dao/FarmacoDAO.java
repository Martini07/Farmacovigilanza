package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.Farmaco;
import it.univr.farmacovigilanza.model.Farmaco.Stato;
import javafx.collections.ObservableList;

/**
 *
 * @author Francesco
 */
public interface FarmacoDAO {
    
    public ObservableList<Farmaco> getFarmaci();
    public ObservableList<Farmaco> getFarmaci(int idFarmacologo);
    public Farmaco getFarmaco(int idFarmaco);
    public void aggiornaFarmaco(int idFarmaco, Stato stato);
    
}
