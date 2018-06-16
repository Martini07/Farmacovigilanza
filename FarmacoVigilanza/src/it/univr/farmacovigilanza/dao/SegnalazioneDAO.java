package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.ReazioneAvversa;
import java.util.List;

/**
 *
 * @author Francesco
 */
public interface SegnalazioneDAO {
    
    public List<ReazioneAvversa> getReazioniAvverse();
    
}
