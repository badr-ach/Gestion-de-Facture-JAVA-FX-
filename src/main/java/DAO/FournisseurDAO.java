/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Fournisseur;
import Utils.MysqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Badr
 */
public
        class FournisseurDAO implements IDAO<Fournisseur>{
    private Connection con;

    public FournisseurDAO(){
        con = MysqlConnection.getConnection();
    }

    public Fournisseur getById(String id){
        String sql = "Select * from fournisseurs where id = ?";
        Fournisseur fournisseur;
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                fournisseur = new Fournisseur(rs.getString(1),rs.getString("nomFournisseur"),rs.getString("adresseFournisseur"),rs.getString("contactFournisseur"));
                return fournisseur;
            }
            return null;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public Fournisseur get(String nom){
        String sql = "Select * from fournisseurs where nomFournisseur = ?";
        Fournisseur fournisseur;
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, nom);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                fournisseur = new Fournisseur(rs.getString(1),rs.getString("nomFournisseur"),rs.getString("adresseFournisseur"),rs.getString("contactFournisseur"));
                return fournisseur;
            }
            return null;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public ResultSet find(String id) {
        String sql = "Select * from fournisseurs where nomFournisseur = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public ResultSet getAll(){
        String sql = "Select * from fournisseurs";
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean add(Fournisseur fournisseur){
        String sql = "INSERT INTO fournisseurs(nomFournisseur,adresseFournisseur,contactFournisseur) values (?,?,?)";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, fournisseur.getNomfournisseur());
            st.setString(2,fournisseur.getAdressefournisseur());
            st.setString(3,fournisseur.getContactfournisseur());
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean update(Fournisseur fournisseur){
        String sql = "UPDATE fournisseurs set nomFournisseur = ? , adresseFournisseur = ? , contactFournisseur = ? where id = ?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, fournisseur.getNomfournisseur());
            st.setString(2,fournisseur.getAdressefournisseur());
            st.setString(3,fournisseur.getContactfournisseur());
            st.setString(4,fournisseur.getId());
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean delete(Fournisseur fournisseur){
        String sql = "DELETE from fournisseurs where id = ?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, fournisseur.getId());
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
