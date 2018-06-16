package it.univr.farmacovigilanza.model;

public class ReazioneAvversa {
    
    private final int id;
    private final String nome;
    private final String descrizione;
    private final int livelloGravita;

    public ReazioneAvversa(int id, String nome, String descrizione, int livelloGravita) {
        if (livelloGravita < 1 || livelloGravita > 5) 
            throw new IllegalArgumentException("Livello gravità deve essere compreso tra 1 e 5");
        this.id = id;
        this.nome = nome;
        this.livelloGravita = livelloGravita;
        this.descrizione = descrizione;
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

    public int getLivelloGravita() {
        return livelloGravita;
    }
    
    @Override
    public String toString(){
        return nome+" "+livelloGravita+"\n"+descrizione+"\n";
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof ReazioneAvversa){
            return this.nome.equals(((ReazioneAvversa) o).nome);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
