package it.univr.farmacovigilanza.model;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Farmacologo extends Utente {

    private List<Segnalazione> segnalazioni;
    
    public Farmacologo(int idUtente, String username, String nome, String cognome, String email) {
        super(idUtente, username, nome, cognome, email);
    }

    public ObservableList<Segnalazione> getSegnalazioni() {
        return FXCollections.observableArrayList(segnalazioni);
    }

    public void setSegnalazioni(List<Segnalazione> segnalazioni) {
        this.segnalazioni = segnalazioni;
    }
    
}
