package it.univr.farmacovigilanza.controlview;

import it.univr.farmacovigilanza.dao.DAOFactory;
import it.univr.farmacovigilanza.dao.PazienteDAO;
import it.univr.farmacovigilanza.model.FattoreRischio;
import it.univr.farmacovigilanza.model.Medico;
import it.univr.farmacovigilanza.model.Paziente;
import it.univr.farmacovigilanza.model.Utente;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javafx.util.Pair;

public class InsertTerapyController implements Initializable{
    private Paziente paziente=null;
    private Medico medico=null;
    @FXML
    private ChoiceBox<Integer> sceltaPaziente;
    @FXML
    private TextField frequenza;
    @FXML
    private ChoiceBox<?> sceltaFarmaco;
    @FXML
    private TextField dose;
    @FXML
    private Label unitaMisura;
    @FXML
    private DatePicker dataInizio;
    @FXML
    private DatePicker dataFine;
    
    private final Pair<Medico,Paziente> data;
    
    public InsertTerapyController(Pair<Medico,Paziente> data){
        this.data = data;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DAOFactory test = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        PazienteDAO pazDao = test.getPazienteDAO();
        ObservableList<Paziente> mieiPazienti= pazDao.getPazienti(data.getKey().getIdUtente());
        ObservableList<Integer> idPazienti=FXCollections.observableArrayList();
        for(Paziente p: mieiPazienti){
            idPazienti.add(p.getId());
        }
        sceltaPaziente.setItems(idPazienti);
        if( data.getValue() != null){
            sceltaPaziente.setValue(data.getValue().getId());
        }
    }
    
}
