
package it.univr.farmacovigilanza.controlview.listener;

import it.univr.farmacovigilanza.controlview.FXMLDocumentController;
import it.univr.farmacovigilanza.dao.DAOFactory;
import it.univr.farmacovigilanza.model.Paziente;
import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class PazienteListener<Integer> implements ChangeListener {
    
    private final FXMLDocumentController controller;

    public PazienteListener(FXMLDocumentController controller) {
        this.controller=controller;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        int idPaziente = controller.comboBoxGet((int) newValue);
        //TODO non serve andare nuovamente a db...
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        Paziente paziente = daoFactory.getPazienteDAO().getPaziente(idPaziente);
        controller.setDateFactory(controller.getDataReazioneAvversa(), controller.getDataNascita(paziente), LocalDate.now());
        controller.getDataReazioneAvversa().setValue(null);
        controller.getDataReazioneAvversa().setDisable(false);
        controller.getSceltaReazioneAvversa().setValue(null);
    }
    
}
