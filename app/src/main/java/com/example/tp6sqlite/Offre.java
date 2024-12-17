package com.example.tp6sqlite;

public class Offre {
    private int id ;
    private String poste ;
    private String description ;

    public Offre(int id, String poste, String description) {
        this.id = id;
        this.poste = poste;
        this.description = description;
    }

    public Offre(String poste, String description) {
        this.poste = poste;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getPoste() {
        return poste;
    }

    public String getDescription() {
        return description;
    }
}
