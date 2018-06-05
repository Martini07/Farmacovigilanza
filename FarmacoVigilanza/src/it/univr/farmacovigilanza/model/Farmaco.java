
package it.univr.farmacovigilanza.model;

import java.util.ArrayList;


public abstract class Farmaco implements Iterable{
    private final String nome;
    private final String casaProduttrice;
    private ArrayList<String> principiAttivi;
    
    public Farmaco(String nome, String casaProduttrice, ArrayList<String> principiAttivi){
        this.nome=nome;
        this.casaProduttrice=casaProduttrice;
        this.principiAttivi=principiAttivi;
    }
    
    //getter
    public String getNome(){ return nome;}
    public String getCasaProduttrice(){ return casaProduttrice;}
    public ArrayList<String> getPrincipiAttivi(){ return principiAttivi;}
    
    //setter
    public void setPrincipiAttivi(ArrayList<String> p){ principiAttivi=p;}
    
    //aggiunta principi attivi
    public boolean addPrincipioAttivo(String s){
        return principiAttivi.add(s);
    }
    
    public boolean addPrincipiAttivi(ArrayList<String> p){
        return principiAttivi.addAll(p);
    }
    
    //rimozione principi attivi
    public boolean remPrincipioAttivo(String s){
        return principiAttivi.remove(s);
    }
    public boolean remPrinicipiAttivi(ArrayList<String> p){
        return principiAttivi.removeAll(p);
    }
    
    @Override
    public String toString(){
        return nome+" "+casaProduttrice+"\n"+principiAttivi+"\n";
    }
    
    @Override
    public boolean equals(Object o){
        if(o != null && o instanceof Farmaco){
            Farmaco f = (Farmaco) o;
            return (this.nome.equals(f.nome) && this.casaProduttrice.equals(f.casaProduttrice) && this.principiAttivi.equals(f.principiAttivi));
        }
        return false;
    }

}
