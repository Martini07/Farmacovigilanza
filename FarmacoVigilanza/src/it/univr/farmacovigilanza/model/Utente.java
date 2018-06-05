/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univr.farmacovigilanza.model;

/**
 *
 * @author Francesco
 */
public class Utente {
    
    private final int id;
    private final String username;

    public Utente(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }
       
    
}
