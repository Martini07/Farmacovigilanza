package it.univr.farmacovigilanza.model;

import java.time.LocalDate;
import java.util.List;

public class Segnalazione {
    
    private final int id;
    private final ReazioneAvversa reazioneAvversa;
    private final LocalDate dataSegnalazione;
    private final LocalDate dataReazione;
    private final Farmaco farmaco;
    private final List<Terapia> terapie;
    
    public Segnalazione(int id, ReazioneAvversa reazioneAvversa, LocalDate dataSegnalazione, LocalDate dataReazione, Farmaco farmaco, List<Terapia> terapie) {
        this.id = id;
        this.reazioneAvversa = reazioneAvversa;
        this.dataSegnalazione = dataSegnalazione;
        this.dataReazione = dataReazione;
        this.farmaco = farmaco;
        this.terapie = terapie;
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

    public Farmaco getFarmaco() {
        return farmaco;
    }

    public List<Terapia> getTerapie(){
        return terapie;
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
