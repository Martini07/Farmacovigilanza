package it.univr.farmacovigilanza.model;

import java.util.Iterator;

public class Farmaco implements Iterable{
   
    private final int id;
    private final String nome;
    private final String descrizione;
    private final String dittaProduttrice;
    private final String codiceMinisteriale;
    private final String principioAttivo;
    private final int quantita;
    private final String unitaMisura;
    private final Stato stato;
    
    public static enum Stato {
        RITIRA,
        CONTROLLA,
        MONITORA
    }

    public Farmaco(int id, String nome, String descrizione, String dittaProduttrice, String codiceMinisteriale, String principioAttivo, int quantita, String unitaMisura, Stato stato) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.dittaProduttrice = dittaProduttrice;
        this.codiceMinisteriale = codiceMinisteriale;
        this.principioAttivo = principioAttivo;
        this.quantita = quantita;
        this.unitaMisura = unitaMisura;
        this.stato = stato;
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

    public String getDittaProduttrice() {
        return dittaProduttrice;
    }

    public String getCodiceMinisteriale() {
        return codiceMinisteriale;
    }

    public String getPrincipioAttivo() {
        return principioAttivo;
    }

    public int getQuantita() {
        return quantita;
    }

    public String getUnitaMisura() {
        return unitaMisura;
    }

    public Stato getStato() {
        return stato;
    }
    
    @Override
    public String toString() {
        return nome + " " + quantita + unitaMisura + ", " + dittaProduttrice + "\nCod. Ministeriale: " + codiceMinisteriale;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Farmaco){
            Farmaco f = (Farmaco) other;
            return (this.codiceMinisteriale.equals(f.codiceMinisteriale));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public Iterator iterator() {
        //TODO
        return null;
    }

}
