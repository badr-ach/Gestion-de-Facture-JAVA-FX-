/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.FactureAchat;
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
        class FactureAchatDAO implements IDAO<FactureAchat> {
    private Connection con;
    private String lastInsertId;

    public String getLastInsertId() {
        return lastInsertId;
    }

    public FactureAchatDAO(){
        con = MysqlConnection.getConnection();
    }

    public ResultSet get(int id_client){
        String sql = "Select * from factures where recevant = ? and type = 'achat' ";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id_client);
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
        String sql = "Select * from factures where numFact = ? and type = 'achat' ";
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
    public ResultSet getAll(){
        String sql = "Select * from factures and type = 'achat'";
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
    public boolean add(FactureAchat factureAchat){
        String sql = "INSERT INTO factures(recevant,date,type) values (?,?,'achat')";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, factureAchat.getClient().getId());
            st.setString(2,factureAchat.getDate().toString());
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
    public boolean update(FactureAchat factureAchat){
        String sql = "UPDATE factures set recevant = ? , date = ? where numFact = ? and type = 'achat' ";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, factureAchat.getClient().getId());
            st.setString(2,factureAchat.getDate().toString());
            st.setString(3,factureAchat.getNumfact());
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(FactureAchat factureAchat){
        String sql = "DELETE from factures where numFact = ? and type = 'achat' ";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, factureAchat.getNumfact());
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
