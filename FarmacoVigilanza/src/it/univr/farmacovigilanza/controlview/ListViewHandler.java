package it.univr.farmacovigilanza.controlview;

import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class ListViewHandler implements EventHandler<MouseEvent>{
    
    private ListView<String> l;
    private FXMLDocumentController t;
    
    protected ListViewHandler(ListView<String> l, FXMLDocumentController t){
        this.l=l;
        this.t=t;
    }
    
    @Override
    public void handle(MouseEvent e){
        t.enableSegnalazione(l.getSelectionModel().getSelectedItem());
    }
}
