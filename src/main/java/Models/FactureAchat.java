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
        class FactureAchat extends Facture{
    private Client client;
    
    public FactureAchat(String numFact, Client client, LocalDate date){
        super(numFact, date, client.getId(),"achat");
        this.client = client;
    }

    public FactureAchat(Client client, LocalDate date){
        super(date);
        this.client = client;
    }


    public
    Client getClient(){
        return client;
    }

    public
    void setClient(Client client){
        this.client = client;
    }

}
