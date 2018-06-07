package it.univr.farmacovigilanza.controlview;

import it.univr.farmacovigilanza.dao.DAOFactory;
import it.univr.farmacovigilanza.dao.UserDAO;
import it.univr.farmacovigilanza.model.Utente;
import it.univr.farmacovigilanza.model.Medico;
import it.univr.farmacovigilanza.model.Farmacologo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void doLogin(ActionEvent event) {
        //try login
        DAOFactory test = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        UserDAO userDao = test.getUserDAO();
        Utente u = userDao.getUtente(username.getText(), null);
        //logged in
        login.setText("Logged "+u.getUsername());
        disableLoginFields();
        //enable logut
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
   
}
