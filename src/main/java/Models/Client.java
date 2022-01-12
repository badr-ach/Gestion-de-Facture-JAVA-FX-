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
        class Client{
    private String id;
    private String nomclient;
    private String adresseclient;
    private String contactclient;

    public
    Client(String nomClient, String adresseClient, String contactClient){
        this.nomclient = nomClient;
        this.adresseclient = adresseClient;
        this.contactclient = contactClient;
    }

    public Client(String id,String nomClient, String adresseClient, String contactClient){
        this(nomClient,adresseClient,contactClient);
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Client() {}

    public
    String getNomclient(){
        return nomclient;
    }

    public
    void setNomClient(String nomClient){
        this.nomclient = nomClient;
    }

    public
    String getAdresseclient(){
        return adresseclient;
    }

    public
    void setAdresseclient(String adresseClient){
        this.adresseclient = adresseClient;
    }

    public
    String getContactclient(){
        return contactclient;
    }

    public
    void setContactClient(String contactClient){
        this.contactclient = contactClient;
    }

}
