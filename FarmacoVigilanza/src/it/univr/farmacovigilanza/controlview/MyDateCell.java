package it.univr.farmacovigilanza.controlview;

import java.time.LocalDate;
import javafx.scene.control.DateCell;


public class MyDateCell extends DateCell{
    private final LocalDate min;
    private final LocalDate max;
    
    public MyDateCell(LocalDate min, LocalDate max){
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
