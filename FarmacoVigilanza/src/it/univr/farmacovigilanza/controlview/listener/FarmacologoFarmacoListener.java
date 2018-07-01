
package it.univr.farmacovigilanza.controlview.listener;

import it.univr.farmacovigilanza.controlview.FXMLDocumentController;
import it.univr.farmacovigilanza.model.Farmaco;
import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class FarmacologoFarmacoListener<String> implements ChangeListener{
    private final FXMLDocumentController controller;
    
    public FarmacologoFarmacoListener(FXMLDocumentController controller){
        this.controller=controller;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        if(newValue!=null && (int) newValue != -1){
            int index = (int) newValue;
            Farmaco farmaco = controller.getFarmaci().get(index);
            int gravita=-1;
            if(controller.getGravita().getValue() != null){
                gravita = controller.getGravita().getValue();
            }
            LocalDate dataInizio = controller.getDataInizio().getValue();
            LocalDate dataFine = controller.getDataFine().getValue();
            int numeroRisultatiQuery=0;
            if(gravita == -1 ) gravita = 0;
            gravita++;
            if(dataInizio == null){
                //query senza date
            }else{
                if(dataFine == null){
                    dataFine = LocalDate.now();
                }
                //query con date
            }
            controller.setRisultatiQuery("Totale: "+numeroRisultatiQuery);
        }
    }
    
    
}
