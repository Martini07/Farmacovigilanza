/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univr.farmacovigilanza.controlview;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;


public class  FarmacoListener<String> implements ChangeListener{
    private int i=1;
    private Label udm;

    FarmacoListener(Label udm) {
        this.udm=udm;
    }
    
    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        String farmaco = (String) newValue;
        udm.setText("changed "+i++);
    }
    
}
