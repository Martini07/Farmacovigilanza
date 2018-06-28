package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.ReazioneAvversa;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Francesco
 */
public interface SegnalazioneDAO {
    
    public ObservableList<ReazioneAvversa> getReazioniAvverse();
    
}
