package it.univr.farmacovigilanza.model;

import java.time.LocalDate;

public class Segnalazione {
    
    private final int id;
    private final ReazioneAvversa reazioneAvversa;
    private final LocalDate dataSegnalazione;
    private final LocalDate dataReazione;
    private final FarmacoItem farmaco;

    public Segnalazione(int id, ReazioneAvversa reazioneAvversa, LocalDate dataSegnalazione, LocalDate dataReazione, FarmacoItem farmaco) {
        this.id = id;
        this.reazioneAvversa = reazioneAvversa;
        this.dataSegnalazione = dataSegnalazione;
        this.dataReazione = dataReazione;
        this.farmaco = farmaco;
    }

    public int getId() {
        return id;
    }

    public ReazioneAvversa getReazioneAvversa() {
        return reazioneAvversa;
    }

    public LocalDate getDataSegnalazione() {
        return dataSegnalazione;
    }

    public LocalDate getDataReazione() {
        return dataReazione;
    }

    public FarmacoItem getFarmaco() {
        return farmaco;
    }

    @Override
    public String toString() {
        return "Segnalazione{" + "id=" + id + ", reazioneAvversa=" + reazioneAvversa + ", dataSegnalazione=" + dataSegnalazione + ", dataReazione=" + dataReazione + '}';
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Segnalazione){
            return this.id == ((Segnalazione) o).id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
