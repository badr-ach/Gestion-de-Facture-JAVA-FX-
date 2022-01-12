/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.LigneFacture;
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
        class LigneFactureDAO implements IDAO<LigneFacture>{
    private Connection con;

    public LigneFactureDAO(){
        con = MysqlConnection.getConnection();
    }

    public ResultSet getAllByTwoIds(String idfacture, String idArticle){
        String sql = "Select * from lignesFactures where idFacture = ? and idArticle = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,idfacture);
            ps.setString(2,idArticle);
            ResultSet rs = ps.executeQuery();
            return rs;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public ResultSet getAllByFacture(String id){
        String sql = "Select * from lignesFactures where idFacture = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            return rs;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet find(String id) {
        String sql = "Select * from lignesFactures where id = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            return rs;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getAll(){
        String sql = "Select * from lignesFactures";
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
    public boolean add(LigneFacture ligneFacture){
        String sql = "INSERT INTO lignesFactures(idArticle,idFacture,prix,qte) values (?,?,?,?)";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(ligneFacture.getIdarticle()));
            st.setInt(2,Integer.parseInt(ligneFacture.getIdfacture()));
            st.setDouble(3,ligneFacture.getPrix());
            st.setInt(4,ligneFacture.getQte());
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(LigneFacture ligneFacture){
        String sql = "UPDATE lignesFactures set idArticle = ? , prix = ? , qte = ? where id = ? ";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(ligneFacture.getIdarticle()));
            st.setDouble(2,ligneFacture.getPrix());
            st.setInt(3,ligneFacture.getQte());
            st.setInt(4,Integer.parseInt(ligneFacture.getId()));
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(LigneFacture ligneFacture){
        String sql = "DELETE from lignesFactures where id = ?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(ligneFacture.getId()));
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
