package Utils;

import DAO.ClientDAO;
import DAO.FournisseurDAO;
import Models.*;
import javafx.util.converter.LocalDateStringConverter;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ResultsetConvert {
    private ArrayList<String> columns = new ArrayList<>();
    private ResultSet rs;

    public ResultsetConvert(ResultSet rs){
        this.rs = rs;
    }

    public ArrayList<String> getColumns(){
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            for(int i=0 ; i< rsmd.getColumnCount() ;i++){
                columns.add(rsmd.getColumnLabel(i+1));
            System.out.println(columns.get(i));}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }

    public ArrayList<User> toUser() {
        ArrayList<User> users = new ArrayList<>();
        try{
        while(rs.next()){
            User user = new User(rs.getString(1),rs.getString(2), rs.getString(3));
            users.add(user);
        }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    public ArrayList<Article> toArticle() {
        ArrayList<Article> articles = new ArrayList<>();
        try{
            while(rs.next()){
                Article article = new Article(rs.getString(1),rs.getString(2));
                articles.add(article);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return articles;
    }

    public ArrayList<Client> toClient() {
        ArrayList<Client> clients = new ArrayList<>();
        try{
            while(rs.next()){
                Client client = new Client(rs.getString(1),rs.getString(2),
                        rs.getString(3),rs.getString(4));
                clients.add(client);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return clients;
    }

    public ArrayList<Fournisseur> toFournisseur(){
        ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
        try{
            while(rs.next()){
                Fournisseur fournisseur = new Fournisseur(rs.getString(1),rs.getString(2),
                        rs.getString(3),rs.getString(4));
                fournisseurs.add(fournisseur);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return fournisseurs;
    }

    public ArrayList<LigneFacture> toLigneFacture(){
        ArrayList<LigneFacture> lignes = new ArrayList<>();
        try{
            while(rs.next()){
                LigneFacture ligne = new LigneFacture(rs.getString(1),rs.getString(2),
                        rs.getString(3),rs.getDouble(4),rs.getInt(5));
                lignes.add(ligne);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lignes;
    }

    public ArrayList<Facture> toFacture(){
        ArrayList<Facture> factures = new ArrayList<>();
        try{
            while(rs.next()){
                if(rs.getString(3).equals("vente")){

                    Date date = new SimpleDateFormat("YYYY-MM-DD").parse(rs.getString(4));
                    LocalDate localDate = LocalDate.of(date.getYear(),date.getMonth()+1,date.getDate());
                    System.out.println(rs.getString(2));
                    Fournisseur fournisseur = new FournisseurDAO().getById(rs.getString(2));
                    System.out.println(fournisseur);
                    FactureVente facture = new FactureVente(rs.getString(1),fournisseur,
                            localDate);
                    factures.add(facture);

                }else if(rs.getString(3).equals("achat")){
                    Date date = new SimpleDateFormat("YYYY-MM-DD").parse(rs.getString(4));
                    LocalDate localDate = LocalDate.of(date.getYear(),date.getMonth()+1,date.getDate());
                    Client client = new ClientDAO().getById(rs.getString(2));
                    FactureAchat facture = new FactureAchat(rs.getString(1),client,
                            localDate);
                    factures.add(facture);
                }

            }
        }catch (SQLException | ParseException e){
            e.printStackTrace();
        }
        return factures;
    }
}
