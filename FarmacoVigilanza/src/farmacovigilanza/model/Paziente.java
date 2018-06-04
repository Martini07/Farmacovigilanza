package farmacovigilanza.model;

import java.util.ArrayList;

public class Paziente {
    
    private final long id; //to evaluate what to use as id
    private final int annoNascita;
    private String provinciaRes;
    private String professione;
    private ArrayList<FattoreRischio> fattoriRischio=null;
    
    public Paziente(long id, int annoNascita, String provinciaRes, String professione, ArrayList<FattoreRischio> fattoriRishio){
        //add some necessary check on input...
        this.id=id;
        this.annoNascita=annoNascita;
        this.provinciaRes=provinciaRes;
        this.professione=professione;
        this.fattoriRischio=fattoriRischio;
    }
    
    //getter
    public long getId(){ return this.id;}
    public int getAnnoNascita(){ return this.annoNascita;}
    public String getProvinciaRes(){ return this.provinciaRes;}
    public String getProfessione(){ return this.professione;}
    public ArrayList<FattoreRischio> fattoriRischio(){ return this.fattoriRischio;}
    
    //setter
    public void setProvinciaRes(String provincia){
        this.provinciaRes=provincia;
    }
    public void setProfessione(String professione){
        this.professione=professione;
    }
    public void addFattoreRischio(FattoreRischio f){
        this.fattoriRischio.add(f);
    }
    public void addFattoriRischio(ArrayList<FattoreRischio> f){
        this.fattoriRischio.addAll(f);
    }
    public void removeFattoreRischio(FattoreRischio f){
        this.fattoriRischio.remove(f);
    }
    public void removeAllFattoriRischio(ArrayList<FattoreRischio> f){
        this.fattoriRischio.removeAll(f);
    }
}
