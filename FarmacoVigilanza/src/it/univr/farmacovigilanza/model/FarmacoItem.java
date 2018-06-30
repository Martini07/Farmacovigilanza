package it.univr.farmacovigilanza.model;

import java.time.LocalDate;

public class FarmacoItem extends Farmaco {

    private final String barcode;
    private final LocalDate dataScadenza;
    private final LocalDate dataProduzione;
    private final Stato stato;
    
    public static enum Stato {
        RITIRA,
        CONTROLLA,
        MONITORA
    }
    
    public FarmacoItem(int id, String nome, String descrizione, String dittaProduttrice, String codiceMinisteriale, String principioAttivo,
            int quantita, String unitaMisura, String barcode, LocalDate dataScadenza, LocalDate dataProduzione, Stato stato) {
        super(id, nome, descrizione, dittaProduttrice, codiceMinisteriale, principioAttivo, quantita, unitaMisura);
        this.barcode = barcode;
        this.dataScadenza = dataScadenza;
        this.dataProduzione = dataProduzione;
        this.stato = stato;
    }

    public String getBarcode() {
        return barcode;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public LocalDate getDataProduzione() {
        return dataProduzione;
    }

    public Stato getStato() {
        return stato;
    }
    
    @Override
    public int hashCode() {
        return getId() + barcode.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof FarmacoItem){
            FarmacoItem farmacoItem = (FarmacoItem) other;
            if (farmacoItem.barcode.equals(this.barcode)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return getNome() + " " + getQuantita() + getUnitaMisura();
    }
    
}
