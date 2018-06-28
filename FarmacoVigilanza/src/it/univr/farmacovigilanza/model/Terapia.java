package it.univr.farmacovigilanza.model;

import java.time.LocalDate;

public class Terapia {
    
    private int id;
    private final Farmaco farmaco;
    private final LocalDate dataInizio;
    private final LocalDate dataFine;
    private final int dose;
    private final int frequenzaGiornaliera;
    private final Paziente paziente;

    public Terapia(int id, Farmaco farmaco, LocalDate dataInizio, LocalDate dataFine, int dose, int frequenzaGiornaliera, Paziente paziente) {
        this.id = id;
        this.farmaco = farmaco;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.dose = dose;
        this.frequenzaGiornaliera = frequenzaGiornaliera;
        this.paziente = paziente;
    }

    public Terapia(Farmaco farmaco, LocalDate dataInizio, LocalDate dataFine, int dose, int frequenzaGiornaliera, Paziente paziente) {
        this.id=-1;
        this.farmaco = farmaco;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.dose = dose;
        this.frequenzaGiornaliera = frequenzaGiornaliera;
        this.paziente = paziente;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id=id;
    }
    public Farmaco getFarmaco() {
        return farmaco;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public int getDose() {
        return dose;
    }

    public int getFrequenzaGiornaliera() {
        return frequenzaGiornaliera;
    }

    public Paziente getPaziente() {
        return paziente;
    }

    @Override
    public String toString() {
        return "Terapia{" + "id=" + id + ", farmaco=" + farmaco + ", dataInizio=" + dataInizio + ", dataFine=" + dataFine + ", dose=" + dose + ", frequenzaGiornaliera=" + frequenzaGiornaliera + ", paziente=" + paziente + '}';
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Terapia){
            return this.id == ((Terapia) o).getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
    
}
