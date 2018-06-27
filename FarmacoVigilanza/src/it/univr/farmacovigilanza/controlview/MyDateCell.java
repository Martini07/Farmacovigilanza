/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univr.farmacovigilanza.controlview;

import java.time.LocalDate;
import javafx.scene.control.DateCell;


public class MyDateCell extends DateCell{
    private final LocalDate min;
    private final LocalDate max;
    MyDateCell(LocalDate min, LocalDate max){
        this.min=min;
        this.max=max;
    }
    @Override
    public void updateItem(LocalDate item, boolean empty) {
        super.updateItem(item, empty);
        
         if (min != null && item.isBefore(min)) {
            setDisable(true);
            setStyle("-fx-background-color: #ffc0cb;");
        }else if (item.isAfter(max)) {
            setDisable(true);
            setStyle("-fx-background-color: #ffc0cb;");
        }
    }
    
}
