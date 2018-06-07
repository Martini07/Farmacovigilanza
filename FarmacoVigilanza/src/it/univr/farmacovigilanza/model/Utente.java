package it.univr.farmacovigilanza.model;

public abstract class Utente {
    
    private final int idUtente;
    private final String username;
    private final String nome;
    private final String cognome;
    private final String email;
    
    public static enum TipoUtente {
        MEDICO,
        FARMACOLOGO
    }

    public Utente(int idUtente, String username, String nome, String cognome, String email) {
        this.idUtente = idUtente;
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public String getUsername() {
        return username;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

}
