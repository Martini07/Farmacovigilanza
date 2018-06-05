package it.univr.farmacovigilanza.model;

public class Terapia {
    private final Paziente paziente;
    private final Farmaco farmaco;
    private final int dose; //equivale a quantità
    private final float frequenzaGiornaliera; //decidere che tipo di dato da usare (esempio dose oraria 0.5 1 dose ogni due ore...)
    //data inzio e fine
    
    public Terapia(Paziente p, Farmaco f,int d, int q){
        paziente=p;
        farmaco=f;
        dose=d;
        frequenzaGiornaliera=q;
    }
    
    //getter
    public Paziente getPaziente(){ return paziente;}
    public Farmaco getFarmaco(){ return farmaco;}
    public int getDose(){ return dose;}
    public float getFrequenzaGiornaliera(){ return frequenzaGiornaliera;}
    
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
