package it.univr.farmacovigilanza.controlview;

import it.univr.farmacovigilanza.dao.DAOFactory;
import it.univr.farmacovigilanza.model.FattoreRischio;
import it.univr.farmacovigilanza.model.Medico;
import it.univr.farmacovigilanza.model.Paziente;
import it.univr.farmacovigilanza.model.Utente;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

public class InsertPatientController implements Initializable{

    @FXML
    private Button insertButton;
    @FXML
    private ComboBox<Integer> annoNascita;
    @FXML
    private ComboBox<String> provinciaRes;
    @FXML
    private TextField professione;
    @FXML
    private VBox checkBoxes;
    
    ObservableList<Integer> pazientiId = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> anni = FXCollections.observableArrayList();
        for(int now = Calendar.getInstance().get(Calendar.YEAR); now >= 1888 ; now--){
            anni.addAll(now);
        }
        annoNascita.setItems(new SortedList(anni));
        annoNascita.setValue(annoNascita.getItems().get(0));
        ObservableList<String> province = FXCollections.observableArrayList();
        province.addAll(Paziente.sigleProvince);
        provinciaRes.setItems(new SortedList(province));
        provinciaRes.setValue(provinciaRes.getItems().get(0));

        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        List<FattoreRischio> fattoriRischio = daoFactory.getPazienteDAO().getFattoriRischio();
        int countCheck = 0;
        HBox row = new HBox();
        for (FattoreRischio fattoreRischio : fattoriRischio) {
            CheckBox checkFattore = new CheckBox(fattoreRischio.getNome());
            checkFattore.setId("CheckBox_" + fattoreRischio.getId());
            checkFattore.setPadding(new Insets(7));
            checkFattore.setPrefWidth(160);
            row.getChildren().add(checkFattore);
            countCheck++;
            if (countCheck == 3){
                checkBoxes.getChildren().add(row);
                row = new HBox();
                countCheck = 0;
            }
        }
        if (countCheck > 0){
            checkBoxes.getChildren().add(row);
        }
    }
    
    @FXML
    private void insertPatient(ActionEvent event) {
        if(professione.getText().isEmpty()) return; //professione non specificata
        Utente utente = FXMLDocumentController.getUtente();
        if (utente instanceof Medico){
            Medico m = (Medico) utente;
            List<FattoreRischio> fattoriRischio = getFattoriRischioSelezionati();
            Paziente nuovoPaziente = new Paziente(annoNascita.getValue(), provinciaRes.getValue(),professione.getText(),fattoriRischio);
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            int idPazienteCreato = daoFactory.getPazienteDAO().salvaPaziente(nuovoPaziente, m.getIdUtente());
            if (idPazienteCreato != -1){
                clear();
                nuovoPaziente.setId(idPazienteCreato);
                pazientiId.add(idPazienteCreato);
                Window window = insertButton.getScene().getWindow();
                window.setUserData(pazientiId);
            }
        }
    }
    
    private List<FattoreRischio> getFattoriRischioSelezionati(){
        List<FattoreRischio> fattoriRischio = new ArrayList();
        for (Node row : checkBoxes.getChildren()){
            if (row instanceof HBox){
                HBox hbox = (HBox) row;
                for (Node column : hbox.getChildren()){
                    if (column instanceof CheckBox){
                        CheckBox checkBox = (CheckBox) column;
                        if (checkBox.isSelected()){
                            String idCheckBox = checkBox.getId();
                            FattoreRischio fattoreSelezionato = new FattoreRischio(Integer.valueOf(idCheckBox.substring(idCheckBox.indexOf('_') + 1)), null, null, -1);
                            fattoriRischio.add(fattoreSelezionato);
                            checkBox.setSelected(false);
                        }
                    }
                }
            }
        }
        return fattoriRischio;
    }
    
    private void clear(){
        annoNascita.setValue(annoNascita.getItems().get(0));
        provinciaRes.setValue(provinciaRes.getItems().get(0));
        professione.clear();
    }
}
