/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Scanner;

/**
 *
 * @author Badr
 */
public
        class Fournisseur{
    private String id;
    private String nomfournisseur;
    private String adressefournisseur;
    private String contactfournisseur;

    public
    Fournisseur(String nomfournisseur, String adressefournisseur, String contactfournisseur){
        this.nomfournisseur = nomfournisseur;
        this.adressefournisseur = adressefournisseur;
        this.contactfournisseur = contactfournisseur;
    }

    public Fournisseur(String id, String nomfournisseur, String adressefournisseur, String contactfournisseur) {
        this(nomfournisseur, adressefournisseur, contactfournisseur);
        this.id = id;
    }

    public
    Fournisseur(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public
    String getNomfournisseur(){
        return nomfournisseur;
    }

    public
    void setNomfournisseur(String nomFournisseur){
        this.nomfournisseur = nomFournisseur;
    }

    public
    String getAdressefournisseur(){
        return adressefournisseur;
    }

    public
    void setAdressefournisseur(String adresseFournisseur){
        this.adressefournisseur = adresseFournisseur;
    }

    public
    String getContactfournisseur(){
        return contactfournisseur;
    }

    public
    void setContactfournisseur(String contactFournisseur){
        this.contactfournisseur = contactFournisseur;
    }

    public String toString(){
        return "Fournisseurnissseur : " + this.getNomfournisseur() + ", Adresse du Fournisseur : "
             + this.getAdressefournisseur() + ", Contcts du fournisseur : " + this.getContactfournisseur();
    }

    public void afficherFournisseur(){
        System.out.println(this);
    }

    public void saisirFournisseur(){
        Scanner input = new Scanner(System.in);

        System.out.println("Saisissez le nom du fournisseur : ");
        String nomFournisseur = input.nextLine();

        System.out.println("Saisissez l'addresse du fournisseur : ");
        String adresseFournisseur = input.nextLine();

        System.out.println("Saisissez les contact du fournisseur : ");
        String contactFournisseur = input.nextLine();

        if ( contactFournisseur.isEmpty() || adresseFournisseur.isEmpty() || nomFournisseur.isEmpty() ){
            System.out.println("Veuillez remplir tous les champs!!");
            this.saisirFournisseur();
        }
        this.setContactfournisseur(contactFournisseur);
        this.setAdressefournisseur(adresseFournisseur);
        this.setNomfournisseur(nomFournisseur);
    }
}
