package it.univr.farmacovigilanza.controlview;

import it.univr.farmacovigilanza.dao.DAOFactory;
import it.univr.farmacovigilanza.dao.UserDAO;
import it.univr.farmacovigilanza.model.Utente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button medico;
    @FXML
    private Button farmacologo;
    @FXML
    private Label login;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void medicoLogin(ActionEvent event) {
        //disable login
        medico.setDisable(true);
        farmacologo.setDisable(true);
        
        //DAOFactory test = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        //UserDAO userDao = test.getUserDAO();
        //Utente u = userDao.getUtente("fra", null);
        
        
        login.setText("Logged as medico");
    }

    @FXML
    private void farmacologoLogin(ActionEvent event) {
        login.setText("Logged as farmacologo");
    }
    
}
