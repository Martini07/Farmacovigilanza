package it.univr.farmacovigilanza.controlview.listener;

import it.univr.farmacovigilanza.controlview.InserimentoTerapiaController;
import it.univr.farmacovigilanza.model.Paziente;
import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;


public class PazienteTerapiaListener<Integer> implements ChangeListener{
    
    private final InserimentoTerapiaController controller;

    public PazienteTerapiaListener(InserimentoTerapiaController controller) {
        this.controller=controller;
    }
    
    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        if(newValue!=null && (int) newValue!=-1){
            int idPaziente = controller.comboBoxGet((int) newValue);
            ObservableList<Paziente> pazienti = controller.getMedico().getPazienti();
            Paziente pazienteSel = null;
            for (Paziente paziente : pazienti) {
                if (paziente.getId() == idPaziente){
                    pazienteSel = paziente;
                    break;
                }
            }
            controller.setPaziente(pazienteSel);
            controller.setDateFactory(controller.getInizio(), controller.getDataNascita(), LocalDate.now());
            controller.setDateFactory(controller.getFine(),  controller.getDataNascita(), LocalDate.now());
            controller.getInizio().setValue(null);
            controller.getFine().setValue(null);
            controller.getFine().setDisable(true);
            controller.clean();
        }
    }
    
}
