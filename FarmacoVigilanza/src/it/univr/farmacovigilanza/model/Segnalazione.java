package it.univr.farmacovigilanza.model;

import java.time.LocalDate;

public class Segnalazione {
    
    private final int id;
    private final ReazioneAvversa reazioneAvversa;
    private final LocalDate dataSegnalazione;
    private final LocalDate dataReazione;

    public Segnalazione(int id, ReazioneAvversa reazioneAvversa, LocalDate dataSegnalazione, LocalDate dataReazione) {
        this.id = id;
        this.reazioneAvversa = reazioneAvversa;
        this.dataSegnalazione = dataSegnalazione;
        this.dataReazione = dataReazione;
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
