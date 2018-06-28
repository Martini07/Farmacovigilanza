/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univr.farmacovigilanza.controlview;

import it.univr.farmacovigilanza.model.Farmaco;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;


public class  FarmacoListener<String> implements ChangeListener{
    private ObservableList<Farmaco> farmaci;
    private Label udm;
    private InsertTerapyController controller;

    FarmacoListener(InsertTerapyController controller) {
        this.controller=controller;
        udm=controller.getUdm();
        farmaci=controller.getFarmaci();
    }
    
    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        int index = (int) newValue;
        Farmaco farmaco=farmaci.get(index);
        controller.setFarmaco(farmaco);
        udm.setText(farmaco.getUnitaMisura());
    }
    
}
