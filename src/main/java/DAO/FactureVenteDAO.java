/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.FactureVente;
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
        class FactureVenteDAO implements IDAO<FactureVente> {
    private Connection con;
    private String lastInsertId;

    public String getLastInsertId() {
        return lastInsertId;
    }

    public FactureVenteDAO(){
        con = MysqlConnection.getConnection();
    }

    public ResultSet get(int id_fournisseur){
        String sql = "Select * from factures where type = 'vente' and recevant = ?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id_fournisseur);
            ResultSet rs = st.executeQuery(sql);
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getAll(){
        String sql = "Select * from factures where type = 'vente' ";
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
    public ResultSet find(String id){
        String sql = "Select * from factures where type = 'vente' and  numFact = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean add(FactureVente factureVente){
        String sql = "INSERT INTO factures (recevant,date,type) values (?,?,'vente')";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, factureVente.getFournisseur().getId());
            st.setString(2,factureVente.getDate().toString());
            int ret = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()){
                lastInsertId = rs.getString(1);
                return true;
            }
            return false;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(FactureVente factureVente){
        String sql = "UPDATE factures set recevant = ? , date = ? where numFact = ? and type = 'vente'";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, factureVente.getFournisseur().getId());
            st.setString(2,factureVente.getDate().toString());
            st.setString(3,factureVente.getNumfact());
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(FactureVente factureVente){
        String sql = "DELETE from factures where numFact = ?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, factureVente.getNumfact());
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
