package it.univr.farmacovigilanza.controlview;

import it.univr.farmacovigilanza.dao.DAOFactory;
import it.univr.farmacovigilanza.dao.PazienteDAO;
import it.univr.farmacovigilanza.model.Medico;
import it.univr.farmacovigilanza.model.Paziente;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Pair;

public class InsertTerapyController implements Initializable{
    private Paziente paziente=null;
    private Medico medico=null;
    @FXML
    private ChoiceBox<Integer> sceltaPaziente;
    @FXML
    private TextField frequenza;
    @FXML
    private ChoiceBox<String> sceltaFarmaco;
    @FXML
    private TextField dose;
    @FXML
    private Label unitaMisura;
    @FXML
    private DatePicker dataInizio;
    @FXML
    private DatePicker dataFine;
    
    private DAOFactory test;
    private PazienteDAO pazDao;
    
    public InsertTerapyController(Pair<Medico,Paziente> data){
        medico = data.getKey();
        paziente = data.getValue();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        test = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        pazDao = test.getPazienteDAO();
        ObservableList<Paziente> mieiPazienti= pazDao.getPazienti(medico.getIdUtente());
        ObservableList<Integer> idPazienti=FXCollections.observableArrayList();
        for(Paziente p: mieiPazienti){
            idPazienti.add(p.getId());
        }
        sceltaPaziente.setItems(idPazienti);
        if( paziente != null){
            sceltaPaziente.setValue(paziente.getId());
            //dataInizioe
        }
        //inserimento farmaci
        sceltaFarmaco.getItems().add("Farmaco 1");
        sceltaFarmaco.getItems().add("Farmaco 2");
        
        sceltaFarmaco.getSelectionModel().selectedIndexProperty().addListener(new FarmacoListener<>(unitaMisura));
        sceltaPaziente.getSelectionModel().selectedIndexProperty().addListener(new PazienteListener<>(this,pazDao));
        setDateFactory(dataInizio,getDataNascita(),LocalDate.now());
        setDateFactory(dataFine,dataInizio.getValue(),LocalDate.now());
    }

    public void setPaziente(Paziente paziente){
        this.paziente=paziente;
    }
    
    public LocalDate getDataNascita(){
        if(paziente != null){
            System.out.println(paziente.getAnnoNascita());
            return LocalDate.of(paziente.getAnnoNascita(), 1, 1);
        }
        return null;
    }
    
    public void setDateFactory(DatePicker datePicker,LocalDate start,LocalDate end){
        CallBack<DatePicker, DateCell> dayCellFactory = new CallBack<>(start,end);
        datePicker.setDayCellFactory(dayCellFactory);
    }
    
    public DatePicker getInizio(){ return dataInizio;}
    public DatePicker getFine(){ return dataFine;}

    
    @FXML
    private void dataInizio(ActionEvent event) {
        setDateFactory(dataFine,dataInizio.getValue(),LocalDate.now());
        if(dataFine.isDisable()){
            dataFine.setDisable(false);
        }
    }
}
