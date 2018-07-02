package it.univr.farmacovigilanza.controlview;

import it.univr.farmacovigilanza.controlview.listener.FarmacoListener;
import it.univr.farmacovigilanza.controlview.listener.PazienteTerapiaListener;
import it.univr.farmacovigilanza.dao.DAOFactory;
import it.univr.farmacovigilanza.model.Farmaco;
import it.univr.farmacovigilanza.model.Medico;
import it.univr.farmacovigilanza.model.Paziente;
import it.univr.farmacovigilanza.model.Terapia;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Pair;

public class InserimentoTerapiaController implements Initializable{
    
    private Paziente paziente=null;
    private Medico medico=null;
    @FXML
    private ComboBox<Integer> sceltaPaziente;
    @FXML
    private TextField frequenza;
    @FXML
    private ComboBox<String> sceltaFarmaco;
    @FXML
    private TextField dose;
    @FXML
    private Label unitaMisura;
    @FXML
    private DatePicker dataInizio;
    @FXML
    private DatePicker dataFine;
    
    private DAOFactory daoFactory;
    private ObservableList<Farmaco> farmaci;
    private Farmaco farmaco=null;
    private int doseValue,frequenzaValue=-1;
    
    public InserimentoTerapiaController(Pair<Medico,Paziente> data){
        medico = data.getKey();
        paziente = data.getValue();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ObservableList<Paziente> mieiPazienti = medico.getPazienti();
        ObservableList<Integer> idPazienti=FXCollections.observableArrayList();
        for(Paziente p: mieiPazienti){
            idPazienti.add(p.getId());
        }
        sceltaPaziente.setItems(idPazienti);
        if( paziente != null){
            sceltaPaziente.setValue(paziente.getId());
            setDateFactory(dataInizio,getDataNascita(),LocalDate.now());
            setDateFactory(dataFine,dataInizio.getValue(),null);
        }
        //inserimento farmaci
        farmaci = daoFactory.getFarmacoDAO().getFarmaci();
        ObservableList<String> nomeFarmaci=FXCollections.observableArrayList();
        for(Farmaco f: farmaci){
            nomeFarmaci.add(f.getNome()+" ("+f.getQuantita()+" "+f.getUnitaMisura()+") - "+f.getDittaProduttrice());
        }
        sceltaFarmaco.setItems(nomeFarmaci);
        sceltaFarmaco.getSelectionModel().selectedIndexProperty().addListener(new FarmacoListener<>(this));
        sceltaPaziente.getSelectionModel().selectedIndexProperty().addListener(new PazienteTerapiaListener<>(this));
        
    }

    public void setPaziente(Paziente paziente){
        this.paziente=paziente;
    }
    
    public LocalDate getDataNascita(){
        if(paziente != null){
            return LocalDate.of(paziente.getAnnoNascita(), 1, 1);
        }
        return null;
    }
    
    public void setDateFactory(DatePicker datePicker,LocalDate start,LocalDate end){
        CallBack<DatePicker, DateCell> dayCellFactory = new CallBack<>(start,end);
        datePicker.setDayCellFactory(dayCellFactory);
    }
    
    public void setFarmaco(Farmaco farmaco){
        this.farmaco=farmaco;
    }
    
    public DatePicker getInizio() {
        return dataInizio;
    }
    
    public DatePicker getFine() { 
        return dataFine;
    }

    public Medico getMedico() {
        return medico;
    }
    
    @FXML
    private void dataInizio(ActionEvent event) {
        setDateFactory(dataFine,dataInizio.getValue(),null);
        if(dataFine.isDisable()){
            dataFine.setDisable(false);
        }
    }
    
    @FXML
    private void inserisciTerapia(ActionEvent event) {
        if(!compiled()) return;
        Terapia terapia = new Terapia(farmaco,dataInizio.getValue(), dataFine.getValue(),doseValue,frequenzaValue,paziente);
        int idTerapia = daoFactory.getTerapiaDAO().salvaTerapia(terapia);
        terapia.setId(idTerapia);
        clean();
    }
    
    private boolean compiled(){
        LocalDate inizio=dataInizio.getValue();
        LocalDate fine=dataFine.getValue();
        int pazienteId=-1;
        if(sceltaPaziente.getValue()!=null && sceltaPaziente.getValue()!=-1){
            pazienteId=sceltaPaziente.getValue();
        }
        String farmacoSelected=null;
        if(sceltaFarmaco.getValue()!=null && !(sceltaFarmaco.getValue().equals(""))){
            farmacoSelected=sceltaFarmaco.getValue();
        }
        String msgErrore=null;
        boolean ret=true;
        if(pazienteId==-1){
            ret=false;
            msgErrore="Selezionare un paziente"; 
        }if(ret && farmacoSelected==null){
            ret=false;
            msgErrore="Selezionare un farmaco";
        }if(ret && !isValidDose()){
            ret=false;
            msgErrore="Inserire una dose valida"; 
        }if(ret && !isValidFrequenza()){
            ret=false;
            msgErrore="Inserire una frequenza valida"; 
        }if(ret && inizio==null){
            ret=false;
            msgErrore="Inserire data inizio terapia"; 
        }if(ret && fine==null){
            ret=false;
            msgErrore="Inserire data fine terapia"; 
        }if(!ret){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Messaggio di errore");
            alert.setHeaderText("Errore nell'inserimento della terapia");
            alert.setContentText(msgErrore);
            alert.showAndWait();
            return ret;
        }
        return ret;
    }
    
    public Label getUdm(){
        return unitaMisura;
    }
    
    public ObservableList<Farmaco> getFarmaci(){
        return farmaci;
    }
    
    private boolean isValidDose(){
        String doseString = dose.getText();
        if(doseString == null || doseString.equals("")){
            dose.clear();
            return false;
        }
        if(doseString.matches("\\d*")){
            doseValue=Integer.parseInt(doseString);
            return true;
        }
        dose.clear();
        return false;
    }
    
    private boolean isValidFrequenza(){
        String frequenzaString = frequenza.getText();
        if(frequenzaString == null ||frequenzaString.equals("")){
            frequenza.clear();
            return false;
        }
        if(frequenzaString.matches("\\d*")){
            frequenzaValue=Integer.parseInt(frequenzaString);
            return true;
        }
        frequenza.clear();
        return false;
    }
    
    public int comboBoxGet(int index){
        return sceltaPaziente.getItems().get(index);
    }
    
    public void cambioFarmaco(){
        dataInizio.setValue(null);
        dataFine.setValue(null);
        dataFine.setDisable(true);
        dose.clear();
        frequenza.clear();
    }
    public void clean(){
        sceltaFarmaco.setValue(null);
        dataInizio.setValue(null);
        dataFine.setValue(null);
        dataFine.setDisable(true);
        dose.clear();
        frequenza.clear();
        unitaMisura.setText(null);
    }
}
