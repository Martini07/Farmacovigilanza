package farmacovigilanza.model;

import java.util.ArrayList;

public class Paziente {
    
    private final int id; //to evaluate what to use as id
    private final int annoNascita;
    private String provinciaRes;
    private String professione;
    private ArrayList<FattoreRischio> fattoriRischio=null;
    
    public Paziente(int id, int annoNascita, String provinciaRes, String professione, ArrayList<FattoreRischio> fattoriRishio){
        //add some necessary check on input...
        this.id=id;
        this.annoNascita=annoNascita;
        this.provinciaRes=provinciaRes;
        this.professione=professione;
        this.fattoriRischio=fattoriRischio;
    }
    
    //getter
    public int getId(){ return this.id;}
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
    //aggiunta fattori rischio
    public boolean addFattoreRischio(FattoreRischio f){
        return this.fattoriRischio.add(f);
    }
    public boolean addFattoriRischio(ArrayList<FattoreRischio> f){
        return this.fattoriRischio.addAll(f);
    }
    //eliminazione fattori rischio
    public boolean removeFattoreRischio(FattoreRischio f){
        return this.fattoriRischio.remove(f);
    }
    public boolean removeAllFattoriRischio(ArrayList<FattoreRischio> f){
        return this.fattoriRischio.removeAll(f);
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
}
