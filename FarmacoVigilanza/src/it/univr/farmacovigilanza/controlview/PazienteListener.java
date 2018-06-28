
package it.univr.farmacovigilanza.controlview;

import it.univr.farmacovigilanza.dao.PazienteDAO;
import it.univr.farmacovigilanza.model.Paziente;
import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;


public class PazienteListener<Integer> implements ChangeListener{
    
    private final FXMLDocumentController controller;
    private final ComboBox<Integer> sceltaPaziente;
    private final ComboBox<String> sceltaReazioneAvversa;
    private final DatePicker dataReazioneAvversa;
    private final PazienteDAO pazDAO;

    public PazienteListener(FXMLDocumentController controller) {
        this.controller=controller;
        sceltaPaziente=(ComboBox<Integer>) controller.getSceltaPaziente();
        sceltaReazioneAvversa=controller.getSceltaReazioneAvversa();
        dataReazioneAvversa=controller.getDataReazioneAvversa();
        pazDAO=controller.getPazienteDAO();
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        int idPaziente = controller.comboBoxGet((int) newValue);
        Paziente paziente = pazDAO.getPaziente(idPaziente);
        controller.setDateFactory(dataReazioneAvversa, controller.getDataNascita(paziente), LocalDate.now());
        dataReazioneAvversa.setValue(null);
        dataReazioneAvversa.setDisable(false);
        sceltaReazioneAvversa.setValue(null);
    }
    
}
