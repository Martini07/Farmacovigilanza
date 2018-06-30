package it.univr.farmacovigilanza.controlview;

import it.univr.farmacovigilanza.controlview.listener.FarmacoListener;
import it.univr.farmacovigilanza.controlview.listener.PazienteTerapiaListener;
import it.univr.farmacovigilanza.dao.DAOFactory;
import it.univr.farmacovigilanza.dao.PazienteDAO;
import it.univr.farmacovigilanza.dao.FarmacoDAO;
import it.univr.farmacovigilanza.dao.TerapiaDAO;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Pair;

public class InsertTerapyController implements Initializable{
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
    
    private DAOFactory test;
    private PazienteDAO pazDao;
    private TerapiaDAO terDAO;
    private ObservableList<Farmaco> farmaci;
    private Farmaco farmaco=null;
    private int doseValue,frequenzaValue=-1;
    
    public InsertTerapyController(Pair<Medico,Paziente> data){
        medico = data.getKey();
        paziente = data.getValue();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        test = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        pazDao = test.getPazienteDAO();
        terDAO = test.getTerapiaDAO();
        ObservableList<Paziente> mieiPazienti= pazDao.getPazienti(medico.getIdUtente());
        ObservableList<Integer> idPazienti=FXCollections.observableArrayList();
        for(Paziente p: mieiPazienti){
            idPazienti.add(p.getId());
        }
        sceltaPaziente.setItems(idPazienti);
        if( paziente != null){
            sceltaPaziente.setValue(paziente.getId());
            setDateFactory(dataInizio,getDataNascita(),LocalDate.now());
            setDateFactory(dataFine,dataInizio.getValue(),LocalDate.now());
        }
        //inserimento farmaci
        FarmacoDAO farmDao = test.getFarmacoDAO();
        farmaci = farmDao.getFarmaci();
        ObservableList<String> nomeFarmaci=FXCollections.observableArrayList();
        for(Farmaco f: farmaci){
            nomeFarmaci.add(f.getNome()+" ("+f.getQuantita()+" "+f.getUnitaMisura()+") - "+f.getDittaProduttrice());
        }
        sceltaFarmaco.setItems(nomeFarmaci);
        sceltaFarmaco.getSelectionModel().selectedIndexProperty().addListener(new FarmacoListener<>(this));
        sceltaPaziente.getSelectionModel().selectedIndexProperty().addListener(new PazienteTerapiaListener<>(this,pazDao));
        
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
    public DatePicker getInizio(){ return dataInizio;}
    public DatePicker getFine(){ return dataFine;}

    
    @FXML
    private void dataInizio(ActionEvent event) {
        setDateFactory(dataFine,dataInizio.getValue(),LocalDate.now());
        if(dataFine.isDisable()){
            dataFine.setDisable(false);
        }
    }
    
    @FXML
    private void inserisciTerapia(ActionEvent event) {
        if(!compiled()) return;
        Terapia terapia = new Terapia(farmaco,dataInizio.getValue(), dataFine.getValue(),doseValue,frequenzaValue,paziente);
        int idTerapia = terDAO.salvaTerapia(terapia);
        terapia.setId(idTerapia);
        clean();
    }
    
    private boolean compiled(){
        return dataInizio.getValue()!= null && dataFine.getValue()!= null && sceltaPaziente.getValue()!=null && sceltaFarmaco.getValue()!=null && isValidDose() & isValidFrequenza();
    }
    
    public Label getUdm(){
        return unitaMisura;
    }
    
    public ObservableList<Farmaco> getFarmaci(){
        return farmaci;
    }
    
    private boolean isValidDose(){
        String doseString = dose.getText();
        if(doseString == null ){
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
        if(frequenzaString == null ){
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
