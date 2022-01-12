/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Utils.Facture;

import java.time.LocalDate;

/**
 *
 * @author Badr
 */
public
        class FactureVente extends Facture{
    private Fournisseur fournisseur;

    public FactureVente(String numFact, Fournisseur fournisseur,LocalDate date){
        super(numFact, date,fournisseur.getId(),"vente");
        this.fournisseur = fournisseur;
    }

    public FactureVente(Fournisseur fournisseur,LocalDate date){
        super(date);
        this.fournisseur = fournisseur;
    }


    public
    Fournisseur getFournisseur(){
        return fournisseur;
    }

    public
    void setFournisseur(Fournisseur fournisseur){
        this.fournisseur = fournisseur;
    }

}
