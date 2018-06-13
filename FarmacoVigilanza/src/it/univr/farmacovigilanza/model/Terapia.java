package it.univr.farmacovigilanza.model;

import java.time.LocalDate;

public class Terapia {
    private final Paziente paziente;
    private final Farmaco farmaco;
    private final int dose; //equivale a quantità
    private final float frequenzaGiornaliera; //decidere che tipo di dato da usare (esempio dose oraria 0.5 1 dose ogni due ore...)
    private final LocalDate dataSegnalazione;
    private final LocalDate dataReazione;

    public Terapia(Paziente paziente, Farmaco farmaco, int dose, float frequenzaGiornaliera, LocalDate dataSegnalazione, LocalDate dataReazione) {
        this.paziente = paziente;
        this.farmaco = farmaco;
        this.dose = dose;
        this.frequenzaGiornaliera = frequenzaGiornaliera;
        this.dataSegnalazione = dataSegnalazione;
        this.dataReazione = dataReazione;
    }

    public Paziente getPaziente() {
        return paziente;
    }

    public Farmaco getFarmaco() {
        return farmaco;
    }

    public int getDose() {
        return dose;
    }

    public float getFrequenzaGiornaliera() {
        return frequenzaGiornaliera;
    }

    public LocalDate getDataSegnalazione() {
        return dataSegnalazione;
    }

    public LocalDate getDataReazione() {
        return dataReazione;
    }
    
    @Override
    public String toString(){
        return "Paziente: \n"+paziente+"Farmaco: \n"+farmaco+"Dose "+dose+" all'ora: "+frequenzaGiornaliera+"\n";
    }
    
    @Override
    public boolean equals(Object o){
        if(o !=null && o instanceof Terapia){
            Terapia t= (Terapia) o;
            return (this.paziente.equals(t.paziente) && this.farmaco.equals(t.farmaco));
        }
        return false;
    }
}
