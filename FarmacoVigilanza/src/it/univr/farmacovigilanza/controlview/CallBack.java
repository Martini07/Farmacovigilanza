/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univr.farmacovigilanza.controlview;

import java.time.LocalDate;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;


public class CallBack<DataPicker,DateCell> implements Callback{
    
    private final LocalDate min;
    private final LocalDate max;
    
    CallBack(LocalDate min, LocalDate max){
        this.min=min;
        this.max=max;
    }
    @Override
    public Object call(Object param) {
        DatePicker datePicker = (DatePicker) param;
        return new MyDateCell(min,max);
    }
    
}
