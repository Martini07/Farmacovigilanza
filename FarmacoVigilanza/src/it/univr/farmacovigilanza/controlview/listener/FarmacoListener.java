package it.univr.farmacovigilanza.controlview.listener;

import it.univr.farmacovigilanza.controlview.InsertTerapyController;
import it.univr.farmacovigilanza.model.Farmaco;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class FarmacoListener<String> implements ChangeListener{
    
    private final InsertTerapyController controller;

    public FarmacoListener(InsertTerapyController controller) {
        this.controller=controller;
    }
    
    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        if(newValue != null){
            int index = (int) newValue;
            Farmaco farmaco=controller.getFarmaci().get(index);
            controller.setFarmaco(farmaco);
            controller.getUdm().setText(farmaco.getUnitaMisura());
            controller.cambioFarmaco();
        }
    }
    
}
