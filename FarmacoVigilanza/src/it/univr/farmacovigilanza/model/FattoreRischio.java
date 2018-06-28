package it.univr.farmacovigilanza.model;

public class FattoreRischio {
    
    private final int id;
    private final String nome;
    private final String descrizione;
    private final int livelloRischio;

    public FattoreRischio(int id, String nome, String descrizione, int livelloRischio) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.livelloRischio = livelloRischio;
    }

    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getLivelloRischio() {
        return livelloRischio;
    }
    
    @Override
    public String toString(){
        return nome;
    }
    
    @Override
    public boolean equals(Object o){
        if(o != null && o instanceof FattoreRischio){
            return this.nome.equals(((FattoreRischio) o).nome);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
