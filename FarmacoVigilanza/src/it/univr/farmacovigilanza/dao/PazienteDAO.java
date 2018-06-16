/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.FattoreRischio;
import it.univr.farmacovigilanza.model.Paziente;
import java.util.List;

/**
 *
 * @author Francesco
 */
public interface PazienteDAO {
    
    public List<Paziente> getPazienti(int idMedico);
    public List<FattoreRischio> getFattoriRischio(int idPaziente);
    public List<FattoreRischio> getFattoriRischio();
    public boolean salvaPaziente(Paziente paziente, int idMedico);

}
