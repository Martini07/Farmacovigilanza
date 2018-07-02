package it.univr.farmacovigilanza.model;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Medico extends Utente {

    private List<Paziente> pazienti;
    
    public Medico(int idUtente, String username, String nome, String cognome, String email) {
        super(idUtente, username, nome, cognome, email);
    }
    
    public ObservableList<Paziente> getPazienti() {
        return FXCollections.observableArrayList(pazienti);
    }

    public void setPazienti(List<Paziente> pazienti) {
        this.pazienti = pazienti;
    }
    public void aggiungiPazienti(List<Paziente> pazienti){
        this.pazienti.addAll(pazienti);
    }
}
