package farmacovigilanza.controlview;

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
        login.setText("Logged as medico");
    }

    @FXML
    private void farmacologoLogin(ActionEvent event) {
        login.setText("Logged as farmacologo");
    }
    
}
