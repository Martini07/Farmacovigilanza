package it.univr.farmacovigilanza.model;


public class FattoreRischio {
    private final String nome;
    private String descrizione;
    private int livelloRischio;
    
    public FattoreRischio(String nome, String descrizione, int livelloRischio){
        //control input
        this.nome=nome;
        this.descrizione=descrizione;
        this.livelloRischio=livelloRischio;
    }
    
    //getter
    public String getNome(){ return nome;}
    public String getDescrizione(){ return descrizione;}
    public int getLivelloRischio(){ return livelloRischio;}
    
    //setter
    public void setDescrizione(String s){ descrizione=s;}
    public void setLivelloRischio(int n){ livelloRischio=n;}
    
    @Override
    public String toString(){
        return nome+" "+livelloRischio+"\n"+descrizione+"\n";
    }
    
    @Override
    public boolean equals(Object o){
        if(o != null && o instanceof FattoreRischio){
            return this.nome.equals(((FattoreRischio) o).nome);
        }
        return false;
    }
}
