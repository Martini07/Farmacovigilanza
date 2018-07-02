package it.univr.farmacovigilanza.controlview;

import it.univr.farmacovigilanza.controlview.listener.FarmacoAzioneListener;
import it.univr.farmacovigilanza.controlview.listener.PazienteListener;
import it.univr.farmacovigilanza.dao.DAOFactory;
import it.univr.farmacovigilanza.model.Farmaco;
import it.univr.farmacovigilanza.model.Farmaco.Stato;
import it.univr.farmacovigilanza.model.Utente;
import it.univr.farmacovigilanza.model.Medico;
import it.univr.farmacovigilanza.model.Farmacologo;
import it.univr.farmacovigilanza.model.FattoreRischio;
import it.univr.farmacovigilanza.model.Paziente;
import it.univr.farmacovigilanza.model.ReazioneAvversa;
import it.univr.farmacovigilanza.model.Segnalazione;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

public class FarmacovigilanzaController implements Initializable {
    private static Utente logged=null;
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
    private ComboBox<Integer> sceltaPaziente;
    @FXML
    private Label pazienteLabel;
    @FXML
    private Label reazioneAvversaLabel;
    @FXML
    private ComboBox<String> sceltaReazioneAvversa;
    @FXML
    private Label dataReazioneAvversaLabel;
    @FXML
    private DatePicker dataReazioneAvversa;
    @FXML
    private Button segnalaPazienteButton;
    @FXML
    private Button terapiaPaziente;
    @FXML
    private Button aggiuntaPaziente;
    @FXML
    private TableView<Paziente> grid;
    @FXML
    private TableColumn<Paziente, Integer> id;
    @FXML
    private TableColumn<Paziente, Integer> anno;
    @FXML
    private TableColumn<Paziente, String> provincia;
    @FXML
    private TableColumn<Paziente, String> professione;
    @FXML
    private TableColumn<Paziente, List<FattoreRischio>> fattoriRischio;
    @FXML
    private Button segnala;
    
    @FXML
    private TableView<Segnalazione> segnalazioni;
    @FXML
    private TableColumn<Segnalazione, Integer> idSegnalazione;
    @FXML
    private TableColumn<Segnalazione, Farmaco> farmaco;
    @FXML
    private TableColumn<Segnalazione, ReazioneAvversa> reazioneAvversa;
    @FXML
    private TableColumn<Segnalazione, LocalDate> dataReazione;
    @FXML
    private TableColumn<Segnalazione, LocalDate> dataSegnalazione;
    @FXML
    private ComboBox<Stato> statoFarmaco;
    @FXML
    private Label farmacoSelezionato;
    @FXML
    private Button applica;
    @FXML
    private ComboBox<String> filtraFarmaco;
    @FXML
    private Label filtraFarmacoLabel;
    @FXML
    private Label filtraGravitaLabel;
    @FXML
    private ComboBox<Integer> filtraGravita;
    @FXML
    private Label dataInizioLabel;
    @FXML
    private DatePicker dataInizio;
    @FXML
    private Label dataFineLabel;
    @FXML
    private DatePicker dataFine;
    @FXML
    private Label numeroRisultatiQuery;
    
    private DAOFactory daoFactory = null;
    private ObservableList<String> nomiReazioniAvverse = null;
    private ObservableList<ReazioneAvversa> reazioniAvverse = null;
    private ObservableList<Farmaco> listaFarmaci=null;
    private ObservableList<String> nomeFarmaci=FXCollections.observableArrayList();
    private int idFarmacoSelezionato = -1;
    @FXML
    private Button cleanFilter;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    }    

    @FXML
    private void doLogin(ActionEvent event) throws UnsupportedEncodingException {
        Utente utente = daoFactory.getUserDAO().getUtente(username.getText(), toHash(password.getText()));
        if (utente != null) {
            if (utente instanceof Medico){
                logged = (Medico) utente;
            } else {
                logged = (Farmacologo) utente;
            }
            caricaInterfaccia();
            login.setText("Benvenuto " + utente.getNome() + " " + utente.getCognome());
            disableLoginFields();
            logoutButton.setVisible(true);
            logoutButton.setDisable(false);
        } else {
            username.clear();
            password.clear();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Messaggio di errore");
            alert.setHeaderText("Errore nella login");
            alert.setContentText("Username o password non corretti");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void doLogout(ActionEvent event) {
        logoutButton.setVisible(false);
        logoutButton.setDisable(true);
        login.setText("");
        enableLoginFields();
        disabilitaInterfaccia();
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
    
    private void caricaInterfaccia(){
        if (logged instanceof Medico){
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            anno.setCellValueFactory(new PropertyValueFactory<>("annoNascita"));
            provincia.setCellValueFactory(new PropertyValueFactory<>("provinciaRes"));
            professione.setCellValueFactory(new PropertyValueFactory<>("professione"));
            fattoriRischio.setCellValueFactory(new PropertyValueFactory<>("fattoriRischio"));
            
            pazientiButton.setDisable(false);
            pazientiButton.setVisible(true);
            //Ottieni i miei pazienti
            ObservableList<Paziente> mieiPazienti= daoFactory.getPazienteDAO().getPazienti(((Medico) logged).getIdUtente());
            ((Medico) logged).setPazienti(mieiPazienti);
            ObservableList<Integer> idPazienti=FXCollections.observableArrayList();
            for (Paziente p: mieiPazienti){
                idPazienti.add(p.getId());
            }
            sceltaPaziente.setItems(idPazienti);
            sceltaPaziente.getSelectionModel().selectedIndexProperty().addListener(new PazienteListener<>(this));

            if (reazioniAvverse == null){
                //segnalazioni cercate una volta sola
                reazioniAvverse = daoFactory.getSegnalazioneDAO().getReazioniAvverse();
                nomiReazioniAvverse = FXCollections.observableArrayList();
                for (ReazioneAvversa reazione : reazioniAvverse){
                    nomiReazioniAvverse.add(reazione.getNome());
                }
                sceltaReazioneAvversa.setItems(nomiReazioniAvverse);
            }
            enableSegnalazione(false);
        } else {
            idSegnalazione.setCellValueFactory(new PropertyValueFactory<>("id"));
            farmaco.setCellValueFactory(new PropertyValueFactory<>("farmaco"));
            reazioneAvversa.setCellValueFactory(new PropertyValueFactory<>("reazioneAvversa"));
            dataReazione.setCellValueFactory(new PropertyValueFactory<>("dataReazione"));
            dataSegnalazione.setCellValueFactory(new PropertyValueFactory<>("dataSegnalazione"));
            
            statoFarmaco.setItems(FXCollections.observableArrayList(Stato.values()));
            
            segnalazioni.getSelectionModel().selectedIndexProperty().addListener(new FarmacoAzioneListener<>(this));
            segnalazioni.setVisible(true);
            segnalazioni.setDisable(false);
            ((Farmacologo) logged).setSegnalazioni(daoFactory.getSegnalazioneDAO().getSegnalazioni(logged.getIdUtente()));
            segnalazioni.setItems(((Farmacologo) logged).getSegnalazioni());
            numeroRisultatiQuery.setText("Totale: "+segnalazioni.getItems().size());
            listaFarmaci=daoFactory.getFarmacoDAO().getFarmaci(((Farmacologo)logged).getIdUtente());
            for(Farmaco f: listaFarmaci) nomeFarmaci.add(f.getNome()+" ("+f.getQuantita()+" "+f.getUnitaMisura()+") - "+f.getDittaProduttrice());
            filtraFarmaco.setItems(nomeFarmaci);
            filtraFarmaco.setVisible(true);
            filtraFarmaco.setDisable(false);
            filtraFarmacoLabel.setVisible(true);
            filtraFarmacoLabel.setDisable(false);
            if(filtraGravita.getItems().isEmpty()){
                //inserisci gravità una volta sola
                for(int i=1; i<=5; i++) filtraGravita.getItems().add(i);
            }
            filtraGravita.setVisible(true);
            filtraGravita.setDisable(false);
            filtraGravitaLabel.setVisible(true);
            filtraGravitaLabel.setDisable(false);
            dataInizio.setVisible(true);
            dataInizio.setDisable(false);
            setDateFactory(dataInizio,null,LocalDate.now());
            dataInizioLabel.setVisible(true);
            dataInizioLabel.setDisable(false);
            dataFine.setVisible(true);
            dataFineLabel.setVisible(true);
            dataFineLabel.setDisable(false);
            numeroRisultatiQuery.setVisible(true);
            numeroRisultatiQuery.setDisable(false);
            cleanFilter.setVisible(true);
            cleanFilter.setDisable(false);
        }
    }

    private void disabilitaInterfaccia(){
        if (logged instanceof Medico) {
            pazientiButton.setDisable(true);
            pazientiButton.setVisible(false);
            grid.setVisible(false);
            grid.setDisable(true);
            segnalaPazienteButton.setDisable(true);
            segnalaPazienteButton.setVisible(false);
            terapiaPaziente.setDisable(true);
            terapiaPaziente.setVisible(false);
            disableSegnalazione();
            grid.getItems().removeAll(grid.getItems());
            sceltaPaziente.getItems().removeAll(sceltaPaziente.getItems());
        } else {
            statoFarmaco.setVisible(false);
            statoFarmaco.setValue(null);
            farmacoSelezionato.setVisible(false);
            farmacoSelezionato.setText(null);
            applica.setVisible(false);
            segnalazioni.setVisible(false);
            segnalazioni.setDisable(true);
            segnalazioni.getItems().removeAll(grid.getItems());
            filtraFarmaco.setVisible(false);
            filtraFarmaco.setDisable(true);
            filtraFarmaco.setValue(null);
            filtraFarmacoLabel.setVisible(false);
            filtraFarmacoLabel.setDisable(true);
            filtraGravita.setVisible(false);
            filtraGravita.setDisable(true);
            filtraGravita.setValue(null);
            filtraGravitaLabel.setVisible(false);
            filtraGravitaLabel.setDisable(true);
            dataInizio.setVisible(false);
            dataInizio.setDisable(true);
            dataInizio.setValue(null);
            dataInizioLabel.setVisible(false);
            dataInizioLabel.setDisable(true);
            dataFine.setVisible(false);
            dataFine.setValue(null);
            dataFineLabel.setDisable(true);
            dataFineLabel.setVisible(false);
            dataFineLabel.setDisable(true);
            numeroRisultatiQuery.setText(null);
            numeroRisultatiQuery.setVisible(false);
            numeroRisultatiQuery.setDisable(true);
            cleanFilter.setVisible(false);
            cleanFilter.setDisable(true);
        }
    }
    
    private void enableSegnalazione(boolean flag){
        pazientiButton.setDisable(false);
        pazientiButton.setVisible(true);
        pazienteLabel.setVisible(true);
        pazienteLabel.setDisable(false);
        segnalaPazienteButton.setDisable(true);
        segnalaPazienteButton.setVisible(false);
        terapiaPaziente.setDisable(true);
        terapiaPaziente.setVisible(false);
        reazioneAvversaLabel.setVisible(true);
        reazioneAvversaLabel.setDisable(false);
        dataReazioneAvversaLabel.setVisible(true);
        dataReazioneAvversaLabel.setDisable(false);
        sceltaPaziente.setVisible(true);
        sceltaPaziente.setDisable(false);
        sceltaReazioneAvversa.setVisible(true);
        sceltaReazioneAvversa.setDisable(false);
        dataReazioneAvversa.setVisible(true);
        if(flag){
            dataReazioneAvversa.setDisable(false);
        }else{
            dataReazioneAvversa.setDisable(true);
        }
        aggiuntaPaziente.setVisible(true);
        aggiuntaPaziente.setDisable(false);
        segnala.setVisible(true);
        segnala.setDisable(false);
    }
    
    private void disableSegnalazione(){
        pazienteLabel.setVisible(false);
        pazienteLabel.setDisable(true);
        reazioneAvversaLabel.setVisible(false);
        reazioneAvversaLabel.setDisable(true);
        dataReazioneAvversaLabel.setVisible(false);
        dataReazioneAvversaLabel.setDisable(true);
        sceltaPaziente.setVisible(false);
        sceltaPaziente.setDisable(true);
        sceltaReazioneAvversa.setVisible(false);
        sceltaReazioneAvversa.setDisable(true);
        dataReazioneAvversa.setVisible(false);
        dataReazioneAvversa.setDisable(true);
        aggiuntaPaziente.setVisible(false);
        aggiuntaPaziente.setDisable(true);
        segnala.setVisible(false);
        segnala.setDisable(true);
        clear();
    }
    
    @FXML
    private void showListaPazienti(ActionEvent event) {
        //disable lista pazienti button
        pazientiButton.setDisable(true);
        pazientiButton.setVisible(false);
        segnalaPazienteButton.setDisable(false);
        segnalaPazienteButton.setVisible(true);
        terapiaPaziente.setDisable(false);
        terapiaPaziente.setVisible(true);
        //disable segnalazione
        disableSegnalazione();
        grid.setVisible(true);
        grid.setDisable(false);
        grid.setItems(((Medico) logged).getPazienti());
    }
    
    public void enableSegnalazione(int id){
        grid.setVisible(false);
        grid.setDisable(true);
        //set this patient
        sceltaPaziente.setValue(id);
        enableSegnalazione(true);
    }

    @FXML
    private void segnalaPaziente(ActionEvent event) {
        Paziente selected = grid.getSelectionModel().getSelectedItem();
        if(selected != null){
            enableSegnalazione(selected.getId());
        }
    }

    @FXML
    private void addTerapiaPaziente(ActionEvent event) {
        Paziente selected = grid.getSelectionModel().getSelectedItem();
        if(selected != null){
            try{
                FXMLLoader loader =  new FXMLLoader(getClass().getResource("InserimentoTerapia.fxml"));
                InserimentoTerapiaController controller = new InserimentoTerapiaController(new Pair<Medico,Paziente>((Medico) logged, selected));
                loader.setController(controller);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
                Stage stage = new Stage();
                stage.getIcons().add(new Image(this.getClass().getResourceAsStream("logo.jpg")));
                stage.setTitle("Inserimento terapia");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            }catch(Exception ex){
                Logger.getLogger(FarmacovigilanzaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void addPaziente(ActionEvent event) {
            try{
                Parent root = FXMLLoader.load(getClass().getResource("InserimentoPaziente.fxml"));
                Stage stage = new Stage();
                stage.getIcons().add(new Image(this.getClass().getResourceAsStream("logo.jpg")));
                stage.setTitle("Inserimento paziente");
                Scene newScene = new Scene(root);
                newScene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
                stage.setScene(newScene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                //update choicebox with new pazienti
                ObservableList<Integer> idInseriti = (ObservableList<Integer>) stage.getUserData();
                sceltaPaziente.getItems().addAll(idInseriti);
                List<Paziente> pazientiInseriti=new ArrayList<>();
                for(int idPaziente : idInseriti){
                    pazientiInseriti.add(daoFactory.getPazienteDAO().getPaziente(idPaziente));
                }
                ((Medico) logged).aggiungiPazienti(pazientiInseriti);
                enableSegnalazione(idInseriti.get(idInseriti.size()-1));
            }catch(Exception ex){
                Logger.getLogger(FarmacovigilanzaController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void setDateFactory(DatePicker datePicker,LocalDate start,LocalDate end){
        CallBack<DatePicker, DateCell> dayCellFactory = new CallBack<>(start,end);
        datePicker.setDayCellFactory(dayCellFactory);
    }
    
    public int comboBoxGet(int index){
        return sceltaPaziente.getItems().get(index);
    }

    private String toHash(String text) {
        String hash = null;
        try {
            byte[] textData = text.getBytes("UTF-8");
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = messageDigest.digest(textData);
            hash = new BigInteger(digest).toString(16);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(FarmacovigilanzaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
    }

    @FXML
    private void segnala(ActionEvent event){
        String msgErrore=null;
        int pazienteId=-1;
        if(sceltaPaziente.getValue()!=null && sceltaPaziente.getValue()!=-1){
            pazienteId=sceltaPaziente.getValue();
        }
        String reazioneAvversaSelected=null;
        if(sceltaReazioneAvversa.getValue() != null && !(sceltaReazioneAvversa.getValue().equals(""))){
            reazioneAvversaSelected=sceltaReazioneAvversa.getValue();
        }
        LocalDate dataReazioneInserita = dataReazioneAvversa.getValue();
        
        if(pazienteId==-1){
            msgErrore="Selezionare un paziente";
        }if(msgErrore==null && reazioneAvversaSelected==null){
            msgErrore="Selezionare una reazione avversa";
        }if(msgErrore==null && dataReazioneInserita==null){
            msgErrore="Inserire una data";
        }
        if (msgErrore == null) {
            int result = daoFactory.getSegnalazioneDAO().salvaSegnalazione(new Segnalazione(-1, reazioniAvverse.get(sceltaReazioneAvversa.getSelectionModel().getSelectedIndex()), LocalDate.now(), dataReazioneInserita, null), sceltaPaziente.getValue());
            if (result == -2){
                msgErrore = "Non sono presenti terapie attive alla data indicata";
            } else {
                clear();
            }
        }
        if(msgErrore != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Messaggio di errore");
            alert.setHeaderText("Errore nell'inserimento della terapia");
            alert.setContentText(msgErrore);
            alert.showAndWait();
        }
    }
    
    private void clear(){
        sceltaPaziente.setValue(null);
        sceltaReazioneAvversa.setValue(null);
        dataReazioneAvversa.setValue(null);
    }
    
    @FXML
    private void applicaAzione(ActionEvent event){
        statoFarmaco.setVisible(false);
        farmacoSelezionato.setVisible(false);
        applica.setVisible(false);
        daoFactory.getFarmacoDAO().aggiornaFarmaco(idFarmacoSelezionato, statoFarmaco.getValue());
        idFarmacoSelezionato = -1;
    }
    
    @FXML
    private void changeDataInizio(ActionEvent event) {
        setDateFactory(dataFine,dataInizio.getValue(),LocalDate.now());
        dataFine.setValue(null);
        dataFine.setDisable(false);
        applicaFiltri();
    }
    
    @FXML
    private void changeFiltro(ActionEvent event) {
        applicaFiltri();
    }
    
    public void applicaFiltri(){
        Farmaco farmacoSel = null;
        if (filtraFarmaco.getValue() != null)
            farmacoSel = listaFarmaci.get(nomeFarmaci.indexOf(filtraFarmaco.getValue()));
        int risultati = 0;
        ObservableList<Segnalazione> segnalazioniDaMostrare = FXCollections.observableArrayList();
        ObservableList<Segnalazione> segnalazioniFarmacologo = ((Farmacologo) logged).getSegnalazioni();
        for (Segnalazione segnalazione : segnalazioniFarmacologo) {
            if ((filtraGravita.getValue() == null || segnalazione.getReazioneAvversa().getLivelloGravita() >= filtraGravita.getValue()) &&
                    (farmacoSel == null || segnalazione.getFarmaco().equals(farmacoSel)) &&
                    (dataInizio.getValue() == null || segnalazione.getDataSegnalazione().isAfter(dataInizio.getValue())) &&
                    (dataFine.getValue() == null || segnalazione.getDataSegnalazione().isBefore(dataFine.getValue()))) {
                segnalazioniDaMostrare.add(segnalazione);
                risultati++;
            }
        }
        numeroRisultatiQuery.setText("Totale: " + risultati);
        segnalazioni.setItems(segnalazioniDaMostrare);
    }
    
    // GETTERS
    
    public static Utente getUtente(){
        return logged;
    }
    
    public ComboBox<Integer> getSceltaPaziente(){
        return sceltaPaziente;
    }
    
    public ComboBox<String> getSceltaReazioneAvversa(){
        return sceltaReazioneAvversa;
    }
    
    public DatePicker getDataReazioneAvversa(){
        return dataReazioneAvversa;
    }
    
    public LocalDate getDataNascita(Paziente paziente){
        if (paziente != null){
            return LocalDate.of(paziente.getAnnoNascita(), 1, 1);
        }
        return null;
    }
    
    public ComboBox<Stato> getStatoFarmaco(){
        return statoFarmaco;
    }
    
    public Label getFarmacoSelezionato(){
        return farmacoSelezionato;
    }
    
    public Button getApplica(){
        return applica;
    }
    
    public void setIdFarmacoSelezionato(int idFarmacoSelezionato) {
        this.idFarmacoSelezionato = idFarmacoSelezionato;
    }

    @FXML
    private void cleanFilter(ActionEvent event) {
        filtraFarmaco.setValue(null);
        filtraGravita.setValue(null);
        dataInizio.setValue(null);
        dataFine.setValue(null);
        applicaFiltri();
    }
}
