/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Client;
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
        class ClientDAO implements IDAO<Client>{
    private Connection con;

    public ClientDAO(){
        con = MysqlConnection.getConnection();
    }

    public Client getById(String id){
        String sql = "Select * from clients where id = ?";
        Client client;
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                client = new Client(rs.getString(1),rs.getString("nomClient"),rs.getString("adresseClient"),rs.getString("contactClient"));
                return client;
            }
            return null;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }


    public Client get(String nom){
        String sql = "Select * from clients where nomClient = ?";
        Client client;
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, nom);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                client = new Client(rs.getString(1),rs.getString("nomClient"),rs.getString("adresseClient"),rs.getString("contactClient"));
                return client;
            }
            return null;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet find(String id){
        String sql = "Select * from clients where nomClient = ?";
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
        String sql = "Select * from clients";
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
    public boolean add(Client client){
        String sql = "INSERT INTO clients(nomClient,adresseClient,contactClient) values (?,?,?)";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, client.getNomclient());
            st.setString(2,client.getAdresseclient());
            st.setString(3,client.getContactclient());
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Client client){
        String sql = "UPDATE clients set nomClient = ? , adresseClient = ? , contactClient = ? where id = ?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, client.getNomclient());
            st.setString(2,client.getAdresseclient());
            st.setString(3,client.getContactclient());
            st.setString(4,client.getId());
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Client client){
        String sql = "DELETE from clients where id = ?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, client.getId());
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
