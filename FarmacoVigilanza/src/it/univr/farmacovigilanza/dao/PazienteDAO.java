/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univr.farmacovigilanza.dao;

import it.univr.farmacovigilanza.model.FattoreRischio;
import it.univr.farmacovigilanza.model.Paziente;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Francesco
 */
public interface PazienteDAO {
    
    public ObservableList<Paziente> getPazienti(int idMedico);
    public List<FattoreRischio> getFattoriRischio(int idPaziente);
    public List<FattoreRischio> getFattoriRischio();
    public int salvaPaziente(Paziente paziente, int idMedico);

}
