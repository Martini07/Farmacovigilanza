package it.univr.farmacovigilanza.model;

import java.util.Arrays;
import java.util.List;

public class Paziente {
    
    private final int id;
    private int annoNascita;
    private String provinciaRes;
    private String professione;
    private List<FattoreRischio> fattoriRischio;
    public static final List<String> sigleProvince = Arrays.asList("AG","AL","AN","AO","AP","AQ","AR","AT","AV","BA","BG","BI","BL","BN","BO","BR","BS","BT","BZ"
,"CA","CB","CE","CH","CI","CL","CN","CO","CR","CS","CT","CV","CZ","EE","EN","FC","FE","FG","FI","FM","FO","FR","GE","GO","GR","IM","IS","KR","LC","LE","LI",
"LO","LT","LU","MB","MC","ME","MI","MN","MO","MS","MT","NA","NO","NU","OG","OR","OT","PA","PC","PD","PE","PG","PI","PN","PO","PR","PS","PT","PU","PV","PZ",
"RA","RC","RE","RG","RI","RM","RN","RO","RS","SA","SI","SO","SP","SR","SS","SU","SV","TA","TE","TN","TO","TP","TR","TS","TV","UD","VA","VB","VC","VE","VI",
"VR","VS","VT","VV");

    public Paziente(int id, int annoNascita, String provinciaRes, String professione, List<FattoreRischio> fattoriRischio) {
        this.id = id;
        this.annoNascita = annoNascita;
        this.provinciaRes = provinciaRes;
        this.professione = professione;
        this.fattoriRischio = fattoriRischio;
    }

    public Paziente(int annoNascita, String provinciaRes, String professione, List<FattoreRischio> fattoriRischio) {
        this.id=-1;
        this.annoNascita = annoNascita;
        this.provinciaRes = provinciaRes;
        this.professione = professione;
        this.fattoriRischio = fattoriRischio;
    }
    
    public int getId() {
        return id;
    }

    public int getAnnoNascita() {
        return annoNascita;
    }

    public void setAnnoNascita(int annoNascita) {
        this.annoNascita = annoNascita;
    }

    public String getProvinciaRes() {
        return provinciaRes;
    }

    public void setProvinciaRes(String provinciaRes) {
        this.provinciaRes = provinciaRes;
    }

    public String getProfessione() {
        return professione;
    }

    public void setProfessione(String professione) {
        this.professione = professione;
    }

    public List<FattoreRischio> getFattoriRischio() {
        return fattoriRischio;
    }

    public void setFattoriRischio(List<FattoreRischio> fattoriRischio) {
        this.fattoriRischio = fattoriRischio;
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

    @Override
    public int hashCode() {
        return id;
    }
}
