package it.univr.farmacovigilanza.model;

import java.util.ArrayList;

public class Paziente {
    
    private final int id;
    private String nome;
    private String cognome;
    private int annoNascita;
    private String provinciaRes;
    private String professione;
    private ArrayList<FattoreRischio> fattoriRischio;

    public Paziente(int id, String nome, String cognome, int annoNascita, String provinciaRes, String professione, ArrayList<FattoreRischio> fattoriRischio) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.annoNascita = annoNascita;
        this.provinciaRes = provinciaRes;
        this.professione = professione;
        this.fattoriRischio = fattoriRischio;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
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

    public ArrayList<FattoreRischio> getFattoriRischio() {
        return fattoriRischio;
    }

    public void setFattoriRischio(ArrayList<FattoreRischio> fattoriRischio) {
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
