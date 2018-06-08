package it.univr.farmacovigilanza.controlview;

import it.univr.farmacovigilanza.dao.DAOFactory;
import it.univr.farmacovigilanza.dao.UserDAO;
import it.univr.farmacovigilanza.model.Utente;
import it.univr.farmacovigilanza.model.Medico;
import it.univr.farmacovigilanza.model.Farmacologo;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {
    private Utente logged=null;
    @FXML
    private Label login;
    @FXML
    private TextField password;
    @FXML
    private TextField username;
    @FXML
    private Button loginButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button pazientiButton;
    @FXML
    private ListView<String> listaPazienti;
    @FXML
    private ChoiceBox<?> sceltaPaziente;
    @FXML
    private Label pazienteLabel;
    @FXML
    private Label reazioneAvversaLabel;
    @FXML
    private ChoiceBox<?> sceltaReazioneAvversa;
    @FXML
    private Label dataReazioneAvversaLabel;
    @FXML
    private Label terapieLabel;
    @FXML
    private DatePicker dataReazioneAvversa;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void doLogin(ActionEvent event) {
        //try login
        DAOFactory test = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        UserDAO userDao = test.getUserDAO();
        Utente utente = userDao.getUtente(username.getText(), password.getText());
        if (utente != null) {
            if (utente instanceof Medico){
                logged=(Medico) utente;
                enableMedicoInterface();
            } else { //Farmacologo
                logged=(Farmacologo) utente;
            }
        } else {
            //login errata
            login.setText("Failed to login");
            //check if username exist...
            username.clear();
            password.clear();
        }     
        //logged in
        login.setText("Logged as " + utente.getNome() + " " + utente.getCognome());
        disableLoginFields();
        //enable logout
        logoutButton.setVisible(true);
        logoutButton.setDisable(false);
    }
    
    @FXML
    private void doLogout(ActionEvent event) {
        //deactivate logout button
        logoutButton.setVisible(false);
        logoutButton.setDisable(true);
        login.setText("");
        
        enableLoginFields();
        if(logged instanceof Medico){
            //disable Medico interface
            disableMedicoInterface();
            
        }
        logged=null;
    }
    
    private void enableLoginFields(){
        username.setVisible(true);
        username.setDisable(false);
        password.setVisible(true);
        password.setDisable(false);
        loginButton.setVisible(true);
        loginButton.setDisable(false);
        username.clear();
        password.clear();
    }
    
    private void disableLoginFields(){
        username.setVisible(false);
        username.setDisable(true);
        password.setVisible(false);
        password.setDisable(true);
        loginButton.setVisible(false);
        loginButton.setDisable(true);
    }
    
    private void enableMedicoInterface(){
        pazientiButton.setDisable(false);
        pazientiButton.setVisible(true);
        enableSegnalazione();
    }

    private void disableMedicoInterface(){
        pazientiButton.setDisable(true);
        pazientiButton.setVisible(false);
        listaPazienti.setVisible(false);
        listaPazienti.setDisable(true);
        disableSegnalazione();
        listaPazienti.getItems().removeAll(listaPazienti.getItems()); //find a better way...
    }
    
    private void enableSegnalazione(){
        pazienteLabel.setVisible(true);
        pazienteLabel.setDisable(false);
        reazioneAvversaLabel.setVisible(true);
        reazioneAvversaLabel.setDisable(false);
        dataReazioneAvversaLabel.setVisible(true);
        dataReazioneAvversaLabel.setDisable(false);
        terapieLabel.setVisible(true);
        terapieLabel.setDisable(false);
        sceltaPaziente.setVisible(true);
        sceltaPaziente.setDisable(false);
        sceltaReazioneAvversa.setVisible(true);
        sceltaReazioneAvversa.setDisable(false);
        dataReazioneAvversa.setVisible(true);
        dataReazioneAvversa.setDisable(false);
    }
    
    private void disableSegnalazione(){
        pazienteLabel.setVisible(false);
        pazienteLabel.setDisable(true);
        reazioneAvversaLabel.setVisible(false);
        reazioneAvversaLabel.setDisable(true);
        dataReazioneAvversaLabel.setVisible(false);
        dataReazioneAvversaLabel.setDisable(true);
        terapieLabel.setVisible(false);
        terapieLabel.setDisable(true);
        sceltaPaziente.setVisible(false);
        sceltaPaziente.setDisable(true);
        sceltaPaziente.getItems().removeAll(sceltaPaziente.getItems());
        sceltaReazioneAvversa.setVisible(false);
        sceltaReazioneAvversa.setDisable(true);
        sceltaReazioneAvversa.getItems().removeAll(sceltaReazioneAvversa.getItems());
        dataReazioneAvversa.setVisible(false);
        dataReazioneAvversa.setDisable(true);
    }
    
    @FXML
    private void showListaPazienti(ActionEvent event) {
        //disable lista pazienti button
        pazientiButton.setDisable(true);
        pazientiButton.setVisible(false);
        //disable segnalazione
        disableSegnalazione();
        listaPazienti.setVisible(true);
        listaPazienti.setDisable(false);
        listaPazienti.setOnMousePressed(new ListViewHandler(listaPazienti, this));
        ArrayList<String> mieiPazienti= new ArrayList<>(); // to integrate with db
        mieiPazienti.add("Paziente 1");
        mieiPazienti.add("Paziente 2");
        mieiPazienti.add("Paziente 3");
        mieiPazienti.add("Paziente 4");
        listaPazienti.getItems().addAll(mieiPazienti);
    }
    
    public void enableSegnalazione(String s){
        listaPazienti.setVisible(true);
        listaPazienti.setDisable(true);
        //set this patient
        enableSegnalazione();
        
        //disable choicebox on patient
        sceltaPaziente.setVisible(false);
        sceltaPaziente.setDisable(true);
        System.out.println("Paziente: "+s);
    }

}
