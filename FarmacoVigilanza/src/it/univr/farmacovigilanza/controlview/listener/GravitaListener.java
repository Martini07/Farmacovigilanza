
package it.univr.farmacovigilanza.controlview.listener;

import it.univr.farmacovigilanza.controlview.FXMLDocumentController;
import it.univr.farmacovigilanza.model.Farmaco;
import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class GravitaListener <Integer> implements ChangeListener{
    
    private final FXMLDocumentController controller;
    
    public GravitaListener(FXMLDocumentController controller){
        this.controller=controller;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        if(newValue!=null && (int) newValue != -1){
            int gravità = (int) newValue;
            gravità++;
            String nomeFarmaco =controller.getFiltraFarmaco().getValue();
            if(nomeFarmaco!=null){
                Farmaco farmaco = controller.getFarmaco(nomeFarmaco);
                LocalDate dataInizio = controller.getDataInizio().getValue();
                LocalDate dataFine = controller.getDataFine().getValue();
                int numeroRisultatiQuery=0;
                if(dataInizio == null){
                //query senza date
                }else{
                    if(dataFine == null){
                        dataFine = LocalDate.now();
                    }
                    //query con date
                }
            }
        }
    }
}
