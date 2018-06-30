package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.ReazioneAvversa;
import it.univr.farmacovigilanza.model.Segnalazione;
import javafx.collections.ObservableList;

/**
 *
 * @author Francesco
 */
public interface SegnalazioneDAO {
    
    public ObservableList<ReazioneAvversa> getReazioniAvverse();
    public int salvaSegnalazione(Segnalazione segnalazione, int idPaziente);
    public ObservableList<Segnalazione> getSegnalazioni(int idFarmacologo);
    
}
