package it.univr.farmacovigilanza.model;

import java.util.List;

public class Paziente {
    
    private final int id;
    private int annoNascita;
    private String provinciaRes;
    private String professione;
    private List<FattoreRischio> fattoriRischio;

    public Paziente(int id, int annoNascita, String provinciaRes, String professione, List<FattoreRischio> fattoriRischio) {
        this.id = id;
        this.annoNascita = annoNascita;
        this.provinciaRes = provinciaRes;
        this.professione = professione;
        this.fattoriRischio = fattoriRischio;
    }

    public int getId() {
        return id;
    }

    public int getAnnoNascita() {
        return annoNascita;
    }

    public void setAnnoNascita(int annoNascita) {
        this.annoNascita = annoNascita;
    }

    public String getProvinciaRes() {
        return provinciaRes;
    }

    public void setProvinciaRes(String provinciaRes) {
        this.provinciaRes = provinciaRes;
    }

    public String getProfessione() {
        return professione;
    }

    public void setProfessione(String professione) {
        this.professione = professione;
    }

    public List<FattoreRischio> getFattoriRischio() {
        return fattoriRischio;
    }

    public void setFattoriRischio(List<FattoreRischio> fattoriRischio) {
        this.fattoriRischio = fattoriRischio;
    }
    
    @Override
    public String toString(){
        return "["+id+"]:\n \t nato/a il "+annoNascita
                + "\n \t residente in "+provinciaRes
                + "\n \t professione: "+professione
                + "\n \t fattori di rischio: "+fattoriRischio+"\n";
    }
    
    @Override
    public boolean equals(Object o){
        if(o != null && o instanceof Paziente){
            return this.id==((Paziente) o).id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
