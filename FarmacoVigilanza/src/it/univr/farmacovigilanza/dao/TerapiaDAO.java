package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.Terapia;
import java.util.List;

/**
 *
 * @author Francesco
 */
public interface TerapiaDAO {
    
    public List<Terapia> getTerapie(int idMedico);
    public boolean salvaTerapia(Terapia terapia);
}
