package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.Terapia;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Francesco
 */
public interface TerapiaDAO {
    
    public List<Terapia> getTerapie(int idPaziente, LocalDate dataInizio, LocalDate dataFine);
    public int salvaTerapia(Terapia terapia);
    
    
}
