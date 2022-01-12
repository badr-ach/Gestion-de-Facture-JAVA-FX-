package Models;

import java.util.Scanner;

/**
 *
 * @author Badr
 */
public class LigneFacture{
    private String id;
    private String idFacture;
    private String idArticle;
    private double prix;
    private int qte;

    public LigneFacture(String id,String idFacture,String idarticle, double prix, int qte){
        this.idArticle = idarticle;
        this.prix = prix;
        this.qte = qte;
        this.id = id;
        this.idFacture = idFacture;
    }

    public LigneFacture(String idFacture,String idarticle, double prix, int qte){
        this.idFacture = idFacture;
        this.idArticle = idarticle;
        this.prix = prix;
        this.qte = qte;
    }

    public LigneFacture(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdfacture() {
        return idFacture;
    }

    public void setIdfacture(String idFacture) {
        this.idFacture = idFacture;
    }

    public String getIdarticle() {
        return idArticle;
    }

    public void setIdarticle(String idArticle) {
        this.idArticle = idArticle;
    }

    public double getPrix(){
        return prix;
    }

    public void setPrix(double prix){
        this.prix = prix;
    }

    public int getQte(){
        return qte;
    }

    public void setQte(int qte){
        this.qte = qte;
    }

}
