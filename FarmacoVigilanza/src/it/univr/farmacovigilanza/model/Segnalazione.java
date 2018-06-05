package it.univr.farmacovigilanza.model;

import java.util.ArrayList;

public class Segnalazione {
    private static int count=0;//make this serializable ?
    private final int id;
    private final Paziente paziente;
    private final ReazioneAvversa reazioneAvversa;
    //start and end date
    private final ArrayList<Terapia> terapie;
    
    public Segnalazione(Paziente paziente,ReazioneAvversa reazioneAvversa, ArrayList<Terapia> terapie){
        this.id=++count;
        //control input...
        this.paziente=paziente;
        this.reazioneAvversa=reazioneAvversa;
        this.terapie=terapie;
    }
    
    //getter
    public Paziente getPaziente(){ return paziente;}
    public ReazioneAvversa getReazioneAvversa(){ return reazioneAvversa;}
    public ArrayList<Terapia> getTerapie(){ return terapie;}
    
    @Override
    public String toString(){
        return "Segnalazione: "+id+"\n Paziente: \n"+paziente+"Reazione avversa: "+reazioneAvversa+"Terapie in corso: \n"+terapie;
    }
    
    @Override
    public boolean equals(Object o){
        if(o!= null && o instanceof Segnalazione){
            return this.id==((Segnalazione) o).id;
        }
        return false;
    }
}
