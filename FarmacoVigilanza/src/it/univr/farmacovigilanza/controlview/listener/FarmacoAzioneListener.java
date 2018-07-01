package it.univr.farmacovigilanza.controlview.listener;

import it.univr.farmacovigilanza.controlview.FXMLDocumentController;
import it.univr.farmacovigilanza.model.Farmacologo;
import it.univr.farmacovigilanza.model.Segnalazione;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class FarmacoAzioneListener<Integer> implements ChangeListener {
    
    private final FXMLDocumentController controller;

    public FarmacoAzioneListener(FXMLDocumentController controller) {
        this.controller=controller;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        if(newValue!=null && (int) newValue!=-1){
            Segnalazione segnalazioneSelezionata = ((Farmacologo) controller.getUtente()).getSegnalazioni().get((int) newValue);
            if (segnalazioneSelezionata.getFarmaco().getStato() == null) {
                controller.getStatoFarmaco().setVisible(true);
                controller.getFarmacoSelezionato().setVisible(true);
                controller.getFarmacoSelezionato().setText("Farmaco: ".concat(segnalazioneSelezionata.getFarmaco().toString()));
                controller.getApplica().setVisible(true);
            }
        }
    }
}
