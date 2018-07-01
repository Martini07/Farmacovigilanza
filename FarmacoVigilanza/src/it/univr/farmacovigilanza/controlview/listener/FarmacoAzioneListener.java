package it.univr.farmacovigilanza.controlview.listener;

import it.univr.farmacovigilanza.controlview.FarmacovigilanzaController;
import it.univr.farmacovigilanza.model.Farmaco;
import it.univr.farmacovigilanza.model.Farmacologo;
import it.univr.farmacovigilanza.model.Segnalazione;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class FarmacoAzioneListener<Integer> implements ChangeListener {
    
    private final FarmacovigilanzaController controller;

    public FarmacoAzioneListener(FarmacovigilanzaController controller) {
        this.controller=controller;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        if(newValue!=null && (int) newValue!=-1){
            Segnalazione segnalazioneSelezionata = ((Farmacologo) FarmacovigilanzaController.getUtente()).getSegnalazioni().get((int) newValue);
            Farmaco farmacoSelezionato = segnalazioneSelezionata.getFarmaco();
            controller.setIdFarmacoSelezionato(farmacoSelezionato.getId());
            controller.getStatoFarmaco().setVisible(true);
            controller.getFarmacoSelezionato().setVisible(true);
            controller.getFarmacoSelezionato().setText("Farmaco: " + farmacoSelezionato.getNome() + " " + farmacoSelezionato.getQuantita() + farmacoSelezionato.getUnitaMisura());
            controller.getApplica().setVisible(true);
        }
    }
}
