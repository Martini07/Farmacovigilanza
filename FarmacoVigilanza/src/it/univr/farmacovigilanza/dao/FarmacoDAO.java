package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.Farmaco;
import java.util.List;

/**
 *
 * @author Francesco
 */
public interface FarmacoDAO {
    
    public List<Farmaco> getFarmaci();
}
