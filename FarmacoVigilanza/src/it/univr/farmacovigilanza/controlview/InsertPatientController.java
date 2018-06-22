package it.univr.farmacovigilanza.controlview;

import it.univr.farmacovigilanza.dao.DAOFactory;
import it.univr.farmacovigilanza.dao.PazienteDAO;
import it.univr.farmacovigilanza.model.FattoreRischio;
import it.univr.farmacovigilanza.dao.PazienteDAOImpl;
import it.univr.farmacovigilanza.dao.UserDAO;
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
import javafx.scene.control.TextField;

public class InsertPatientController implements Initializable{

    @FXML
    private Button insertButton;
    @FXML
    private ChoiceBox<Integer> annoNascita;
    @FXML
    private ChoiceBox<String> provinciaRes;
    @FXML
    private TextField professione;
    @FXML
    private CheckBox fumatore;
    @FXML
    private CheckBox sovrappeso;
    @FXML
    private CheckBox sottopeso;
    @FXML
    private CheckBox farmaci;
    @FXML
    private CheckBox iperteso;
    @FXML
    private CheckBox sedentario;
    @FXML
    private CheckBox asmatico;
    @FXML
    private CheckBox cardiopatico;
    @FXML
    private CheckBox diabetico;
    @FXML
    private CheckBox droghe;
    @FXML
    private CheckBox alcool;
    @FXML
    private CheckBox anemico;
    @FXML
    private CheckBox esposto;
    @FXML
    private CheckBox lavoroRischio;

    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> anni = FXCollections.observableArrayList();
        for(int now=2018; now >= 1888 ; now--){
            anni.addAll(now);
        }
        annoNascita.setItems(new SortedList(anni));
        annoNascita.setValue(annoNascita.getItems().get(0));
        ObservableList<String> province = FXCollections.observableArrayList();
        province.addAll(Paziente.sigleProvince);
        provinciaRes.setItems(new SortedList(province));
        provinciaRes.setValue(provinciaRes.getItems().get(0));
        checkBoxes.add(alcool);
        checkBoxes.add(asmatico);
        checkBoxes.add(anemico);
        checkBoxes.add(cardiopatico);
        checkBoxes.add(diabetico);
        checkBoxes.add(droghe);
        checkBoxes.add(esposto);
        checkBoxes.add(farmaci);
        checkBoxes.add(fumatore);
        checkBoxes.add(iperteso);
        checkBoxes.add(lavoroRischio);
        checkBoxes.add(sedentario);
        checkBoxes.add(sottopeso);
        checkBoxes.add(sovrappeso);
    }
    
    @FXML
    private void insertPatient(ActionEvent event) {
        if(professione.getText().isEmpty()) return; //professione non specificata
        List<FattoreRischio> fattoriRischio=getFattoriRischio();
        Paziente p= new Paziente(annoNascita.getValue(), provinciaRes.getValue(),professione.getText(),fattoriRischio);
        clear();
        Utente u;
        if((u = FXMLDocumentController.getUtente()) instanceof Medico){
            Medico m = (Medico) u;
            DAOFactory test = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            PazienteDAO pazDao = test.getPazienteDAO();
            if(!pazDao.salvaPaziente(p, m.getIdUtente())){
                System.out.println("Errore");
                //errore salva paziente
            };
        }
    }

    @FXML
    private void sovrappesoSelected(ActionEvent event) {
        sottopeso.setSelected(false);
    }

    @FXML
    private void sottopesoSelected(ActionEvent event) {
        sovrappeso.setSelected(false);
    }
    
    private List<FattoreRischio> getFattoriRischio(){
        List<FattoreRischio> ris = new ArrayList<>();
        return ris;
    }
    
    private void clear(){
        professione.clear();
        for(CheckBox c : checkBoxes) c.setSelected(false);
    }
}
