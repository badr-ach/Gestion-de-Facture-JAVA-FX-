/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Article;
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
        class ArticleDAO implements IDAO<Article>{
    private Connection con;

    public ArticleDAO(){
        con = MysqlConnection.getConnection();
    }

    public Article get(String libelle){
        String sql = "Select * from articles where libelle like '"+libelle+"%' ";
        Article article;
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                return new Article(rs.getString(1),rs.getString(2));
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
        String sql = "Select * from articles where libelle like \""+id+ "%\"";
        Article article;
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
    public ResultSet getAll(){
        String sql = "Select * from articles";
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
    public boolean add(Article article){
        String sql = "INSERT INTO articles(libelle) values (?)";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, article.getLibelle());
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Article article){
        String sql = "UPDATE articles set libelle = ? where numArt = ?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, article.getLibelle());
            st.setString(2,article.getNumart());
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Article article){
        String sql = "DELETE from articles where numArt = ?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, article.getNumart());
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
