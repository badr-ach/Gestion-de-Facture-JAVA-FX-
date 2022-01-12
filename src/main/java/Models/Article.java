package Models;

import java.util.Scanner;


public class Article {
    private String numart;
    private String libelle;


    public Article(String numart, String libelle){
        this(libelle);
        this.numart = numart;
    }

    public Article(String libelle) {
        this.libelle = libelle;
    }

    public Article(){}

    public String getNumart() {
        return numart;
    }

    public
    void setNumart(String numart) {
        this.numart = numart;
    }

    public
    String getLibelle() {
        return libelle;
    }

    public
    void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
