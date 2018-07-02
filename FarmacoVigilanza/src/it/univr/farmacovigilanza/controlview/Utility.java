package it.univr.farmacovigilanza.controlview;

import it.univr.farmacovigilanza.model.Farmaco;
import it.univr.farmacovigilanza.model.Farmacologo;
import it.univr.farmacovigilanza.model.Segnalazione;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utility {
    
    public static String toHash(String text) {
        String hash = null;
        try {
            byte[] textData = text.getBytes("UTF-8");
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = messageDigest.digest(textData);
            hash = new BigInteger(digest).toString(16);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(FarmacovigilanzaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
    }
    
    public static int getNumeroSegnalazioniSettimana(Farmacologo farmacologo){
        int numeroSegnalazioni = 0;
        LocalDate lunedi = LocalDate.now().with(DayOfWeek.MONDAY);
        List<Segnalazione> segnalazioni = farmacologo.getSegnalazioni();
        for (Segnalazione segnalazione : segnalazioni) {
            if (segnalazione.getDataSegnalazione().isAfter(lunedi) || segnalazione.getDataSegnalazione().isEqual(lunedi))
                numeroSegnalazioni++;
        }
        return numeroSegnalazioni;
    }
    
    public static List<Farmaco> getFarmaciDaSegnalare(Farmacologo farmacologo){
        List<Farmaco> farmaciDaSegnalare = new ArrayList();
        Map<Farmaco, Integer> mapFarmaciNumeroSegnalazioni = new HashMap();
        List<Segnalazione> segnalazioni = farmacologo.getSegnalazioni();
        LocalDate primoGiornoAnno = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        for (Segnalazione segnalazione : segnalazioni) {
            if (segnalazione.getReazioneAvversa().getLivelloGravita() > 3 &&
                    (segnalazione.getDataSegnalazione().isAfter(primoGiornoAnno) || segnalazione.getDataSegnalazione().isEqual(primoGiornoAnno))){
                Farmaco farmaco = segnalazione.getFarmaco();
                if (mapFarmaciNumeroSegnalazioni.containsKey(farmaco)){
                    int valoreCorrente = mapFarmaciNumeroSegnalazioni.get(farmaco);
                    mapFarmaciNumeroSegnalazioni.put(farmaco, ++valoreCorrente);
                } else {
                    mapFarmaciNumeroSegnalazioni.put(farmaco, 1);
                }
            }
        }
        for (Map.Entry<Farmaco, Integer> entrySet : mapFarmaciNumeroSegnalazioni.entrySet()) {
            if (entrySet.getValue() > 10){
                farmaciDaSegnalare.add(entrySet.getKey());
            }
        }
        return farmaciDaSegnalare;
    }
}
