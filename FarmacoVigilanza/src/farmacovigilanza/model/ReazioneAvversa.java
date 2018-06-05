package farmacovigilanza.model;
import java.lang.Exception;

public class ReazioneAvversa {
    
    private final String nome;
    private int livelloGravita;
    private String descrizione;
    
    public ReazioneAvversa(String nome, int livelloGravita, String descriozione) throws IllegalArgumentException{
        //add some check on input
        this.nome=nome;
        this.livelloGravita=livelloGravita;
        this.descrizione=descrizione;
        if(livelloGravita<1 || livelloGravita>5) throw new IllegalArgumentException("Livello gravità deve essere compreso tra 1 e 5");
    }
    
    //getter
    public String getNome(){ return this.nome;}
    public int getLivelloGravita(){ return this.livelloGravita;}
    public String getDescrizione(){ return this.descrizione;}
    
    //setter
    public void setLivelloGravita(int l) throws IllegalArgumentException{
        if(l<1 || l>5) throw new IllegalArgumentException("Livello gravità deve essere compreso tra 1 e 5");
        livelloGravita=l;
    }
    
    public void setDescrizione(String s){ descrizione=s; }
    
    @Override
    public String toString(){
        return nome+" "+livelloGravita+"\n"+descrizione+"\n";
    }
    
    @Override
    public boolean equals(Object o){
        if(o != null && o instanceof ReazioneAvversa){
            return this.nome.equals(((ReazioneAvversa) o).nome);
        }
        return false;
    }
}
